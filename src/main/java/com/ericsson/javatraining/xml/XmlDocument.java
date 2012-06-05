package com.ericsson.javatraining.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlDocument {

    private Document document;
    private Element root;
    private static XmlDocument instance = null;

    public static XmlDocument getInstance() {
        if (instance == null) {
            instance = new XmlDocument();
        }
        return instance;
    }

    void setDocument(Document document) {
        this.document = document;
    }

    Document getDocument() {
        return document;
    }

    void setRoot(Element root) {
        this.root = root;
    }

    Element getRoot() {
        return root;
    }
}