/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

import java.sql.Statement;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import sa.lib.db.SDbDatabase;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiClient {

    public JFrame getFrame();
    public JTabbedPane getTabbedPane();
    public SDbDatabase getSysDatabase();
    public Statement getSysStatement();

    public SGuiSession getSession();
    public SGuiDatePicker getDatePicker();
    public SGuiDateRangePicker getDateRangePicker();
    public SGuiYearPicker getYearPicker();
    public SGuiYearMonthPicker getYearMonthPicker();
    public JFileChooser getFileChooser();
    public ImageIcon getImageIcon(final int icon);
    public SGuiUserGui readUserGui(final int[] key);
    public SGuiUserGui saveUserGui(final int[] key, final String gui);
    public HashMap<String, Object> createReportParams();
    public String getTableCompany();
    public String getTableUser();
    public String getAppName();
    public String getAppRelease();
    public String getAppCopyright();
    public String getAppProvider();

    public void showMsgBoxError(final String msg);
    public void showMsgBoxWarning(final String msg);
    public void showMsgBoxInformation(final String msg);
    public int showMsgBoxConfirm(final String msg);
}
