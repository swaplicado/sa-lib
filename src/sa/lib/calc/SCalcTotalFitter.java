/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.calc;

import java.util.ArrayList;
import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public class SCalcTotalFitter {

    protected double mdTotalToFit;
    protected int mnDecimals;
    protected ArrayList<SCalcTotalElement> maElements;

    public SCalcTotalFitter(double totalToFit, int decimals) {
        mdTotalToFit = totalToFit;
        mnDecimals = decimals;
        maElements = new ArrayList<SCalcTotalElement>();
    }

    public double getTotalToFit() {
        return mdTotalToFit;
    }

    public int getDecimals() {
        return mnDecimals;
    }

    public ArrayList<SCalcTotalElement> getElements() {
        return maElements;
    }

    public void fitElements() {
        double total = 0;
        SCalcTotalElement biggest = null;

        for (SCalcTotalElement element : maElements) {
            total += element.getValue();
            if (biggest == null || element.getValue() >= biggest.getValue()) {
                biggest = element;
            }
        }

        if (biggest != null && SLibUtils.round(mdTotalToFit - total, mnDecimals) != 0) {
            biggest.setNewValue(biggest.getValue() + SLibUtils.round(mdTotalToFit - total, mnDecimals));
        }
    }
}
