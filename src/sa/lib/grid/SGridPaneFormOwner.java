/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

/**
 *
 * @author Sergio Flores
 */
public interface SGridPaneFormOwner {

    public void notifyRowNew(final int gridType, final int gridSubtype, final int row, SGridRow gridRow);
    public void notifyRowEdit(final int gridType, final int gridSubtype, final int row, SGridRow gridRow);
    public void notifyRowDelete(final int gridType, final int gridSubtype, final int row, SGridRow gridRow);
}
