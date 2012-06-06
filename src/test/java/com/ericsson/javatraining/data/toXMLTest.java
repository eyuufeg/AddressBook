package com.ericsson.javatraining.data;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class toXMLTest {
    private toXML toxml;
    private Document document;
    private Element root;
    private Data data;
    private PhoneModule phone;
    @Before
    public void setUp() throws Exception {
        data = Data.getInstance();
        phone = new PhoneModule();

    }

    @Test
    public void testcreateDocumentTree() {
        phone.setName("ema");
        phone.setNumber("123");
        phone.setAddress("shanghai");
        data.addtempPhone(phone);
        toxml = new toXML();
        try {
            document = toxml.createDocumentTree();
        } catch (IOException e) {
            e.printStackTrace();
        }
        root = document.getDocumentElement();
        NodeList recordNodeList = root.getElementsByTagName("record");
        assertTrue(recordNodeList.getLength() == 8);

}
}