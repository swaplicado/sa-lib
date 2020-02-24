/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.SLibConsts;
import sa.lib.SLibRpnArgument;
import sa.lib.SLibRpnArgumentType;
import sa.lib.SLibRpnOperator;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;
import sa.lib.grid.cell.SGridCellRendererBoolean;
import sa.lib.grid.cell.SGridCellRendererDate;
import sa.lib.grid.cell.SGridCellRendererDefault;
import sa.lib.grid.cell.SGridCellRendererIcon;
import sa.lib.grid.cell.SGridCellRendererNumber;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiDate;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGridUtils {

    public static final SGridCellRendererNumber CellRendererInteger = new SGridCellRendererNumber(SLibUtils.DecimalFormatInteger);
    public static final SGridCellRendererNumber CellRendererIntegerRaw = new SGridCellRendererNumber(SLibUtils.DecimalFormatIntegerRaw);
    public static final SGridCellRendererNumber CellRendererCalendarYear = new SGridCellRendererNumber(SLibUtils.DecimalFormatCalendarYear);
    public static final SGridCellRendererNumber CellRendererCalendarMonth = new SGridCellRendererNumber(SLibUtils.DecimalFormatCalendarMonth);
    public static final SGridCellRendererNumber CellRendererValue0D = new SGridCellRendererNumber(SLibUtils.DecimalFormatValue0D);
    public static final SGridCellRendererNumber CellRendererValue2D = new SGridCellRendererNumber(SLibUtils.DecimalFormatValue2D);
    public static final SGridCellRendererNumber CellRendererValue4D = new SGridCellRendererNumber(SLibUtils.DecimalFormatValue4D);
    public static final SGridCellRendererNumber CellRendererValue8D = new SGridCellRendererNumber(SLibUtils.DecimalFormatValue8D);
    public static final SGridCellRendererNumber CellRendererPercentage0D = new SGridCellRendererNumber(SLibUtils.DecimalFormatPercentage0D);
    public static final SGridCellRendererNumber CellRendererPercentage2D = new SGridCellRendererNumber(SLibUtils.DecimalFormatPercentage2D);
    public static final SGridCellRendererNumber CellRendererPercentage4D = new SGridCellRendererNumber(SLibUtils.DecimalFormatPercentage4D);
    public static final SGridCellRendererNumber CellRendererPercentage8D = new SGridCellRendererNumber(SLibUtils.DecimalFormatPercentage8D);
    public static final SGridCellRendererDefault CellRendererString = new SGridCellRendererDefault();
    public static final SGridCellRendererBoolean CellRendererBoolean = new SGridCellRendererBoolean();
    public static final SGridCellRendererDate CellRendererDate = new SGridCellRendererDate(SLibUtils.DateFormatDate);
    public static final SGridCellRendererDate CellRendererDatetime = new SGridCellRendererDate(SLibUtils.DateFormatDatetime);
    public static final SGridCellRendererDate CellRendererTime = new SGridCellRendererDate(SLibUtils.DateFormatTime);
    public static final SGridCellRendererIcon CellRendererIcon = new SGridCellRendererIcon();

    public static SGridCellRendererNumber getCellRendererNumber() {
        return CellRendererValue8D;
    }

    public static SGridCellRendererNumber getCellRendererNumberAmount() {
        return CellRendererValue2D;
    }

    public static SGridCellRendererNumber getCellRendererNumberAmountUnitary() {
        return CellRendererValue8D;
    }

    public static SGridCellRendererNumber getCellRendererNumberExchangeRate() {
        return CellRendererValue4D;
    }

    public static SGridCellRendererNumber getCellRendererNumberQuantity() {
        return CellRendererValue4D;
    }

    public static SGridCellRendererNumber getCellRendererNumberPercentage() {
        return CellRendererPercentage8D;
    }

    public static SGridCellRendererNumber getCellRendererNumberPercentageTax() {
        return CellRendererPercentage4D;
    }

    public static SGridCellRendererNumber getCellRendererNumberPercentageDiscount() {
        return CellRendererPercentage4D;
    }

    public static int getColumnWidth(final int columnType) {
        int width = 0;

        switch (columnType) {
            case SGridConsts.COL_TYPE_INT_1B:
                width = 25;
                break;
            case SGridConsts.COL_TYPE_INT_2B:
                width = 50;
                break;
            case SGridConsts.COL_TYPE_INT_4B:
                width = 75;
                break;
            case SGridConsts.COL_TYPE_INT_8B:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_INT_RAW:
                width = 75;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_MONTH:
                width = 30;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_YEAR:
                width = 40;
                break;
            case SGridConsts.COL_TYPE_INT_ICON:
                width = 16;
                break;
            case SGridConsts.COL_TYPE_DEC_0D:
                width = 60;
                break;
            case SGridConsts.COL_TYPE_DEC_2D:
                width = 80;
                break;
            case SGridConsts.COL_TYPE_DEC_4D:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_DEC_8D:
                width = 120;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT_UNIT:
                width = 120;
                break;
            case SGridConsts.COL_TYPE_DEC_EXC_RATE:
                width = 80;
                break;
            case SGridConsts.COL_TYPE_DEC_QTY:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_0D:
                width = 60;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_2D:
                width = 80;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_4D:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_8D:
                width = 120;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_TAX:
                width = 80;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_DISC:
                width = 80;
                break;
            case SGridConsts.COL_TYPE_BOOL_S:
                width = 50;
                break;
            case SGridConsts.COL_TYPE_BOOL_M:
                width = 75;
                break;
            case SGridConsts.COL_TYPE_BOOL_L:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_CO:
                width = 35;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_BPR:
                width = 50;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_CAT:
                width = 50;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_ITM:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_UNT:
                width = 35;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_CUR:
                width = 35;
                break;
            case SGridConsts.COL_TYPE_TEXT_CODE_ACC:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_S:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_M:
                width = 200;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_L:
                width = 300;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_S:
                width = 150;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_L:
                width = 300;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_S:
                width = 150;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_L:
                width = 300;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_USR:
                width = 100;
                break;
            case SGridConsts.COL_TYPE_TEXT_NAME_ACC:
                width = 200;
                break;
            case SGridConsts.COL_TYPE_TEXT_REG_PER:
                width = 50;
                break;
            case SGridConsts.COL_TYPE_TEXT_REG_NUM:
                width = 85;
                break;
            case SGridConsts.COL_TYPE_DATE:
                width = 65;
                break;
            case SGridConsts.COL_TYPE_DATE_DATETIME:
                width = 110;
                break;
            case SGridConsts.COL_TYPE_DATE_TIME:
                width = 45;
                break;
            default:
                SLibUtils.showException(SGridUtils.class.getName(), new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN));
        }

        return width;
    }

    public static DefaultTableCellRenderer getCellRenderer(final int columnType) {
        DefaultTableCellRenderer renderer = null;

        switch (columnType) {
            case SGridConsts.COL_TYPE_INT_1B:
            case SGridConsts.COL_TYPE_INT_2B:
            case SGridConsts.COL_TYPE_INT_4B:
            case SGridConsts.COL_TYPE_INT_8B:
                renderer = CellRendererInteger;
                break;
            case SGridConsts.COL_TYPE_INT_RAW:
                renderer = CellRendererIntegerRaw;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_MONTH:
                renderer = CellRendererCalendarMonth;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_YEAR:
                renderer = CellRendererCalendarYear;
                break;
            case SGridConsts.COL_TYPE_INT_ICON:
                renderer = CellRendererIcon;
                break;
            case SGridConsts.COL_TYPE_DEC_0D:
                renderer = CellRendererValue0D;
                break;
            case SGridConsts.COL_TYPE_DEC_2D:
                renderer = CellRendererValue2D;
                break;
            case SGridConsts.COL_TYPE_DEC_4D:
                renderer = CellRendererValue4D;
                break;
            case SGridConsts.COL_TYPE_DEC_8D:
                renderer = CellRendererValue8D;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT:
                renderer = CellRendererValue2D;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT_UNIT:
                renderer = CellRendererValue8D;
                break;
            case SGridConsts.COL_TYPE_DEC_EXC_RATE:
                renderer = CellRendererValue4D;
                break;
            case SGridConsts.COL_TYPE_DEC_QTY:
                renderer = CellRendererValue4D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_0D:
                renderer = CellRendererPercentage0D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_2D:
                renderer = CellRendererPercentage2D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_4D:
                renderer = CellRendererPercentage4D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_8D:
                renderer = CellRendererPercentage8D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_TAX:
                renderer = CellRendererPercentage4D;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_DISC:
                renderer = CellRendererPercentage4D;
                break;
            case SGridConsts.COL_TYPE_BOOL_S:
            case SGridConsts.COL_TYPE_BOOL_M:
            case SGridConsts.COL_TYPE_BOOL_L:
                renderer = CellRendererBoolean;
                break;
            case SGridConsts.COL_TYPE_TEXT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CO:
            case SGridConsts.COL_TYPE_TEXT_CODE_BPR:
            case SGridConsts.COL_TYPE_TEXT_CODE_CAT:
            case SGridConsts.COL_TYPE_TEXT_CODE_ITM:
            case SGridConsts.COL_TYPE_TEXT_CODE_UNT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CUR:
            case SGridConsts.COL_TYPE_TEXT_CODE_ACC:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_M:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_USR:
            case SGridConsts.COL_TYPE_TEXT_NAME_ACC:
            case SGridConsts.COL_TYPE_TEXT_REG_PER:
            case SGridConsts.COL_TYPE_TEXT_REG_NUM:
                renderer = CellRendererString;
                break;
            case SGridConsts.COL_TYPE_DATE:
                renderer = CellRendererDate;
                break;
            case SGridConsts.COL_TYPE_DATE_DATETIME:
                renderer = CellRendererDatetime;
                break;
            case SGridConsts.COL_TYPE_DATE_TIME:
                renderer = CellRendererTime;
                break;
            default:
                SLibUtils.showException(SGridUtils.class.getName(), new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN));
        }

        return renderer;
    }

    public static int getDataType(final int columnType) {
        int type = SLibConsts.UNDEFINED;

        switch (columnType) {
            case SGridConsts.COL_TYPE_INT_1B:
            case SGridConsts.COL_TYPE_INT_2B:
            case SGridConsts.COL_TYPE_INT_4B:
            case SGridConsts.COL_TYPE_INT_8B:
            case SGridConsts.COL_TYPE_INT_RAW:
            case SGridConsts.COL_TYPE_INT_CAL_MONTH:
            case SGridConsts.COL_TYPE_INT_CAL_YEAR:
            case SGridConsts.COL_TYPE_INT_ICON:
                type = SLibConsts.DATA_TYPE_INT;
                break;
            case SGridConsts.COL_TYPE_DEC_0D:
            case SGridConsts.COL_TYPE_DEC_2D:
            case SGridConsts.COL_TYPE_DEC_4D:
            case SGridConsts.COL_TYPE_DEC_8D:
            case SGridConsts.COL_TYPE_DEC_AMT:
            case SGridConsts.COL_TYPE_DEC_AMT_UNIT:
            case SGridConsts.COL_TYPE_DEC_EXC_RATE:
            case SGridConsts.COL_TYPE_DEC_QTY:
            case SGridConsts.COL_TYPE_DEC_PER_0D:
            case SGridConsts.COL_TYPE_DEC_PER_2D:
            case SGridConsts.COL_TYPE_DEC_PER_4D:
            case SGridConsts.COL_TYPE_DEC_PER_8D:
            case SGridConsts.COL_TYPE_DEC_PER_TAX:
            case SGridConsts.COL_TYPE_DEC_PER_DISC:
                type = SLibConsts.DATA_TYPE_DEC;
                break;
            case SGridConsts.COL_TYPE_BOOL_S:
            case SGridConsts.COL_TYPE_BOOL_M:
            case SGridConsts.COL_TYPE_BOOL_L:
                type = SLibConsts.DATA_TYPE_BOOL;
                break;
            case SGridConsts.COL_TYPE_TEXT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CO:
            case SGridConsts.COL_TYPE_TEXT_CODE_BPR:
            case SGridConsts.COL_TYPE_TEXT_CODE_CAT:
            case SGridConsts.COL_TYPE_TEXT_CODE_ITM:
            case SGridConsts.COL_TYPE_TEXT_CODE_UNT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CUR:
            case SGridConsts.COL_TYPE_TEXT_CODE_ACC:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_M:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_USR:
            case SGridConsts.COL_TYPE_TEXT_NAME_ACC:
            case SGridConsts.COL_TYPE_TEXT_REG_PER:
            case SGridConsts.COL_TYPE_TEXT_REG_NUM:
                type = SLibConsts.DATA_TYPE_TEXT;
                break;
            case SGridConsts.COL_TYPE_DATE:
            case SGridConsts.COL_TYPE_DATE_DATETIME:
            case SGridConsts.COL_TYPE_DATE_TIME:
                type = SLibConsts.DATA_TYPE_DATE;
                break;
            default:
                SLibUtils.showException(SGridUtils.class.getName(), new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN));
        }

        return type;
    }

    public static int getGuiType(final int columnType) {
        int type = SLibConsts.UNDEFINED;

        switch (columnType) {
            case SGridConsts.COL_TYPE_INT_1B:
            case SGridConsts.COL_TYPE_INT_2B:
            case SGridConsts.COL_TYPE_INT_4B:
            case SGridConsts.COL_TYPE_INT_8B:
                type = SGuiConsts.GUI_TYPE_INT;
                break;
            case SGridConsts.COL_TYPE_INT_RAW:
                type = SGuiConsts.GUI_TYPE_INT_RAW;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_MONTH:
                type = SGuiConsts.GUI_TYPE_INT_CAL_MONTH;
                break;
            case SGridConsts.COL_TYPE_INT_CAL_YEAR:
                type = SGuiConsts.GUI_TYPE_INT_CAL_YEAR;
                break;
            case SGridConsts.COL_TYPE_INT_ICON:
                type = SGuiConsts.GUI_TYPE_INT;
                break;
            case SGridConsts.COL_TYPE_DEC_0D:
            case SGridConsts.COL_TYPE_DEC_2D:
            case SGridConsts.COL_TYPE_DEC_4D:
            case SGridConsts.COL_TYPE_DEC_8D:
                type = SGuiConsts.GUI_TYPE_DEC;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT:
                type = SGuiConsts.GUI_TYPE_DEC_AMT;
                break;
            case SGridConsts.COL_TYPE_DEC_AMT_UNIT:
                type = SGuiConsts.GUI_TYPE_DEC_AMT_UNIT;
                break;
            case SGridConsts.COL_TYPE_DEC_EXC_RATE:
                type = SGuiConsts.GUI_TYPE_DEC_EXC_RATE;
                break;
            case SGridConsts.COL_TYPE_DEC_QTY:
                type = SGuiConsts.GUI_TYPE_DEC_QTY;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_0D:
            case SGridConsts.COL_TYPE_DEC_PER_2D:
            case SGridConsts.COL_TYPE_DEC_PER_4D:
            case SGridConsts.COL_TYPE_DEC_PER_8D:
                type = SGuiConsts.GUI_TYPE_DEC_PER;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_TAX:
                type = SGuiConsts.GUI_TYPE_DEC_PER_TAX;
                break;
            case SGridConsts.COL_TYPE_DEC_PER_DISC:
                type = SGuiConsts.GUI_TYPE_DEC_PER_DISC;
                break;
            case SGridConsts.COL_TYPE_BOOL_S:
            case SGridConsts.COL_TYPE_BOOL_M:
            case SGridConsts.COL_TYPE_BOOL_L:
                type = SGuiConsts.GUI_TYPE_BOOL;
                break;
            case SGridConsts.COL_TYPE_TEXT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CO:
            case SGridConsts.COL_TYPE_TEXT_CODE_BPR:
            case SGridConsts.COL_TYPE_TEXT_CODE_CAT:
            case SGridConsts.COL_TYPE_TEXT_CODE_ITM:
            case SGridConsts.COL_TYPE_TEXT_CODE_UNT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CUR:
            case SGridConsts.COL_TYPE_TEXT_CODE_ACC:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_M:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_USR:
            case SGridConsts.COL_TYPE_TEXT_NAME_ACC:
            case SGridConsts.COL_TYPE_TEXT_REG_PER:
            case SGridConsts.COL_TYPE_TEXT_REG_NUM:
                type = SGuiConsts.GUI_TYPE_TEXT;
                break;
            case SGridConsts.COL_TYPE_DATE:
                type = SGuiConsts.GUI_TYPE_DATE;
                break;
            case SGridConsts.COL_TYPE_DATE_DATETIME:
                type = SGuiConsts.GUI_TYPE_DATE_DATETIME;
                break;
            case SGridConsts.COL_TYPE_DATE_TIME:
                type = SGuiConsts.GUI_TYPE_DATE_TIME;
                break;
            default:
                SLibUtils.showException(SGridUtils.class.getName(), new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN));
        }

        return type;
    }

    public static Class<?> getDataTypeClass(final int columnType) {
        Class<?> typeClass = null;

        switch (columnType) {
            case SGridConsts.COL_TYPE_INT_1B:
            case SGridConsts.COL_TYPE_INT_2B:
            case SGridConsts.COL_TYPE_INT_4B:
                typeClass = Integer.class;
                break;
            case SGridConsts.COL_TYPE_INT_8B:
                typeClass = Long.class;
                break;
            case SGridConsts.COL_TYPE_INT_RAW:
            case SGridConsts.COL_TYPE_INT_CAL_MONTH:
            case SGridConsts.COL_TYPE_INT_CAL_YEAR:
            case SGridConsts.COL_TYPE_INT_ICON:
                typeClass = Integer.class;
                break;
            case SGridConsts.COL_TYPE_DEC_0D:
            case SGridConsts.COL_TYPE_DEC_2D:
            case SGridConsts.COL_TYPE_DEC_4D:
            case SGridConsts.COL_TYPE_DEC_8D:
            case SGridConsts.COL_TYPE_DEC_AMT:
            case SGridConsts.COL_TYPE_DEC_AMT_UNIT:
            case SGridConsts.COL_TYPE_DEC_EXC_RATE:
            case SGridConsts.COL_TYPE_DEC_QTY:
            case SGridConsts.COL_TYPE_DEC_PER_0D:
            case SGridConsts.COL_TYPE_DEC_PER_2D:
            case SGridConsts.COL_TYPE_DEC_PER_4D:
            case SGridConsts.COL_TYPE_DEC_PER_8D:
            case SGridConsts.COL_TYPE_DEC_PER_TAX:
            case SGridConsts.COL_TYPE_DEC_PER_DISC:
                typeClass = Double.class;
                break;
            case SGridConsts.COL_TYPE_BOOL_S:
            case SGridConsts.COL_TYPE_BOOL_M:
            case SGridConsts.COL_TYPE_BOOL_L:
                typeClass = Boolean.class;
                break;
            case SGridConsts.COL_TYPE_TEXT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CO:
            case SGridConsts.COL_TYPE_TEXT_CODE_BPR:
            case SGridConsts.COL_TYPE_TEXT_CODE_CAT:
            case SGridConsts.COL_TYPE_TEXT_CODE_ITM:
            case SGridConsts.COL_TYPE_TEXT_CODE_UNT:
            case SGridConsts.COL_TYPE_TEXT_CODE_CUR:
            case SGridConsts.COL_TYPE_TEXT_CODE_ACC:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_M:
            case SGridConsts.COL_TYPE_TEXT_NAME_CAT_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_BPR_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_S:
            case SGridConsts.COL_TYPE_TEXT_NAME_ITM_L:
            case SGridConsts.COL_TYPE_TEXT_NAME_USR:
            case SGridConsts.COL_TYPE_TEXT_NAME_ACC:
            case SGridConsts.COL_TYPE_TEXT_REG_PER:
            case SGridConsts.COL_TYPE_TEXT_REG_NUM:
                typeClass = String.class;
                break;
            case SGridConsts.COL_TYPE_DATE:
            case SGridConsts.COL_TYPE_DATE_DATETIME:
            case SGridConsts.COL_TYPE_DATE_TIME:
                typeClass = Date.class;
                break;
            default:
                SLibUtils.showException(SGridUtils.class.getName(), new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN));
        }

        return typeClass;
    }

    private static int seek(final SGridPane gridPane, final int col, final SortOrder sortOrder, final int rowFirst, final int rowLast, final double value) {
        int rowMiddle;
        double valueComparison;

        rowMiddle = rowFirst + ((rowLast - rowFirst) / 2);
        valueComparison = value - ((Number) gridPane.getTable().getValueAt(rowMiddle, col)).doubleValue();

        if (valueComparison == 0) {
            return rowMiddle;
        }

        switch (sortOrder) {
            case ASCENDING:
                if (valueComparison < 0) {
                    if ((rowMiddle - 1) >= rowFirst) {
                        return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                    }
                }
                else {
                    if (rowLast >= (rowMiddle + 1)) {
                        return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                    }
                }
                break;
            case DESCENDING:
                if (valueComparison < 0) {
                    if (rowLast >= (rowMiddle + 1)) {
                        return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                    }
                }
                else {
                    if ((rowMiddle - 1) >= rowFirst) {
                        return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                    }
                }
                break;
            default:
        }

        return -1;
    }

    private static int seek(final SGridPane gridPane, final int col, final SortOrder sortOrder, final int rowFirst, final int rowLast, final String value) {
        int rowMiddle;
        int valueComparison;

        rowMiddle = rowFirst + ((rowLast - rowFirst) / 2);
        valueComparison = value.compareToIgnoreCase(
        (((java.lang.String) gridPane.getTable().getValueAt(rowMiddle, col)).length() > value.length() ?
        ((java.lang.String) gridPane.getTable().getValueAt(rowMiddle, col)).substring(0, value.length()) :
        (java.lang.String) gridPane.getTable().getValueAt(rowMiddle, col)));

        if (valueComparison == 0) {
            return rowMiddle;
        }

        switch (sortOrder) {
            case ASCENDING:
                if (valueComparison < 0) {
                    if ((rowMiddle - 1) >= rowFirst) {
                        return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                    }
                }
                else {
                    if (rowLast >= (rowMiddle + 1)) {
                        return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                    }
                }
                break;
            case DESCENDING:
                if (valueComparison < 0) {
                    if (rowLast >= (rowMiddle + 1)) {
                        return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                    }
                }
                else {
                    if ((rowMiddle - 1) >= rowFirst) {
                        return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                    }
                }
                break;
            default:
        }

        return -1;
    }

    private static int seek(final SGridPane gridPane, final int col, final SortOrder sortOrder, final int rowFirst, final int rowLast, final Date value) {
        int rowMiddle;
        int valueComparison;

        rowMiddle = rowFirst + ((rowLast - rowFirst) / 2);
        valueComparison = value.compareTo((java.util.Date) gridPane.getTable().getValueAt(rowMiddle, col));

        if (valueComparison == 0) {
            return rowMiddle;
        }

        switch (sortOrder) {
            case ASCENDING:
                if (valueComparison < 0) {
                    if ((rowMiddle - 1) >= rowFirst) {
                        return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                    }
                }
                else {
                    if (rowLast >= (rowMiddle + 1)) {
                        return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                    }
                }
                break;
            case DESCENDING:
            if (valueComparison < 0) {
                if (rowLast >= (rowMiddle + 1)) {
                    return seek(gridPane, col, sortOrder, rowMiddle + 1, rowLast, value);
                }
            }
            else {
                if ((rowMiddle - 1) >= rowFirst) {
                    return seek(gridPane, col, sortOrder, rowFirst, rowMiddle - 1, value);
                }
            }
                break;
            default:
        }

        return -1;
    }

    public static void seekValue(SGridPane gridPane, String valueToSeek) {
        int col = 0;
        int colType = SLibConsts.UNDEFINED;
        int row = -1;
        int rows = rows = gridPane.getTable().getRowCount();
        double valueAsDouble = 0d;
        Date valueAsDate = null;
        SortOrder sortOrder = null;

        if (gridPane.getTable().getRowSorter().getSortKeys().isEmpty()) {
            col = gridPane.getTable().convertColumnIndexToView(0);
            sortOrder = SortOrder.ASCENDING;
        }
        else {
            col = gridPane.getTable().convertColumnIndexToView(((RowSorter.SortKey) gridPane.getTable().getRowSorter().getSortKeys().get(0)).getColumn());
            sortOrder = ((RowSorter.SortKey) gridPane.getTable().getRowSorter().getSortKeys().get(0)).getSortOrder();
        }

        if (rows > 0) {
            if (col >= 0 && col < gridPane.getTable().getColumnCount()) {
                colType = gridPane.getModel().getGridColumns().get(gridPane.getTable().convertColumnIndexToModel(col)).getColumnType();

                switch (SGridUtils.getDataType(colType)) {
                    case SLibConsts.DATA_TYPE_BOOL:
                    case SLibConsts.DATA_TYPE_INT:
                    case SLibConsts.DATA_TYPE_DEC:
                        valueAsDouble = SLibUtils.parseDouble(valueToSeek);
                        row = seek(gridPane, col, sortOrder, 0, rows - 1, valueAsDouble);
                        break;

                    case SLibConsts.DATA_TYPE_TEXT:
                        row = seek(gridPane, col, sortOrder, 0, rows - 1, SLibUtils.textTrim(valueToSeek));
                        break;

                    case SLibConsts.DATA_TYPE_DATE:
                        SimpleDateFormat dateFormat = null;

                        switch (colType) {
                            case SGridConsts.COL_TYPE_DATE:
                                dateFormat = SLibUtils.DateFormatDate;
                                break;
                            case SGridConsts.COL_TYPE_DATE_DATETIME:
                                dateFormat = SLibUtils.DateFormatDatetime;
                                break;
                            case SGridConsts.COL_TYPE_DATE_TIME:
                                dateFormat = SLibUtils.DateFormatTime;
                                break;
                            default:
                        }

                        try {
                            valueAsDate = dateFormat.parse(valueToSeek);
                            row = seek(gridPane, col, sortOrder, 0, rows - 1, valueAsDate);
                        }
                        catch (java.text.ParseException e) {
                            SLibUtils.showException(SGridUtils.class.getName(), e);
                        }
                        break;

                    default:
                        break;
                }

                if (row == -1) {
                    gridPane.getClient().showMsgBoxWarning(SGridConsts.ERR_MSG_VAL_NOT_FOUND + "\n(" + valueToSeek + ")");
                }
                else {
                    // Go back to first occurrence of value:

                    switch (SGridUtils.getDataType(colType)) {
                        case SLibConsts.DATA_TYPE_BOOL:
                        case SLibConsts.DATA_TYPE_INT:
                        case SLibConsts.DATA_TYPE_DEC:
                            while (row > 0 && valueAsDouble == ((Number) gridPane.getTable().getValueAt(row - 1, col)).doubleValue()) {
                                row--;
                            }
                            break;

                        case SLibConsts.DATA_TYPE_TEXT:
                            while (row > 0 && valueToSeek.compareToIgnoreCase(
                            (((String) gridPane.getTable().getValueAt(row - 1, col)).length() > valueToSeek.length() ?
                            ((String) gridPane.getTable().getValueAt(row - 1, col)).substring(0, valueToSeek.length()) :
                            (String) gridPane.getTable().getValueAt(row - 1, col))) == 0) {
                                row--;
                            }
                            break;

                        case SLibConsts.DATA_TYPE_DATE:
                            while (row > 0 && valueAsDate.compareTo((Date) gridPane.getTable().getValueAt(row - 1, col)) == 0) {
                                row--;
                            }
                            break;

                        default:
                    }

                    // Scroll to value:

                    gridPane.setSelectedGridRow(row);
                    gridPane.getTable().requestFocus();
                }
            }
        }
    }

    public static void searchValue(SGridPane gridPane, String valueToSearch, boolean fromTop) {
        int row = 0;
        int col = 0;
        int rows = gridPane.getTable().getRowCount();
        int cols = gridPane.getTable().getColumnCount();
        String valueToSearchUpper = valueToSearch.toUpperCase();
        
        if (!valueToSearchUpper.isEmpty()) {
            if (rows > 0) {
                row:
                for (row = fromTop ? 0 : (gridPane.getTable().getSelectedRow() + 1); row < rows; row++) {
                    col:
                    for (col = 0; col < cols; col++) {
                        if (gridPane.getTable().getValueAt(row, col) != null) {
                            if (gridPane.getTable().getValueAt(row, col).toString().toUpperCase().contains(valueToSearchUpper)) {
                                gridPane.getTable().setRowSelectionInterval(row, row);
                                break row;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void saveCsv(SGridPane gridPane, java.lang.String title) {
        int col = 0;
        int colType = 0;
        int colModel = 0;
        int rowModel = 0;
        int row = 0;
        String buffer = "";

        gridPane.getClient().getFileChooser().setSelectedFile(new File(title + " " + SLibUtils.FileDateFormatDatetime.format(new Date()) + ".csv"));
        if (gridPane.getClient().getFileChooser().showSaveDialog(gridPane.getClient().getFrame()) == JFileChooser.APPROVE_OPTION) {
            File file = new File(gridPane.getClient().getFileChooser().getSelectedFile().getAbsolutePath());

            try {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

                for (col = 0; col < gridPane.getTable().getColumnCount(); col++) {
                    colModel = gridPane.getTable().convertColumnIndexToModel(col);
                    buffer += (buffer.length() == 0 ? "" : ",") + "\"" + SLibUtils.textToAscii(gridPane.getModel().getGridColumns().get(colModel).getColumnTitle()) + "\"";
                }
                bw.write(buffer);

                for (row = 0; row < gridPane.getTable().getRowCount(); row++) {
                    buffer = "";

                    for (col = 0; col < gridPane.getTable().getColumnCount(); col++) {
                        buffer += (col == 0 ? "" : ",");
                        rowModel = gridPane.getTable().convertRowIndexToModel(row);
                        colModel = gridPane.getTable().convertColumnIndexToModel(col);

                        if (gridPane.getModel().getValueAt(rowModel, colModel) != null) {
                            colType = gridPane.getModel().getGridColumns().get(colModel).getColumnType();

                            switch (SGridUtils.getDataType(colType)) {
                                case SLibConsts.DATA_TYPE_BOOL:
                                    buffer += (((Boolean) gridPane.getModel().getValueAt(rowModel, colModel)).booleanValue() ? "1" : "0");
                                    break;
                                case SLibConsts.DATA_TYPE_INT:
                                    buffer += ((Number) gridPane.getModel().getValueAt(rowModel, colModel)).longValue();
                                    break;
                                case SLibConsts.DATA_TYPE_DEC:
                                    buffer += ((Number) gridPane.getModel().getValueAt(rowModel, colModel)).doubleValue();
                                    break;
                                case SLibConsts.DATA_TYPE_TEXT:
                                    buffer += "\"" + (!gridPane.getModel().getGridColumns().get(colModel).isApostropheOnCsvRequired() ? "" : "'") + SLibUtils.textToAscii(((String) gridPane.getModel().getValueAt(rowModel, colModel)).replaceAll("\"", "\"\"")) + "\"";
                                    break;
                                case SLibConsts.DATA_TYPE_DATE:
                                    switch (colType) {
                                        case SGridConsts.COL_TYPE_DATE:
                                            buffer += SLibUtils.CsvFormatDate.format((Date) gridPane.getModel().getValueAt(rowModel, colModel));
                                            break;
                                        case SGridConsts.COL_TYPE_DATE_DATETIME:
                                            buffer += SLibUtils.CsvFormatDatetime.format((Date) gridPane.getModel().getValueAt(rowModel, colModel));
                                            break;
                                        case SGridConsts.COL_TYPE_DATE_TIME:
                                            buffer += SLibUtils.CsvFormatTime.format((Date) gridPane.getModel().getValueAt(rowModel, colModel));
                                            break;
                                        default:
                                    }
                                    break;
                                default:
                                    buffer += "\"?\"";
                            }
                        }
                    }
                    bw.write("\n");
                    bw.write(SLibUtils.textToAscii(buffer));
                }

                bw.flush();
                bw.close();

                if (gridPane.getClient().showMsgBoxConfirm(SGuiConsts.MSG_FILE_SAVED + file.getPath() + "\n" + SGuiConsts.MSG_CNF_FILE_OPEN) == JOptionPane.YES_OPTION) {
                    SLibUtils.launchFile(file.getPath());
                }
            }
            catch (Exception e) {
                SLibUtils.showException(SGridUtils.class.getName(), e);
            }
        }
    }

    public static void computeRpn(SGridModel gridModel) {
        int col = 0;
        int row = 0;
        double a = 0;
        double b = 0;
        double value = 0;
        Deque<Object> stack = new ArrayDeque<Object>();
        Vector<SLibRpnArgument> arguments = null;
        SLibRpnOperator operator = null;

        try {
            for (col = 0; col < gridModel.getColumnCount(); col++) {
                arguments = gridModel.getGridColumns().get(col).getRpnArguments();

                if (arguments.size() > 0) {
                    for (row = 0; row < gridModel.getRowCount(); row++) {
                        for (SLibRpnArgument argument : arguments) {
                            if (argument.getArgumentType() == SLibRpnArgumentType.OPERAND) {
                                stack.addFirst(gridModel.getValueAtFieldName(row, (String) argument.getArgument()));
                            }
                            else if (argument.getArgumentType() == SLibRpnArgumentType.OPERATOR) {
                                if (stack.size() < 2) {
                                    throw new Exception(SLibConsts.ERR_MSG_RPN_ARGS_FEW);
                                }
                                else {
                                    b = stack.getFirst() instanceof Boolean ? (((Boolean) stack.pollFirst()) ? 1d : 0d) : ((Number) stack.pollFirst()).doubleValue();
                                    a = stack.getFirst() instanceof Boolean ? (((Boolean) stack.pollFirst()) ? 1d : 0d) : ((Number) stack.pollFirst()).doubleValue();
                                    operator = (SLibRpnOperator) argument.getArgument();

                                    switch (operator) {
                                        case ADDITION:
                                            stack.addFirst(a + b);
                                            break;
                                        case SUBTRACTION:
                                            stack.addFirst(a - b);
                                            break;
                                        case MULTIPLICATION:
                                            stack.addFirst(a * b);
                                            break;
                                        case DIVISION:
                                            stack.addFirst(b == 0d ? 0d : a / b);
                                            break;
                                        default:
                                    }
                                }
                            }
                        }

                        if (stack.size() == 1) {
                            value = ((Number) stack.pollFirst()).doubleValue();
                        }
                        else if (stack.size() < 1) {
                            throw new Exception(SLibConsts.ERR_MSG_RPN_ARGS_FEW);
                        }
                        else if (stack.size() > 1) {
                            throw new Exception(SLibConsts.ERR_MSG_RPN_ARGS_MANY);
                        }

                        gridModel.setValueAt(value, row, col);
                    }
                }
            }
        }
        catch (Exception e) {
            SLibUtils.showException(SGridUtils.class.getName(), e);
        }
    }

    public static String getSqlFilterDate(final String fieldName, final SGuiDate guiDate) {
        String sql = "";
        int[] date = null;

        switch (guiDate.getGuiType()) {
            case SGuiConsts.GUI_DATE_DATE:
                sql = fieldName + " = '" + SLibUtils.DbmsDateFormatDate.format(guiDate) + "' ";
                break;
            case SGuiConsts.GUI_DATE_MONTH:
                date = SLibTimeUtils.digestMonth(guiDate);
                sql = "(YEAR(" + fieldName + ") = " + date[0] + " AND MONTH(" + fieldName + ") = " + date[1] + ") ";
                break;
            case SGuiConsts.GUI_DATE_YEAR:
                date = SLibTimeUtils.digestYear(guiDate);
                sql = "YEAR(" + fieldName + ") = " + date[0] + " ";
                break;
            default:
        }

        return sql;
    }

    public static String getSqlFilterDateRange(final String fieldName, final Date[] range) {
        return fieldName + " BETWEEN '" + SLibUtils.DbmsDateFormatDate.format(range[0]) + "' AND '" + SLibUtils.DbmsDateFormatDate.format(range[1]) + "' ";
    }

    public static String getSqlFilterKey(final String[] fieldNames, final int[] key) {
        String sql = "";

        if (fieldNames.length == key.length) {
            for (int i = 0; i < fieldNames.length; i++) {
                if (key[i] != SLibConsts.UNDEFINED) {
                    sql += (sql.length() == 0 ? "" : "AND ") + fieldNames[i] + " = " + key[i] + " ";
                }
            }
        }

        return sql;
    }

    public static JButton createButton(final ImageIcon icon, final String toolTip, final ActionListener listener) {
        JButton button = new JButton(icon);

        button.setPreferredSize(new Dimension(23, 23));
        button.setToolTipText(toolTip);
        button.addActionListener(listener);

        return button;
    }

    public static JTextField createTextField(final String toolTip) {
        return createTextField(toolTip, new Dimension(100, 23));
    }

    public static JTextField createTextField(final String toolTip, final Dimension dimension) {
        JTextField textField = new JTextField();

        textField.setEditable(false);
        textField.setFocusable(false);
        textField.setPreferredSize(dimension);
        textField.setToolTipText(toolTip);

        return textField;
    }
}
