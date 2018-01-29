/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui.bean;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class SBeanFieldKey extends JComboBox implements SGuiFieldKey {

    protected String msFieldName;
    protected int[] manDefaultValue;
    protected boolean mbMandatory;
    protected SGuiField moNextField;
    protected JButton moNextButton;
    protected JButton moFormButton;
    protected JButton moFieldButton;
    protected int mnTab;
    protected SGuiClient miClient;

    @SuppressWarnings("unchecked")
    public SBeanFieldKey() {
        msFieldName = "";
        manDefaultValue = null;
        mbMandatory = true;
        moNextField = null;
        moNextButton = null;
        moFormButton = null;
        moFieldButton = null;
        mnTab = -1;
        miClient = null;

        setPreferredSize(new Dimension(100, 23));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                processKeyPressed(evt);
            }
        });

        removeAllItems();
        addItem(new SGuiItem(""));

        resetField();
    }

    /*
     * Class private methods:
     */

    private void processKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
        else if (evt.getKeyCode() == KeyEvent.VK_F5) {
            if (moFieldButton != null && moFieldButton.isEnabled()) {
                moFieldButton.doClick();
            }
        }
    }

    /*
     * Class public methods:
     */

    public void setFieldButton(final JButton button) {
        moFieldButton = button;
        setToolTipText(button == null ? null : SGuiConsts.MSG_GUI_TTT_PICK_OPTION);
    }

    public JButton getFieldButton() {
        return moFieldButton;
    }

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
        return this;
    }

    @Override
    public boolean isMandatory() {
        return mbMandatory;
    }

    @Override
    public void setValue(final Object value) {
        SGuiUtils.locateItem(this, (int[]) value);
    }

    @Override
    public void setDefaultValue(final Object value) {
        manDefaultValue = (int[]) value;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void setEditable(final boolean editable) {
        super.setEnabled(editable);
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
        return super.isEnabled();
    }

    @Override
    public boolean isEditable() {
        return false;
    }

    @Override
    public boolean isFocusable() {
        return super.isEnabled() && super.isFocusable();
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
            this.requestFocus();
        }
        else {
            processKeyPressed(new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED));
        }
    }

    @Override
    public void resetField() {
        setValue(manDefaultValue);
    }

    @Override
    public SGuiValidation validateField() {
        SGuiValidation validation = new SGuiValidation();

        if (mbMandatory && this.getSelectedIndex() <= 0) {
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
        return ((SGuiItem) this.getSelectedItem()).getPrimaryKey();
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
        Object item = super.getSelectedItem();
        return (SGuiItem) (item == null || item instanceof SGuiItem ? item : null);
    }
}
