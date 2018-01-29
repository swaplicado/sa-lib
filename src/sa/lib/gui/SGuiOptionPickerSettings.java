/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.gui;

import java.util.ArrayList;
import sa.lib.SLibConsts;
import sa.lib.grid.SGridColumnForm;

/**
 *
 * @author Sergio Flores
 */
public final class SGuiOptionPickerSettings {

    private String msTitle;
    private String msSql;
    private ArrayList<SGridColumnForm> maGridColumns;   // array size defines number of option picker values in SQL query
    private int mnPrimaryKeyLength;
    private boolean mbMainOptionApplying;
    private int mnMainOptionDataType;

    /**
     * @param title Option picker dialog's title.
     * @param sql SQL query.
     * @param gridColumns Option picker columns. Each column value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_PICK</code> + col_number (column number starts from 1).
     */
    public SGuiOptionPickerSettings(String title, String sql, ArrayList<SGridColumnForm> gridColumns) {
        this(title, sql, gridColumns, 0, SLibConsts.UNDEFINED);
    }

    /**
     * @param title Option picker dialog's title.
     * @param sql SQL query.
     * @param gridColumns Option picker columns. Each column value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_PICK</code> + col_number (column number starts from 1).
     * @param pkLength Length of each option's primary key. If no PK is needed, <code>pkLength</code> can be 0. Each primary key value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_ID</code> + pk_number (PK number starts from 1).
     */
    public SGuiOptionPickerSettings(String title, String sql, ArrayList<SGridColumnForm> gridColumns, int pkLength) {
        this(title, sql, gridColumns, pkLength, SLibConsts.UNDEFINED);
    }

    /**
     * @param title Option picker dialog's title.
     * @param sql SQL query.
     * @param gridColumns Option picker columns. Each column value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_PICK</code> + col_number (column number starts from 1).
     * @param pkLength Length of each option's primary key. If no PK is needed, <code>pkLength</code> can be 0. Each primary key value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_ID</code> + pk_number (PK number starts from 1).
     * @param mainOptionDataType Data type of each option's main option. Constants defined in <code>sa.lib.SLibConsts</code> (DATA_TYPE...). Value will be found in query by text: <code>sa.lib.db.SDbConsts.FIELD_VALUE</code>.
     */
    public SGuiOptionPickerSettings(String title, String sql, ArrayList<SGridColumnForm> gridColumns, int pkLength, int mainOptionDataType) {
        msTitle = title;
        msSql = sql;
        maGridColumns = new ArrayList<SGridColumnForm>();
        maGridColumns.addAll(gridColumns);
        mnPrimaryKeyLength = pkLength;
        mbMainOptionApplying = mainOptionDataType != SLibConsts.UNDEFINED;
        mnMainOptionDataType = mainOptionDataType;
    }

    public String getTitle() { return msTitle; }
    public String getSql() { return msSql; }
    public ArrayList<SGridColumnForm> getGridColumns() { return maGridColumns; }
    public int getPrimaryKeyLength() { return mnPrimaryKeyLength; }
    public boolean isMainOptionApplying() { return mbMainOptionApplying; }
    public int getMainOptionDataType() { return mnMainOptionDataType; }
}
