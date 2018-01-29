/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import sa.lib.db.SDbRegistry;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiFormOwner {

    public boolean validateRegistryNew(final SDbRegistry registry);
    public boolean validateRegistryEdit(final SDbRegistry registry);
}
