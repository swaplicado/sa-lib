/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sergio Flores
 */
public class SGridColumnView extends SGridColumn {

    protected boolean mbSumApplying;

    public SGridColumnView(int columnType, String fieldName, String columnTitle) {
        this(columnType, fieldName, columnTitle, SGridUtils.getColumnWidth(columnType), SGridUtils.getCellRenderer(columnType));
    }

    public SGridColumnView(int columnType, String fieldName, String columnTitle, TableCellRenderer tableCellRenderer) {
        this(columnType, fieldName, columnTitle, SGridUtils.getColumnWidth(columnType), tableCellRenderer);
    }

    public SGridColumnView(int columnType, String fieldName, String columnTitle, int columnWidth) {
        this(columnType, fieldName, columnTitle, columnWidth, SGridUtils.getCellRenderer(columnType));
    }

    public SGridColumnView(int columnType, String fieldName, String columnTitle, int columnWidth, TableCellRenderer cellRenderer) {
        super(columnType, fieldName, columnTitle, columnWidth, cellRenderer);

        mbSumApplying = false;
    }

    public void setSumApplying(boolean b) { mbSumApplying = b; }

    public boolean isSumApplying() { return mbSumApplying; }
}
