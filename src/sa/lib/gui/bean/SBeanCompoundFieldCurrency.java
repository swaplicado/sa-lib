/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SBeanCompoundFieldCurrency.java
 *
 * Created on 1/10/2011, 07:44:26 PM
 */

package sa.lib.gui.bean;

import java.awt.Font;
import javax.swing.JPanel;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiCompoundField;
import sa.lib.gui.SGuiFieldDecimal;

/**
 *
 * @author Sergio Flores
 */
public class SBeanCompoundFieldCurrency extends JPanel implements SGuiCompoundField {

    protected SGuiClient miClient;
    private int[] manCurrencyKey;

    /** Creates new form SBeanCompoundFieldCurrency */
    public SBeanCompoundFieldCurrency() {
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

        moDecCurrency = new sa.lib.gui.bean.SBeanFieldDecimal();
        jtfCurrencyCode = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout(5, 0));
        add(moDecCurrency, java.awt.BorderLayout.CENTER);

        jtfCurrencyCode.setEditable(false);
        jtfCurrencyCode.setText("CUR");
        jtfCurrencyCode.setFocusable(false);
        jtfCurrencyCode.setPreferredSize(new java.awt.Dimension(35, 23));
        add(jtfCurrencyCode, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsCustom() {
        miClient = null;
        manCurrencyKey = null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jtfCurrencyCode;
    private sa.lib.gui.bean.SBeanFieldDecimal moDecCurrency;
    // End of variables declaration//GEN-END:variables

    public void setCurrencyKey(final int[] key) {
        manCurrencyKey = key;
        setCompoundText(manCurrencyKey == null ? "?" : miClient.getSession().getSessionCustom().getCurrencyCode(manCurrencyKey));
    }

    public int[] getCurrencyKey() {
        return manCurrencyKey;
    }

    @Override
    public void setCompoundFieldSettings(SGuiClient client) {
        miClient = client;
        setCurrencyKey(miClient.getSession().getSessionCustom().getLocalCurrencyKey());
    }

    @Override
    public void setCompoundText(String text) {
        jtfCurrencyCode.setText(SLibUtils.textTrim(text));
        jtfCurrencyCode.setCaretPosition(0);
    }

    @Override
    public SGuiFieldDecimal getField() {
        return moDecCurrency;
    }

    @Override
    public String getCompoundText() {
        return jtfCurrencyCode.getText();
    }

    @Override
    public void setCompoundFont(Font font) {
        super.setFont(font);
        moDecCurrency.setFont(font);
        jtfCurrencyCode.setFont(font);
    }

    @Override
    public void setEnabled(boolean enabled) {
        moDecCurrency.setEnabled(enabled);
        jtfCurrencyCode.setEnabled(enabled);
        super.setEnabled(enabled);
    }

    @Override
    public void setEditable(boolean editable) {
        moDecCurrency.setEditable(editable);
        super.setFocusable(editable);
    }

    @Override
    public boolean isEnabled() {
        return moDecCurrency.isEnabled();
    }

    @Override
    public boolean isEditable() {
        return moDecCurrency.isEditable();
    }
}
