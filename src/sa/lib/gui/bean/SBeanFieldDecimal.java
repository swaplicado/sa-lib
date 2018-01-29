/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui.bean;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JTextField;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiField;
import sa.lib.gui.SGuiFieldDecimal;
import sa.lib.gui.SGuiValidation;

/**
 *
 * @author Sergio Flores
 */
public class SBeanFieldDecimal extends JTextField implements SGuiFieldDecimal {

    protected String msFieldName;
    protected int mnGuiType;
    protected DecimalFormat moDecimalFormat;
    protected double mdDefaultValue;
    protected double mdMinDouble;
    protected double mdMaxDouble;
    protected boolean mbMandatory;
    protected SGuiField moNextField;
    protected JButton moNextButton;
    protected JButton moFormButton;
    protected JButton moFieldButton;
    protected int mnTab;

    public SBeanFieldDecimal() {
        msFieldName = "";
        mnGuiType = SLibConsts.UNDEFINED;
        moDecimalFormat = null;
        mdDefaultValue = 0;
        mdMinDouble = 0;
        mdMaxDouble = Double.MAX_VALUE;
        mbMandatory = true;
        moNextField = null;
        moNextButton = null;
        moFormButton = null;
        moFieldButton = null;
        mnTab = -1;

        setPreferredSize(new Dimension(100, 23));
        setHorizontalAlignment(JTextField.TRAILING);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                processActionPerformed(evt);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                processKeyPressed(evt);
            }
        });

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evt) {
                processFocusGained(evt);
            }
            @Override
            public void focusLost(FocusEvent evt) {
                processFocusLost(evt);
            }
        });

        resetGuiType();
        resetField();
    }

    /*
     * Class private methods:
     */

    private void resetGuiType() {
        mnGuiType = SGuiConsts.GUI_TYPE_DEC;
        moDecimalFormat = SLibUtils.getDecimalFormat();
    }

    private void processActionPerformed(ActionEvent evt) {
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

    private void processKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_F5) {
            if (moFieldButton != null && moFieldButton.isEnabled()) {
                moFieldButton.doClick();
            }
        }
    }

    private void processFocusGained(FocusEvent evt) {
        if (SLibUtils.parseDouble(getText()) == 0) {
            setText("");
        }
        else {
            selectAll();
        }
    }

    private void processFocusLost(FocusEvent evt) {
        setText(moDecimalFormat.format(SLibUtils.parseDouble(getText()) / moDecimalFormat.getMultiplier()));
    }

    /*
     * Class public methods:
     */

    public boolean isPercentage() {
        return mnGuiType == SGuiConsts.GUI_TYPE_DEC_PER || mnGuiType == SGuiConsts.GUI_TYPE_DEC_PER_DISC || mnGuiType == SGuiConsts.GUI_TYPE_DEC_PER_TAX;
    }

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
        return SLibConsts.DATA_TYPE_INT;
    }

    @Override
    public int getGuiType() {
        return mnGuiType;
    }

    @Override
    public String getFieldName() {
        return msFieldName;
    }

    @Override
    public JTextField getComponent() {
        return this;
    }

    @Override
    public boolean isMandatory() {
        return mbMandatory;
    }

    @Override
    public void setValue(final Object value) {
        setText(moDecimalFormat.format(SLibUtils.round((Double) value, moDecimalFormat.getMaximumFractionDigits() + (int) Math.log10(moDecimalFormat.getMultiplier()))));
        setCaretPosition(0);
    }

    @Override
    public void setDefaultValue(final Object value) {
        mdDefaultValue = (Double) value;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void setEditable(final boolean editable) {
        super.setEditable(editable);
        super.setFocusable(editable);
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
        return super.isEditable();
    }

    @Override
    public boolean isFocusable() {
        return super.isEnabled()&& super.isEditable() && super.isFocusable();
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
            requestFocus();
        }
        else {
            processActionPerformed(null);
        }
    }

    @Override
    public void resetField() {
        setValue(mdDefaultValue);
    }

    @Override
    public SGuiValidation validateField() {
        double d = 0;
        SGuiValidation validation = new SGuiValidation();

        processFocusLost(null);

        d = getValue();

        if (mbMandatory && d == 0) {
            validation.setMessage("Se debe especificar un valor decimal para el campo '" + msFieldName + "'.");
        }
        else if (d < mdMinDouble) {
            validation.setMessage("El valor del campo '" + msFieldName + "' no puede ser menor que " + moDecimalFormat.format(mdMinDouble) + ".");
        }
        else if (d > mdMaxDouble) {
            validation.setMessage("El valor del campo '" + msFieldName + "' no puede ser mayor que " + moDecimalFormat.format(mdMaxDouble) + ".");
        }

        if (!validation.isValid()) {
            validation.setComponent(this);
            validation.setTab(mnTab);
        }

        return validation;
    }

    /*
     * Implementation of SGuiFieldDecimal:
     */

    @Override
    public Double getValue() {
        return SLibUtils.parseDouble(getText()) / moDecimalFormat.getMultiplier();
    }

    @Override
    public Double getDefaultValue() {
        return mdDefaultValue;
    }

    @Override
    public void setDecimalSettings(final String name, final int guiType, final boolean mandatory) {
        setFieldName(name);
        setGuiType(guiType);
        setMandatory(mandatory);
    }

    @Override
    public void setDecimalFormat(final DecimalFormat format) {
        moDecimalFormat = format;
    }

    @Override
    public void setGuiType(final int type) {
        mnGuiType = type;

        switch (type) {
            case SGuiConsts.GUI_TYPE_DEC:
                moDecimalFormat = SLibUtils.getDecimalFormat();
                break;
            case SGuiConsts.GUI_TYPE_DEC_AMT:
                moDecimalFormat = SLibUtils.getDecimalFormatAmount();
                break;
            case SGuiConsts.GUI_TYPE_DEC_AMT_UNIT:
                moDecimalFormat = SLibUtils.getDecimalFormatAmountUnitary();
                break;
            case SGuiConsts.GUI_TYPE_DEC_EXC_RATE:
                moDecimalFormat = SLibUtils.getDecimalFormatExchangeRate();
                break;
            case SGuiConsts.GUI_TYPE_DEC_QTY:
                moDecimalFormat = SLibUtils.getDecimalFormatQuantity();
                break;
            case SGuiConsts.GUI_TYPE_DEC_PER:
                moDecimalFormat = SLibUtils.getDecimalFormatPercentage();
                break;
            case SGuiConsts.GUI_TYPE_DEC_PER_TAX:
                moDecimalFormat = SLibUtils.getDecimalFormatPercentageTax();
                break;
            case SGuiConsts.GUI_TYPE_DEC_PER_DISC:
                moDecimalFormat = SLibUtils.getDecimalFormatPercentageDiscount();
                break;
            default:
                resetGuiType();
        }
    }

    @Override
    public void setMandatory(final boolean isMandatory) {
        mbMandatory = isMandatory;
    }

    @Override
    public void setMinDouble(final double min) {
        mdMinDouble = min;
    }

    @Override
    public void setMaxDouble(final double max) {
        mdMaxDouble = max;
    }

    @Override
    public double getMinDouble() {
        return mdMinDouble;
    }

    @Override
    public double getMaxDouble() {
        return mdMaxDouble;
    }
}
