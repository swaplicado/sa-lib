/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import sa.lib.gui.SGuiItem;
import sa.lib.gui.SGuiUtils;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellEditorKey extends AbstractCellEditor implements TableCellEditor {

    protected JComboBox moComboBox;

    public SGridCellEditorKey(JComboBox comboBox) {
        moComboBox = comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return ((SGuiItem) moComboBox.getSelectedItem()).getPrimaryKey();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        SGuiUtils.locateItem(moComboBox, (int[]) value);
        return moComboBox;
    }
}
