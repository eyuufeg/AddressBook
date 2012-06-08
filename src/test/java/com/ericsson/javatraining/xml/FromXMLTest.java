package com.ericsson.javatraining.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;

public class FromXMLTest {
    private FromXML fromxml = new FromXML();
    private String expectedName = "ema";
    private String expectedNumber = "123";
    private String expectedAddress = "shanghai";
    private PhoneModule phonemodule;
    private Data data;
    @Before
    public void setUp() throws Exception {
        phonemodule = new PhoneModule();
        phonemodule.setName(expectedName);
        phonemodule.setNumber(expectedNumber);
        phonemodule.setAddress(expectedAddress);

        File xmlFile = new File("phonebook.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();
        Element root = document.createElement("addressbook");
        document.appendChild(root);
        Node addrecord = document.createElement("record");
        root.appendChild(addrecord);

        Element addname = document.createElement("name");
        addname.setTextContent("ema");
        addrecord.appendChild(addname);

        Element addnumber = document.createElement("number");
        addnumber.setTextContent("123");
        addrecord.appendChild(addnumber);

        Element addaddress = document.createElement("address");
        addaddress.setTextContent("shanghai");
        addrecord.appendChild(addaddress);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);

    }

    @After
    public void tearDown() throws Exception {
        File xmlFile = new File("phonebook.xml");
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }

    @Test
    public void testread() {
        try {
            fromxml.read();
            data = Data.getInstance();
            assertEquals(phonemodule.getName(), data.getPhone(expectedName).getName());
            assertEquals(phonemodule.getNumber(), data.getPhone(expectedName).getNumber());
            assertEquals(phonemodule.getAddress(), data.getPhone(expectedName).getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
