package com.atypon.backstage;

import com.atypon.domain.Issue;

import com.atypon.service.IssueService;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;

public class IssueMetadataProcessor implements Processor<String> {
    private final IssueService issueService;

    public IssueMetadataProcessor(IssueService issueService) {
        this.issueService = issueService;
    }

    @Override
    public void process(String issueFilePath) throws ProcessingException {
        Issue issue = extractIssue(issueFilePath);
        issueService.createIfNotExist(issue);
    }

    private Issue extractIssue(String issueFilePath) throws ProcessingException {
        try {
            Issue issue = new Issue();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(false);
            saxParserFactory.setFeature("http://xml.org/sax/features/namespaces", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlIssueHandler handler = new XmlIssueHandler(issue);
            saxParser.parse(new FileInputStream(issueFilePath), handler);
            return issue;
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
    }

    private static class XmlIssueHandler extends DefaultHandler {

        private Issue issue;
        private String tagName = "";

        private XmlIssueHandler(Issue issue) {
            this.issue = issue;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            switch (qName) {
                case "issn":
                    if (attributes.getValue("pub-type").equals("ppub")) {
                        tagName = "printIssn";
                    }
                    break;
                case "year":
                    tagName = "year";
                    break;
                case "month":
                    tagName = "month";
                    break;
                case "volume":
                    tagName = "volume";
                    break;
                case "issue":
                    tagName = "issueNumber";
                    break;
                case "issue-id":
                    tagName = "issueDao";
                    break;
                case "subject":
                    tagName = "subject";
                    break;
                default:
                    tagName = "";
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = String.copyValueOf(ch, start, length).trim();
            switch (tagName) {
                case "printIssn":
                    issue.setJournalPrintIssn(content);
                    break;
                case "year":
                    issue.setYear(Integer.parseInt(content));
                    break;
                case "month":
                    issue.setMonth(Integer.parseInt(content));
                    break;
                case "volume":
                    issue.setVolume(Integer.parseInt(content));
                    break;
                case "issueNumber":
                    issue.setNumber(Integer.parseInt(content));
                    break;
                case "issueDao":
                    issue.setDoi(content.split("/")[0]);
                    break;
                case "subject":
                    issue.addSubject(content);
                    break;
            }
            tagName = "";
        }

    }


}
