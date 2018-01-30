/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SBeanFormDialog.java
 *
 * Created on 30/06/2011, 12:00:26 AM
 */

package sa.lib.gui.bean;

import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridPaneForm;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiField;
import sa.lib.gui.SGuiFields;
import sa.lib.gui.SGuiForm;
import sa.lib.gui.SGuiFormOwner;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;

/**
 *
 * @author Sergio Flores
 */
public abstract class SBeanFormDialog extends JDialog implements SGuiForm {

    public static JFrame OwnerFrame;

    protected SGuiClient miClient;
    protected int mnFormType;
    protected int mnFormSubtype;
    protected int mnFormResult;
    protected boolean mbFirstActivation;
    protected String msTitle;
    protected Vector<SGridPaneForm> mvFormGrids;
    protected SGuiFields moFields;
    protected SGuiFormOwner miFormOwner;

    /** Creates new form SBeanFormDialog */
    public SBeanFormDialog() {
        super(OwnerFrame, true);
        initComponents();
        initComponentsBean();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpContainer = new javax.swing.JPanel();
        jpCommand = new javax.swing.JPanel();
        jpCommandLeft = new javax.swing.JPanel();
        jpCommandCenter = new javax.swing.JPanel();
        jpCommandRight = new javax.swing.JPanel();
        jbSave = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DBeanForm");
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jpContainer.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jpContainer, java.awt.BorderLayout.CENTER);

        jpCommand.setLayout(new java.awt.BorderLayout());

        jpCommandLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        jpCommand.add(jpCommandLeft, java.awt.BorderLayout.WEST);
        jpCommand.add(jpCommandCenter, java.awt.BorderLayout.CENTER);

        jpCommandRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbSave.setText("Guardar");
        jbSave.setMaximumSize(new java.awt.Dimension(75, 23));
        jbSave.setMinimumSize(new java.awt.Dimension(75, 23));
        jbSave.setPreferredSize(new java.awt.Dimension(75, 23));
        jbSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSaveActionPerformed(evt);
            }
        });
        jpCommandRight.add(jbSave);

        jbCancel.setText("Cancelar");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });
        jpCommandRight.add(jbCancel);

        jpCommand.add(jpCommandRight, java.awt.BorderLayout.EAST);

        getContentPane().add(jpCommand, java.awt.BorderLayout.SOUTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-480)/2, (screenSize.height-300)/2, 480, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSaveActionPerformed
        actionSave();
    }//GEN-LAST:event_jbSaveActionPerformed

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        actionCancel();
    }//GEN-LAST:event_jbCancelActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        windowClosed();
    }//GEN-LAST:event_formWindowClosed

    protected void initComponentsBean() {
        miClient = null;
        mnFormType = SLibConsts.UNDEFINED;
        mnFormSubtype = SLibConsts.UNDEFINED;
        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = false;
        mvFormGrids = new Vector<SGridPaneForm>();
        moFields = new SGuiFields();
        miFormOwner = null;

        SGuiUtils.createActionMap(rootPane, this, "actionCancel", "cancel", KeyEvent.VK_ESCAPE);
    }

    protected void windowActivated() {
        if (mbFirstActivation) {
            mbFirstActivation = false;

            for (SGuiField field : moFields.getFields()) {
                if (field.isFocusable()) {
                    field.getComponent().requestFocus();
                    break;
                }
            }
        }
    }

    protected void windowClosed() {
        for (SGridPaneForm grid : mvFormGrids) {
            grid.paneViewClosed();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton jbCancel;
    protected javax.swing.JButton jbSave;
    private javax.swing.JPanel jpCommand;
    private javax.swing.JPanel jpCommandCenter;
    private javax.swing.JPanel jpCommandLeft;
    private javax.swing.JPanel jpCommandRight;
    private javax.swing.JPanel jpContainer;
    // End of variables declaration//GEN-END:variables

    /*
     * Public methods
     */

    public boolean isFormEditable() {
        return true;
    }

    public JPanel getBeanPanel() {
        return jpContainer;
    }

    public void setFormOwner(SGuiFormOwner owner) {
        miFormOwner = owner;
    }

    public void initForm() {
        // Method to be overrided when needed
    }

    public void resetForm() {
        // Method to be overrided when needed
    }

    /*
     * Overriden methods
     */

    @Override
    public int getBeanFormType() {
        return SLibConsts.UNDEFINED;
    }

    @Override
    public int getFormType() {
        return mnFormType;
    }

    @Override
    public int getFormSubtype() {
        return mnFormSubtype;
    }

    @Override
    public int getFormStatus() {
        return SGuiConsts.FORM_STATUS_EDIT;
    }

    @Override
    public int getFormResult() {
        return mnFormResult;
    }

    @Override
    public boolean canShowForm() {
        return true;
    }

    @Override
    public void setFormSettings(final SGuiClient client, final int beanFormType, final int formType, final int formSubtype, final String title) {
        miClient = client;
        mnFormType = formType;
        mnFormSubtype = formSubtype;
        msTitle = title;

        setTitle(msTitle);
    }

    @Override
    public void setFormVisible(final boolean visible) {
        setVisible(visible);
    }

    @Override
    public void setFormEditable(final boolean editable) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void updateFormControlStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract void addAllListeners();
    public abstract void removeAllListeners();
    public abstract void reloadCatalogues();
    public abstract void setRegistry(final SDbRegistry registry) throws Exception;
    public abstract SDbRegistry getRegistry() throws Exception;
    public abstract SGuiValidation validateForm();

    @Override
    public boolean isFormDataEdited() {
        return false;
    }

    @Override
    public void setValue(final int type, final Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getValue(final int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiFields getFields() {
        return moFields;
    }

    @Override
    public void actionEdit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionReadInfo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionSave() {
        if (jbSave.isEnabled()) {
            if (SGuiUtils.computeValidation(miClient, validateForm())) {
                boolean save = true;
                SDbRegistry registry = null;

                if (miFormOwner != null) {
                    try {
                        registry = getRegistry();

                        if (registry != null) {
                            if (registry.getFormAction() == SGuiConsts.FORM_ACTION_NEW) {
                                save = miFormOwner.validateRegistryNew(registry);
                            }
                            else {
                                save = miFormOwner.validateRegistryEdit(registry);
                            }
                        }
                    }
                    catch (Exception e) {
                        SLibUtils.showException(this, e);
                    }
                }

                if (save) {
                    mnFormResult = SGuiConsts.FORM_RESULT_OK;
                    dispose();
                }
            }
        }
    }

    @Override
    public void actionCancel() {
        if (jbCancel.isEnabled()) {
            mnFormResult = SGuiConsts.FORM_RESULT_CANCEL;
            dispose();
        }
    }
}
