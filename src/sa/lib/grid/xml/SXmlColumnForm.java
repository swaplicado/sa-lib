/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.xml;

import sa.lib.xml.SXmlAttribute;

/**
 *
 * @author Sergio Flores
 */
public class SXmlColumnForm extends SXmlColumn {

    public static final String ATT_IS_EDITABLE = "isEditable";

    protected SXmlAttribute moIsEditable;

    public SXmlColumnForm() {
        moIsEditable = new SXmlAttribute(ATT_IS_EDITABLE);

        mvXmlAttributes.add(moIsEditable);
    }
}
