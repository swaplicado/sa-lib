/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SGridFilterDatePeriod.java
 *
 * Created on 4/09/2011, 11:56:10 PM
 */

package sa.lib.grid;

import javax.swing.JPanel;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiDate;
import sa.lib.gui.SGuiDatePicker;

/**
 *
 * @author Sergio Flores
 */
public class SGridFilterDatePeriod extends JPanel implements SGridFilter {

    protected SGuiClient miClient;
    protected SGridPaneView moPaneView;
    int mnDatePickerType;
    protected SGuiDatePicker moDatePicker;
    protected SGuiDate moDate;

    /** Creates new form SGridFilterDatePeriod */
    public SGridFilterDatePeriod(SGuiClient client, SGridPaneView paneView, int datePickerType) {
        miClient = client;
        moPaneView = paneView;
        mnDatePickerType = datePickerType;
        initComponents();
        initComponentsCustom();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfPeriod = new javax.swing.JTextField();
        jbPeriod = new javax.swing.JButton();
        jbCurrentDate = new javax.swing.JButton();
        jbCurrentMonth = new javax.swing.JButton();
        jbCurrentYear = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(0, 5, 0));

        jtfPeriod.setEditable(false);
        jtfPeriod.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jtfPeriod.setText("01/01/2001");
        jtfPeriod.setToolTipText("Período");
        jtfPeriod.setFocusable(false);
        jtfPeriod.setPreferredSize(new java.awt.Dimension(65, 23));
        add(jtfPeriod);

        jbPeriod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cal_cal.gif"))); // NOI18N
        jbPeriod.setToolTipText("Seleccionar período");
        jbPeriod.setPreferredSize(new java.awt.Dimension(23, 23));
        jbPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbPeriodActionPerformed(evt);
            }
        });
        add(jbPeriod);

        jbCurrentDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cal_date_day.gif"))); // NOI18N
        jbCurrentDate.setToolTipText("Día actual");
        jbCurrentDate.setPreferredSize(new java.awt.Dimension(23, 23));
        jbCurrentDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCurrentDateActionPerformed(evt);
            }
        });
        add(jbCurrentDate);

        jbCurrentMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cal_date_month.gif"))); // NOI18N
        jbCurrentMonth.setToolTipText("Mes actual");
        jbCurrentMonth.setPreferredSize(new java.awt.Dimension(23, 23));
        jbCurrentMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCurrentMonthActionPerformed(evt);
            }
        });
        add(jbCurrentMonth);

        jbCurrentYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cal_date_year.gif"))); // NOI18N
        jbCurrentYear.setToolTipText("Año actual");
        jbCurrentYear.setPreferredSize(new java.awt.Dimension(23, 23));
        jbCurrentYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCurrentYearActionPerformed(evt);
            }
        });
        add(jbCurrentYear);
    }// </editor-fold>//GEN-END:initComponents

    private void jbPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbPeriodActionPerformed
        moDatePicker.resetPicker();
        moDatePicker.setOption(moDate);
        moDatePicker.setVisible(true);

        if (moDatePicker.getPickerResult() == SGuiConsts.FORM_RESULT_OK) {
            setPeriod(moDatePicker.getOption());
            moPaneView.putFilter(SGridConsts.FILTER_DATE_PERIOD, moDate);
        }
    }//GEN-LAST:event_jbPeriodActionPerformed

    private void jbCurrentDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCurrentDateActionPerformed
        setPeriod(new SGuiDate(SGuiConsts.GUI_DATE_DATE, miClient.getSession().getWorkingDate().getTime()));
        moPaneView.putFilter(SGridConsts.FILTER_DATE_PERIOD, moDate);
    }//GEN-LAST:event_jbCurrentDateActionPerformed

    private void jbCurrentMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCurrentMonthActionPerformed
        if (mnDatePickerType == SGuiConsts.DATE_PICKER_DATE) {
            setPeriod(new SGuiDate(SGuiConsts.GUI_DATE_DATE, SLibTimeUtils.getEndOfMonth(miClient.getSession().getWorkingDate()).getTime()));
        }
        else {
            setPeriod(new SGuiDate(SGuiConsts.GUI_DATE_MONTH, miClient.getSession().getWorkingDate().getTime()));
        }
        moPaneView.putFilter(SGridConsts.FILTER_DATE_PERIOD, moDate);
    }//GEN-LAST:event_jbCurrentMonthActionPerformed

    private void jbCurrentYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCurrentYearActionPerformed
        if (mnDatePickerType == SGuiConsts.DATE_PICKER_DATE) {
            setPeriod(new SGuiDate(SGuiConsts.GUI_DATE_DATE, SLibTimeUtils.getEndOfYear(miClient.getSession().getWorkingDate()).getTime()));
        }
        else {
            setPeriod(new SGuiDate(SGuiConsts.GUI_DATE_YEAR, miClient.getSession().getWorkingDate().getTime()));
        }
        moPaneView.putFilter(SGridConsts.FILTER_DATE_PERIOD, moDate);
    }//GEN-LAST:event_jbCurrentYearActionPerformed

    private void initComponentsCustom() {
        switch (mnDatePickerType) {
            case SGuiConsts.DATE_PICKER_DATE:
                moDatePicker = new SGuiDatePicker(miClient, mnDatePickerType);
                jtfPeriod.setToolTipText("Fecha de corte");
                jbPeriod.setToolTipText("Seleccionar fecha de corte");
                break;
            case SGuiConsts.DATE_PICKER_DATE_PERIOD:
                moDatePicker = new SGuiDatePicker(miClient, mnDatePickerType);
                jtfPeriod.setToolTipText("Período");
                jbPeriod.setToolTipText("Seleccionar período");
                break;
            default:
                miClient.showMsgBoxError(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        setPeriod(null);
    }

    private void renderPeriod() {
        int[] date = null;

        if (moDate == null) {
            jtfPeriod.setText("");
        }
        else {
            switch (moDate.getGuiType()) {
                case SGuiConsts.GUI_DATE_DATE:
                    jtfPeriod.setText(SLibUtils.DateFormatDate.format(moDate));
                    break;
                case SGuiConsts.GUI_DATE_MONTH:
                    date = SLibTimeUtils.digestMonth(moDate);
                    jtfPeriod.setText(SLibUtils.DecimalFormatCalendarYear.format(date[0]) + "-" + SLibUtils.DecimalFormatCalendarMonth.format(date[1]));
                    break;
                case SGuiConsts.GUI_DATE_YEAR:
                    date = SLibTimeUtils.digestYear(moDate);
                    jtfPeriod.setText(SLibUtils.DecimalFormatCalendarYear.format(date[0]));
                    break;
                default:
            }
        }
    }

    private void setPeriod(SGuiDate date) {
        moDate = date;
        renderPeriod();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbCurrentDate;
    private javax.swing.JButton jbCurrentMonth;
    private javax.swing.JButton jbCurrentYear;
    private javax.swing.JButton jbPeriod;
    private javax.swing.JTextField jtfPeriod;
    // End of variables declaration//GEN-END:variables

    /**
     * @param value Date as sa.lib.gui.SGuiDate.
     */
    @Override
    public void initFilter(final Object value) {
        setPeriod((SGuiDate) value);
        moPaneView.getFiltersMap().put(SGridConsts.FILTER_DATE_PERIOD, moDate);
    }
}
