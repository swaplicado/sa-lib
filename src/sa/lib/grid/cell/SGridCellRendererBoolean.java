/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.SLibUtils;
import sa.lib.grid.SGridConsts;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellRendererBoolean extends DefaultTableCellRenderer {

    private ImageIcon moIconNull = new ImageIcon(getClass().getResource("/sa/lib/img/view_null.png"));
    private ImageIcon moIconTrue = new ImageIcon(getClass().getResource("/sa/lib/img/view_bool_true.gif"));
    private ImageIcon moIconFalse = new ImageIcon(getClass().getResource("/sa/lib/img/view_bool_false.gif"));
    private JLabel moLabel;
    private JCheckBox moCheckBox;

    public SGridCellRendererBoolean() {
        moLabel = new JLabel();
        moLabel.setHorizontalAlignment(SwingConstants.CENTER);
        moLabel.setOpaque(true);

        moCheckBox = new JCheckBox();
        moCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        moCheckBox.setOpaque(true);
    }

    public void setLabel(JLabel o) { moLabel = o; }

    public JLabel getLabel() { return moLabel; }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component component = null;

        if (table.isCellEditable(row, col)) {
            moCheckBox.setSelected((Boolean) value);

            if (isSelected) {
                moCheckBox.setForeground(SGridConsts.COLOR_FG_EDIT);
                moCheckBox.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_EDIT_FOCUS : SGridConsts.COLOR_BG_SELECT_EDIT);
            }
            else {
                moCheckBox.setForeground(SGridConsts.COLOR_FG_EDIT);
                moCheckBox.setBackground(SGridConsts.COLOR_BG_PLAIN_EDIT);
            }

            component = moCheckBox;
        }
        else {
            ImageIcon icon = value == null ? moIconNull : moIconFalse;

            if (value != null) {
                try {
                    if (((Boolean) value)) {
                        icon = moIconTrue;
                    }
                }
                catch (Exception e) {
                    SLibUtils.showException(this, e);
                }
            }

            moLabel.setIcon(icon);
            moLabel.setAlignmentY(SwingConstants.CENTER);

            if (isSelected) {
                moLabel.setForeground(SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_READ_FOCUS : SGridConsts.COLOR_BG_SELECT_READ);
            }
            else {
                moLabel.setForeground(SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(SGridConsts.COLOR_BG_PLAIN_READ);
            }

            component = moLabel;
        }

        return component;
    }
}
