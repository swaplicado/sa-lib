/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import sa.lib.SLibConsts;

/**
 *
 * @author Sergio Flores
 */
public final class SGuiCatalogueSettings {

    private String msCatalogueName;
    private int mnPrimaryKeyLength;
    private int mnForeignKeyLength;
    private boolean mbComplementApplying;
    private int mnComplementDataType;
    private boolean mbCodeApplying;
    private boolean mbCodeVisible;
    private boolean mbDateApplying;
    private boolean mbValueApplying;
    private boolean mbSelectionItemApplying;
    private String msSql;

    public SGuiCatalogueSettings(String catalogueName, int pkLength) {
        this(catalogueName, pkLength, 0, SLibConsts.UNDEFINED);
    }

    public SGuiCatalogueSettings(String catalogueName, int pkLength, int fkLength) {
        this(catalogueName, pkLength, fkLength, SLibConsts.UNDEFINED);
    }

    /**
     * @param catalogueName Catalogue name.
     * @param pkLength Primary key length.
     * @param fkLength Foreign key length.
     * @param complementDataType Complement data type (Constants defined in SLibConsts.DATA_TYPE_...).
     */
    public SGuiCatalogueSettings(String catalogueName, int pkLength, int fkLength, int complementDataType) {
        msCatalogueName = catalogueName;
        mnPrimaryKeyLength = pkLength;
        mnForeignKeyLength = fkLength;
        mbComplementApplying = complementDataType != SLibConsts.UNDEFINED;
        mnComplementDataType = complementDataType;
        mbCodeApplying = false;
        mbCodeVisible = true;
        mbDateApplying = false;
        mbValueApplying = false;
        mbSelectionItemApplying = true;
        msSql = "";
    }

    public void setCatalogueName(String name) { msCatalogueName = name; }
    public void setPrimaryKeyLength(int length) { mnPrimaryKeyLength = length; }
    public void setForeignKeyLength(int length) { mnForeignKeyLength = length; }
    public void setComplementApplying(boolean applying) { mbComplementApplying = applying; }
    public void setComplementDataType(int type) { mnComplementDataType = type; }
    public void setCodeApplying(boolean applying) { mbCodeApplying = applying; }
    public void setCodeVisible(boolean visible) { mbCodeVisible = visible; }
    public void setDateApplying(boolean applying) { mbDateApplying = applying; }
    public void setValueApplying(boolean applying) { mbValueApplying = applying; }
    public void setSelectionItemApplying(boolean b) { mbSelectionItemApplying = b; }
    public void setSql(String sql) { msSql = sql; }

    public String getCatalogueName() { return msCatalogueName; }
    public int getPrimaryKeyLength() { return mnPrimaryKeyLength; }
    public int getForeignKeyLength() { return mnForeignKeyLength; }
    public boolean isComplementApplying() { return mbComplementApplying; }
    public int getComplementDataType() { return mnComplementDataType; }
    public boolean isCodeApplying() { return mbCodeApplying; }
    public boolean isCodeVisible() { return mbCodeVisible; }
    public boolean isDateApplying() { return mbDateApplying; }
    public boolean isValueApplying() { return mbValueApplying; }
    public boolean isSelectionItemApplying() { return mbSelectionItemApplying; }
    public String getSql() { return msSql; }
    
    public void setCodeSettings(final boolean applying, final boolean visible) {
        mbCodeApplying = applying;
        mbCodeVisible = visible;
    }
}
