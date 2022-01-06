/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.db;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiParams;
import sa.lib.gui.SGuiSession;
import sa.lib.srv.SSrvLock;
import sa.lib.srv.redis.SRedisLock;

/**
 *
 * @author Sergio Flores
 */
public abstract class SDbRegistry implements Serializable {

    public static final int FIELD_CODE = 1001;
    public static final int FIELD_NAME = 1002;
    public static final int FIELD_NAME_ABBR = 1003;
    public static final int FIELD_BASE = 2000;
    public static final int FIELD_TS_USR = 2101;
    public static final int FIELD_TS_USR_INS = 2111;
    public static final int FIELD_TS_USR_UPD = 2112;

    protected int mnRegistryType;
    protected boolean mbRegistryNew;
    protected boolean mbRegistryEdited;
    protected int mnFormAction;
    protected long mlTimeout;
    protected Object moPostSaveTarget;
    protected Method moPostSaveMethod;
    protected Object[] maoPostSaveMethodArgs;
    protected ArrayList<SSrvLock> maLocks;
/* Bloque de codigo correspondiente a los candados de Redis    
    protected ArrayList<SRedisLock> maRedisLocks;
*/
    protected int mnQueryResultId;
    protected String msQueryResult;
    protected String msSql;

    public SDbRegistry(int type) {
        mnRegistryType = type;
        initRegistry();
        initQueryMembers();
    }

    /*
     * Initialization methods:
     */

    protected void initBaseRegistry() {
        mbRegistryNew = true;
        mbRegistryEdited = false;
        mnFormAction = SLibConsts.UNDEFINED;
        mlTimeout = 1000 * 60 * 15; // 15 min
        moPostSaveTarget = null;
        moPostSaveMethod = null;
        maoPostSaveMethodArgs = null;
        maLocks = new ArrayList<>();
/* Bloque de codigo correspondiente a los candados de Redis        
        maRedisLocks = new ArrayList<>();
*/    
    }

    public void initQueryMembers() {
        mnQueryResultId = SLibConsts.UNDEFINED;
        msQueryResult = "";
        msSql = "";
    }

    public void initPrimaryKey() {
        setPrimaryKey(new int[getPrimaryKey().length]);
    }

    public void postInitMembers(final SGuiParams params) {
        /* To be overriden when specific registry members values are needed on new SDbRegistry objects;
         * invoked sistematically on getRegistry() class SGuiModule method.
         */
    }

    /*
     * Setter/getter methods:
     */

    public void setRegistryNew(boolean b) { mbRegistryNew = b; }
    public void setRegistryEdited(boolean b) { mbRegistryEdited = b; }
    public void setFormAction(int n) { mnFormAction = n; }
    public void setTimeout(long l) { mlTimeout = l; }
    public void setPostSaveTarget(Object o) { moPostSaveTarget = o; }
    public void setPostSaveMethod(Method o) { moPostSaveMethod = o; }
    public void setPostSaveMethodArgs(Object[] ao) { maoPostSaveMethodArgs = ao; }

    public void setQueryResultId(int n) { mnQueryResultId = n; }
    public void setQueryResult(String s) { msQueryResult = s; }
    public void setSql(String s) { msSql = s; }

    public int getRegistryType() { return mnRegistryType; }
    public boolean isRegistryNew() { return mbRegistryNew; }
    public boolean isRegistryEdited() { return mbRegistryEdited; }
    public int getFormAction() { return mnFormAction; }
    public long getTimeout() { return mlTimeout; }
    public Object getPostSaveTarget() { return moPostSaveTarget; }
    public Method getPostSaveMethod() { return moPostSaveMethod; }
    public Object[] getPostSaveMethodArgs() { return maoPostSaveMethodArgs; }
    public ArrayList<SSrvLock> getLocks() { return maLocks; }
/* Bloque de codigo correspondiente a los candados de Redis    
    public ArrayList<SRedisLock> getRedisLocks() { return maRedisLocks; }
*/
    public int getQueryResultId() { return mnQueryResultId; }
    public String getQueryResult() { return msQueryResult; }
    public String getSql() { return msSql; }

    public void setCode(String s) { throw new UnsupportedOperationException("Not supported yet."); }
    public void setName(String s) { throw new UnsupportedOperationException("Not supported yet."); }
    public void setDeleted(boolean b) { throw new UnsupportedOperationException("Not supported yet."); }

    public String getCode() { throw new UnsupportedOperationException("Not supported yet."); }
    public String getName() { throw new UnsupportedOperationException("Not supported yet."); }
    public boolean isDeleted() { throw new UnsupportedOperationException("Not supported yet."); }

    /*
     * Public static methods:
     */

    public static boolean isBaseRegistryField(final int field) {
        return SLibUtils.belongsTo(field, new int[] { FIELD_CODE, FIELD_NAME, FIELD_NAME_ABBR });
    }

    /*
     * Abstract methods:
     */

    public abstract void setPrimaryKey(final int[] pk);
    public abstract int[] getPrimaryKey();
    public abstract void initRegistry();

    public abstract String getSqlTable();
    public abstract String getSqlWhere();
    public abstract String getSqlWhere(final int[] pk);

    public abstract void computePrimaryKey(final SGuiSession session) throws SQLException, Exception;
    public abstract void read(final SGuiSession session, final int[] pk) throws SQLException, Exception;
    public abstract void save(final SGuiSession session) throws SQLException, Exception;

    @Override
    public abstract SDbRegistry clone() throws CloneNotSupportedException;

    /*
     * Overridable methods:
     */

    public String getSqlFromWhere() {
        return "FROM " + getSqlTable() + " " + getSqlWhere();
    }

    public String getSqlFromWhere(final int[] pk) {
        return "FROM " + getSqlTable() + " " + getSqlWhere(pk);
    }

    public boolean canSave(final SGuiSession session) throws SQLException, Exception {
        initQueryMembers();
        return true;
    }

    public boolean canDisable(final SGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean canDelete(final SGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void disable(final SGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void delete(final SGuiSession session) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void verifyRegistryNew(final SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        msSql = "SELECT COUNT(*) " + getSqlFromWhere();
        resultSet = session.getStatement().executeQuery(msSql);

        if (resultSet.next()) {
            mbRegistryNew = resultSet.getInt(1) == 0;
        }
    }

    public Object readField(final Statement statement, final int[] pk, final int field) throws SQLException, Exception {
        Object value = null;
        ResultSet resultSet = null;

        initQueryMembers();
        mnQueryResultId = SDbConsts.READ_ERROR;

        msSql = "SELECT ";

        switch (field) {
            case FIELD_CODE:
                msSql += "code ";
                break;
            case FIELD_NAME:
                msSql += "name ";
                break;
            case FIELD_NAME_ABBR:
                msSql += "name_abbr ";
                break;
            case FIELD_TS_USR:
                msSql += "ts_usr ";
                break;
            case FIELD_TS_USR_INS:
                msSql += "ts_usr_ins ";
                break;
            case FIELD_TS_USR_UPD:
                msSql += "ts_usr_upd ";
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        msSql += getSqlFromWhere(pk);

        resultSet = statement.executeQuery(msSql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            switch (field) {
                case FIELD_CODE:
                case FIELD_NAME:
                case FIELD_NAME_ABBR:
                    value = resultSet.getString(1);
                    break;
                case FIELD_TS_USR:
                case FIELD_TS_USR_INS:
                case FIELD_TS_USR_UPD:
                    value = resultSet.getTimestamp(1);
                    break;
                default:
            }
        }

        mnQueryResultId = SDbConsts.READ_OK;
        return value;
    }

    public void saveField(final Statement statement, final int[] pk, final int field, final Object value) throws SQLException, Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
