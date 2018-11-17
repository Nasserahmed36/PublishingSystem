package com.atypon.backstage;


import com.atypon.domain.Article;

import static com.atypon.domain.Article.Author;

import com.atypon.service.ArticleService;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;

public class ArticleMetadataProcessor implements Processor<String> {

    private final ArticleService articleService;

    public ArticleMetadataProcessor(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public void process(String articleFilePath) throws ProcessingException {
        Article article = extractArticle(articleFilePath);
        articleService.save(article);
    }

    private Article extractArticle(String articleFilePath) throws ProcessingException {
        try {
            Article article = new Article();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(false);
            saxParserFactory.setFeature("http://xml.org/sax/features/namespaces", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XmlArticleHandler handler = new XmlArticleHandler(article);
            saxParser.parse(new FileInputStream(articleFilePath), handler);
            return article;
        } catch (Exception e) {
            throw new ProcessingException(e);
        }
    }

    private static class XmlArticleHandler extends DefaultHandler {

        private Article article;
        private String tagName = "";
        private Author author;

        private XmlArticleHandler(Article article) {
            this.article = article;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes)
                throws SAXException {
            switch (qName) {
                case "article-id":
                    if (attributes.getValue("pub-id-type").equals("doi")) {
                        tagName = "doi";
                    }
                    break;
                case "subject":
                    tagName = "subject";
                    break;
                case "article-title":
                    tagName = "articleTitle";
                    break;
                case "fpage":
                    tagName = "firstPage";
                    break;
                case "lpage":
                    tagName = "lastPage";
                    break;
                case "contrib":
                    author = new Author();
                case "given-names":
                    tagName = "givenName";
                    break;
                case "surname":
                    tagName = "surname";
                    break;
                case "degrees":
                    tagName = "degrees";
                    break;
                default:
                    tagName = "";
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String content = String.copyValueOf(ch, start, length).trim();
            switch (tagName) {
                case "doi":
                    article.setDoi(content);
                    article.setIssueDoi(content.split("/")[0]);
                    break;
                case "subject":
                    article.setSubject(content);
                    break;
                case "articleTitle":
                    article.setTitle(content);
                    break;
                case "firstPage":
                    article.setFirstPage(content);
                    break;
                case "lastPage":
                    article.setLastPage(content);
                    break;
                case "givenName":
                    author.setGivenName(content);
                    break;
                case "surname":
                    author.setSurName(content);
                    break;
                case "degrees":
                    author.setDegrees(content);
                    break;

            }
            tagName = "";
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {

                case "contrib":
                    article.addAuthor(author);
                    author = new Author();
                    break;

            }
        }
    }

}
