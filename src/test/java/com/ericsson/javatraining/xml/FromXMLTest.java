package com.ericsson.javatraining.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
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
import com.ericsson.javatraining.exception.AddressBookException;

public class FromXMLTest {
    private static String FILENAME = "phonebook.xml";
    private FromXML fromxml = new FromXML();
    private static String expectedName = "ema";
    private static String expectedNumber = "123";
    private static String expectedAddress = "shanghai";
    private PhoneModule phonemodule;
    private Data data;

    @Before
    public void setUp() throws Exception {
        phonemodule = new PhoneModule();
        phonemodule.setName(expectedName);
        phonemodule.setNumber(expectedNumber);
        phonemodule.setAddress(expectedAddress);

        File xmlFile = new File(FILENAME);
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
        File xmlFile = new File(FILENAME);
        if (xmlFile.exists()) {
            xmlFile.delete();
        }
    }

    @Test
    public void testread() {
        try {
            fromxml.read();
            data = Data.getInstance();
            assertEquals(phonemodule.getName(), data.getPhone(expectedNumber).getName());
            assertEquals(phonemodule.getNumber(), data.getPhone(expectedNumber).getNumber());
            assertEquals(phonemodule.getAddress(), data.getPhone(expectedNumber).getAddress());
        } catch (AddressBookException e) {

        }
    }

    @Test(expected = AddressBookException.class)
    public void testReadWrongFile() throws AddressBookException {

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.parse(FILENAME);
            Element root = document.getDocumentElement();

            Node addrecord = document.createElement("record");
            root.appendChild(addrecord);

            Element addname = document.createElement("name");
            addname.setTextContent(expectedName);
            addrecord.appendChild(addname);

            Element addaddress = document.createElement("address");
            addaddress.setTextContent(expectedAddress);
            addrecord.appendChild(addaddress);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new java.io.File(FILENAME));
            transformer.transform(source, result);

            fromxml.read();

        } catch (AddressBookException e) {
            throw e;
        } catch (Exception e1) {
            System.out.print(e1.getMessage());
        }
    }

}
