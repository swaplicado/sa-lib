/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.SLibUtils;
import sa.lib.grid.SGridConsts;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellRenderereKey extends DefaultTableCellRenderer {
    protected JLabel moLabel;
    protected Vector<SGridCellKeyValue> mvKeyValues;

    public SGridCellRenderereKey(Vector<SGridCellKeyValue> keyValues) {
        mvKeyValues = new Vector<SGridCellKeyValue>();
        mvKeyValues.addAll(keyValues);
        moLabel = new JLabel();
        moLabel.setOpaque(true);
    }

    public void setLabel(JLabel o) { moLabel = o; }

    public JLabel getLabel() { return moLabel; }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String labelValue = "?";

        if (value != null) {
            for (SGridCellKeyValue keyValue : mvKeyValues) {
                if (SLibUtils.compareKeys((int[]) value, keyValue.Key)) {
                    labelValue = keyValue.Value;
                    break;
                }
            }
        }

        moLabel.setText(labelValue);

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
