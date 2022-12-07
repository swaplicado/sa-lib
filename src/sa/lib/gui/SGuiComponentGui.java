/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.awt.Component;
import java.util.HashMap;

/**
 *
 * @author Sergio Flores
 */
public class SGuiComponentGui {

    protected SGuiSession moSession;
    protected Component moComponentGui;
    protected HashMap<Integer, Integer> moUserPrivilegesMap; // map of <privilege, level access>
    
    public SGuiComponentGui(SGuiSession session, Component component) {
        moSession = session;
        moComponentGui = component;
        moUserPrivilegesMap = new HashMap<>();
    }
    
    public Component getComponentGui() { return  moComponentGui; }
    public HashMap<Integer, Integer> getUserPrivilegesMap() { return moUserPrivilegesMap; }
}
