/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import sa.lib.SLibUtils;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridModel;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellRendererNumberVariable extends DefaultTableCellRenderer {
    
    private NumberFormat[] moNumberFormats;
    private JLabel moLabel;
    
    public SGridCellRendererNumberVariable() {
        moNumberFormats = new NumberFormat[SGridConsts.MAX_DECS + 1];
        for (int i = 0; i < moNumberFormats.length; i++) {
            moNumberFormats[i] = new DecimalFormat("#,##0" + (i == 0 ? "" : "." + SLibUtils.textRepeat("0", i)));
        }
        
        moLabel = new JLabel();
        moLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        moLabel.setOpaque(true);
    }
    
    public void setLabel(JLabel o) { moLabel = o; }
    
    public JLabel getLabel() { return moLabel; }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        int nDecimals = ((SGridCellNumber) ((SGridModel) table.getModel()).getGridRows().get(table.convertRowIndexToModel(row))).getDecimals();
        double dNumber = 0;
        String sNumber = "";
        
        if (value == null) {
            sNumber = table.isCellEditable(row, col) ? "0" : "";
            value = new Integer(0);
        }
        else {
            try {
                dNumber = ((Number) value).doubleValue();
                sNumber = nDecimals >= 0 && nDecimals <= SGridConsts.MAX_DECS ? moNumberFormats[nDecimals].format(dNumber) : moNumberFormats[SGridConsts.MAX_DECS].format(dNumber);
            }
            catch (Exception e) {
                SLibUtils.showException(this, e);
            }
        }
        
        moLabel.setText(sNumber);
        
        if (isSelected) {
            if (table.isCellEditable(row, col)) {
                moLabel.setForeground(dNumber < 0 ? SGridConsts.COLOR_FG_EDIT_NEG : SGridConsts.COLOR_FG_EDIT);
                moLabel.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_EDIT_FOCUS : SGridConsts.COLOR_BG_SELECT_EDIT);
            }
            else {
                moLabel.setForeground(dNumber < 0 ? SGridConsts.COLOR_FG_READ_NEG : SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(hasFocus ? SGridConsts.COLOR_BG_SELECT_READ_FOCUS : SGridConsts.COLOR_BG_SELECT_READ);
            }
        }
        else {
            if (table.isCellEditable(row, col)) {
                moLabel.setForeground(dNumber < 0 ? SGridConsts.COLOR_FG_EDIT_NEG : SGridConsts.COLOR_FG_EDIT);
                moLabel.setBackground(SGridConsts.COLOR_BG_PLAIN_EDIT);
            }
            else {
                moLabel.setForeground(dNumber < 0 ? SGridConsts.COLOR_FG_READ_NEG : SGridConsts.COLOR_FG_READ);
                moLabel.setBackground(SGridConsts.COLOR_BG_PLAIN_READ);
            }
        }
        
        return moLabel;
    }
}
