package com.atypon.backstage;

import javax.xml.transform.*;
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
        turnOffValidation(transformerFactory);
        Source xslDoc = new StreamSource(xsltLocation);
        Source xmlSourceDoc = new StreamSource(sourceFileLocation);
        OutputStream outputHtmlFile = new FileOutputStream(outputFileLocation);
        Transformer transformer = transformerFactory.newTransformer(xslDoc);
        transformer.transform(xmlSourceDoc, new StreamResult(outputHtmlFile));
    }

    private void turnOffValidation(TransformerFactory transformerFactory) throws TransformerConfigurationException {
        transformerFactory.setFeature("http://xml.org/sax/features/namespaces", false);
        transformerFactory.setFeature("http://xml.org/sax/features/validation", false);
        transformerFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        transformerFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    }


}
