/*
 * Copyright 2010-2011 Sergio Abraham Flores Gutiérrez
 * All rights reserved.
 */

package sa.lib.xml;

import sa.lib.SLibUtils;

/**
 *
 * @author Sergio Flores
 */
public class SXmlAttribute implements java.io.Serializable {

    protected String msName;
    protected Object moValue;

    public SXmlAttribute(String name) {
        this(name, null);
    }

    public SXmlAttribute(String name, Object value) {
        msName = name;
        moValue = value;
    }

    public void setName(String s) { msName = s; }
    public void setValue(Object o) { moValue = o; }

    public String getName() { return msName; }
    public Object getValue() { return moValue; }

    public String getXmlString() {
        return msName + "=\"" + SLibUtils.textToXml(moValue.toString()) + "\"";
    }
}
