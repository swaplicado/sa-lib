/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.gui.xml;

import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sa.lib.SLibUtils;
import sa.lib.xml.SXmlDocument;
import sa.lib.xml.SXmlUtils;

/**
 *
 * @author Juan Barajas
 */
public class SXmlSessionXml extends SXmlDocument {

    public static final String NAME = "Session";

    public SXmlSessionXml() {
        super(NAME);
    }

    @Override
    public void processXml(String xml) throws Exception {
        Document document = null;
        NodeList nodeList = null;
        Vector<Node> childNodes = null;
        NamedNodeMap namedNodeMap = null;

        clear();

        document = SXmlUtils.parseDocument(xml);
        nodeList = SXmlUtils.extractElements(document, SXmlSessionXml.NAME);
        
        // Views:

        if (SXmlUtils.hasChildElement(nodeList.item(0), SXmlView.NAME)) {
            childNodes = SXmlUtils.extractChildElements(nodeList.item(0), SXmlView.NAME);
            for (Node child : childNodes) {
                SXmlView view = new SXmlView() {};
                namedNodeMap = child.getAttributes();

                view.getAttribute(SXmlView.ATT_VIEW_TYPE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlView.ATT_VIEW_TYPE).getNodeValue()));
                view.getAttribute(SXmlView.ATT_VIEW_SUB_TYPE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlView.ATT_VIEW_SUB_TYPE).getNodeValue()));
                view.getAttribute(SXmlView.ATT_VIEW_MODE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlView.ATT_VIEW_MODE).getNodeValue()));
                view.getAttribute(SXmlView.ATT_VIEW_SUB_MODE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlView.ATT_VIEW_SUB_MODE).getNodeValue()));

                mvXmlElements.add(view);
            }
        }
    }
}
