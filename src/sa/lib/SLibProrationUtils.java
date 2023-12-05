/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib;

/**
 *
 * @author Sergio Flores
 */
public abstract class SLibProrationUtils {
    
    public static double[] prorateAmount(final double amount, final double[] ratios) throws Exception {
        // validate ratios:
        
        if (ratios == null) {
            throw new Exception("No existen razones de prorrateo.");
        }
        
        if (ratios.length == 0) {
            throw new Exception("No hay razones de prorrateo.");
        }
        
        double totalRatios = 0;
        
        for (double ratio : ratios) {
            totalRatios += ratio;
        }
        
        totalRatios = SLibUtils.round(totalRatios, 4);
        
        if (totalRatios != 1.0) {
            throw new Exception("La suma de las razones de prorrateo es " + totalRatios + ", no es igual a 1.");
        }
        
        // prorate amount:
        
        double[] prorations = new double[ratios.length];
        double amoutProrated = 0;
        
        for (int i = 0; i < ratios.length; i++) {
            prorations[i] = SLibUtils.roundAmount(amount * ratios[i]);
            amoutProrated = SLibUtils.roundAmount(amoutProrated + prorations[i]);
        }
        
        // adjust prorations if necesary:
        
        if (!SLibUtils.compareAmount(amount, amoutProrated)) {
            double max = 0;
            int indexToAdjust = 0;
            
            for (int i = 0; i < prorations.length; i++) {
                if (prorations[i] >= max) {
                    max = prorations[i];
                    indexToAdjust = i;
                }
            }
            
            prorations[indexToAdjust] = SLibUtils.roundAmount(prorations[indexToAdjust] + SLibUtils.roundAmount(amount - amoutProrated));
        }
        
        return prorations;
    }
}
