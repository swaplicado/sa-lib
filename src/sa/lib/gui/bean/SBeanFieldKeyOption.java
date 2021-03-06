/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SBeanFieldKeyOption.java
 *
 * Created on 17/06/2011, 04:14:33 PM
 */

package sa.lib.gui.bean;

import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import sa.lib.SLibConsts;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiField;
import sa.lib.gui.SGuiFieldKey;
import sa.lib.gui.SGuiItem;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;

/**
 *
 * @author Sergio Flores
 */
public class SBeanFieldKeyOption extends JPanel implements SGuiFieldKey {

    protected String msFieldName;
    protected int[] manDefaultValue;
    protected boolean mbMandatory;
    protected SGuiField moNextField;
    protected JButton moNextButton;
    protected JButton moFormButton;
    protected int mnTab;
    protected SGuiClient miClient;

    /** Creates new form SBeanFieldKeyOption */
    public SBeanFieldKeyOption() {
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

        jcbKey = new javax.swing.JComboBox();
        jbKey = new javax.swing.JButton();

        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(new java.awt.BorderLayout(5, 0));

        jcbKey.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbKey.setPreferredSize(new java.awt.Dimension(100, 23));
        jcbKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jcbKeyKeyPressed(evt);
            }
        });
        add(jcbKey, java.awt.BorderLayout.CENTER);

        jbKey.setText("...");
        jbKey.setToolTipText("Seleccionar opción");
        jbKey.setPreferredSize(new java.awt.Dimension(23, 23));
        jbKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbKeyActionPerformed(evt);
            }
        });
        add(jbKey, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jbKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbKeyActionPerformed
        // XXX
    }//GEN-LAST:event_jbKeyActionPerformed

    private void jcbKeyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbKeyKeyPressed
        processKeyPressed(evt);
    }//GEN-LAST:event_jcbKeyKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        if (evt.getOppositeComponent() != jcbKey) {
            processFocus();
        }
    }//GEN-LAST:event_formFocusGained

    private void initComponentsCustom() {
        msFieldName = "";
        manDefaultValue = null;
        mbMandatory = true;
        moNextField = null;
        moNextButton = null;
        moFormButton = null;
        mnTab = -1;
        miClient = null;

        jcbKey.removeAllItems();
        jbKey.setToolTipText(SGuiConsts.MSG_GUI_TTT_PICK_OPTION);

        resetField();
    }

    /*
     * Class private methods:
     */

    private void processKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            if (jbKey.isEnabled()) {
                jbKey.doClick();
            }
        }
        else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (moNextButton != null && moNextButton.isEnabled()) {
                moNextButton.requestFocus();
            }
            else if (moNextField != null) {
                moNextField.processFocus();
            }
            else if (moFormButton != null && moFormButton.isEnabled()) {
                moFormButton.requestFocus();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbKey;
    private javax.swing.JComboBox jcbKey;
    // End of variables declaration//GEN-END:variables

    /*
     * Class public methods:
     */

    /*
     * Implemented and overrided methods:
     */

    @Override
    public void setFieldName(final String name) {
        msFieldName = name;
    }

    @Override
    public int getDataType() {
        return SLibConsts.DATA_TYPE_KEY;
    }

    @Override
    public int getGuiType() {
        return SGuiConsts.GUI_TYPE_KEY;
    }

    @Override
    public String getFieldName() {
        return msFieldName;
    }

    @Override
    public JComboBox getComponent() {
        return jcbKey;
    }

    @Override
    public boolean isMandatory() {
        return mbMandatory;
    }

    @Override
    public void setValue(final Object value) {
        SGuiUtils.locateItem(jcbKey, (int[]) value);
    }

    @Override
    public void setDefaultValue(final Object value) {
        manDefaultValue = (int[]) value;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        jcbKey.setEnabled(enabled);
        jbKey.setEnabled(enabled);
    }

    @Override
    public void setEditable(final boolean editable) {
        jcbKey.setEnabled(editable);
        jbKey.setEnabled(editable);
    }

    @Override
    public void setNextField(final SGuiField field) {
        moNextField = field;
    }

    @Override
    public void setNextButton(final JButton button) {
        moNextButton = button;
    }

    @Override
    public void setFormButton(final JButton button) {
        moFormButton = button;
    }

    @Override
    public void setTab(int tab) {
        mnTab = tab;
    }

    @Override
    public int getTab() {
        return mnTab;
    }

    @Override
    public boolean isEnabled() {
        return jcbKey.isEnabled();
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public boolean isFocusable() {
        return jcbKey.isEnabled() && jcbKey.isFocusable();
    }

    @Override
    public SGuiField getNextField() {
        return moNextField;
    }

    @Override
    public JButton getNextButton() {
        return moNextButton;
    }

    @Override
    public JButton getFormButton() {
        return moFormButton;
    }

    @Override
    public void processFocus() {
        if (isFocusable()) {
            jcbKey.requestFocus();
        }
        else {
            jcbKeyKeyPressed(new KeyEvent(jcbKey, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED));
        }
    }

    @Override
    public void resetField() {
        setValue(manDefaultValue);
    }

    @Override
    public SGuiValidation validateField() {
        SGuiValidation validation = new SGuiValidation();

        if (mbMandatory && jcbKey.getSelectedIndex() <= 0) {
            validation.setMessage("Se debe especificar un valor para el campo '" + msFieldName + "'.");
            validation.setComponent(this);
            validation.setTab(mnTab);
        }

        return validation;
    }

    /*
     * Implementation of SGuiFieldKey:
     */

    @Override
    public int[] getValue() {
        return ((SGuiItem) jcbKey.getSelectedItem()).getPrimaryKey();
    }

    @Override
    public int[] getDefaultValue() {
        return manDefaultValue;
    }

    @Override
    public void setKeySettings(final SGuiClient client, final String name, final boolean mandatory) {
        miClient = client;
        setFieldName(name);
        setMandatory(mandatory);
    }

    @Override
    public void setMandatory(final boolean isMandatory) {
        mbMandatory = isMandatory;
    }

    @Override
    public SGuiItem getSelectedItem() {
        Object item = jcbKey.getSelectedItem();
        return (SGuiItem) (item == null || item instanceof SGuiItem ? item : null);
    }
}
