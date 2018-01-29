/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.grid;

import java.util.ArrayList;

/**
 *
 * @author Sergio Flores
 */
public final class SGridRowOptionPicker extends SGridRowCustom {

    private Object moMainOption;
    private ArrayList<Object> maValues;

    public SGridRowOptionPicker() {
        this(null);
    }

    public SGridRowOptionPicker(int[] pk) {
        super(pk, "", "");
        moMainOption = null;
        maValues = new ArrayList<Object>();
    }

    public void setMainOption(Object o) { moMainOption = o; }

    public Object getMainOption() { return moMainOption; }

    public ArrayList<Object> getValues() { return maValues; }

    @Override
    public Object getRowValueAt(int col) {
        Object value = null;

        if (col >= 0 && col < maValues.size()) {
            value = maValues.get(col);
        }

        return value;
    }

    @Override
    public void setRowValueAt(Object value, int col) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
