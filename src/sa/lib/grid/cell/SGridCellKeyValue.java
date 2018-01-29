/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.cell;

/**
 *
 * @author Sergio Flores
 */
public class SGridCellKeyValue {
    public int[] Key;
    public String Value;

    public SGridCellKeyValue() {
        Key = null;
        Value = "";
    }

    public SGridCellKeyValue(int[] key, String value) {
        Key = key;
        Value = value;
    }
}
