/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFieldCalendar extends SGuiField {

    public Integer getValue();
    public Integer getDefaultValue();

    public void setCalendarSettings(final String name);
}
