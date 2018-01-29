/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.util.HashMap;
import java.util.Vector;
import javax.swing.JComboBox;
import sa.lib.db.SDbRegistry;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiController {

    public SDbRegistry getRegistry(final int type, final SGuiParams params);
    public void populateCatalogue(JComboBox comboBox, final int type, final int subtype, final SGuiParams params);
    public void showView(final int type, final int subtype, final SGuiParams params);
    public void showOptionPicker(final int type, final int subtype, final SGuiParams params, final SGuiField field);
    public void showForm(final int type, final int subtype, final SGuiParams params);
    public void printReport(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap);
    public void printReportNow(final int type, final int subtype, final SGuiParams params, final HashMap<String, Object> paramsMap, final boolean withPrintDialog);
    public Vector<SGuiItem> readItems(final int type, final int subtype, final SGuiParams params);
    public SDbRegistry readRegistry(final int type, final int[] pk);
    public SDbRegistry readRegistry(final int type, final int[] pk, final int mode);
    public int saveRegistry(final SDbRegistry registry);
    public int disableRegistry(final int type, final int[] pk);
    public int deleteRegistry(final int type, final int[] pk);
    public Object readField(final int type, final int[] pk, final int field);
    public int saveField(final int type, final int[] pk, final int field, final Object value);
}
