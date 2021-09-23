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
public class SXmlFilter extends SXmlElement {

    public static final String NAME = "Filter";
    public static final String ATT_FILTER_TYPE = "filterType";
    public static final String ATT_DATA_TYPE = "dataType";
    public static final String ATT_VALUE = "value";

    protected SXmlAttribute moType;
    protected SXmlAttribute moDataType;
    protected SXmlAttribute moValue;

    public SXmlFilter() {
        super(NAME);

        moType = new SXmlAttribute(ATT_FILTER_TYPE);
        moDataType = new SXmlAttribute(ATT_DATA_TYPE);
        moValue = new SXmlAttribute(ATT_VALUE);

        mvXmlAttributes.add(moType);
        mvXmlAttributes.add(moDataType);
        mvXmlAttributes.add(moValue);
    }
}
