/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFieldBoolean extends SGuiField {

    public Boolean getValue();
    public Boolean getDefaultValue();

    public void setBooleanSettings(final String name, final boolean defaultValue);
}
