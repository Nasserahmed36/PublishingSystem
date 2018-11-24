package com.atypon.backstage;

import com.atypon.context.ApplicationContext;
import com.atypon.domain.Article;
import com.atypon.domain.Issue;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import java.util.ArrayList;
import java.util.List;

import static com.atypon.backstage.BackstageSettings.*;


public class ProcessedContentProcessor implements Processor<String> {
    private final IssueService issueService;
    private final ArticleService articleService;

    public ProcessedContentProcessor() {
        issueService = (IssueService) ApplicationContext.getAttribute("issueService");
        articleService = (ArticleService) ApplicationContext.getAttribute("articleService");
    }

    @Override
    public void process(String processedContentDir) throws ProcessingException {
        Issue issue = extractIssue(issueMetadataPath(processedContentDir));
        Article article = extractArticle(articleMetadataPath(processedContentDir));
        issueService.createIfNotExist(issue);
        if (!articleService.create(article)) {
            throw new ProcessingException("Article cannot be created");
        }
    }

    private Issue extractIssue(String issueMetadataPath) throws ProcessingException {
        Issue issue = new Issue();
        Element root = getRoot(issueMetadataPath);
        issue.setJournalPrintIssn(getValue(root, "journal-print-issn"));
        issue.setDoi(getValue(root, "doi"));
        issue.setVolume(getIntValue(root, "volume"));
        issue.setMonth(getIntValue(root, "month"));
        issue.setYear(getIntValue(root, "year"));
        issue.setNumber(getIntValue(root, "number"));
        issue.setSubjects(getValues(root, "subject"));
        return issue;
    }

    private Article extractArticle(String articleMetadataPath) throws ProcessingException {
        Article article = new Article();
        Element root = getRoot(articleMetadataPath);
        article.setDoi(getValue(root, "doi"));
        article.setIssueDoi(getValue(root, "issue-doi"));
        article.setSubject(getValue(root, "subject"));
        article.setTitle(getValue(root, "title"));
        article.setFirstPage(getValue(root, "first-page"));
        article.setLastPage(getValue(root, "last-page"));
        article.setMonth(getIntValue(root, "month"));
        article.setYear(getIntValue(root, "year"));
        article.setAuthors(getAuthors(root));
        return article;
    }


    private static Element getRoot(String documentPath) throws ProcessingException {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(documentPath);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
    }


    private static List<Article.Author> getAuthors(Element root) {
        List<Article.Author> authors = new ArrayList<>();
        NodeList nodeList = root.getElementsByTagName("authors").item(0).getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (!(nodeList.item(i) instanceof Element)) {
                continue;
            }
            Article.Author author = new Article.Author();
            author.setGivenName(getValue((Element) nodeList.item(i), "given-names"));
            author.setSurName(getValue((Element) nodeList.item(i), "surname"));
            author.setDegrees(getValue((Element) nodeList.item(i), "degrees"));
            authors.add(author);
        }
        return authors;
    }


    private static String getValue(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private static List<String> getValues(Element element, String tagName) {
        List<String> values = new ArrayList<>();
        NodeList nodeList = element.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            values.add(nodeList.item(i).getTextContent());
        }
        return values;
    }

    private static int getIntValue(Element element, String tagName) {
        return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
    }

}

