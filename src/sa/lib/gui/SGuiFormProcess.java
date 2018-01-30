/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFormProcess {

    public int getFormType();
    public int getFormSubtype();
    public int getFormResult();

    public void setFormSettings(final SGuiClient client, final int formType, final int formSubtype, final String title);
    public void setFormVisible(final boolean visible);

    public void setValue(final int type, final Object value);
    public Object getValue(final int type);

    public void actionPrev();
    public void actionNext();
    public void actionFinish();
    public void actionCancel();
}
