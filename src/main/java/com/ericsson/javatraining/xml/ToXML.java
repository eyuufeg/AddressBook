package com.ericsson.javatraining.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
import com.ericsson.javatraining.exception.AddressBookException;

/**
 * toXML implements write info to xml.
 * 
 * @author eyuufeg
 */
public class ToXML {
    private static final Logger logger = LoggerFactory.getLogger(ToXML.class);
    private static String FILENAME = "phonebook.xml";
    private Data data;
    private Document document;
    private Element root;

    /**
     * Default constructor
     */
    public ToXML() {
        super();
        data = Data.getInstance();
    }

    /**
     * Create a DOM tree with addressbook node when phonebook.xml is not existing
     * 
     */
    public Document createBasicDocument() throws AddressBookException {

        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = docBuilder.newDocument();
            root = document.createElement("addressbook");
            document.appendChild(root);
        } catch (Exception e) {

            logger.error("createBasicDocument failed", e);
            throw new AddressBookException("createBasicDocument failed", e);

        }

        return document;
    }

    /**
     * This method used to create document for user info
     * 
     * @throws IOException
     */
    public Document createDocumentTree() throws AddressBookException {

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
        } catch (Exception e) {
            logger.error("createDocumentTree failed", e);
            throw new AddressBookException("createDocumentTree failed", e);
        }

        return document;
    }

    /**
     * Write info to xml
     * 
     * @param document
     *            created by function called createDocumentTree
     * 
     * 
     */
    public void toxml(Document document) throws AddressBookException {
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new java.io.File(FILENAME));
            transformer.transform(source, result);
        } catch (Exception e) {
            logger.error("toxml failed", e);
            throw new AddressBookException("toxml failed", e);
        }
    }

}
