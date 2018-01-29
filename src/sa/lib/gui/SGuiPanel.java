/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import sa.lib.db.SDbRegistry;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiPanel {

    public int getPanelStatus();

    public void setPanelSettings(final SGuiClient client);
    public void setPanelEditable(final boolean editable);
    public void addAllListeners();
    public void removeAllListeners();
    public void reloadCatalogues();

    public void setRegistry(final SDbRegistry registry) throws Exception;
    public SDbRegistry getRegistry() throws Exception;
    public SGuiValidation validatePanel();
    public SGuiField getFieldFirst();
    public SGuiField getFieldLast();
    public SGuiFields getFields();

    public void setValue(final int type, final Object value);
    public Object getValue(final int type);
}
