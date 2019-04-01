/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.mail;

import java.util.Base64;

/**
 *
 * @author Sergio Flores
 */
public abstract class SMailUtils {
    
    /**
     * Encondes raw text for subject into an UTF-8 encoded and properly enclosed string ready to be used in mailing.
     * @param subject
     * @return 
     */
    public String encodeSubjectUtf8(final String subject) {
        return SMailConsts.SUBJECT_ENC_UTF8_BEGIN + 
                Base64.getEncoder().encodeToString(subject.getBytes()) + 
                SMailConsts.SUBJECT_ENC_UTF8_END;
    }
}
