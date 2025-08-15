/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.mail;

import java.util.Base64;
import java.util.regex.Pattern;

/**
 *
 * @author Sergio Flores
 */
public abstract class SMailUtils {
    
    // Reasonable email regex (not 100% RFC 5322 strict, but practical)
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Validates if given email matches the reasonable email regex.
     * @param email
     * @return 
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Encondes raw text for subject into an UTF-8 encoded and properly enclosed string ready to be used in mailing.
     * @param subject
     * @return 
     */
    public static String encodeSubjectUtf8(final String subject) {
        return SMailConsts.SUBJECT_ENC_UTF8_BEGIN + 
                Base64.getEncoder().encodeToString(subject.getBytes()) + 
                SMailConsts.SUBJECT_ENC_UTF8_END;
    }
}
