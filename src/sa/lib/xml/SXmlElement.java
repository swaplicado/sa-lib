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
public class SXmlElement implements java.io.Serializable {

    protected String msName;
    protected Vector<SXmlAttribute> mvXmlAttributes;
    protected Vector<SXmlElement> mvXmlElements;

    public SXmlElement(String name) {
        msName = name;
        mvXmlAttributes = new Vector<SXmlAttribute>();
        mvXmlElements = new Vector<SXmlElement>();
    }

    public void setName(String s) { msName = s; }

    public String getName() { return msName; }

    public Vector<SXmlAttribute> getXmlAttributes() { return mvXmlAttributes; }
    public Vector<SXmlElement> getXmlElements() { return mvXmlElements; }

    public void clear() {
        mvXmlAttributes.clear();
        mvXmlElements.clear();
    }

    public String getXmlString() {
        String aux = "";
        String xml = "<" + msName;

        for (SXmlAttribute attribute : mvXmlAttributes) {
            aux = attribute.getXmlString();
            xml += aux.length() == 0 ? "" : " " + aux;
        }

        if (mvXmlElements.isEmpty()) {
            xml += "/>";
        }
        else {
            xml += ">";

            for (SXmlElement element : mvXmlElements) {
                aux = element.getXmlString();
                xml += aux.length() == 0 ? "" : "\n" + aux;
            }

            xml += "\n</" + msName + ">";
        }

        return xml;
    }

    public SXmlAttribute getAttribute(final String name) {
        SXmlAttribute attribute = null;

        for (SXmlAttribute attributeAux : mvXmlAttributes) {
            if (name.compareTo(attributeAux.getName()) == 0) {
                attribute = attributeAux;
                break;
            }
        }

        return attribute;
    }

    public Vector<SXmlElement> getElements(final String name) {
        Vector<SXmlElement> elements = new Vector<SXmlElement>();

        for (SXmlElement element : mvXmlElements) {
            if (name.compareTo(element.getName()) == 0) {
                elements.add(element);
            }
        }

        return elements;
    }
}
