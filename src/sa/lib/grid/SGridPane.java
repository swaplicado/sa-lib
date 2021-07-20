/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import sa.lib.gui.SGuiClient;

/**
 *
 * @author Sergio Flores
 */
public interface SGridPane {

    public SGuiClient getClient();
    public int getGridPaneType();
    public int getGridType();
    public int getGridSubtype();
    public int getGridMode();
    public int getGridSubmode();
    public SGridModel getModel();
    public JTable getTable();
    public JScrollPane getScrollPane();

    public void clearGrid();
    public void clearGridRows();
    public void renderGrid();
    public void renderGridRows();
    public void initSortKeys();
    public void initSortKeysDescending();
    public void putFilter(final int filterType, final Object filterValue);

    public void setGridRow(final SGridRow gridRow, final int row);
    public void setGridColumn(final SGridColumn gridColumn, final int col);
    public SGridRow getGridRow(final int row);
    public SGridColumn getGridColumn(final int col);
    public void addGridRow(final SGridRow gridRow);
    public void addGridColumn(final SGridColumn gridColumn);
    public void insertGridRow(final SGridRow gridRow, final int row);
    public void insertGridColumn(final SGridColumn gridColumn, final int col);
    public SGridRow removeGridRow(final int row);
    public SGridColumn removeGridColumn(final int col);
    public void setSelectedGridColumn(final int col);
    public void setSelectedGridRow(final int row);
    public SGridRow getSelectedGridRow();
    public void setRowValueAtFieldName(final Object value, final int row, final String fieldName);
    public Object getRowValueAtFieldName(final int row, final String fieldName);
}
