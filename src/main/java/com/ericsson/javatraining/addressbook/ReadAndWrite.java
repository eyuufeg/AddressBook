package com.ericsson.javatraining.addressbook;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
    final Logger logger = LoggerFactory.getLogger(ReadAndWrite.class);

    private int size = 0;
    private int tempsize = 0;
    private PhoneModule[] phones = new PhoneModule[100];
    private PhoneModule[] tempphones = new PhoneModule[100];
    private List<String> recordlist = new ArrayList<String>();
    private String FILENAME = "phonebook.xml";
    private Document document;
    private Element root;

    /**
     * initialize phones
     */
    public ReadAndWrite() {

        try {

            logger.info("Try to read exists xml file ");
            read();

            logger.info("Succeed to read exists xml file ");
        } catch (IOException e) {
            logger.error("Fail to read exists xml file ");
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

            System.out.println("Try to read exists xml file ");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < recordlist.size(); i++) {
            if (recordlist.get(i).trim().length() > 0) {
                PhoneModule phone = new PhoneModule(recordlist.get(i).trim());
                addPhone(phone);

            }
        }


    }

    /**
     * this method used to store info to xml
     * 
     * @throws IOException
     */
    public void store() throws IOException {
        logger.info("Store all the information");
        for (PhoneModule phone : tempphones) {

            if (phone != null) {
                Node addrecord = document.createElement("record");
                root.appendChild(addrecord);

                Element addname = document.createElement("name");
                addname.setTextContent(phone.name);
                addrecord.appendChild(addname);

                Element addnumber = document.createElement("number");
                addnumber.setTextContent(phone.number);
                addrecord.appendChild(addnumber);

                Element addaddress = document.createElement("address");
                addaddress.setTextContent(phone.address);
                addrecord.appendChild(addaddress);
                } else
                    continue;
            }

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new java.io.File(FILENAME));
            transformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void addPhone(PhoneModule phone) {
        phones[size++] = phone;
    }

    /**
     * tempphones used to save the new add info
     */
    public void addtempPhone(PhoneModule phone) {
        tempphones[tempsize++] = phone;
    }

    public PhoneModule getPhone(String name) {
        for (PhoneModule phone : phones) {
            if (phone == null) {
                continue;
            }
            if (phone.name.equalsIgnoreCase(name)) {
                return phone;
            }
        }
        return null;
    }

    public PhoneModule gettempPhone(String name) {
        for (PhoneModule phone : tempphones) {
            if (phone == null) {
                continue;
            }
            if (phone.name.equalsIgnoreCase(name)) {
                return phone;
            }
        }
        return null;
    }
    public PhoneModule[] getPhones() {
        return phones;
    }
}

