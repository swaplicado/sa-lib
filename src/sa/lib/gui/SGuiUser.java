/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiUser {

    public int getPkUserId();
    public String getName();
    public int getFkUserTypeId();

    public boolean isAdministrator();
    public boolean isSupervisor();
    public boolean hasModuleAccess(final int module);
    public boolean hasPrivilege(final int privilege);
    public boolean hasPrivilege(final int[] privileges);
    public int getPrivilegeLevel(final int privilege);
    public HashMap<Integer, Integer> getPrivilegesMap();
    public HashSet<Integer> getModulesSet();
    public void computeAccess(final SGuiSession session) throws SQLException, Exception;
    public SGuiSessionCustom createDefaultUserSession(final SGuiClient client);
    public SGuiSessionCustom createDefaultUserSession(final SGuiClient client, final int terminal);
    public boolean showUserSessionConfigOnLogin();
}
