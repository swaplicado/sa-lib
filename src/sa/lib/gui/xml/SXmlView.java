/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui.xml;

import sa.lib.xml.SXmlAttribute;
import sa.lib.xml.SXmlElement;

/**
 *
 * @author Juan Barajas
 */
public abstract class SXmlView extends SXmlElement {

    public static final String NAME = "View";
    public static final String ATT_VIEW_TYPE = "viewType";
    public static final String ATT_VIEW_SUB_TYPE = "viewSubType";
    public static final String ATT_VIEW_MODE = "viewMode";
    public static final String ATT_VIEW_SUB_MODE = "viewSubMode";

    protected SXmlAttribute moViewType;
    protected SXmlAttribute moViewSubType;
    protected SXmlAttribute moViewMode;
    protected SXmlAttribute moViewSubMode;

    public SXmlView() {
        super(NAME);

        moViewType = new SXmlAttribute(ATT_VIEW_TYPE);
        moViewSubType = new SXmlAttribute(ATT_VIEW_SUB_TYPE);
        moViewMode = new SXmlAttribute(ATT_VIEW_MODE);
        moViewSubMode = new SXmlAttribute(ATT_VIEW_SUB_MODE);

        mvXmlAttributes.add(moViewType);
        mvXmlAttributes.add(moViewSubType);
        mvXmlAttributes.add(moViewMode);
        mvXmlAttributes.add(moViewSubMode);
    }
}
