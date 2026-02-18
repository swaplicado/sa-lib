/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SGridPaneView.java
 *
 * Created on 14/06/2011, 09:25:19 AM
 */

package sa.lib.grid;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibConsts;
import sa.lib.SLibRpnArgument;
import sa.lib.SLibRpnArgumentType;
import sa.lib.SLibRpnUtils;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.grid.xml.SXmlColumnView;
import sa.lib.grid.xml.SXmlGridXml;
import sa.lib.grid.xml.SXmlRpnArgument;
import sa.lib.grid.xml.SXmlSortKey;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiComponentGui;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiParams;
import sa.lib.gui.SGuiUserGui;
import sa.lib.gui.SGuiUtils;
import sa.lib.xml.SXmlElement;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGridPaneView extends JPanel implements SGridPane, ListSelectionListener {

    private ImageIcon moIconReset = new ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reset.gif"));
    private ImageIcon moIconResetUpd = new ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reset_upd.gif"));
    private ImageIcon moIconReload = new ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reload.gif"));
    private ImageIcon moIconReloadUpd = new ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reload_upd.gif"));

    protected SGuiClient miClient;
    protected int mnGridType;
    protected int mnGridSubtype;
    protected int mnGridMode;
    protected int mnGridSubmode;
    protected int mnGridViewType;
    protected int mnModuleType;
    protected int mnModuleSubtype;
    protected String msTitle;
    protected String msSql;
    protected SGuiParams moPaneParams;
    protected SGuiParams moFormParams;

    protected SGridModel moModel;
    protected SGridSeeker moSeeker;
    protected SGridPaneSettings moPaneSettings;
    protected HashSet<Integer> moSuscriptionsSet;
    protected List<RowSorter.SortKey> miSortKeysList;
    protected HashMap<Integer, Object> moFiltersMap;
    protected HashMap<Integer, Integer> moColumnComplementsMap;

    protected int mnListSelectionModel;
    protected int mnRenderAttempts;
    protected int[] manUserGuiKey;
    protected SGuiUserGui miUserGui;

    protected int mnPrivilegeView;
    protected int mnUserLevelAccess;
    protected boolean mbPreventUserGuiFromSaving;
    protected boolean mbClearSettingsNeeded;
    protected boolean mbReloadNeeded;
    protected boolean mbApplyNew;
    protected boolean mbApplyCopy;
    protected boolean mbApplyEdit;
    protected boolean mbApplyDisable;
    protected boolean mbApplyDelete;
    protected boolean mbShowSums;
    protected ArrayList<SGuiComponentGui> maComponentGuis;

    /** Creates new form SGridPaneView */
    public SGridPaneView(SGuiClient client, int viewType, int gridType, int gridSubtype, String title) {
        this(client, viewType, gridType, gridSubtype, title, SLibConsts.UNDEFINED, null, false);
    }

    /** Creates new form SGridPaneView */
    public SGridPaneView(SGuiClient client, int viewType, int gridType, int gridSubtype, String title, SGuiParams params) {
        this(client, viewType, gridType, gridSubtype, title, SLibConsts.UNDEFINED, params, false);
    }

    /** Creates new form SGridPaneView */
    public SGridPaneView(SGuiClient client, int viewType, int gridType, int gridSubtype, String title, int privilegeView) {
        this(client, viewType, gridType, gridSubtype, title, privilegeView, null, false);
    }

    /** Creates new form SGridPaneView */
    public SGridPaneView(SGuiClient client, int viewType, int gridType, int gridSubtype, String title, int privilegeView, SGuiParams params) {
        this(client, viewType, gridType, gridSubtype, title, privilegeView, params, false);
    }

    /** Creates new form SGridPaneView */
    public SGridPaneView(SGuiClient client, int viewType, int gridType, int gridSubtype, String title, int privilegeView, SGuiParams params, boolean preventUserGuiFromSaving) {
        miClient = client;
        mnGridType = gridType;
        mnGridSubtype = gridSubtype;
        mnGridMode = params == null ? SLibConsts.UNDEFINED : params.getType();
        mnGridSubmode = params == null ? SLibConsts.UNDEFINED : params.getSubtype();
        mnGridViewType = viewType;
        mnModuleType = miClient.getSession().getModuleUtils().getModuleTypeByType(gridType);
        mnModuleSubtype = miClient.getSession().getModuleUtils().getModuleSubtypeBySubtype(gridType, gridSubtype);
        msTitle = title;
        msSql = "";
        moPaneParams = params;
        moFormParams = null;
        mnRenderAttempts = 0;

        mnPrivilegeView = privilegeView;
        mnUserLevelAccess = miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView);
        mbPreventUserGuiFromSaving = preventUserGuiFromSaving;
        
        mbShowSums = true;

        initComponents();
        initComponentsCustom();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jpCommands = new javax.swing.JPanel();
        jpCommandsSys = new javax.swing.JPanel();
        jpCommandsSysLeft = new javax.swing.JPanel();
        jbRowNew = new javax.swing.JButton();
        jbRowCopy = new javax.swing.JButton();
        jbRowEdit = new javax.swing.JButton();
        jbRowDisable = new javax.swing.JButton();
        jbRowDelete = new javax.swing.JButton();
        jpCommandsSysCenter = new javax.swing.JPanel();
        jtbFilterDeleted = new javax.swing.JToggleButton();
        jpCommandsSysRight = new javax.swing.JPanel();
        jbGridSaveCsv = new javax.swing.JButton();
        jbGridClearSettings = new javax.swing.JButton();
        jbGridReload = new javax.swing.JButton();
        jpCommandsCustom = new javax.swing.JPanel();
        jpCommandsCustomLeft = new javax.swing.JPanel();
        jpCommandsCustomCenter = new javax.swing.JPanel();
        jpCommandsCustomRight = new javax.swing.JPanel();
        jspScrollPane = new javax.swing.JScrollPane();
        jtTable = new javax.swing.JTable();
        jpStatus = new javax.swing.JPanel();
        jpStatusLeft = new javax.swing.JPanel();
        jtfRows = new javax.swing.JTextField();
        jpStatusCenter = new javax.swing.JPanel();
        jtfGridSearch = new javax.swing.JTextField();
        jbGridSearchNext = new javax.swing.JButton();
        jbGridSearchNextCol = new javax.swing.JButton();
        jpStatusRight = new javax.swing.JPanel();
        jtbAutoReload = new javax.swing.JToggleButton();

        jLabel1.setText("jLabel1");

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        setLayout(new java.awt.BorderLayout());

        jpCommands.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 5, 0));
        jpCommands.setLayout(new java.awt.BorderLayout());

        jpCommandsSys.setLayout(new java.awt.BorderLayout());

        jpCommandsSysLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jbRowNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_new.gif"))); // NOI18N
        jbRowNew.setToolTipText("Nuevo (Ctrl+N)");
        jbRowNew.setPreferredSize(new java.awt.Dimension(23, 23));
        jbRowNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRowNewActionPerformed(evt);
            }
        });
        jpCommandsSysLeft.add(jbRowNew);

        jbRowCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_copy.gif"))); // NOI18N
        jbRowCopy.setToolTipText("Duplicar (Ctrl+D)");
        jbRowCopy.setPreferredSize(new java.awt.Dimension(23, 23));
        jbRowCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRowCopyActionPerformed(evt);
            }
        });
        jpCommandsSysLeft.add(jbRowCopy);

        jbRowEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_edit.gif"))); // NOI18N
        jbRowEdit.setToolTipText("Modificar (Ctrl+M)");
        jbRowEdit.setPreferredSize(new java.awt.Dimension(23, 23));
        jbRowEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRowEditActionPerformed(evt);
            }
        });
        jpCommandsSysLeft.add(jbRowEdit);

        jbRowDisable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_disable.gif"))); // NOI18N
        jbRowDisable.setToolTipText("Inhabilitar (Ctrl+I)");
        jbRowDisable.setPreferredSize(new java.awt.Dimension(23, 23));
        jbRowDisable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRowDisableActionPerformed(evt);
            }
        });
        jpCommandsSysLeft.add(jbRowDisable);

        jbRowDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_delete.gif"))); // NOI18N
        jbRowDelete.setToolTipText("Eliminar (Ctrl+E)");
        jbRowDelete.setPreferredSize(new java.awt.Dimension(23, 23));
        jbRowDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRowDeleteActionPerformed(evt);
            }
        });
        jpCommandsSysLeft.add(jbRowDelete);

        jpCommandsSys.add(jpCommandsSysLeft, java.awt.BorderLayout.WEST);

        jpCommandsSysCenter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jtbFilterDeleted.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/swi_filter_off.gif"))); // NOI18N
        jtbFilterDeleted.setToolTipText("Filtrar eliminados");
        jtbFilterDeleted.setPreferredSize(new java.awt.Dimension(23, 23));
        jtbFilterDeleted.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/swi_filter_on.gif"))); // NOI18N
        jtbFilterDeleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbFilterDeletedActionPerformed(evt);
            }
        });
        jpCommandsSysCenter.add(jtbFilterDeleted);

        jpCommandsSys.add(jpCommandsSysCenter, java.awt.BorderLayout.CENTER);

        jpCommandsSysRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jbGridSaveCsv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_save.gif"))); // NOI18N
        jbGridSaveCsv.setToolTipText("Guardar como CSV (Ctrl+G)");
        jbGridSaveCsv.setPreferredSize(new java.awt.Dimension(23, 23));
        jbGridSaveCsv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGridSaveCsvActionPerformed(evt);
            }
        });
        jpCommandsSysRight.add(jbGridSaveCsv);

        jbGridClearSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reset.gif"))); // NOI18N
        jbGridClearSettings.setToolTipText("Limpiar preferencias (Ctrl+L)");
        jbGridClearSettings.setPreferredSize(new java.awt.Dimension(23, 23));
        jbGridClearSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGridClearSettingsActionPerformed(evt);
            }
        });
        jpCommandsSysRight.add(jbGridClearSettings);

        jbGridReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_reload.gif"))); // NOI18N
        jbGridReload.setToolTipText("Refrescar (Ctrl+R)");
        jbGridReload.setPreferredSize(new java.awt.Dimension(23, 23));
        jbGridReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGridReloadActionPerformed(evt);
            }
        });
        jpCommandsSysRight.add(jbGridReload);

        jpCommandsSys.add(jpCommandsSysRight, java.awt.BorderLayout.EAST);

        jpCommands.add(jpCommandsSys, java.awt.BorderLayout.PAGE_START);

        jpCommandsCustom.setLayout(new java.awt.BorderLayout());

        jpCommandsCustomLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jpCommandsCustom.add(jpCommandsCustomLeft, java.awt.BorderLayout.WEST);

        jpCommandsCustomCenter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jpCommandsCustom.add(jpCommandsCustomCenter, java.awt.BorderLayout.CENTER);

        jpCommandsCustomRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jpCommandsCustom.add(jpCommandsCustomRight, java.awt.BorderLayout.EAST);

        jpCommands.add(jpCommandsCustom, java.awt.BorderLayout.CENTER);

        add(jpCommands, java.awt.BorderLayout.NORTH);

        jtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jspScrollPane.setViewportView(jtTable);

        add(jspScrollPane, java.awt.BorderLayout.CENTER);

        jpStatus.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 0, 0));
        jpStatus.setLayout(new java.awt.BorderLayout());

        jpStatusLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jtfRows.setEditable(false);
        jtfRows.setText("000,000/000,000");
        jtfRows.setToolTipText("Renglón actual");
        jtfRows.setFocusable(false);
        jtfRows.setPreferredSize(new java.awt.Dimension(100, 23));
        jpStatusLeft.add(jtfRows);

        jpStatus.add(jpStatusLeft, java.awt.BorderLayout.WEST);

        jpStatusCenter.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jtfGridSearch.setToolTipText("Buscar (Ctrl+B)");
        jtfGridSearch.setPreferredSize(new java.awt.Dimension(100, 23));
        jtfGridSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfGridSearchActionPerformed(evt);
            }
        });
        jpStatusCenter.add(jtfGridSearch);

        jbGridSearchNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_next_row.gif"))); // NOI18N
        jbGridSearchNext.setToolTipText("Siguiente (F3)");
        jbGridSearchNext.setPreferredSize(new java.awt.Dimension(23, 23));
        jbGridSearchNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGridSearchNextActionPerformed(evt);
            }
        });
        jpStatusCenter.add(jbGridSearchNext);

        jbGridSearchNextCol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_grid_next.gif"))); // NOI18N
        jbGridSearchNextCol.setToolTipText("Siguiente columna");
        jbGridSearchNextCol.setPreferredSize(new java.awt.Dimension(23, 23));
        jbGridSearchNextCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGridSearchNextColActionPerformed(evt);
            }
        });
        jpStatusCenter.add(jbGridSearchNextCol);

        jpStatus.add(jpStatusCenter, java.awt.BorderLayout.CENTER);

        jpStatusRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jtbAutoReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/swi_action_off.gif"))); // NOI18N
        jtbAutoReload.setToolTipText("Refrescar automáticamente");
        jtbAutoReload.setPreferredSize(new java.awt.Dimension(23, 23));
        jtbAutoReload.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/swi_action_on.gif"))); // NOI18N
        jtbAutoReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtbAutoReloadActionPerformed(evt);
            }
        });
        jpStatusRight.add(jtbAutoReload);

        jpStatus.add(jpStatusRight, java.awt.BorderLayout.EAST);

        add(jpStatus, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jbRowNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRowNewActionPerformed
        actionRowNew();
    }//GEN-LAST:event_jbRowNewActionPerformed

    private void jbRowEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRowEditActionPerformed
        actionRowEdit();
    }//GEN-LAST:event_jbRowEditActionPerformed

    private void jbRowCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRowCopyActionPerformed
        actionRowCopy();
    }//GEN-LAST:event_jbRowCopyActionPerformed

    private void jbRowDisableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRowDisableActionPerformed
        actionRowDisable();
    }//GEN-LAST:event_jbRowDisableActionPerformed

    private void jbRowDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRowDeleteActionPerformed
        actionRowDelete();
    }//GEN-LAST:event_jbRowDeleteActionPerformed

    private void jbGridSaveCsvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGridSaveCsvActionPerformed
        actionGridSaveCsv();
    }//GEN-LAST:event_jbGridSaveCsvActionPerformed

    private void jbGridClearSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGridClearSettingsActionPerformed
        actionGridClearSettings();
    }//GEN-LAST:event_jbGridClearSettingsActionPerformed

    private void jbGridReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGridReloadActionPerformed
        actionGridReload();
    }//GEN-LAST:event_jbGridReloadActionPerformed

    private void jtbFilterDeletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbFilterDeletedActionPerformed
        actionToggleFilterDeleted();
    }//GEN-LAST:event_jtbFilterDeletedActionPerformed

    private void jtbAutoReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtbAutoReloadActionPerformed
        actionToggleAutoReload();
    }//GEN-LAST:event_jtbAutoReloadActionPerformed

    private void jtfGridSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfGridSearchActionPerformed
        actionGridSearchValue();
    }//GEN-LAST:event_jtfGridSearchActionPerformed

    private void jbGridSearchNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGridSearchNextActionPerformed
        actionGridSearchNextValue();
    }//GEN-LAST:event_jbGridSearchNextActionPerformed

    private void jbGridSearchNextColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGridSearchNextColActionPerformed
        actionGridSearchNextColValue();
    }//GEN-LAST:event_jbGridSearchNextColActionPerformed

    private void initComponentsCustom() {
        moModel = new SGridModel();
        moSeeker = new SGridSeeker(miClient.getFrame());
        moPaneSettings = null;
        moSuscriptionsSet = new HashSet<>();
        miSortKeysList = new ArrayList<>();
        moFiltersMap = new HashMap<>();
        moColumnComplementsMap = new HashMap<>();

        mbClearSettingsNeeded = false;
        mbReloadNeeded = false;

        mbApplyNew = false;
        mbApplyCopy = false;
        mbApplyEdit = false;
        mbApplyDisable = false;
        mbApplyDelete = false;
        maComponentGuis = new ArrayList<>();

        defineSuscriptions();

        switch (mnGridViewType) {
            case SGridConsts.GRID_VIEW_TAB:
                mnListSelectionModel = ListSelectionModel.SINGLE_INTERVAL_SELECTION;
                manUserGuiKey = new int[] { miClient.getSession().getUser().getPkUserId(), SGuiConsts.GUI_COMP_VIEW_TAB, mnGridType, mnGridSubtype, mnGridMode, mnGridSubmode };

                jbRowNew.setEnabled(true);
                jbRowEdit.setEnabled(true);
                jbRowCopy.setEnabled(true);
                jbRowDisable.setEnabled(true);
                jbRowDelete.setEnabled(true);
                jbGridSaveCsv.setEnabled(true);
                jbGridClearSettings.setEnabled(true);
                jbGridReload.setEnabled(true);
                jtbFilterDeleted.setEnabled(true);
                jtbAutoReload.setEnabled(true);

                jtbFilterDeleted.setSelected(true);
                jtbAutoReload.setSelected(true);

                moFiltersMap.put(SGridConsts.FILTER_DELETED, true);
                break;

            case SGridConsts.GRID_VIEW_OPTION_PICKER:
                mnListSelectionModel = ListSelectionModel.SINGLE_SELECTION;
                manUserGuiKey = new int[] { miClient.getSession().getUser().getPkUserId(), SGuiConsts.GUI_COMP_VIEW_OPTION_PICKER, mnGridType, mnGridSubtype, mnGridMode, mnGridSubmode };

                jbRowNew.setEnabled(true);
                jbRowEdit.setEnabled(true);
                jbRowCopy.setEnabled(true);
                jbRowDisable.setEnabled(false);
                jbRowDelete.setEnabled(false);
                jbGridSaveCsv.setEnabled(true);
                jbGridClearSettings.setEnabled(true);
                jbGridReload.setEnabled(true);
                jtbFilterDeleted.setEnabled(false);
                jtbAutoReload.setEnabled(false);
                break;

            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        miUserGui = mbPreventUserGuiFromSaving ? null : miClient.readUserGui(manUserGuiKey);

        SGuiUtils.createActionMap(this, this, "actionRowNew", "rowNew", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionRowEdit", "rowEdit", KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionRowCopy", "rowCopy", KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionRowDisable", "rowDisable", KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionRowDelete", "rowDelete", KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionGridSaveCsv", "gridSaveCsv", KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionGridClearSettings", "gridClearSettings", KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionGridReload", "gridReload", KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionGridSeekValue", "gridSeekValue", KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "grabFocusToSearch", "focusToSearch", KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
        SGuiUtils.createActionMap(this, this, "actionGridSearchNextValue", "gridSearchNextValue", KeyEvent.VK_F3, 0);
    }

    protected void initGrid() {
        miUserGui = null; // reset grid's user preferences
        populateGrid(SGridConsts.REFRESH_MODE_RESET);
    }

    // This method differs from SA-Lib-10.
//    protected void computeUserGuiFilters(final SXmlGridXml gridXml_n) {
//        SXmlGridXml gridXml = gridXml_n;
//
//        try {
//            if (gridXml == null) {
//                gridXml = new SXmlGridXml(SGridConsts.GRID_PANE_VIEW);
//                gridXml.processXml(miUserGui.getGui());
//            }
//
//            for (SXmlElement element : gridXml.getXmlElements()) {
//                if (element instanceof SXmlFilter) {
//                    // Filters:
//
//                    SXmlFilter xmlFilter = (SXmlFilter) element;
//                    SGridFilterValue filterValue = new SGridFilterValue(
//                             (Integer) xmlFilter.getAttribute(SXmlFilter.ATT_FILTER_TYPE).getValue(),
//                             (Integer) xmlFilter.getAttribute(SXmlFilter.ATT_DATA_TYPE).getValue(),
//                             xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).getValue());
//
//                    switch ((Integer) xmlFilter.getAttribute(SXmlFilter.ATT_DATA_TYPE).getValue()) {
//                        case SGridConsts.FILTER_DATA_TYPE_DATE:
//                        case SGridConsts.FILTER_DATA_TYPE_DATE_ARRAY:
//                        case SGridConsts.FILTER_DATA_TYPE_GUIDATE:
//                        case SGridConsts.FILTER_DATA_TYPE_GUIDATE_ARRAY:
//                            break;
//
//                        case SGridConsts.FILTER_DATA_TYPE_INT_ARRAY:
//                            filterValue.setValue(SLibUtils.textExplodeAsIntArray((String) xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).getValue(), "-"));
//                            moFiltersMap.put(filterValue.getFilterType(), filterValue);
//                            break;
//
//                        case SGridConsts.FILTER_DATA_TYPE_BOOL:
//                            filterValue.setValue(Boolean.parseBoolean(xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).getValue().toString()));
//                            moFiltersMap.put(filterValue.getFilterType(), filterValue);
//
//                            if (filterValue.getFilterType() == SGridConsts.FILTER_DELETED) {
//                                jtbFilterDeleted.setSelected(Boolean.parseBoolean(xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).getValue().toString()));
//                            }
//                            break;
//
//                        default:
//                            miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
//                    }
//                }
//            }
//        }
//        catch (Exception e) {
//            SLibUtils.showException(this, e);
//        }
//    }

    protected void computeUserGui() {
        boolean found = false;
        ArrayList<SGridColumnView> defaultGridColumns = null;
        SXmlGridXml gridXml = new SXmlGridXml(SGridConsts.GRID_PANE_VIEW);

        miSortKeysList.clear();
        mbClearSettingsNeeded = false;

        try {
            gridXml.processXml(miUserGui.getGui());

            //jtbAutoReload.setSelected((Boolean) gridXml.getAttribute(SXmlGridXml.ATT_AUTO_RELOAD).getValue());

            for (SXmlElement element : gridXml.getXmlElements()) {
                if (element instanceof SXmlColumnView) {
                    // Columns:

                    SXmlColumnView xmlColumn = (SXmlColumnView) element;
                    SGridColumnView gridColumn = new SGridColumnView(
                            (Integer) xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_TYPE).getValue(),
                            (String) xmlColumn.getAttribute(SXmlColumnView.ATT_FIELD_NAME).getValue(),
                            (String) xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_TITLE).getValue(),
                            (Integer) xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_WIDTH).getValue());
                    gridColumn.setSumApplying((Boolean) xmlColumn.getAttribute(SXmlColumnView.ATT_IS_SUM_APPLYING).getValue());

                    if (!xmlColumn.getXmlElements().isEmpty()) {
                        for (SXmlElement child : xmlColumn.getXmlElements()) {
                            if (child.getName().compareTo(SXmlRpnArgument.NAME) == 0) {
                                SXmlRpnArgument xmlRpnArgument = (SXmlRpnArgument) child;
                                SLibRpnArgument rpnArgument = null;
                                switch (SLibRpnUtils.getArgumentType((String) xmlRpnArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT_TYPE).getValue())) {
                                    case OPERAND:
                                        rpnArgument = new SLibRpnArgument(
                                                (String) xmlRpnArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT).getValue(),
                                                SLibRpnArgumentType.OPERAND);
                                        break;
                                    case OPERATOR:
                                        rpnArgument = new SLibRpnArgument(
                                                SLibRpnUtils.getOperator((String) xmlRpnArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT).getValue()),
                                                SLibRpnArgumentType.OPERATOR);
                                        break;
                                    default:
                                }
                                if (rpnArgument != null) {
                                    gridColumn.getRpnArguments().add(rpnArgument);
                                }
                            }
                        }
                    }

                    moModel.getGridColumns().add(gridColumn);
                }
                else if (element instanceof SXmlSortKey) {
                    // Sort keys:

                    SXmlSortKey xmlSortKey = (SXmlSortKey) element;
                    RowSorter.SortKey sortKey = null;
                    SortOrder sortOrder = null;
                    String sortOrderValue = (String) xmlSortKey.getAttribute(SXmlSortKey.ATT_SORT_ORDER).getValue();

                    if (sortOrderValue.compareTo(SortOrder.ASCENDING.toString()) == 0) {
                        sortOrder = SortOrder.ASCENDING;
                    }
                    else if (sortOrderValue.compareTo(SortOrder.DESCENDING.toString()) == 0) {
                        sortOrder = SortOrder.DESCENDING;
                    }
                    else {
                        sortOrder = SortOrder.UNSORTED;
                    }

                    sortKey = new RowSorter.SortKey(
                            (Integer) xmlSortKey.getAttribute(SXmlSortKey.ATT_COLUMN).getValue(),
                            sortOrder);

                    miSortKeysList.add(sortKey);
                }
            }
        }
        catch (Exception e) {
            SLibUtils.printException(this, e);
            miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_PREFS_VIEW);
            initGrid();
        }

        // Check if customized columns are equivalent to default columns:

//        defaultGridColumns = createGridColumns();
//
//        if (defaultGridColumns.size() != moModel.getGridColumns().size()) {
//            mbClearSettingsNeeded = true;
//        }
//        else {
//            for (int i = 0; i < defaultGridColumns.size(); i++) {
//                found = false;
//                for (int j = 0; j < moModel.getGridColumns().size(); j++) {
//                    if (defaultGridColumns.get(i).getFieldName().compareTo(moModel.getGridColumns().get(j).getFieldName()) == 0 &&
//                            defaultGridColumns.get(i).getColumnType() == moModel.getGridColumns().get(j).getColumnType() &&
//                            defaultGridColumns.get(i).getColumnTitle().compareTo(moModel.getGridColumns().get(j).getColumnTitle()) == 0 &&
//                            defaultGridColumns.get(i).isSumApplying() == ((SGridColumnView) moModel.getGridColumns().get(j)).isSumApplying()) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    mbClearSettingsNeeded = true;
//                    break;
//                }
//            }
//        }

        if (mbClearSettingsNeeded) {
            jbGridClearSettings.setIcon(moIconResetUpd);
        }
    }

    protected void preserveUserGui() {
        if (!mbPreventUserGuiFromSaving && jtTable != null && jtTable.getRowSorter() != null) {
            String xml = "";
            SXmlGridXml gridXml = new SXmlGridXml(SGridConsts.GRID_PANE_VIEW);
            @SuppressWarnings("unchecked")
            List<RowSorter.SortKey> sortKeys = (List<RowSorter.SortKey>) jtTable.getRowSorter().getSortKeys();

            // Grid attributes:

//            gridXml.getAttribute(SXmlGridXml.ATT_AUTO_RELOAD).setValue(jtbAutoReload.isSelected());

            // Columns:

            for (int i = 0; i < jtTable.getColumnCount(); i++) {
                SXmlColumnView xmlColumn = new SXmlColumnView();
                SGridColumnView gridColumn = (SGridColumnView) (moModel.getGridColumns().get(jtTable.convertColumnIndexToModel(i)));

                xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_TYPE).setValue(gridColumn.getColumnType());
                xmlColumn.getAttribute(SXmlColumnView.ATT_FIELD_NAME).setValue(gridColumn.getFieldName());
                xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_TITLE).setValue(gridColumn.getColumnTitle());
                xmlColumn.getAttribute(SXmlColumnView.ATT_COLUMN_WIDTH).setValue(jtTable.getColumnModel().getColumn(i).getWidth());
                xmlColumn.getAttribute(SXmlColumnView.ATT_IS_SUM_APPLYING).setValue(gridColumn.isSumApplying());
//                xmlColumn.getAttribute(SXmlColumnView.ATT_CELL_RENDERER).setValue(SLibConsts.UNDEFINED);

                for (SLibRpnArgument argument : gridColumn.getRpnArguments()) {
                    SXmlRpnArgument xmlArgument = new SXmlRpnArgument();
                    xmlArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT_TYPE).setValue(argument.getArgumentType());
                    xmlArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT).setValue(argument.getArgument());
                    xmlColumn.getXmlElements().add(xmlArgument);
                }

                gridXml.getXmlElements().add(xmlColumn);
            }

            // Sort keys:

            if (sortKeys.isEmpty()) {
                sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));    // just in case there are not sort keys
            }
            
            for (RowSorter.SortKey sortKey : sortKeys) {
                SXmlSortKey xmlSortKey = new SXmlSortKey();
                xmlSortKey.getAttribute(SXmlSortKey.ATT_COLUMN).setValue(jtTable.convertColumnIndexToView(sortKey.getColumn()));
                xmlSortKey.getAttribute(SXmlSortKey.ATT_SORT_ORDER).setValue(sortKey.getSortOrder().toString());
                gridXml.getXmlElements().add(xmlSortKey);
            }

            /* Filter user preferences temporarily disabled (Sergio Flores, 2013-12-05):
            // Filters:

            if (!moFiltersMap.isEmpty()) {
                 SGridFilterValue filterValue = null;

                for (int i = 0; i < moFiltersMap.size(); i++) {
                    filterValue = (SGridFilterValue) moFiltersMap.values().toArray()[i];

                    if (filterValue.getValue() != null) {
                        SXmlFilter xmlFilter = new SXmlFilter();
                        xmlFilter.getAttribute(SXmlFilter.ATT_FILTER_TYPE).setValue(filterValue.getFilterType());

                        switch (filterValue.getDataType()) {
                            case SGridConsts.FILTER_DATA_TYPE_DATE:
                            case SGridConsts.FILTER_DATA_TYPE_DATE_ARRAY:
                            case SGridConsts.FILTER_DATA_TYPE_GUIDATE:
                            case SGridConsts.FILTER_DATA_TYPE_GUIDATE_ARRAY:
                                break;
                            case SGridConsts.FILTER_DATA_TYPE_INT_ARRAY:
                                xmlFilter.getAttribute(SXmlFilter.ATT_DATA_TYPE).setValue(SGridConsts.FILTER_DATA_TYPE_INT_ARRAY);
                                xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).setValue(SLibUtils.textKey((int[]) filterValue.getValue()));
                                gridXml.getXmlElements().add(xmlFilter);
                                break;
                            case SGridConsts.FILTER_DATA_TYPE_BOOL:
                                xmlFilter.getAttribute(SXmlFilter.ATT_DATA_TYPE).setValue(SGridConsts.FILTER_DATA_TYPE_BOOL);
                                xmlFilter.getAttribute(SXmlFilter.ATT_VALUE).setValue(filterValue.getValue());
                                gridXml.getXmlElements().add(xmlFilter);
                                break;
                            default:
                                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                        }
                    }
                }
            }
            */

            xml = gridXml.getXmlString();
            miUserGui = miClient.saveUserGui(manUserGuiKey, xml);
        }
    }

    protected void refreshGridWithRefresh() {
        preserveUserGui();
        populateGrid(SGridConsts.REFRESH_MODE_RESET);
    }

    protected void refreshGridWithReload() {
        preserveUserGui();
        populateGrid(SGridConsts.REFRESH_MODE_RELOAD);
    }

    protected void resetGrid() {
        moModel.clearGrid();
        if (jtTable != null) {
            jtTable.invalidate();
            validate();
        }
    }

    protected void resetGridRows() {
        moModel.clearGridRows();
        if (jtTable != null) {
            jtTable.invalidate();
            validate();
        }
    }

    protected void createGridView() {
        SGridColumnView column = null;

        // Create columns in table model:

        clearGrid();

        if (miUserGui != null) {
            computeUserGui();       // customized columns added into moModel and sort keys into miSortKeysList
        }
        else {
            initSortKeys();
            createGridColumns();    // default columns added into moModel
        }

        // Create table:

        jtTable = new JTable(moModel);
        jtTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtTable.setSelectionMode(mnListSelectionModel);
        jtTable.setColumnSelectionAllowed(false);
        jtTable.setRowSorter(new TableRowSorter<AbstractTableModel>(moModel));
        jtTable.setTableHeader(new SGridHeader(jtTable.getColumnModel(), moModel.getColumnNames()));
        jtTable.getSelectionModel().addListSelectionListener(this);

        jtTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    actionMouseClicked();
                }
            }
        });

        jtTable.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                moSeeker.handleKeyPressedEvent(evt, getSeekerLocation());
                if (moSeeker.isSeekRequested()) {
                    SGridUtils.seekValue(SGridPaneView.this, moSeeker.getText());
                }
            }
        });

        for (int i = 0; i < moModel.getColumnCount(); i++) {
            column = (SGridColumnView) moModel.getGridColumns().get(i);

            jtTable.getColumnModel().getColumn(i).setPreferredWidth(column.getColumnWidth());

            if (column.getCellRenderer() != null) {
                jtTable.getColumnModel().getColumn(i).setCellRenderer(column.getCellRenderer());
            }
            else {
                jtTable.getColumnModel().getColumn(i).setCellRenderer(SGridUtils.getCellRenderer(column.getColumnType()));
            }
        }

        jtfRows.setText("0/0");
        jbGridSaveCsv.setEnabled(false);

        jspScrollPane.setViewportView(jtTable);
    }

    protected void readGridData() {
        int i = 0;
        int col = 0;
        int row = 0;
        int dataType = SLibConsts.UNDEFINED;
        int[] key = null;
        int sumInt = 0;
        long sumLng = 0;
        double sumDbl = 0;
        boolean rpnApplying = false;
        boolean sumApplying = false;
        boolean dataAvailable = false;
        boolean[] colsWithRpn = null;
        boolean[] colsWithSum = null;
        Class colClass = null;
        ResultSet resultSet = null;
        SGridColumnView gridColumnView = null;
        SGridRowView gridRowView = null;

        try {
            miClient.getFrame().getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));

            createGridView();
            prepareSqlQuery();

            colsWithRpn = new boolean[moModel.getColumnCount()];
            for (col = 0; col < moModel.getColumnCount(); col++) {
                if (!moModel.getGridColumns().get(col).getRpnArguments().isEmpty()) {
                    dataType = SGridUtils.getDataType(moModel.getGridColumns().get(col).getColumnType());
                    if (dataType != SLibConsts.DATA_TYPE_INT && dataType != SLibConsts.DATA_TYPE_DEC) {
                        throw new Exception(SLibConsts.ERR_MSG_WRONG_TYPE);
                    }
                    else {
                        rpnApplying = true;
                        colsWithRpn[col] = true;
                    }
                }
            }

            colsWithSum = new boolean[moModel.getColumnCount()];
            for (col = 0; col < moModel.getColumnCount(); col++) {
                if (((SGridColumnView) moModel.getGridColumns().get(col)).isSumApplying()) {
                    dataType = SGridUtils.getDataType(moModel.getGridColumns().get(col).getColumnType());
                    if (dataType != SLibConsts.DATA_TYPE_INT && dataType != SLibConsts.DATA_TYPE_DEC) {
                        throw new Exception(SLibConsts.ERR_MSG_WRONG_TYPE);
                    }
                    else {
                        sumApplying = true;
                        colsWithSum[col] = true;
                    }
                }
            }

            resultSet = miClient.getSession().getStatement().executeQuery(msSql);
            while (resultSet.next()) {
                key = new int[moPaneSettings.getPrimaryKeyLength()];
                for (i = 0; i < moPaneSettings.getPrimaryKeyLength(); i++) {
                    key[i] = resultSet.getInt(SDbConsts.FIELD_ID + (i + 1));
                }

                gridRowView = new SGridRowView(key, resultSet.getString(SDbConsts.FIELD_CODE), resultSet.getString(SDbConsts.FIELD_NAME));

                if (moPaneSettings.getTypeKeyLength() == 0) {
                    key = null;
                }
                else {
                    key = new int[moPaneSettings.getTypeKeyLength()];
                    for (i = 0; i < moPaneSettings.getTypeKeyLength(); i++) {
                        key[i] = resultSet.getInt(SDbConsts.FIELD_TYPE_ID + (i + 1));
                    }

                    gridRowView.setRowRegistryTypeKey(key);
                    gridRowView.setRowRegistryType(resultSet.getString(SDbConsts.FIELD_TYPE));
                }

                if (moPaneSettings.isDateApplying()) {
                    gridRowView.setRowDate(resultSet.getDate(SDbConsts.FIELD_DATE));
                }

                if (moPaneSettings.isUpdatableApplying()) {
                    gridRowView.setUpdatable(resultSet.getBoolean(SDbConsts.FIELD_CAN_UPD));
                }

                if (moPaneSettings.isDisableableApplying()) {
                    gridRowView.setDisableable(resultSet.getBoolean(SDbConsts.FIELD_CAN_DIS));
                    gridRowView.setDisabled(resultSet.getBoolean(SDbConsts.FIELD_IS_DIS));
                }

                if (moPaneSettings.isDeletableApplying()) {
                    gridRowView.setDeletable(resultSet.getBoolean(SDbConsts.FIELD_CAN_DEL));
                    gridRowView.setDeleted(resultSet.getBoolean(SDbConsts.FIELD_IS_DEL));
                }

                if (moPaneSettings.isDisabledApplying()) {
                    gridRowView.setDisabled(resultSet.getBoolean(SDbConsts.FIELD_IS_DIS));
                }

                if (moPaneSettings.isDeletedApplying()) {
                    gridRowView.setDeleted(resultSet.getBoolean(SDbConsts.FIELD_IS_DEL));
                }

                if (moPaneSettings.isSystemApplying()) {
                    gridRowView.setRowSystem(resultSet.getBoolean(SDbConsts.FIELD_IS_SYS));
                }

                if (moPaneSettings.isUserApplying()) {
                    gridRowView.setFkUserId(resultSet.getInt(SDbConsts.FIELD_USER_USR_ID));
                    gridRowView.setTsUser(resultSet.getDate(SDbConsts.FIELD_USER_USR_TS));
                    gridRowView.setUser(resultSet.getString(SDbConsts.FIELD_USER_USR_NAME));
                }

                if (moPaneSettings.isUserInsertApplying()) {
                    gridRowView.setFkUserInsertId(resultSet.getInt(SDbConsts.FIELD_USER_INS_ID));
                    gridRowView.setTsUserInsert(resultSet.getDate(SDbConsts.FIELD_USER_INS_TS));
                    gridRowView.setUserInsert(resultSet.getString(SDbConsts.FIELD_USER_INS_NAME));
                }

                if (moPaneSettings.isUserUpdateApplying()) {
                    gridRowView.setFkUserUpdateId(resultSet.getInt(SDbConsts.FIELD_USER_UPD_ID));
                    gridRowView.setTsUserUpdate(resultSet.getDate(SDbConsts.FIELD_USER_UPD_TS));
                    gridRowView.setUserUpdate(resultSet.getString(SDbConsts.FIELD_USER_UPD_NAME));
                }

                // Read grid row values:

                for (col = 0; col < moModel.getColumnCount(); col++) {
                    gridColumnView = (SGridColumnView) moModel.getGridColumns().get(col);

                    if (colsWithRpn[col]) {
                        gridRowView.getValues().add((double) 0);
                    }
                    else {
                        colClass = SGridUtils.getDataTypeClass(gridColumnView.getColumnType());

                        if (colClass == Long.class) {
                            gridRowView.getValues().add(resultSet.getLong(gridColumnView.getFieldName()));
                        }
                        else if (colClass == Integer.class) {
                            gridRowView.getValues().add(resultSet.getInt(gridColumnView.getFieldName()));
                        }
                        else if (colClass == Double.class) {
                            gridRowView.getValues().add(resultSet.getDouble(gridColumnView.getFieldName()));
                        }
                        else if (colClass == Float.class) {
                            gridRowView.getValues().add(resultSet.getFloat(gridColumnView.getFieldName()));
                        }
                        else if (colClass == Boolean.class) {
                            gridRowView.getValues().add(resultSet.getBoolean(gridColumnView.getFieldName()));
                        }
                        else if (colClass == String.class) {
                            gridRowView.getValues().add(resultSet.getString(gridColumnView.getFieldName()));
                        }
                        else if (colClass == Date.class) {
                            switch (gridColumnView.getColumnType()) {
                                case SGridConsts.COL_TYPE_DATE:
                                    gridRowView.getValues().add(resultSet.getDate(gridColumnView.getFieldName()));
                                    break;
                                case SGridConsts.COL_TYPE_DATE_DATETIME:
                                    gridRowView.getValues().add(resultSet.getTimestamp(gridColumnView.getFieldName()));
                                    break;
                                case SGridConsts.COL_TYPE_DATE_TIME:
                                    gridRowView.getValues().add(resultSet.getTime(gridColumnView.getFieldName()));
                                    break;
                                default:
                                    throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                            }
                        }
                        else {
                            throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                        }
                    }
                }

                // Read aswell grid row complements if any:

                if (moColumnComplementsMap.size() > 0) {
                    for (Integer complementKey : moColumnComplementsMap.keySet()) {

                        colClass = SGridUtils.getDataTypeClass(moColumnComplementsMap.get(complementKey));

                        if (colClass == Long.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getLong(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == Integer.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getInt(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == Double.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getDouble(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == Float.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getFloat(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == Boolean.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getBoolean(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == String.class) {
                            gridRowView.getComplementsMap().put(complementKey, resultSet.getString(SDbConsts.FIELD_COMP + complementKey));
                        }
                        else if (colClass == Date.class) {
                            switch (moColumnComplementsMap.get(complementKey)) {
                                case SGridConsts.COL_TYPE_DATE:
                                    gridRowView.getComplementsMap().put(complementKey, resultSet.getDate(SDbConsts.FIELD_COMP + complementKey));
                                    break;
                                case SGridConsts.COL_TYPE_DATE_DATETIME:
                                    gridRowView.getComplementsMap().put(complementKey, resultSet.getTimestamp(SDbConsts.FIELD_COMP + complementKey));
                                    break;
                                case SGridConsts.COL_TYPE_DATE_TIME:
                                    gridRowView.getComplementsMap().put(complementKey, resultSet.getTime(SDbConsts.FIELD_COMP + complementKey));
                                    break;
                                default:
                                    throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                            }
                        }
                        else {
                            throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
                        }
                    }
                }

                moModel.getGridRows().add(gridRowView);
                dataAvailable = true;
            }

            if (rpnApplying) {
                SGridUtils.computeRpn(moModel);
            }

            computeGridData(); // further data prcessing, when needdd

            if (sumApplying && dataAvailable) {
                gridRowView = new SGridRowView(null, "", "");
                gridRowView.setRowType(SGridConsts.ROW_TYPE_SUM);

                for (col = 0; col < moModel.getColumnCount(); col++) {
                    if (!mbShowSums || !colsWithSum[col]) {
                        gridRowView.getValues().add(null);
                    }
                    else {
                        switch (SGridUtils.getDataType(moModel.getGridColumns().get(col).getColumnType())) {
                            case SLibConsts.DATA_TYPE_INT:
                                if (SGridUtils.getDataTypeClass(moModel.getGridColumns().get(col).getColumnType()) == Long.class) {
                                    sumLng = 0;
                                    for (row = 0; row < moModel.getRowCount(); row++) {
                                        sumLng += ((Number) moModel.getValueAt(row, col)).longValue();
                                    }
                                    gridRowView.getValues().add(sumLng);
                                }
                                else {
                                    sumInt = 0;
                                    for (row = 0; row < moModel.getRowCount(); row++) {
                                        sumInt += ((Number) moModel.getValueAt(row, col)).intValue();
                                    }
                                    gridRowView.getValues().add(sumInt);
                                }
                                break;
                            case SLibConsts.DATA_TYPE_DEC:
                                sumDbl = 0;
                                for (row = 0; row < moModel.getRowCount(); row++) {
                                    sumDbl += ((Number) moModel.getValueAt(row, col)).doubleValue();
                                }
                                gridRowView.getValues().add(sumDbl);
                                break;
                            default:
                        }
                    }
                }

                moModel.getGridRows().add(gridRowView);
            }
            
            mnRenderAttempts = 0; // clear count, data already read
        }
        catch (SQLException e) {
            SLibUtils.showException(this, e);
            SLibUtils.printSqlQuery(this, msSql);
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        finally {
            miClient.getFrame().getContentPane().setCursor(Cursor.getDefaultCursor());
            
            try {
                jtTable.getRowSorter().setSortKeys(miSortKeysList);
            }
            catch (Exception e) {
                SLibUtils.printException(this, e);
                miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_PREFS_VIEW);
                initGrid();
            }

            if (dataAvailable) {
                jbGridSaveCsv.setEnabled(true);
                setSelectedGridRow(0);
            }
        }
    }

    protected Point getSeekerLocation() {
        Point point = jspScrollPane.getLocationOnScreen();

        point.y += jtTable.getTableHeader().getHeight();

        return point;
    }

    protected void applyUserPrivileges() {
        if (mnPrivilegeView != SLibConsts.UNDEFINED) {
            for (SGuiComponentGui c : maComponentGuis) {
                c.getComponentGui().setEnabled(mnUserLevelAccess >= c.getUserPrivilegesMap().get(mnPrivilegeView));
            }
        }

        jbRowNew.setEnabled(mbApplyNew && (mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView) >= SUtilConsts.LEV_CAPTURE));
        jbRowCopy.setEnabled(mbApplyCopy && (mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView) >= SUtilConsts.LEV_CAPTURE));
        jbRowEdit.setEnabled(mbApplyEdit && (mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView) >= SUtilConsts.LEV_AUTHOR));
        jbRowDisable.setEnabled(mbApplyDisable && (mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView) >= SUtilConsts.LEV_MANAGER));
        jbRowDelete.setEnabled(mbApplyDelete && (mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().getPrivilegeLevel(mnPrivilegeView) >= SUtilConsts.LEV_MANAGER));
    }

    public void actionMouseClicked() {
        actionRowEdit();
    }

    public void actionRowNew() {
        if (jbRowNew.isEnabled()) {
            miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, moFormParams);
            moFormParams = null;
        }
    }

    public void actionRowCopy() {
        if (jbRowCopy.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();
                SGuiParams params = null;

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else {
                    params = new SGuiParams(getSelectedGridRow().getRowPrimaryKey(), true);
                    miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, params);
                }
            }
        }
    }

    public void actionRowEdit() {
        actionRowEdit(false);
    }

    public void actionRowEdit(boolean showSystemRegistries) {
        if (jbRowEdit.isEnabled()) {
            if (jtTable.getSelectedRowCount() != 1) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
            }
            else {
                SGridRowView gridRow = (SGridRowView) getSelectedGridRow();
                SGuiParams params = null;

                if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                    miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                }
                else if (!showSystemRegistries && gridRow.isRowSystem()) {
                    miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_IS_SYSTEM);
                }
                else if (!gridRow.isUpdatable()) {
                    miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_NON_UPDATABLE);
                }
                else if (moPaneSettings.isUserInsertApplying() && mnUserLevelAccess == SUtilConsts.LEV_AUTHOR && gridRow.getFkUserInsertId() != miClient.getSession().getUser().getPkUserId()) {
                    miClient.showMsgBoxWarning(SDbConsts.MSG_REG_DENIED_RIGHT);
                }
                else {
                    params = moFormParams != null ? moFormParams : new SGuiParams();
                    params.setKey(gridRow.getRowPrimaryKey());

                    miClient.getSession().getModule(mnModuleType, mnModuleSubtype).showForm(mnGridType, mnGridSubtype, params);
                    moFormParams = null;
                }
            }
        }
    }

    public void actionRowDisable() {
        if (jbRowDisable.isEnabled()) {
            if (jtTable.getSelectedRowCount() == 0) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROWS);
            }
            else if (miClient.showMsgBoxConfirm(SGridConsts.MSG_CONFIRM_REG_DIS) == JOptionPane.YES_OPTION) {
                boolean updates = false;
                SGridRow[] gridRows = getSelectedGridRows();

                for (SGridRow gridRow : gridRows) {
                    if (((SGridRowView) gridRow).getRowType() != SGridConsts.ROW_TYPE_DATA) {
                        miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                    }
                    else if (((SGridRowView) gridRow).isRowSystem()) {
                        miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_IS_SYSTEM);
                    }
                    else if (!((SGridRowView) gridRow).isDisableable()) {
                        miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_NON_DISABLEABLE);
                    }
                    else {
                        if (miClient.getSession().getModule(mnModuleType, mnModuleSubtype).disableRegistry(mnGridType, gridRow.getRowPrimaryKey()) == SDbConsts.SAVE_OK) {
                            updates = true;
                        }
                    }
                }

                if (updates) {
                    miClient.getSession().notifySuscriptors(mnGridType);
                }
            }
        }
    }

    public void actionRowDelete() {
        if (jbRowDelete.isEnabled()) {
            if (jtTable.getSelectedRowCount() == 0) {
                miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROWS);
            }
            else if (miClient.showMsgBoxConfirm(SGridConsts.MSG_CONFIRM_REG_DEL) == JOptionPane.YES_OPTION) {
                boolean updates = false;
                SGridRow[] gridRows = getSelectedGridRows();

                for (SGridRow gridRow : gridRows) {
                    if (((SGridRowView) gridRow).getRowType() != SGridConsts.ROW_TYPE_DATA) {
                        miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
                    }
                    else if (((SGridRowView) gridRow).isRowSystem()) {
                        miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_IS_SYSTEM);
                    }
                    else if (!((SGridRowView) gridRow).isDeletable()) {
                        miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_NON_DELETABLE);
                    }
                    else {
                        if (miClient.getSession().getModule(mnModuleType, mnModuleSubtype).deleteRegistry(mnGridType, gridRow.getRowPrimaryKey()) == SDbConsts.SAVE_OK) {
                            updates = true;
                        }
                    }
                }

                if (updates) {
                    miClient.getSession().notifySuscriptors(mnGridType);
                }
            }
        }
    }

    public void actionGridSaveCsv() {
        if (jbGridSaveCsv.isEnabled()) {
            SGridUtils.saveCsv(this, msTitle);
        }
    }

    public void actionGridClearSettings() {
        if (jbGridClearSettings.isEnabled()) {
            if (miClient.showMsgBoxConfirm(SGridConsts.MSG_CONFIRM_RESET_SETTINGS) == JOptionPane.YES_OPTION) {
                initGrid();

                if (mbClearSettingsNeeded) {
                    mbClearSettingsNeeded = false;
                    jbGridClearSettings.setIcon(moIconReset);
                }
            }
        }
    }

    public void actionGridReload() {
        if (jbGridReload.isEnabled()) {
            refreshGridWithRefresh();
        }
    }

    public void actionGridSeekValue() {
        if (jtTable.getRowCount() > 0) {
            moSeeker.openSeeker(getSeekerLocation());
            if (moSeeker.isSeekRequested()) {
                SGridUtils.seekValue(this, moSeeker.getText());
            }
        }
    }
    
    public void grabFocusToSearch() {
        jtfGridSearch.requestFocusInWindow();
    }

    public void actionGridSearchValue() {
        String text = jtfGridSearch.getText().trim(); // just a simple trim
        
        if (!text.isEmpty() && jtTable.getRowCount() > 0) {
            SGridUtils.searchValue(this, text, true);
            jbGridSearchNext.requestFocusInWindow();
        }
    }

    public void actionGridSearchNextValue() {
        String text = jtfGridSearch.getText().trim(); // just a simple trim
        
        if (!text.isEmpty() && jtTable.getRowCount() > 0) {
            SGridUtils.searchValue(this, text, false);
        }
    }
    
    public void actionGridSearchNextColValue() {
        String text = jtfGridSearch.getText().trim(); // just a simple trim
        
        if (!text.isEmpty() && jtTable.getRowCount() > 0) {
            SGridUtils.searchValueCol(this, text);
        }
    }

    public void actionToggleFilterDeleted() {
        if (jtbFilterDeleted.isEnabled()) {
            moFiltersMap.put(SGridConsts.FILTER_DELETED, jtbFilterDeleted.isSelected());
            refreshGridWithRefresh();
        }
    }

    public void actionToggleAutoReload() {
        if (jtbAutoReload.isEnabled()) {
            // By now this method has not any code
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jbGridClearSettings;
    private javax.swing.JButton jbGridReload;
    private javax.swing.JButton jbGridSaveCsv;
    private javax.swing.JButton jbGridSearchNext;
    private javax.swing.JButton jbGridSearchNextCol;
    protected javax.swing.JButton jbRowCopy;
    protected javax.swing.JButton jbRowDelete;
    protected javax.swing.JButton jbRowDisable;
    protected javax.swing.JButton jbRowEdit;
    protected javax.swing.JButton jbRowNew;
    private javax.swing.JPanel jpCommands;
    private javax.swing.JPanel jpCommandsCustom;
    private javax.swing.JPanel jpCommandsCustomCenter;
    private javax.swing.JPanel jpCommandsCustomLeft;
    private javax.swing.JPanel jpCommandsCustomRight;
    private javax.swing.JPanel jpCommandsSys;
    private javax.swing.JPanel jpCommandsSysCenter;
    private javax.swing.JPanel jpCommandsSysLeft;
    private javax.swing.JPanel jpCommandsSysRight;
    private javax.swing.JPanel jpStatus;
    private javax.swing.JPanel jpStatusCenter;
    private javax.swing.JPanel jpStatusLeft;
    private javax.swing.JPanel jpStatusRight;
    protected javax.swing.JScrollPane jspScrollPane;
    protected javax.swing.JTable jtTable;
    protected javax.swing.JToggleButton jtbAutoReload;
    protected javax.swing.JToggleButton jtbFilterDeleted;
    private javax.swing.JTextField jtfGridSearch;
    private javax.swing.JTextField jtfRows;
    // End of variables declaration//GEN-END:variables

    /*
     * Abstract methods
     */

    public abstract void prepareSqlQuery();
    public abstract void createGridColumns();
    public abstract void defineSuscriptions();

    /*
     * Public methods
     */

    public void setFormParams(SGuiParams params) { moFormParams = params; }
    public void setApplyNew(boolean b) { mbApplyNew = b; }
    public void setApplyCopy(boolean b) { mbApplyCopy = b; }
    public void setApplyEdit(boolean b) { mbApplyEdit = b; }
    public void setApplyDisable(boolean b) { mbApplyDisable = b; }
    public void setApplyDelete(boolean b) { mbApplyDelete = b; }
    public void setShowSums(boolean b) { mbShowSums = b; }

    public int getGridViewType() { return mnGridViewType; }
    public String getTitle() { return msTitle; }
    public String getSql() { return msSql; }
    public HashMap<Integer, Integer> getColumnComplementsMap() { return moColumnComplementsMap; }
    public HashMap<Integer, Object> getFiltersMap() { return moFiltersMap; }

    public int getPrivilegeView() { return mnPrivilegeView; }
    public int getUserLevelAccess() { return mnUserLevelAccess; }
    public boolean getApplyNew() { return  mbApplyNew; }
    public boolean getApplyCopy() { return  mbApplyCopy; }
    public boolean getApplyEdit() { return  mbApplyEdit; }
    public boolean getApplyDisable() { return  mbApplyDisable; }
    public boolean getApplyDelete() { return  mbApplyDelete; }
    public boolean getShowSums() { return mbShowSums; }
    public ArrayList<SGuiComponentGui> getComponentGuis() { return maComponentGuis; }

    public Object getFilterValue(int typeFilter) {
        Object obj = null;

        if (!moFiltersMap.isEmpty()) {
            obj = moFiltersMap.get(typeFilter);
        }

        if (obj != null) {
            obj = moFiltersMap.get(typeFilter);
        }

        return obj;
    }

    public boolean hasUserPrivilegeView() {
        return mnPrivilegeView == SLibConsts.UNDEFINED || miClient.getSession().getUser().hasPrivilege(mnPrivilegeView);
    }

    /**
     * Override this method to compute grid data before it is rendered.
     */
    public void computeGridData() {
        
    }

    public void populateGrid(final int refreshMode) {
        int index = -1;
        int posVer = 0;
        int posHor = 0;

        if (refreshMode == SGridConsts.REFRESH_MODE_RELOAD && jtTable != null) {
            index = jtTable.getSelectedRow();
            posVer = jspScrollPane.getVerticalScrollBar().getValue();
            posHor = jspScrollPane.getHorizontalScrollBar().getValue();
        }

        if (mnRenderAttempts++ > 1) {
            miClient.showMsgBoxWarning("Se alcanzó el número máximo de intentos para mostrar esta vista.");
            return;
        }
        
        readGridData();

        if (refreshMode == SGridConsts.REFRESH_MODE_RELOAD) {
            jspScrollPane.validate();
            jspScrollPane.getVerticalScrollBar().setValue(
                    posVer < jspScrollPane.getVerticalScrollBar().getMaximum() ? posVer : jspScrollPane.getVerticalScrollBar().getMaximum());
            jspScrollPane.getHorizontalScrollBar().setValue(
                    posHor < jspScrollPane.getHorizontalScrollBar().getMaximum() ? posHor : jspScrollPane.getHorizontalScrollBar().getMaximum());

            if (index != -1) {
                if (index > jtTable.getRowCount()) {
                    index = jtTable.getRowCount() - 1;
                }

                setSelectedGridRow(index);
            }
        }

        if (mbReloadNeeded) {
            mbReloadNeeded = false;
            jbGridReload.setIcon(moIconReload);
        }
    }

    public void triggerSuscription(final int suscription) {
        if (moSuscriptionsSet.contains(suscription)) {
            if (jtbAutoReload.isSelected()) {
                refreshGridWithReload();
            }
            else {
                mbReloadNeeded = true;
                jbGridReload.setIcon(moIconReloadUpd);
            }
        }
    }

    public void paneViewClosed() {
        preserveUserGui();
    }

    public void setRowButtonsEnabled(boolean enabled) {
        setRowButtonsEnabled(enabled, enabled, enabled, enabled, enabled);
    }

    public void setRowButtonsEnabled(boolean newEnabled, boolean editEnabled, boolean copyEnabled, boolean disableEnabled, boolean deleteEnabled) {
        mbApplyNew = newEnabled;
        mbApplyCopy = copyEnabled;
        mbApplyEdit = editEnabled;
        mbApplyDisable = disableEnabled;
        mbApplyDelete = deleteEnabled;

        applyUserPrivileges();
    }

    public void setSelectedGridRowInterval(final int row0, final int row1) {
        int value = 0;

        if (row0 >= 0 && row0 < jtTable.getRowCount() && row1 >= 0 && row1 < jtTable.getRowCount() && row0 <= row1) {
            jtTable.setRowSelectionInterval(row0, row1);

            value = row0 * jtTable.getRowHeight();
            if (value < jspScrollPane.getVerticalScrollBar().getValue() || value > jspScrollPane.getVerticalScrollBar().getValue() + jspScrollPane.getVerticalScrollBar().getVisibleAmount()) {
                jspScrollPane.getVerticalScrollBar().setValue(value);
            }
        }
    }

    public SGridRow[] getSelectedGridRows() {
        int[] rows = jtTable.getSelectedRows();
        SGridRow[] gridRows = null;

        if (rows != null) {
            gridRows = new SGridRow[rows.length];

            for (int i = 0; i < rows.length; i++) {
                gridRows[i] = moModel.getGridRows().get(jtTable.convertRowIndexToModel(rows[i]));
            }
        }

        return gridRows;
    }

    public JPanel getPanelCommandsSys(final int guiPanel) {
        JPanel panel = null;

        switch(guiPanel) {
            case SGuiConsts.PANEL_LEFT:
                panel = jpCommandsSysLeft;
                break;
            case SGuiConsts.PANEL_CENTER:
                panel = jpCommandsSysCenter;
                break;
            case SGuiConsts.PANEL_RIGHT:
                panel = jpCommandsSysRight;
                break;
            default:
        }

        return panel;
    }

    public JPanel getPanelCommandsCustom(final int guiPanel) {
        JPanel panel = null;

        switch(guiPanel) {
            case SGuiConsts.PANEL_LEFT:
                panel = jpCommandsCustomLeft;
                break;
            case SGuiConsts.PANEL_CENTER:
                panel = jpCommandsCustomCenter;
                break;
            case SGuiConsts.PANEL_RIGHT:
                panel = jpCommandsCustomRight;
                break;
            default:
        }

        return panel;
    }

    public JPanel getPanelStatus(final int guiPanel) {
        JPanel panel = null;

        switch(guiPanel) {
            case SGuiConsts.PANEL_LEFT:
                panel = jpStatusLeft;
                break;
            case SGuiConsts.PANEL_CENTER:
                panel = jpStatusCenter;
                break;
            case SGuiConsts.PANEL_RIGHT:
                panel = jpStatusRight;
                break;
            default:
        }

        return panel;
    }
    
    /**
     * Check if a row of type date is selected, otherwise inform user in GUI.
     * @return 
     */
    public boolean isRowDataSelected() {
        boolean isSelected = false;
        
        if (jtTable.getSelectedRowCount() != 1) {
            miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
        }
        else {
            SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

            if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
            }
            else {
                isSelected = true;
            }
        }
        
        return isSelected;
    }
    
    /**
     * Check if a row of type date and updatable is selected, otherwise inform user in GUI.
     * @return 
     */
    public boolean isRowDataUpdatableSelected() {
        boolean isSelected = false;
        
        if (jtTable.getSelectedRowCount() != 1) {
            miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
        }
        else {
            SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

            if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
            }
            else if (!gridRow.isUpdatable()) {
                miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_NON_UPDATABLE);
            }
            else {
                isSelected = true;
            }
        }
        
        return isSelected;
    }
    
    /**
     * Check if a row of type date, non-system and updatable is selected, otherwise inform user in GUI.
     * @return 
     */
    public boolean isRowDataNonSystemUpdatableSelected() {
        boolean isSelected = false;
        
        if (jtTable.getSelectedRowCount() != 1) {
            miClient.showMsgBoxInformation(SGridConsts.MSG_SELECT_ROW);
        }
        else {
            SGridRowView gridRow = (SGridRowView) getSelectedGridRow();

            if (gridRow.getRowType() != SGridConsts.ROW_TYPE_DATA) {
                miClient.showMsgBoxWarning(SGridConsts.ERR_MSG_ROW_TYPE_DATA);
            }
            else if (gridRow.isRowSystem()) {
                miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_IS_SYSTEM);
            }
            else if (!gridRow.isUpdatable()) {
                miClient.showMsgBoxWarning(SDbConsts.MSG_REG_ + gridRow.getRowName() + SDbConsts.MSG_REG_NON_UPDATABLE);
            }
            else {
                isSelected = true;
            }
        }
        
        return isSelected;
    }

    /*
     * Overriden methods
     */

    @Override
    public SGuiClient getClient() {
        return miClient;
    }

    @Override
    public int getGridPaneType() {
        return SGridConsts.GRID_PANE_VIEW;
    }

    @Override
    public int getGridType() {
        return mnGridType;
    }

    @Override
    public int getGridSubtype() {
        return mnGridSubtype;
    }

    @Override
    public int getGridMode() {
        return mnGridMode;
    }

    @Override
    public int getGridSubmode() {
        return mnGridSubmode;
    }

    @Override
    public SGridModel getModel() {
        return moModel;
    }

    @Override
    public JTable getTable() {
        return jtTable;
    }

    @Override
    public JScrollPane getScrollPane() {
        return jspScrollPane;
    }

    @Override
    public void clearGrid() {
        resetGrid();
        renderGrid();
    }

    @Override
    public void clearGridRows() {
        resetGridRows();
        renderGridRows();
    }

    @Override
    public void renderGrid() {
        moModel.renderGrid();
    }

    @Override
    public void renderGridRows() {
        moModel.renderGridRows();
    }

    @Override
    public void initSortKeys() {
        miSortKeysList.clear();
        miSortKeysList.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
    }

    @Override
    public void initSortKeysDescending() {
        miSortKeysList.clear();
        miSortKeysList.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
    }

    @Override
    public void putFilter(final int filterType, final Object filterValue) {
        moFiltersMap.put(filterType, filterValue);
        refreshGridWithRefresh();
    }

    @Override
    public void setGridRow(final SGridRow gridRow, final int row) {
        moModel.getGridRows().setElementAt(gridRow, jtTable.convertRowIndexToModel(row));
    }

    @Override
    public void setGridColumn(final SGridColumn gridColumn, final int col) {
        moModel.getGridColumns().setElementAt(gridColumn, jtTable.convertColumnIndexToModel(col));
    }

    @Override
    public SGridRow getGridRow(final int row) {
        return moModel.getGridRows().get(jtTable.convertRowIndexToModel(row));
    }

    @Override
    public SGridColumn getGridColumn(final int col) {
        return moModel.getGridColumns().get(jtTable.convertColumnIndexToModel(col));
    }

    @Override
    public void addGridRow(final SGridRow gridRow) {
        moModel.getGridRows().add(gridRow);
    }

    @Override
    public void addGridColumn(final SGridColumn gridColumn) {
        moModel.getGridColumns().add(gridColumn);
    }

    @Override
    public void insertGridRow(final SGridRow gridRow, final int row) {
        moModel.getGridRows().insertElementAt(gridRow, jtTable.convertRowIndexToModel(row));
    }

    @Override
    public void insertGridColumn(final SGridColumn gridColumn, final int col) {
        moModel.getGridColumns().insertElementAt(gridColumn, jtTable.convertColumnIndexToModel(col));
    }

    @Override
    public SGridRow removeGridRow(final int row) {
        return moModel.getGridRows().remove(jtTable.convertRowIndexToModel(row));
    }

    @Override
    public SGridColumn removeGridColumn(final int col) {
        return moModel.getGridColumns().remove(jtTable.convertColumnIndexToModel(col));
    }

    @Override
    public void setSelectedGridColumn(final int col) {
        if (col >= 0 && col < jtTable.getColumnCount()) {
            jtTable.setColumnSelectionInterval(col, col);
        }
    }

    @Override
    public void setSelectedGridRow(final int row) {
        int value = 0;

        if (row >= 0 && row < jtTable.getRowCount()) {
            jtTable.setRowSelectionInterval(row, row);

            value = row * jtTable.getRowHeight();
            if (value < jspScrollPane.getVerticalScrollBar().getValue() || value > jspScrollPane.getVerticalScrollBar().getValue() + jspScrollPane.getVerticalScrollBar().getVisibleAmount()) {
                jspScrollPane.getVerticalScrollBar().setValue(value);
            }
        }
    }

    @Override
    public SGridRow getSelectedGridRow() {
        return jtTable.getSelectedRow() == -1 ? null : moModel.getGridRows().get(jtTable.convertRowIndexToModel(jtTable.getSelectedRow()));
    }

    @Override
    public void setRowValueAtFieldName(final Object value, final int row, final String fieldName) {
        int modelColumnIndex = moModel.getColumnIndexAtFieldName(fieldName);

        if (modelColumnIndex != -1) {
            moModel.setValueAt(value, row, modelColumnIndex);
            jtTable.setValueAt(value, row, jtTable.convertColumnIndexToView(modelColumnIndex));
        }
    }

    @Override
    public Object getRowValueAtFieldName(final int row, final String fieldName) {
        return moModel.getValueAtFieldName(row, fieldName);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        jtfRows.setText(SLibUtils.DecimalFormatInteger.format(jtTable.getSelectedRow() + 1) + "/" + SLibUtils.DecimalFormatInteger.format(jtTable.getRowCount()));
    }
}
