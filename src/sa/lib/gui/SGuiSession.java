/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JComboBox;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbDatabase;
import sa.lib.db.SDbDatabaseMonitor;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridPaneView;
import sa.lib.srv.SSrvSessionServerSide;

/**
 *
 * @author Sergio Flores
 */
public class SGuiSession implements SGuiController {

    protected SGuiClient miClient;
    protected SGuiUser miUser;
    protected SGuiSessionCustom miSessionCustom;
    protected Date mtSystemDate;
    protected Date mtCurrentDate;
    protected Date mtUserTs;
    protected SDbDatabase moDatabase;
    protected SDbDatabaseMonitor moDatabaseMonitor;
    protected Statement miStatement;
    protected SGuiConfigSystem miConfigSystem;
    protected SGuiConfigCompany miConfigCompany;
    protected SGuiConfigBranch miConfigBranch;
    protected SGuiConfigBranch miConfigBranchHq;
    protected SGuiEdsSignature miEdsSignature;
    protected SGuiModuleUtils miModuleUtils;
    protected Vector<SGuiModule> mvModules;
    protected SSrvSessionServerSide miSessionServerSide;

    public SGuiSession(SGuiClient client) {
        miClient = client;
        
        mvModules = new Vector<>();
        initSession();
    }

    private void stopMonitor() {
        if (moDatabaseMonitor != null && moDatabaseMonitor.isAlive()) {
            moDatabaseMonitor.stopThread();
        }
    }

    private void checkStatement() {
        try {
            if (miStatement == null || miStatement.isClosed()) {
                // restore database statement:
                System.out.println("Intentando restaurar el objeto declaración de la base de datos...");

                // first check database:
                if (moDatabase == null) {
                    throw new Exception("No se ha definido la base de datos.");
                }
                else if (!moDatabase.isConnected()) {
                    System.out.println("Intentando reconectar la base de datos...");
                    if (moDatabase.reconnect() != SDbConsts.CONNECTION_OK) {
                        throw new Exception("No se pudo reconectar la base de datos.");
                    }
                }
            
                if (moDatabaseMonitor == null || !moDatabaseMonitor.isAlive()) {
                    prepareDatabase(); // creates statement aswell
                }
                else {
                    // create statement:
                    miStatement = moDatabase.getConnection().createStatement();
                }
                
                System.out.println("El objeto declaración de la base de datos fue restaurado!");
            }
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }

    private void prepareDatabase() {
        System.out.println("Preparando el monitor de la base de datos...");
        
        try {
            moDatabaseMonitor = new SDbDatabaseMonitor(moDatabase);
            moDatabaseMonitor.startThread();

            miStatement = moDatabase.getConnection().createStatement();
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }

    public void initSession() {
        stopMonitor();

        miUser = null;
        miSessionCustom = null;
        mtSystemDate = null;
        mtCurrentDate = null;
        mtUserTs = null;
        moDatabase = null;
        moDatabaseMonitor = null;
        miStatement = null;
        miConfigSystem = null;
        miConfigCompany = null;
        miConfigBranch = null;
        miConfigBranchHq = null;
        miEdsSignature = null;
        miModuleUtils = null;
        miSessionServerSide = null;
        
        mvModules.clear();
    }

    public void setClient(SGuiClient o) { miClient = o; }
    public void setUser(SGuiUser o) { miUser = o; }
    public void setSessionCustom(SGuiSessionCustom o) { miSessionCustom = o; }
    public void setSystemDate(Date t) { mtSystemDate = t; }
    public void setCurrentDate(Date t) { mtCurrentDate = t; }
    public void setUserTs(Date t) { mtUserTs = t; }
    public void setDatabase(SDbDatabase o) { moDatabase = o; prepareDatabase(); }
    //public void setStatement(Statement i) { miStatement = i; } // statement should not be set manually
    public void setConfigSystem(SGuiConfigSystem i) { miConfigSystem = i; }
    public void setConfigCompany(SGuiConfigCompany i) { miConfigCompany = i; }
    public void setConfigBranch(SGuiConfigBranch i) { miConfigBranch = i; }
    public void setConfigBranchHq(SGuiConfigBranch i) { miConfigBranchHq = i; }
    public void setEdsSignature(SGuiEdsSignature i) { miEdsSignature = i; }
    public void setModuleUtils(SGuiModuleUtils i) { miModuleUtils = i; }
    public void setSessionServerSide(SSrvSessionServerSide i) { miSessionServerSide = i; }

    public SGuiClient getClient() { return miClient; }
    public SGuiUser getUser() { return miUser; }
    public SGuiSessionCustom getSessionCustom() { return miSessionCustom; }
    public Date getSystemDate() { return mtSystemDate; }
    public Date getCurrentDate() { return mtCurrentDate; }
    public Date getUserTs() { return mtUserTs; }
    public SDbDatabase getDatabase() { return moDatabase; }
    public Statement getStatement() { checkStatement(); return miStatement; }
    public SGuiConfigSystem getConfigSystem() { return miConfigSystem; }
    public SGuiConfigCompany getConfigCompany() { return miConfigCompany; }
    public SGuiConfigBranch getConfigBranch() { return miConfigBranch; }
    public SGuiConfigBranch getConfigBranchHq() { return miConfigBranchHq; }
    public SGuiEdsSignature getEdsSignature() { return miEdsSignature; }
    public SGuiModuleUtils getModuleUtils() { return miModuleUtils; }
    public SSrvSessionServerSide getSessionServerSide() { return miSessionServerSide; }
    public Vector<SGuiModule> getModules() { return mvModules; }

    
    public int getSystemYear() { return SLibTimeUtils.digestYear(mtSystemDate)[0]; }
    public int getCurrentYear() { return SLibTimeUtils.digestYear(mtCurrentDate)[0]; }

    /**
     * Gets module by module type.
     * @param type Module type. Constants defined in DModConsts (MOD_...).
     * @return GUI module.
     */
    public SGuiModule getModule(final int type) {
        return getModule(type, SLibConsts.UNDEFINED);
    }

    /**
     * Gets module by module type and subtype.
     * @param type Module type. Constants defined in DModConsts (MOD_...).
     * @param subtype Module subtype. Constants defined in DModSysConsts (CS_MOD_...).
     * @return GUI module.
     */
    public SGuiModule getModule(final int type, final int subtype) {
        SGuiModule module = null;

        for (SGuiModule mod : mvModules) {
            if (type == mod.getModuleType() && (subtype == mod.getModuleSubtype() || subtype == SLibConsts.UNDEFINED)) {
                module = mod;
                break;
            }
        }

        return module;
    }

    /**
     * Gets module by GUI type and subtype (i.e. registry, view, form, etc.).
     * @param type Module type. Constants defined in DModConsts (MOD_...).
     * @param subtype Module subtype. Constants defined in DModSysConsts (CS_MOD_...).
     * @return GUI module.
     */
    public SGuiModule getModuleByGuiType(final int type, final int subtype) {
        SGuiModule module = null;
        int moduleType = miModuleUtils.getModuleTypeByType(type);
        int moduleSubtype = miModuleUtils.getModuleSubtypeBySubtype(type, subtype);

        for (SGuiModule mod : mvModules) {
            if (moduleType == mod.getModuleType() && (moduleSubtype == mod.getModuleSubtype() || moduleSubtype == SLibConsts.UNDEFINED)) {
                module = mod;
                break;
            }
        }

        return module;
    }

    public void notifySuscriptors(final int suscriptor) {
        int count = miClient.getTabbedPane().getTabCount();

        try {
            SGuiUtils.setCursorWait(miClient);

            for (int index = 0; index < count; index++) {
                if (miClient.getTabbedPane().getComponentAt(index) instanceof SGridPaneView) {
                    ((SGridPaneView) miClient.getTabbedPane().getComponentAt(index)).triggerSuscription(suscriptor);
                }
            }
        }
        catch (Exception e) {
            SLibUtils.printException(this, e);
        }
        finally {
            SGuiUtils.setCursorDefault(miClient);
        }
    }

    @Override
    public SDbRegistry getRegistry(final int type, final SGuiParams params) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).getRegistry(type, params);
    }

    @Override
    public void populateCatalogue(JComboBox comboBox, final int type, final int subtype, final SGuiParams params) {
        getModuleByGuiType(type, subtype).populateCatalogue(comboBox, type, subtype, params);
    }

    @Override
    public void showView(final int type, final int subtype, final SGuiParams params) {
        getModuleByGuiType(type, subtype).showView(type, subtype, params);
    }

    @Override
    public void showOptionPicker(final int type, final int subtype, final SGuiParams params, final SGuiField field) {
        getModuleByGuiType(type, subtype).showOptionPicker(type, subtype, params, field);
    }

    @Override
    public void showForm(final int type, final int subtype, final SGuiParams params) {
        getModuleByGuiType(type, subtype).showForm(type, subtype, params);
    }

    @Override
    public void printReport(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap) {
        getModuleByGuiType(type, subtype).printReport(type, subtype, params, paramsMap);
    }

    @Override
    public void printReportNow(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap, final boolean withPrintDialog) {
        getModuleByGuiType(type, subtype).printReportNow(type, subtype, params, paramsMap, withPrintDialog);
    }

    @Override
    public Vector<SGuiItem> readItems(final int type, final int subtype, final SGuiParams params) {
        return getModuleByGuiType(type, subtype).readItems(type, subtype, params);
    }

    /**
     * Reads database registry (reading verbose mode).
     * @param type Registry type.
     * @param pk Registry primary key.
     * @return Registry.
     */
    @Override
    public SDbRegistry readRegistry(final int type, final int[] pk) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).readRegistry(type, pk);
    }

    /**
     * Reads database registry.
     * @param type Registry type.
     * @param pk Registry primary key.
     * @param mode Reading mode. Constants defined in DDbConsts (MODE_VERBOSE, MODE_STEALTH).
     * @return Registry.
     */
    @Override
    public SDbRegistry readRegistry(final int type, final int[] pk, final int mode) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).readRegistry(type, pk, mode);
    }

    @Override
    public int saveRegistry(final SDbRegistry registry) {
        return getModuleByGuiType(registry.getRegistryType(), SLibConsts.UNDEFINED).saveRegistry(registry);
    }

    @Override
    public int disableRegistry(final int type, final int[] pk) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).disableRegistry(type, pk);
    }

    @Override
    public int deleteRegistry(final int type, final int[] pk) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).deleteRegistry(type, pk);
    }

    @Override
    public Object readField(final int type, final int[] pk, final int field) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).readField(type, pk, field);
    }

    @Override
    public int saveField(final int type, final int[] pk, final int field, final Object value) {
        return getModuleByGuiType(type, SLibConsts.UNDEFINED).saveField(type, pk, field, value);
    }

    @Override
    public void finalize() throws Throwable {
        stopMonitor();
    }
}
