/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib;

import java.util.Arrays;

/**
 *
 * @author Sergio Flores
 */
public class SLibKey {

    protected int[] manPrimaryKey;

    public SLibKey(int[] pk) {
        manPrimaryKey = pk;
    }

    public void setPrimaryKey(int[] pk) { manPrimaryKey = pk; }

    public int[] getPrimaryKey() { return manPrimaryKey; }

    @Override
    public boolean equals(Object object) {
        return object instanceof SLibKey && SLibUtils.compareKeys(manPrimaryKey, ((SLibKey) object).getPrimaryKey());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Arrays.hashCode(this.manPrimaryKey);
        return hash;
    }
}
