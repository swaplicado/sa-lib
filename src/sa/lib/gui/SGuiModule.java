/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridPaneView;
import sa.lib.grid.SGridTabComponent;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGuiModule implements SGuiController {

    private static final int ACTION_SAVE = 1;
    private static final int ACTION_DISABLE = 2;
    private static final int ACTION_DELETE = 3;

    protected SGuiClient miClient;
    protected int mnModuleType;
    protected int mnModuleSubtype;
    protected JMenu[] maMenus;
    protected SDbRegistry moLastRegistry;
    protected int mnLastFormResult;
    protected HashMap<SGuiUserForm, SGuiForm> moUserFormsMap;
    protected ImageIcon moModuleIcon;

    public SGuiModule(SGuiClient client, int type, int subtype) {
        miClient = client;
        mnModuleType = type;
        mnModuleSubtype = subtype;
        maMenus = null;
        moLastRegistry = null;
        mnLastFormResult = 0;
        moUserFormsMap = null;
        moModuleIcon = null;
    }

    /*
     * Private methods
     */

    private void startTransaction() throws SQLException, Exception {
        miClient.getSession().getStatement().execute("START TRANSACTION");
    }

    private void commitTransaction() throws SQLException, Exception {
        miClient.getSession().getStatement().execute("COMMIT");
    }

    private void rollbackTransaction() throws SQLException, Exception {
        miClient.getSession().getStatement().execute("ROLLBACK");
    }

    private int computeAction(final SDbRegistry registry, final int action) {
        int result = SLibConsts.UNDEFINED;

        try {
            SGuiUtils.setCursorWait(miClient);
            startTransaction();

            switch (action) {
                case ACTION_SAVE:
                    if (registry.canSave(miClient.getSession())) {
                        registry.save(miClient.getSession());
                    }
                    else {
                        throw new Exception(SDbConsts.MSG_REG_DENIED_UPDATE +
                                (registry.getQueryResult().length() == 0 ? "" : "\n" + registry.getQueryResult()));
                    }
                    break;
                case ACTION_DISABLE:
                    if (registry.canDisable(miClient.getSession())) {
                        registry.disable(miClient.getSession());
                    }
                    else {
                        throw new Exception(SDbConsts.MSG_REG_DENIED_DISABLE +
                                (registry.getQueryResult().length() == 0 ? "" : "\n" + registry.getQueryResult()));
                    }
                    break;
                case ACTION_DELETE:
                    if (registry.canDelete(miClient.getSession())) {
                        registry.delete(miClient.getSession());
                    }
                    else {
                        throw new Exception(SDbConsts.MSG_REG_DENIED_DELETE +
                                (registry.getQueryResult().length() == 0 ? "" : "\n" + registry.getQueryResult()));
                    }
                    break;
                default:
                    throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
            }

            result = registry.getQueryResultId();

            if (registry.getPostSaveTarget() != null && registry.getPostSaveMethod() != null) {
                SLibUtils.invoke(registry.getPostSaveTarget(), registry.getPostSaveMethod(), registry.getPostSaveMethodArgs());
            }
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, registry.getSql());
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            try {
                if (result == SDbConsts.SAVE_OK) {
                    commitTransaction();
                }
                else {
                    rollbackTransaction();
                }
            }
            catch (Exception e) {
                SLibUtils.showException(this, e);
            }

            SGuiUtils.setCursorDefault(miClient);
        }

        return result;
    }

    /*
     * Public methods
     */

    public void setUserFormsMap(HashMap<SGuiUserForm, SGuiForm> o) { moUserFormsMap = o; }

    public int getModuleType() { return mnModuleType; }
    public int getModuleSubtype() { return mnModuleSubtype; }
    public SDbRegistry getLastRegistry() { return moLastRegistry; }
    public int getLastFormResult() { return mnLastFormResult; }
    public HashMap<SGuiUserForm, SGuiForm> getUserFormsMap() { return moUserFormsMap; }
    public ImageIcon getModuleIcon() { return moModuleIcon; }
    public void afterRegistrySaved() { }

    public abstract JMenu[] getMenus();
    public abstract SDbRegistry getRegistry(final int type, final SGuiParams params);
    public abstract SGuiCatalogueSettings getCatalogueSettings(final int type, final int subtype, final SGuiParams params);
    public abstract SGridPaneView getView(final int type, final int subtype, final SGuiParams params);
    public abstract SGuiOptionPicker getOptionPicker(final int type, final int subtype, final SGuiParams params);
    public abstract SGuiForm getForm(final int type, final int subtype, final SGuiParams params);
    public abstract SGuiReport getReport(final int type, final int subtype, final SGuiParams params);

    @Override
    @SuppressWarnings("unchecked")
    public void populateCatalogue(JComboBox comboBox, final int type, final int subtype, final SGuiParams params) {
        Vector<SGuiItem> items = readItems(type, subtype, params);

        comboBox.removeAllItems();
        for (SGuiItem item : items) {
            comboBox.addItem(item);
        }
    }

    @Override
    public void showView(final int type, final int subtype, final SGuiParams params) {
        int count = 0;
        int index = 0;
        int mode = params == null ? SLibConsts.UNDEFINED : params.getType();
        int submode = params == null ? SLibConsts.UNDEFINED : params.getSubtype();
        boolean exists = false;
        SGridPaneView paneView = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            count = miClient.getTabbedPane().getTabCount();
            for (index = 0; index < count; index++) {
                if (miClient.getTabbedPane().getComponentAt(index) instanceof SGridPaneView) {
                    paneView = (SGridPaneView) miClient.getTabbedPane().getComponentAt(index);
                    if (type == paneView.getGridType() && subtype == paneView.getGridSubtype() &&
                            mode == paneView.getGridMode() && submode == paneView.getGridSubmode()) {
                        exists = true;
                        break;
                    }
                }
            }

            if (exists) {
                miClient.getTabbedPane().setSelectedIndex(index);
            }
            else {
                paneView = getView(type, subtype, params);
                paneView.populateGrid(SGridConsts.REFRESH_MODE_RESET);
                miClient.getTabbedPane().addTab(paneView.getTitle(), paneView);
                miClient.getTabbedPane().setTabComponentAt(count, new SGridTabComponent(miClient, moModuleIcon));
                miClient.getTabbedPane().setSelectedIndex(count);
                miClient.getTabbedPane().getComponentAt(count).requestFocus();
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }
    }

    @Override
    public void showOptionPicker(final int type, final int subtype, final SGuiParams params, final SGuiField field) {
        SGuiOptionPicker picker = getOptionPicker(type, subtype, params);

        picker.resetPicker();
        picker.setOption(field.getValue());
        picker.setPickerVisible(true);

        if (picker.getPickerResult() == SGuiConsts.FORM_RESULT_OK) {
            field.setValue(picker.getOption());
            field.getComponent().requestFocus();
        }
    }

    @Override
    public void showForm(final int type, final int subtype, final SGuiParams params) {
        SGuiForm form = null;
        SDbRegistry registry = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            form = getForm(type, subtype, params);
            registry = getRegistry(type, params);
            moLastRegistry = null;
            mnLastFormResult = 0;

            if (params == null) {
                registry.setFormAction(SGuiConsts.FORM_ACTION_NEW);
            }
            else {
                if (params.getKey() == null) {
                    registry.setFormAction(SGuiConsts.FORM_ACTION_NEW);
                }
                else {
                    registry.read(miClient.getSession(), params.getKey());

                    if (!params.isCopy()) {
                        registry.setFormAction(SGuiConsts.FORM_ACTION_EDIT);
                    }
                    else {
                        registry.setFormAction(SGuiConsts.FORM_ACTION_NEW);
                        registry.setRegistryNew(true);
                    }
                }
            }

            form.setRegistry(registry);

            if (form.canShowForm()) {
                if (params != null) {
                    for (Integer key : params.getParamsMap().keySet()) {
                        form.setValue(key, params.getParamsMap().get(key));
                    }
                }
            }

            SGuiUtils.setCursorDefault(miClient);

            form.setFormVisible(true);

            if ((mnLastFormResult = form.getFormResult()) == SGuiConsts.FORM_RESULT_OK) {
                registry = form.getRegistry();

                if (miClient.getSession().saveRegistry(registry) != SDbConsts.SAVE_OK) {
                    moLastRegistry = null;
                }
                else {
                    moLastRegistry = registry.clone();
                    moLastRegistry.setRegistryEdited(true);
                    miClient.getSession().notifySuscriptors(moLastRegistry.getRegistryType());
                }
                
                afterRegistrySaved();
            }
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, registry.getSql());
        }
        catch (IllegalAccessException e) {
            SLibUtils.showException(this, e);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }
    }

    @Override
    public void printReport(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap) {
        File file = null;
        SGuiReport report = null;
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        JasperViewer jasperViewer = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            report = getReport(type, subtype, params);
            file = new File(report.getFileName());
            jasperReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperReport, paramsMap, miClient.getSession().getStatement().getConnection());
            jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setTitle(report.getReportTitle());
            jasperViewer.setVisible(true);
        }
        catch (JRException e) {
            SLibUtils.showException(this, e);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }
    }

    @Override
    public void printReportNow(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap, final boolean withPrintDialog) {
        File file = null;
        SGuiReport report = null;
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            report = getReport(type, subtype, params);
            file = new File(report.getFileName());
            jasperReport = (JasperReport) JRLoader.loadObject(file);
            jasperPrint = JasperFillManager.fillReport(jasperReport, paramsMap, miClient.getSession().getStatement().getConnection());
            JasperPrintManager.printReport(jasperPrint, withPrintDialog);
        }
        catch (JRException e) {
            SLibUtils.showException(this, e);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }
    }

    @Override
    public Vector<SGuiItem> readItems(final int type, final int subtype, final SGuiParams params) {
        int i = 0;
        int[] key = null;
        ResultSet resultSet = null;
        SGuiItem item = null;
        Vector<SGuiItem> items = null;
        SGuiCatalogueSettings settings = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            items = new Vector<SGuiItem>();
            settings = getCatalogueSettings(type, subtype, params);

            if (settings.isSelectionItemApplying()) {
                items.add(new SGuiItem("- " + settings.getCatalogueName() + " -"));
            }

            resultSet = miClient.getSession().getStatement().executeQuery(settings.getSql());
            while (resultSet.next()) {
                key = new int[settings.getPrimaryKeyLength()];
                for (i = 1; i <= settings.getPrimaryKeyLength(); i++) {
                    key[i - 1] = resultSet.getInt(SDbConsts.FIELD_ID + i);
                }

                item = new SGuiItem(key, resultSet.getString(SDbConsts.FIELD_ITEM));

                if (settings.getForeignKeyLength() > 0) {
                    key = new int[settings.getForeignKeyLength()];
                    for (i = 1; i <= settings.getForeignKeyLength(); i++) {
                        key[i - 1] = resultSet.getInt(SDbConsts.FIELD_FK + i);
                    }

                    item.setForeignKey(key);
                }

                if (settings.isComplementApplying()) {
                    switch (settings.getComplementDataType()) {
                        case SLibConsts.DATA_TYPE_INT:
                            item.setComplement(resultSet.getInt(SDbConsts.FIELD_COMP));
                            break;
                        case SLibConsts.DATA_TYPE_DEC:
                            item.setComplement(resultSet.getDouble(SDbConsts.FIELD_COMP));
                            break;
                        case SLibConsts.DATA_TYPE_BOOL:
                            item.setComplement(resultSet.getBoolean(SDbConsts.FIELD_COMP));
                            break;
                        case SLibConsts.DATA_TYPE_TEXT:
                            item.setComplement(resultSet.getString(SDbConsts.FIELD_COMP));
                            break;
                        case SLibConsts.DATA_TYPE_DATE:
                            item.setComplement(resultSet.getDate(SDbConsts.FIELD_COMP));
                            break;
                        default:
                            throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                    }
                }

                if (settings.isCodeApplying()) {
                    item.setCode(resultSet.getString(SDbConsts.FIELD_CODE));
                }

                item.setCodeVisible(settings.isCodeVisible());

                if (settings.isDateApplying()) {
                    item.setDate(resultSet.getDate(SDbConsts.FIELD_DATE));
                }

                if (settings.isValueApplying()) {
                    item.setValue(resultSet.getDouble(SDbConsts.FIELD_VALUE));
                }

                items.add(item);
            }
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, settings.getSql());
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }

        return items;
    }

    /**
     * Reads database registry (reading verbose mode).
     * @param type Registry type.
     * @param pk Registry primary key.
     */
    @Override
    public SDbRegistry readRegistry(final int type, final int[] pk) {
        return readRegistry(type, pk, SDbConsts.MODE_VERBOSE);
    }

    /**
     * Reads database registry.
     * @param type Registry type.
     * @param pk Registry primary key.
     * @param mode Reading mode. Constants defined in SDbConsts (MODE_VERBOSE, MODE_STEALTH).
     */
    @Override
    public SDbRegistry readRegistry(final int type, final int[] pk, final int mode) {
        SDbRegistry registry = null;

        try {
            SGuiUtils.setCursorWait(miClient);

            registry = getRegistry(type, null);
            registry.read(miClient.getSession(), pk);
            if (registry.getQueryResultId() != SDbConsts.READ_OK) {
                throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
            }
        }
        catch (SQLException e) {
            if (mode == SDbConsts.MODE_VERBOSE) {
                SLibUtils.showException(this, e);
            }
            else {
                SLibUtils.printException(this, e);
            }

            SLibUtils.printSqlQuery(this, registry.getSql());
        }
        catch (Exception e) {
            if (mode == SDbConsts.MODE_VERBOSE) {
                SLibUtils.showException(this, e);
            }
            else {
                SLibUtils.printException(this, e);
            }
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }

        return registry;
    }

    @Override
    public int saveRegistry(final SDbRegistry registry) {
        return computeAction(registry, ACTION_SAVE);
    }

    @Override
    public int disableRegistry(final int type, final int[] pk) {
        int result = SLibConsts.UNDEFINED;
        SDbRegistry registry = readRegistry(type, pk);

        if (registry.getQueryResultId() == SDbConsts.READ_OK) {
            result = computeAction(registry, ACTION_DISABLE);
        }

        return result;
    }

    @Override
    public int deleteRegistry(final int type, final int[] pk) {
        int result = SLibConsts.UNDEFINED;
        SDbRegistry registry = readRegistry(type, pk);

        if (registry.getQueryResultId() == SDbConsts.READ_OK) {
            result = computeAction(registry, ACTION_DELETE);
        }

        return result;
    }

    @Override
    public Object readField(final int type, final int[] pk, final int field) {
        Object value = null;
        SDbRegistry registry = null;

        try {
            registry = getRegistry(type, null);
            value = registry.readField(miClient.getSession().getStatement(), pk, field);
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, registry.getSql());
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }

        return value;
    }

    @Override
    public int saveField(final int type, final int[] pk, final int field, final Object value) {
        int result = SLibConsts.UNDEFINED;
        SDbRegistry registry = null;

        try {
            registry = getRegistry(type, null);
            registry.saveField(miClient.getSession().getStatement(), pk, field, value);
            result = SDbConsts.SAVE_OK;
        }
        catch (SQLException e) {
            result = SDbConsts.SAVE_ERROR;
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, registry.getSql());
        }
        catch (Exception e) {
            result = SDbConsts.SAVE_ERROR;
            SLibUtils.showException(this, e);
        }

        return result;
    }
}
