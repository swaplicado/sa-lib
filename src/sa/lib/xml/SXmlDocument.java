/*
 * Copyright 2010-2011 Sergio Abraham Flores Gutiérrez
 * All rights reserved.
 */

package sa.lib.xml;

import java.util.Vector;

/**
 *
 * @author Sergio Flores
 */
public abstract class SXmlDocument extends SXmlElement {

    protected boolean mbAddDefaultHeader;
    protected String msCustomHeader;
    protected Vector<SXmlElement> mvXmlElements;

    public SXmlDocument(String name) {
        this(name, true);
    }

    public SXmlDocument(String name, boolean addDefaultHeader) {
        super(name);
        mbAddDefaultHeader = addDefaultHeader;
        msCustomHeader = "";
        mvXmlElements = new Vector<SXmlElement>();
    }

    public void setCustomHeader(String s) { msCustomHeader = s; }

    public String getCustomHeader() { return msCustomHeader; }
    public Vector<SXmlElement> getXmlElements() { return mvXmlElements; }

    public abstract void processXml(final String xml) throws Exception;

    @Override
    public void clear() {
        super.clear();
        mvXmlElements.clear();
    }

    @Override
    public String getXmlString() {
        String aux = "";
        String xml = "";

        xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";

        if (mbAddDefaultHeader) {
            xml += "<!-- Copyright 2010-2015 Software Aplicado, SA de CV. All rights reserved. -->\n";
        }

        xml += "<" + msName + (!mbAddDefaultHeader ? "" : " xmlns=\"http://www.swaplicado.com.mx\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        xml += msCustomHeader.isEmpty() ? "" : " " + msCustomHeader;

        for (SXmlAttribute attribute : mvXmlAttributes) {
            aux = attribute.getXmlString();
            xml += aux.isEmpty() ? "" : " " + aux;
        }

        xml += ">";

        for (SXmlElement element : mvXmlElements) {
            aux = element.getXmlString();
            xml += aux.isEmpty() ? "" : "\n" + aux;
        }

        xml += "\n</" + msName + ">";

        return xml;
    }
}
