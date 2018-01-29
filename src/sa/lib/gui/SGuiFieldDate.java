/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.util.Date;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFieldDate extends SGuiField {

    public Date getValue();
    public Date getDefaultValue();

    public void setDateSettings(final SGuiClient client, final String name, final boolean mandatory);

    public void setMandatory(final boolean mandatory);
    public void setGuiType(final int type);
    public void setMinDate(final Date min);
    public void setMaxDate(final Date max);

    public Date getMinDate();
    public Date getMaxDate();
}
