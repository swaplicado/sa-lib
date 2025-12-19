/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui.bean;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeConsts;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiField;
import sa.lib.gui.SGuiFieldCalendar;
import sa.lib.gui.SGuiValidation;

/**
 *
 * @author Sergio Flores
 */
public class SBeanFieldCalendarMonth extends JSpinner implements SGuiFieldCalendar {

    protected String msFieldName;
    protected int mnDefaultValue;
    protected int mnMinInteger;
    protected int mnMaxInteger;
    protected SGuiField moNextField;
    protected JButton moNextButton;
    protected JButton moFormButton;
    protected int mnTab;

    public SBeanFieldCalendarMonth() {
        msFieldName = "";
        mnDefaultValue = SLibTimeConsts.MONTH_MIN;
        mnMinInteger = SLibTimeConsts.MONTH_MIN;
        mnMaxInteger = SLibTimeConsts.MONTH_MAX;
        moNextField = null;
        moNextButton = null;
        moFormButton = null;
        mnTab = -1;

        setModel(new SpinnerNumberModel(mnDefaultValue, mnMinInteger, mnMaxInteger, 1));
        setEditor(new JSpinner.NumberEditor(this, SLibUtils.DecimalFormatCalendarMonth.toPattern()));

        setPreferredSize(new Dimension(50, 23));

        ((JSpinner.DefaultEditor) getEditor()).getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                processKeyPressed(evt);
            }
        });

        resetField();
    }

    /*
     * Class protected methods:
     */

    protected void processKeyPressed(KeyEvent evt) {
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
    }

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
        return SLibConsts.DATA_TYPE_INT;
    }

    @Override
    public int getGuiType() {
        return SGuiConsts.GUI_TYPE_INT_CAL_MONTH;
    }

    @Override
    public String getFieldName() {
        return msFieldName;
    }

    @Override
    public JSpinner getComponent() {
        return this;
    }

    @Override
    public boolean isMandatory() {
        return true;
    }

    @Override
    public void setValue(final Object value) {
        super.setValue((Integer) value);
    }

    @Override
    public void setDefaultValue(final Object value) {
        mnDefaultValue = (Integer) value;
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
        return super.isEnabled();
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
            ((JSpinner.DefaultEditor) getEditor()).getTextField().requestFocus();
        }
        else {
            processKeyPressed(new KeyEvent(this, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_ENTER, KeyEvent.CHAR_UNDEFINED));
        }
    }

    @Override
    public void resetField() {
        setValue(mnDefaultValue);
    }

    @Override
    public SGuiValidation validateField() {
        int n = getValue();
        SGuiValidation validation = new SGuiValidation();

        if (n == 0) {
            validation.setMessage("Se debe especificar un mes para el campo '" + msFieldName + "'.");
            validation.setComponent(this);
            validation.setTab(mnTab);
        }

        return validation;
    }

    /*
     * Implementation of SGuiFieldCalendar:
     */

    @Override
    public Integer getValue() {
        return (Integer) super.getValue();
    }

    @Override
    public Integer getDefaultValue() {
        return mnDefaultValue;
    }

    @Override
    public void setCalendarSettings(final String name) {
        msFieldName = name;
    }
}
