package com.ericsson.javatraining.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ToXMLTest {

    private ToXML toxml;
    private Document document;
    private Element root;

    @Before
    public void setUp() throws Exception {
        toxml = new ToXML();
    }

    @After
    public void tearDown() throws Exception {
        File xmlFile = new File("phonebook.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }
    @Test
    public void testcreateBasicDocument() {
        document = toxml.createBasicDocument();
        root = document.getDocumentElement();
        String name = root.getTagName();
        toxml.toxml(document);
        assertEquals("return value should be addressbook", "addressbook", name);
    }
 
}
