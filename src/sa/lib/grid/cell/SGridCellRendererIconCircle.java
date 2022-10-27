/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.grid.SGridConsts;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellRendererIconCircle extends DefaultTableCellRenderer {

    public static final ImageIcon moIconNull = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_null.png"));
    public static final ImageIcon moIconWhite = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_white.png"));
    public static final ImageIcon moIconRed = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_red.png"));
    public static final ImageIcon moIconYellow = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_yellow.png"));
    public static final ImageIcon moIconGreen = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_green.png"));
    public static final ImageIcon moIconCyan = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_cyan.png"));
    public static final ImageIcon moIconBlue = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_blue.png"));
    public static final ImageIcon moIconMagenta = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_magenta.png"));
    public static final ImageIcon moIconBlack = new ImageIcon(new Object().getClass().getResource("/sa/lib/img/view_circ_black.png"));

    private JLabel moLabel;

    public SGridCellRendererIconCircle() {
        moLabel = new JLabel();
        moLabel.setOpaque(true);
        moLabel.setHorizontalAlignment(JLabel.CENTER);
    }

    public void setLabel(JLabel o) { moLabel = o; }

    public JLabel getLabel() { return moLabel; }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        int icon = SLibConsts.UNDEFINED;

        try {
            icon = value == null ? SGridConsts.ICON_NULL : ((Number) value).intValue();
        }
        catch (java.lang.Exception e) {
            SLibUtils.showException(this, e);
        }

        switch (icon) {
            case SGridConsts.ICON_CIRC_WHITE:
                moLabel.setIcon(moIconWhite);
                break;
            case SGridConsts.ICON_CIRC_RED:
                moLabel.setIcon(moIconRed);
                break;
            case SGridConsts.ICON_CIRC_YELLOW:
                moLabel.setIcon(moIconYellow);
                break;
            case SGridConsts.ICON_CIRC_GREEN:
                moLabel.setIcon(moIconGreen);
                break;
            case SGridConsts.ICON_CIRC_CYAN:
                moLabel.setIcon(moIconCyan);
                break;
            case SGridConsts.ICON_CIRC_BLUE:
                moLabel.setIcon(moIconBlue);
                break;
            case SGridConsts.ICON_CIRC_MAGENTA:
                moLabel.setIcon(moIconMagenta);
                break;
            case SGridConsts.ICON_CIRC_BLACK:
                moLabel.setIcon(moIconBlack);
                break;
            default:
                moLabel.setIcon(moIconNull);
        }

        if (isSelected) {
            if (table.isCellEditable(row, col)) {
                moLabel.setForeground(SGridConsts.COLOR_FG_EDIT);
                moLabel.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_EDIT_FOCUS : SGridConsts.COLOR_BG_SELECT_EDIT);
            }
            else {
                moLabel.setForeground(SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_READ_FOCUS : SGridConsts.COLOR_BG_SELECT_READ);
            }
        }
        else {
            if (table.isCellEditable(row, col)) {
                moLabel.setForeground(SGridConsts.COLOR_FG_EDIT);
                moLabel.setBackground(SGridConsts.COLOR_BG_PLAIN_EDIT);
            }
            else {
                moLabel.setForeground(SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(SGridConsts.COLOR_BG_PLAIN_READ);
            }
        }

        return moLabel;
    }
}
