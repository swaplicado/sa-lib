/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import java.util.Vector;
import javax.swing.table.TableCellRenderer;
import sa.lib.SLibRpnArgument;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGridColumn {

    protected int mnColumnType;
    protected int mnColumnWidth;
    protected String msColumnTitle;
    protected String msFieldName;
    protected TableCellRenderer miCellRenderer;
    protected boolean mbApostropheOnCsvRequired;
    protected Vector<SLibRpnArgument> mvRpnArguments;

    public SGridColumn(int columnType, String fieldName, String columnTitle) {
        this(columnType, fieldName, columnTitle, SGridUtils.getColumnWidth(columnType), SGridUtils.getCellRenderer(columnType));
    }

    public SGridColumn(int columnType, String fieldName, String columnTitle, TableCellRenderer cellRenderer) {
        this(columnType, fieldName, columnTitle, SGridUtils.getColumnWidth(columnType), cellRenderer);
    }

    public SGridColumn(int columnType, String fieldName, String columnTitle, int columnWidth) {
        this(columnType, fieldName, columnTitle, columnWidth, SGridUtils.getCellRenderer(columnType));
    }

    public SGridColumn(int columnType, String fieldName, String columnTitle, int columnWidth, TableCellRenderer cellRenderer) {
        mnColumnType = columnType;
        mnColumnWidth = columnWidth;
        msColumnTitle = columnTitle;
        msFieldName = fieldName;
        miCellRenderer = cellRenderer;
        mbApostropheOnCsvRequired = false;
        mvRpnArguments = new Vector<SLibRpnArgument>();
    }

    public void setColumnType(int n) { mnColumnType = n; }
    public void setColumnWidth(int n) { mnColumnWidth = n; }
    public void setColumnTitle(String s) { msColumnTitle = s; }
    public void setFieldName(String s) { msFieldName = s; }
    public void setCellRenderer(TableCellRenderer i) { miCellRenderer = i; }
    public void setApostropheOnCsvRequired(boolean b) { mbApostropheOnCsvRequired = b; }

    public int getColumnType() { return mnColumnType; }
    public int getColumnWidth() { return mnColumnWidth; }
    public String getColumnTitle() { return msColumnTitle; }
    public String getFieldName() { return msFieldName; }
    public TableCellRenderer getCellRenderer() { return miCellRenderer; }
    public boolean isApostropheOnCsvRequired() { return mbApostropheOnCsvRequired; }

    public Vector<SLibRpnArgument> getRpnArguments() { return mvRpnArguments; }
}
