/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiOptionPicker {

    public void setPickerSettings(final SGuiClient client, final int pickerType, final int pickerSubtype, final SGuiOptionPickerSettings settings);
    public void resetPicker();
    public void setPickerVisible(final boolean visible);
    public void setOption(final Object option);
    public Object getOption();
    public SGuiValidation validatePicker();
    public int getPickerType();
    public int getPickerSubtype();
    public int getPickerResult();
    public void actionOk();
    public void actionCancel();
}
