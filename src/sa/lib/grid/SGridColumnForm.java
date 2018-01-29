/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Sergio Flores
 */
public class SGridColumnForm extends SGridColumn {

    protected boolean mbEditable;
    protected TableCellEditor miCellEditor;

    public SGridColumnForm(int columnType, String columnTitle) {
        this(columnType, columnTitle, SGridUtils.getColumnWidth(columnType), SGridUtils.getCellRenderer(columnType), null);
    }

    public SGridColumnForm(int columnType, String columnTitle, TableCellRenderer cellRenderer) {
        this(columnType, columnTitle, SGridUtils.getColumnWidth(columnType), cellRenderer, null);
    }

    public SGridColumnForm(int columnType, String columnTitle, TableCellEditor cellEditor) {
        this(columnType, columnTitle, SGridUtils.getColumnWidth(columnType), SGridUtils.getCellRenderer(columnType), cellEditor);
    }

    public SGridColumnForm(int columnType, String columnTitle, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        this(columnType, columnTitle, SGridUtils.getColumnWidth(columnType), cellRenderer, cellEditor);
    }

    public SGridColumnForm(int columnType, String columnTitle, int columnWidth) {
        this(columnType, columnTitle, columnWidth, SGridUtils.getCellRenderer(columnType), null);
    }

    public SGridColumnForm(int columnType, String columnTitle, int columnWidth, TableCellRenderer cellRenderer) {
        this(columnType, columnTitle, columnWidth, cellRenderer, null);
    }

    public SGridColumnForm(int columnType, String columnTitle, int columnWidth, TableCellEditor cellEditor) {
        this(columnType, columnTitle, columnWidth, SGridUtils.getCellRenderer(columnType), cellEditor);
    }

    public SGridColumnForm(int columnType, String columnTitle, int columnWidth, TableCellRenderer cellRenderer, TableCellEditor cellEditor) {
        super(columnType, "", columnTitle, columnWidth, cellRenderer);

        mbEditable = cellEditor != null;
        miCellEditor = cellEditor;
    }

    public void setEditable(boolean b) { mbEditable = b; }
    public void setCellEditor(DefaultCellEditor o) { miCellEditor = o; }

    public boolean isEditable() { return mbEditable; }
    public TableCellEditor getCellEditor() { return miCellEditor; }
}
