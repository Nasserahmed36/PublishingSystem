package com.atypon.backstage;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class XsltTransformer implements FileTransformer {

    private final String xsltLocation;

    public XsltTransformer(String xsltLocation) {
        this.xsltLocation = xsltLocation;
    }

    @Override
    public void transform(String sourceFileLocation, String outputFileLocation) throws FileNotFoundException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Source xslDoc = new StreamSource(xsltLocation);
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        XMLReader xmlReader = null;
        try {
            // Turn off validation
            saxParserFactory.setNamespaceAware(true);
            saxParserFactory.setValidating(false);
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            xmlReader = saxParserFactory.newSAXParser().getXMLReader();
        } catch (SAXException | ParserConfigurationException e) {
            throw new TransformerException(e);
        }
        OutputStream outputHtmlFile = new FileOutputStream(outputFileLocation);
        Transformer transformer = transformerFactory.newTransformer(xslDoc);
        transformer.transform(new SAXSource(xmlReader, new InputSource(sourceFileLocation)), new StreamResult(outputHtmlFile));
    }

    public static void main(String[] args) throws FileNotFoundException, TransformerException {
        XsltTransformer x = new XsltTransformer("/home/nasserahmed36/IdeaProjects/Literatum/WebaAminTool/src/main/resources/xsl/jatsToFullPage.xsl");
        x.transform("/home/nasserahmed36/Desktop/UN-Processed  (Submissions)/bhda_bhda_42_2_20181029050741952/bhda_42_2/0198742916688653/0198742916688653.xml","/home/nasserahmed36/Desktop/UN-Processed  (Submissions)/bhda_bhda_42_2_20181029050741952/bhda_42_2/0198742916688653/0198742916688653.html");
    }


}
