/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.lib.prt;

import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public abstract class SPrtUtils {

    public static java.lang.String formatText(java.lang.String text, int length, int align, int truncMode) {
        java.lang.String s = "";

        if (text.length() <= length) {
            switch (align) {
                case SPrtConsts.ALIGN_LEFT:
                    s = text + SLibUtils.textRepeat(" ", length - text.length());
                    break;
                case SPrtConsts.ALIGN_RIGHT:
                    s = SLibUtils.textRepeat(" ", length - text.length()) + text;
                    break;
                case SPrtConsts.ALIGN_CENTER:
                    s = SLibUtils.textRepeat(" ", ((length - text.length()) / 2)) + text + SLibUtils.textRepeat(" ", ((length - text.length()) / 2) + ((length - text.length()) % 2 == 0 ? 0 : 1));
                    break;
                default:
            }
        }
        else {
            if (truncMode == SPrtConsts.TRUNC_HIDE) {
                s = SLibUtils.textRepeat("*", length);
            }
            else if (truncMode == SPrtConsts.TRUNC_TRUNC) {
                switch (align) {
                    case SPrtConsts.ALIGN_LEFT:
                        s = text.substring(0, length);
                        break;
                    case SPrtConsts.ALIGN_RIGHT:
                        s = text.substring(text.length() - length);
                        break;
                    case SPrtConsts.ALIGN_CENTER:
                        s = text.substring((text.length() - length) / 2, ((text.length() - length) / 2) + length);
                        break;
                    default:
                }
            }
        }

        return s;
    }
}
