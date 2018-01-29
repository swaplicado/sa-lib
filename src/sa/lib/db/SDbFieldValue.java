/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.db;

/**
 *
 * @author Sergio Flores
 */
public class SDbFieldValue {

    protected Object moValue;
    private int mnFkUserId;

    public SDbFieldValue(Object value, int userId) {
        moValue = value;
        mnFkUserId = userId;
    }

    public void setValue(Object Value) {
        this.moValue = Value;
    }

    public void setFkUserId(int FkUserId) {
        this.mnFkUserId = FkUserId;
    }

    public Object getValue() {
        return moValue;
    }

    public int getFkUserId() {
        return mnFkUserId;
    }
}
