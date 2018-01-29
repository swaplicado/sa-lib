/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.util.HashMap;
import sa.lib.SLibConsts;

/**
 *
 * @author Sergio Flores
 */
public class SGuiParams {

    protected int mnType;
    protected int mnSubtype;
    protected int[] manKey;
    protected boolean mbCopy;
    protected int[] manTypeKey;
    protected HashMap<Integer, Object> moParamsMap;
    protected HashMap<Integer, Object> moPostInitValuesMap;

    public SGuiParams() {
        this(SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, null, false);
    }

    public SGuiParams(int type) {
        this(type, SLibConsts.UNDEFINED, null, false);
    }

    public SGuiParams(int type, boolean copy) {
        this(type, SLibConsts.UNDEFINED, null, copy);
    }

    public SGuiParams(int type, int subtype) {
        this(type, subtype, null, false);
    }

    public SGuiParams(int type, int subtype, boolean copy) {
        this(type, subtype, null, copy);
    }

    public SGuiParams(int type, int[] key) {
        this(type, SLibConsts.UNDEFINED, key, false);
    }

    public SGuiParams(int type, int subtype, int[] key) {
        this(type, subtype, key, false);
    }

    public SGuiParams(int[] key) {
        this(SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, key, false);
    }

    public SGuiParams(int[] key, boolean copy) {
        this(SLibConsts.UNDEFINED, SLibConsts.UNDEFINED, key, copy);
    }

    public SGuiParams(int type, int subtype, int[] key, boolean copy) {
        mnType = type;
        mnSubtype = subtype;
        manKey = key;
        mbCopy = copy;
        manTypeKey = null;
        moParamsMap = new HashMap<Integer, Object>();
        moPostInitValuesMap = new HashMap<Integer, Object>();
    }

    public void setType(int n) { mnType = n; }
    public void setSubtype(int n) { mnSubtype = n; }
    public void setKey(int[] key) { manKey = key; }
    public void setCopy(boolean b) { mbCopy = b; }
    public void setTypeKey(int[] key) { manTypeKey = key; }

    public int getType() { return mnType; }
    public int getSubtype() { return mnSubtype; }
    public int[] getKey() { return manKey; }
    public boolean isCopy() { return mbCopy; }
    public int[] getTypeKey() { return manTypeKey; }

    public HashMap<Integer, Object> getParamsMap() { return moParamsMap; }
    public HashMap<Integer, Object> getPostInitValuesMap() { return moPostInitValuesMap; }
}
