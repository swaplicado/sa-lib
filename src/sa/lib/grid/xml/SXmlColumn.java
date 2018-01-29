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
public abstract class SXmlColumn extends SXmlElement {

    public static final String NAME = "Column";
    public static final String ATT_COLUMN_TYPE = "columnType";
    public static final String ATT_COLUMN_TITLE = "columnTitle";
    public static final String ATT_COLUMN_WIDTH = "columnWidth";
    public static final String ATT_FIELD_NAME = "fieldName";

    protected SXmlAttribute moColumnType;
    protected SXmlAttribute moColumnTitle;
    protected SXmlAttribute moColumnWidth;
    protected SXmlAttribute moFieldName;

    public SXmlColumn() {
        super(NAME);

        moColumnType = new SXmlAttribute(ATT_COLUMN_TYPE);
        moColumnTitle = new SXmlAttribute(ATT_COLUMN_TITLE);
        moColumnWidth = new SXmlAttribute(ATT_COLUMN_WIDTH);
        moFieldName = new SXmlAttribute(ATT_FIELD_NAME);

        mvXmlAttributes.add(moColumnType);
        mvXmlAttributes.add(moColumnTitle);
        mvXmlAttributes.add(moColumnWidth);
        mvXmlAttributes.add(moFieldName);
    }
}
