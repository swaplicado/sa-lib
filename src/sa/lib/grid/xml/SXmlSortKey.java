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
public class SXmlSortKey extends SXmlElement {

    public static final String NAME = "SortKey";
    public static final String ATT_COLUMN = "column";
    public static final String ATT_SORT_ORDER = "sortOrder";

    protected SXmlAttribute moColumn;
    protected SXmlAttribute moSortOrder;

    public SXmlSortKey() {
        super(NAME);

        moColumn = new SXmlAttribute(ATT_COLUMN);
        moSortOrder = new SXmlAttribute(ATT_SORT_ORDER);

        mvXmlAttributes.add(moColumn);
        mvXmlAttributes.add(moSortOrder);
    }
}
