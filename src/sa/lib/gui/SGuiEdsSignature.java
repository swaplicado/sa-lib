/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui;

/**
 *
 * @author Sergio Flores
 */
public interface SGuiEdsSignature {

    public String signText(final String textToSign, final int year);
    public String getCertificateNumber();
    public String getCertificateBase64();
    public int getCertificateId();
}
