/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFieldKey extends SGuiField {

    public int[] getValue();
    public int[] getDefaultValue();

    public void setKeySettings(final SGuiClient client, final String name, final boolean mandatory);

    public void setMandatory(final boolean mandatory);

    public SGuiItem getSelectedItem();
}
