package com.ericsson.javatraining.xml;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class toXML {
    private static final Logger logger = LoggerFactory.getLogger(toXML.class);
    private String FILENAME = "phonebook.xml";
    private Document document;
    private Element root;
    private Data data = Data.getInstance();


    public void createBasicDocument() {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
            root = document.createElement("addressbook");
            document.appendChild(root);
        } catch (DOMException e) {

            e.printStackTrace();
        } catch (ParserConfigurationException e) {

            e.printStackTrace();
        }

        
}

    /**
     * this method used to store info to xml file
     * 
     * @throws IOException
     */
    public Document createDocumentTree() throws IOException {
        logger.info("Store all the information");

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = docBuilder.parse(FILENAME);
            root = document.getDocumentElement();
            for (PhoneModule phone : data.gettempPhones()) {

                if (phone != null) {
                    Node addrecord = document.createElement("record");
                    root.appendChild(addrecord);

                    Element addname = document.createElement("name");
                    addname.setTextContent(phone.getName());
                    addrecord.appendChild(addname);

                    Element addnumber = document.createElement("number");
                    addnumber.setTextContent(phone.getNumber());
                    addrecord.appendChild(addnumber);

                    Element addaddress = document.createElement("address");
                    addaddress.setTextContent(phone.getAddress());
                    addrecord.appendChild(addaddress);
                }
                    }
        } catch (DOMException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
                }


        return document;
    }

    public void toxml(Document document) {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new java.io.File(FILENAME));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            logger.error("TransformerConfigurationException ", e);
        } catch (TransformerFactoryConfigurationError e) {
            logger.error("TransformerFactoryConfigurationError ", e);
        } catch (TransformerException e) {
            logger.error("TransformerException ", e);
        }

    }


}

