package com.ericsson.javatraining.data;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ toXMLTest.class, File.class })
public class toXMLTest {
    private toXML toxml;
    private Document document;
    private Element root;
    private String FILENAME = "test.xml";
    private File file;
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
