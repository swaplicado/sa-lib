/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.gui.bean;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import sa.lib.SLibTimeConsts;
import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public class SBeanFieldCalendarWeek extends SBeanFieldCalendarMonth {
    
    public SBeanFieldCalendarWeek() {
        super();
        mnMaxInteger = SLibTimeConsts.WEEK_MAX;
        
        setModel(new SpinnerNumberModel(mnDefaultValue, mnMinInteger, mnMaxInteger, 1));
        setEditor(new JSpinner.NumberEditor(this, SLibUtils.DecimalFormatCalendarMonth.toPattern()));

        setPreferredSize(new Dimension(50, 23));

        ((JSpinner.DefaultEditor) getEditor()).getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                processKeyPressed(evt);
            }
        });

        resetField();
    }
}
