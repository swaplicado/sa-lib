/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.xml;

import sa.lib.xml.SXmlAttribute;
import sa.lib.xml.SXmlElement;

/**
 *
 * @author Sergio Flores
 */
public class SXmlRpnArgument extends SXmlElement {

    public static final String NAME = "RpnArgument";
    public static final String ATT_ARGUMENT_TYPE = "argumentType";
    public static final String ATT_ARGUMENT = "argument";

    protected SXmlAttribute moArgumentType;
    protected SXmlAttribute moArgument;

    public SXmlRpnArgument() {
        super(NAME);

        moArgumentType = new SXmlAttribute(ATT_ARGUMENT_TYPE);
        moArgument = new SXmlAttribute(ATT_ARGUMENT);

        mvXmlAttributes.add(moArgumentType);
        mvXmlAttributes.add(moArgument);
    }
}
