package com.ericsson.javatraining.data;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class toXMLTest {
    private toXML toxml;
    private Document document;
    private Element root;
    @Before
    public void setUp() throws Exception {
        toxml = new toXML();
    }

    @Test
    public void testcreateBasicDocument() {
        document = toxml.createBasicDocument();
        root = document.getDocumentElement();
        String name = root.getTagName();
        assertEquals("return value should be addressbook", "addressbook", name);
    }

}
