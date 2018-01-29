/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid;

import java.awt.event.MouseEvent;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public final class SGridHeader extends JTableHeader {

    private String[] masHeaderToolTips;

    public SGridHeader(TableColumnModel tableColumnModel, String[] headerToolTips) {
        super(tableColumnModel);
        masHeaderToolTips = headerToolTips;
    }

    public void setHeaderToolTips(String[] as) { masHeaderToolTips = as; }

    public String[] getHeaderToolTips() { return masHeaderToolTips; }

    @Override
    public java.lang.String getToolTipText(MouseEvent evt) {
        int col = 0;
        int colModel = 0;
        String toolTip = "";

        col = columnAtPoint(evt.getPoint());

        if (col != -1 && col < getColumnModel().getColumnCount()) {
            colModel = getTable().convertColumnIndexToModel(col);
            try {
                toolTip = masHeaderToolTips[colModel];
            }
            catch (NullPointerException e) {
                SLibUtils.printException(this, e);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                SLibUtils.printException(this, e);
            }
        }

        return toolTip.length() == 0 ? null : toolTip;
    }
}
