/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.db;

import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public final class SDbDatabaseMonitor extends Thread {

    public static final long DELAY_15_MIN = 1000 * 60 * 15;
    public static final long DELAY_30_MIN = 1000 * 60 * 30;
    public static final long DELAY_60_MIN = 1000 * 60 * 60;

    private SDbDatabase moDatabase;
    private long mlMonitorDelay;
    private volatile boolean mbActive;

    public SDbDatabaseMonitor(SDbDatabase database) {
        this(database, DELAY_15_MIN);
    }

    public SDbDatabaseMonitor(SDbDatabase database, long delay) {
        moDatabase = database;
        mlMonitorDelay = delay;
        mbActive = false;
    }

    public void startThread() {
        mbActive = true;
        setDaemon(true);
        super.start();
    }

    public void stopThread() {
        mbActive = false;
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        while (mbActive) {
            try {
                sleep(mlMonitorDelay);

                if (moDatabase.monitorConnection() != SDbConsts.CONNECTION_OK) {
                    throw new Exception(SDbConsts.ERR_MSG_DB_CLOSED);
                }
            }
            catch (InterruptedException e) {
                SLibUtils.printException(this, e);
            }
            catch (Exception e) {
                SLibUtils.printException(this, e);
            }
        }

        moDatabase.disconnect();
    }
}
