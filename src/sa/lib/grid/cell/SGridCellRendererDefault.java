/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.grid.SGridConsts;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellRendererDefault extends DefaultTableCellRenderer {

    private JLabel moLabel;

    public SGridCellRendererDefault() {
        moLabel = new JLabel();
        moLabel.setOpaque(true);
    }

    public void setLabel(JLabel o) { moLabel = o; }

    public JLabel getLabel() { return moLabel; }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        moLabel.setText(value == null ? "" : value.toString());

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
