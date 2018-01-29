/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

import java.util.Vector;
import javax.swing.JComboBox;
import sa.lib.gui.SGuiItem;

/**
 *
 * @author Sergio Flores
 */
public abstract class SGridCellUtils {

    /**
     * @param comboBox JComboBox populated with SGuiItem items.
     * @return Vector of SGridCellKeyValue objects for DGridCellRendererKey.
     */
    public static Vector<SGridCellKeyValue> createKeyValues(JComboBox comboBox) {
        SGuiItem item = null;
        Vector<SGridCellKeyValue> keyValues = new Vector<SGridCellKeyValue>();

        keyValues.add(new SGridCellKeyValue(null, "?"));

        for (int i = 1; i < comboBox.getItemCount(); i++) {
            item = (SGuiItem) comboBox.getItemAt(i);
            keyValues.add(new SGridCellKeyValue(item.getPrimaryKey(), item.getItem()));
        }

        return keyValues;
    }
}
