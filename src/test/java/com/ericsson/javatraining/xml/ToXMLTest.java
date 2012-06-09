package com.ericsson.javatraining.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
import com.ericsson.javatraining.exception.AddressBookException;
public class ToXMLTest {

    private static String FILENAME = "phonebook.xml";
    private static String expectedName = "ema";
    private static String expectedNumber = "123";
    private static String expectedAddress = "shanghai";
    private List<String> recordlist;
    private ToXML toxml;
    private Document document;
    private Element root;
    private Data data;
    private PhoneModule phonemodule;

    @Before
    public void setUp() throws Exception {

        phonemodule = new PhoneModule();
        phonemodule.setName(expectedName);
        phonemodule.setNumber(expectedNumber);
        phonemodule.setAddress(expectedAddress);
        recordlist = new ArrayList<String>();
        File xmlFile = new File(FILENAME);
        if (xmlFile.exists()) {
            xmlFile.delete();
        }

        data = Data.getInstance();
        data.addtempPhone(phonemodule);
        toxml = new ToXML();

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        document = docBuilder.newDocument();
        root = document.createElement("addressbook");
        document.appendChild(root);

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
    public void testcreateBasicDocument() {
        try {
            document = toxml.createBasicDocument();
            root = document.getDocumentElement();
            String name = root.getTagName();
            toxml.toxml(document);
            assertEquals("return value should be addressbook", "addressbook", name);
        } catch (AddressBookException e) {

        }
    }

    @Test
    public void testtoxml() {

        try {
            document = toxml.createDocumentTree();
            toxml.toxml(document);

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = docBuilder.parse(FILENAME);
            root = document.getDocumentElement();
        } catch (AddressBookException e) {

        } catch (Exception e) {

        }

        NodeList recordNodeList = root.getElementsByTagName("record");
        for (int i = 0; i < recordNodeList.getLength(); i++) {
            Node recordNode = recordNodeList.item(i);

            NodeList recordChild = recordNode.getChildNodes();
            String recordstring = "";
            for (int j = 0; j < recordChild.getLength(); j++) {
                Node recordChildNode = recordChild.item(j);
                if (recordChildNode instanceof Element) {
                    Element recordChildElement = (Element) recordChildNode;
                    String text = ((Text) recordChildElement.getFirstChild()).getData().trim();
                    recordstring = recordstring + text + "::";
                }
            }
            recordlist.add(recordstring);
        }
        for (int i = 0; i < recordlist.size(); i++) {
            if (recordlist.get(i).trim().length() > 0) {
                PhoneModule phone = new PhoneModule(recordlist.get(i).trim());
                data.addPhone(phone);
            }
        }

        assertEquals("return value should be ema", phonemodule.getName(), data.getPhone(expectedNumber).getName());
        assertEquals("return value should be 123", phonemodule.getAddress(), data.getPhone(expectedNumber).getAddress());
        assertEquals("return value should be shanghai", phonemodule.getNumber(), data.getPhone(expectedNumber).getNumber());
    }

}
