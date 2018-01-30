/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sa.lib.grid.xml;

import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sa.lib.SLibUtils;
import sa.lib.grid.SGridConsts;
import sa.lib.xml.SXmlAttribute;
import sa.lib.xml.SXmlDocument;
import sa.lib.xml.SXmlUtils;

/**
 *
 * @author Sergio Flores
 */
public class SXmlGridXml extends SXmlDocument {

    public static final String NAME = "Grid";
    public static final String ATT_AUTO_RELOAD = "autoReload";

    private int mnGridPaneType;
    protected SXmlAttribute moAutoReload;

    /**
     * @param gridPaneType Constant defined in lib.grid.SGridConsts
     */
    public SXmlGridXml(int gridPaneType) {
        super(NAME);

        mnGridPaneType = gridPaneType;
        moAutoReload = new SXmlAttribute(ATT_AUTO_RELOAD);
        mvXmlAttributes.add(moAutoReload);
    }

    public void setGridPaneType(int n) { mnGridPaneType = n; }

    public int getGridPaneType() { return mnGridPaneType; }

    @Override
    public void processXml(String xml) throws Exception {
        Document document = null;
        NodeList nodeList = null;
        Vector<Node> childNodes = null;
        Vector<Node> grandChildNodes = null;
        NamedNodeMap namedNodeMap = null;

        clear();

        document = SXmlUtils.parseDocument(xml);
        nodeList = SXmlUtils.extractElements(document, SXmlGridXml.NAME);
        
        // Attributes grid
        
        moAutoReload.setValue(nodeList.item(0).getAttributes().getNamedItem(ATT_AUTO_RELOAD).getNodeValue().compareTo("" + true) == 0);
        mvXmlAttributes.add(moAutoReload);
        
        // Columns:

        childNodes = SXmlUtils.extractChildElements(nodeList.item(0), SXmlColumn.NAME);
        for (Node child : childNodes) {
            SXmlColumn column = mnGridPaneType == SGridConsts.GRID_PANE_VIEW ? new SXmlColumnView() : new SXmlColumnForm();
            namedNodeMap = child.getAttributes();

            column.getAttribute(SXmlColumn.ATT_COLUMN_TYPE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlColumn.ATT_COLUMN_TYPE).getNodeValue()));
            column.getAttribute(SXmlColumn.ATT_COLUMN_TITLE).setValue(namedNodeMap.getNamedItem(SXmlColumn.ATT_COLUMN_TITLE).getNodeValue());
            column.getAttribute(SXmlColumn.ATT_COLUMN_WIDTH).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlColumn.ATT_COLUMN_WIDTH).getNodeValue()));
            column.getAttribute(SXmlColumn.ATT_FIELD_NAME).setValue(namedNodeMap.getNamedItem(SXmlColumn.ATT_FIELD_NAME).getNodeValue());

            if (mnGridPaneType == SGridConsts.GRID_PANE_VIEW) {
                ((SXmlColumnView) column).getAttribute(SXmlColumnView.ATT_IS_SUM_APPLYING).setValue(namedNodeMap.getNamedItem(SXmlColumnView.ATT_IS_SUM_APPLYING).getNodeValue().compareTo("" + true) == 0);
                ((SXmlColumnView) column).getAttribute(SXmlColumnView.ATT_CELL_RENDERER).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlColumnView.ATT_CELL_RENDERER).getNodeValue()));
            }
            else {
                ((SXmlColumnForm) column).getAttribute(SXmlColumnForm.ATT_IS_EDITABLE).setValue(namedNodeMap.getNamedItem(SXmlColumnForm.ATT_IS_EDITABLE).getNodeValue().compareTo("" + true) == 0);
            }

            if (SXmlUtils.hasChildElement(child, SXmlRpnArgument.NAME)) {
                grandChildNodes = SXmlUtils.extractChildElements(child, SXmlRpnArgument.NAME);
                for (Node grandChild : grandChildNodes) {
                    SXmlRpnArgument rpnArgument = new SXmlRpnArgument();
                    namedNodeMap = grandChild.getAttributes();

                    rpnArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT_TYPE).setValue(namedNodeMap.getNamedItem(SXmlRpnArgument.ATT_ARGUMENT_TYPE).getNodeValue());
                    rpnArgument.getAttribute(SXmlRpnArgument.ATT_ARGUMENT).setValue(namedNodeMap.getNamedItem(SXmlRpnArgument.ATT_ARGUMENT).getNodeValue());

                    column.getXmlElements().add(rpnArgument);
                }
            }

            mvXmlElements.add(column);
        }

        // Sort keys:

        childNodes = SXmlUtils.extractChildElements(nodeList.item(0), SXmlSortKey.NAME);
        for (Node node : childNodes) {
            SXmlSortKey sortKey = new SXmlSortKey();
            namedNodeMap = node.getAttributes();

            sortKey.getAttribute(SXmlSortKey.ATT_COLUMN).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlSortKey.ATT_COLUMN).getNodeValue()));
            sortKey.getAttribute(SXmlSortKey.ATT_SORT_ORDER).setValue(namedNodeMap.getNamedItem(SXmlSortKey.ATT_SORT_ORDER).getNodeValue());

            mvXmlElements.add(sortKey);
        }
        
        // Filters:

        if (SXmlUtils.hasChildElement(nodeList.item(0), SXmlFilter.NAME)) {
            childNodes = SXmlUtils.extractChildElements(nodeList.item(0), SXmlFilter.NAME);
            for (Node node : childNodes) {
                SXmlFilter filter = new SXmlFilter();
                namedNodeMap = node.getAttributes();

                filter.getAttribute(SXmlFilter.ATT_FILTER_TYPE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlFilter.ATT_FILTER_TYPE).getNodeValue()));
                filter.getAttribute(SXmlFilter.ATT_DATA_TYPE).setValue(SLibUtils.parseInt(namedNodeMap.getNamedItem(SXmlFilter.ATT_DATA_TYPE).getNodeValue()));
                filter.getAttribute(SXmlFilter.ATT_VALUE).setValue(namedNodeMap.getNamedItem(SXmlFilter.ATT_VALUE).getNodeValue());

                mvXmlElements.add(filter);
            }
        }
    }
}
