package com.ericsson.javatraining.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.ericsson.javatraining.data.Data;
import com.ericsson.javatraining.data.PhoneModule;
import com.ericsson.javatraining.exception.AddressBookException;

/**
 * FromXML implements read info from XML.
 * 
 * @author eyuufeg
 */
public class FromXML {
    private static final Logger logger = LoggerFactory.getLogger(FromXML.class);
    private static String FILENAME = "phonebook.xml";
    private List<String> recordlist;
    private Data data;
    private ToXML toxml;
    private Document document;
    private Element root;

    /**
     * Default constructor
     */
    public FromXML() {
        super();
        recordlist = new ArrayList<String>();
        data = Data.getInstance();
        toxml = new ToXML();
    }

    /**
     * read info from xml to phones
     * 
     * @throws IOException
     */
    public void read() throws AddressBookException {
        try {
            File f = new File(FILENAME);
            if (!f.exists()) {
                logger.info("The file :" + FILENAME + "is not exists");
                logger.info("Create" + FILENAME + "...");
                toxml.toxml(toxml.createBasicDocument());
                return;
            }

            else {

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

            }

            for (int i = 0; i < recordlist.size(); i++) {
                if (recordlist.get(i).trim().length() > 0) {
                    PhoneModule phone = new PhoneModule(recordlist.get(i).trim());
                    data.addPhone(phone);
                }
            }
        } catch (Exception e) {
            logger.error("read from xml file error", e);
            throw new AddressBookException("read from xml file error", e);
        }
    }
}
