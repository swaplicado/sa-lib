/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import sa.lib.grid.SGridPaneForm;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFormProcessPanel {

    public void setValue(final int type, final Object value);
    public Object getValue(final int type);

    public void giveFocus();
    public SGridPaneForm[] getFormGrids();
    public SGuiValidation validatePanel();
}
