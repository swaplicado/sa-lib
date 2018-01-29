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
public class SXmlColumnView extends SXmlColumn {

    public static final String ATT_IS_SUM_APPLYING = "isSumApplying";

    protected SXmlAttribute moIsSumApplying;

    public SXmlColumnView() {
        moIsSumApplying = new SXmlAttribute(ATT_IS_SUM_APPLYING);

        mvXmlAttributes.add(moIsSumApplying);
    }
}
