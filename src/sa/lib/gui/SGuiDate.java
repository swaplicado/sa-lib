/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public class SGuiDate extends java.util.Date {

    private int mnGuiType;

    public SGuiDate(int guiType) {
        this(guiType, 0);
    }

    public SGuiDate(int guiType, long date) {
        super(date);
        mnGuiType = guiType;
    }

    public void setGuiType(int guiType) {
        mnGuiType = guiType;
    }

    public int getGuiType() {
        return mnGuiType;
    }
}
