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

public class fromXML {
    private static final Logger logger = LoggerFactory.getLogger(fromXML.class);
    private List<String> recordlist = new ArrayList<String>();
    private String FILENAME = "phonebook.xml";
    private Document document;
    private Element root;
    private Data data = Data.getInstance();
    private toXML toxml = new toXML();

    /**
     * initialize phones
     */
    public fromXML() {
        
        try {
            read();
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
            System.out.println("!!!!!!!!!!!!");
            logger.info("The file :" + FILENAME + "is not exists");
            logger.warn("Please create" + FILENAME);
            toxml.createBasicDocument();
            return;
        }

        else {
            try {
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                document = docBuilder.parse(FILENAME);
                root = document.getDocumentElement();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

        } 
        System.out.println("!!!!!!!!!!!!");
        for (int i = 0; i < recordlist.size(); i++) {
            if (recordlist.get(i).trim().length() > 0) {
                PhoneModule phone = new PhoneModule(recordlist.get(i).trim());
                data.addPhone(phone);

            }
        }

        }
}

