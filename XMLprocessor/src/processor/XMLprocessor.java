package processor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class XMLprocessor {
    public XMLprocessor() {}
    public String getTagContent(String xmlString, String tagName) throws ParserConfigurationException, IOException, SAXException {
        ByteArrayInputStream input = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
        xmlString = xmlString.replaceAll("[\n\r]", "");
        xmlString = xmlString.replaceAll("\s+", "").trim();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(input);
        NodeList nameList = doc.getElementsByTagName(tagName);
        if (nameList.getLength() > 0) {
            Element nameElement = (Element) nameList.item(0);
            return nameElement.getTextContent();
        }
        return null;
    }
    public String fixXML(String xmlFile) {
        xmlFile = xmlFile.replaceAll("[\n\r]", "");
        xmlFile = xmlFile.replaceAll("\s+", "").trim();
        xmlFile = xmlFile + '\n';
        return xmlFile;
    }
    public String replacePlaceholder(String xmlFile, String placeholder, String content) {
        xmlFile = xmlFile.replace(placeholder, content);
        return xmlFile;
    }

}

