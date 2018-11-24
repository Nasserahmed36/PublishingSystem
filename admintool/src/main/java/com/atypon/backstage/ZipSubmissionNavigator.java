package com.atypon.backstage;

import java.io.File;
import java.io.FileInputStream;


import com.atypon.commons.FileUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ZipSubmissionNavigator implements ArticleSubmissionNavigator {

    private static final String ISSUE_FILES_DIR_NAME = "issue-files";


    private final String rootDir;
    private File manifest;
    private File issueDir;
    private File issueFilesDir;
    private File articleDir;
    private File articleGraphicsDir;
    private File issueMetadataFile;
    private File articleFile;
    private File articlePdfFile;
    private File issueCover;
    private String articleDoi;
    private String issueId;
    private String issueDoi;

    public ZipSubmissionNavigator(String zipFilePath) {
        this.rootDir = unzip(zipFilePath);
    }

    private String unzip(String zipFilePath) {
        String destDir = zipFilePath.replace(".zip", "");
        FileUtils.unzip(zipFilePath, destDir);
        return destDir;
    }


    public File getManifestFile() {
        if (manifest == null) {
            File root = new File(rootDir);
            File[] files = root.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    manifest = file;
                    break;
                }
            }
        }
        return manifest;
    }

    public File getIssueDir() {
        if (issueDir == null) {
            File root = new File(rootDir);
            File[] files = root.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    issueDir = file;
                    break;
                }
            }
        }
        return issueDir;
    }

    public File getIssueFilesDir() {
        if (issueFilesDir == null) {
            File issueDir = getIssueDir();
            File[] files = issueDir.listFiles();
            for (File file : files) {
                if (file.getName().equals(ISSUE_FILES_DIR_NAME)) {
                    issueFilesDir = file;
                    break;
                }
            }
        }
        return issueFilesDir;
    }

    public File getArticleDir() {
        if (articleDir == null) {
            File issueDir = getIssueDir();
            File[] files = issueDir.listFiles();
            for (File file : files) {
                if (!file.getName().equals(ISSUE_FILES_DIR_NAME)) {
                    articleDir = file;
                    break;
                }
            }
        }
        return articleDir;
    }


    public File getIssueMetadataFile() {
        if (issueMetadataFile == null) {
            String issueId = getIssueId();
            File issueFilesDir = getIssueFilesDir();
            issueMetadataFile = new File(issueFilesDir.getPath() + File.separator + issueId + ".xml");
        }
        return issueMetadataFile;
    }

    @Override
    public File getIssueCover() {
        if (issueCover == null) {
            String issueId = getIssueId();
            File issueFilesDir = getIssueFilesDir();
            issueCover = new File(issueFilesDir.getPath()
                    + File.separator + issueId + ".cover.png");
        }
        return issueCover;
    }

    public File getArticleFile() {
        if (articleFile == null) {
            String articleDoi = getArticleDoi();
            File articleDir = getArticleDir();
            articleFile = new File(articleDir + File.separator + articleDoi + ".xml");
        }
        return articleFile;
    }

    @Override
    public File getArticlePdfFile() {
        if (articlePdfFile == null) {
            String articleDoi = getArticleDoi();
            File articleDir = getArticleDir();
            articlePdfFile = new File(articleDir + File.separator + articleDoi + ".pdf");
        }
        return articlePdfFile;
    }

    @Override
    public File getArticleGraphicsDir() {
        if (articleGraphicsDir == null) {
            File articleDir = getArticleDir();
            articleGraphicsDir = new File(articleDir + File.separator + "graphic");
        }
        return articleGraphicsDir;
    }

    public String getArticleDoi() {
        if (articleDoi == null) {
            articleDoi = getArticleDir().getName();
        }
        return articleDoi;
    }

    public String getIssueId() {
        if (issueId == null) {
            issueId = getIssueDir().getName();
        }
        return issueId;
    }

    @Override
    public String getIssueDoi() {
        if (issueDoi == null) {
            fillIssueDoi();
        }
        return issueDoi;
    }

    private void fillIssueDoi() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(false);
            saxParserFactory.setFeature("http://xml.org/sax/features/namespaces", false);
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            saxParserFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new FileInputStream(getManifestFile()), new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    issueDoi = attributes.getValue("group-doi").split("/")[0];
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
