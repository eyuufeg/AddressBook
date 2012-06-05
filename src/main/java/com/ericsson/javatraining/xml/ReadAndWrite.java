package com.ericsson.javatraining.xml;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class ReadAndWrite {
    private static final Logger logger = LoggerFactory.getLogger(ReadAndWrite.class);
    private List<String> recordlist = new ArrayList<String>();
    private String FILENAME = "phonebook.xml";
    private Document document = null;
    private Element root = null;
    private Data data = Data.getInstance();
    /**
     * initialize phones
     */
    public ReadAndWrite() {

        try {
            logger.info("Try to read exists xml file ");
            read();
            logger.info("Succeed to read exists xml file ");
        } catch (IOException e) {
            logger.error("Fail to read exists xml file", e);
        }
    }

    /**
     * read info from xml to phones
     * 
     * @throws IOException
     */
    public void read() throws IOException {
        File f = new File(FILENAME);
        if (!f.exists()) {
            logger.info("The file :" + FILENAME + "is not exists");
            logger.warn("Please create" + FILENAME);

            return;
        }

        else {
            try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            document = docBuilder.parse(FILENAME);
            root = document.getDocumentElement();

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

        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException ", e);
        } catch (IOException e) {
            logger.error("IOException ", e);
        } catch (SAXException e) {
            logger.error("SAXException ", e);
        }

        for (int i = 0; i < recordlist.size(); i++) {
            if (recordlist.get(i).trim().length() > 0) {
                PhoneModule phone = new PhoneModule(recordlist.get(i).trim());
                data.addPhone(phone);

            }
        }
        }

    }

    /**
     * this method used to store info to xml file
     * 
     * @throws IOException
     */
    public void store() throws IOException {
        logger.info("Store all the information");
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

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
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

