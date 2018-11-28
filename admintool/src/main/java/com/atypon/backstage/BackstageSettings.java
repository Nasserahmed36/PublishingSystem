package com.atypon.backstage;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * for internal usage
 *
 * @author Nasser Alkhateeb
 * @version 1.0, 2018/08/14
 */
class BackstageSettings {

    private static final ClassLoader classLoader = BackstageSettings.class.getClassLoader();
    private static final File file = new File(classLoader.getResource("config/settings.properties").getFile());
    private static final Properties properties = new Properties();
    private static final String RESOURCES_PATH = BackstageSettings.class.getResource("/").getPath();

    private static final String XSL_FILES = RESOURCES_PATH + File.separator + "xsl";
    private static final String TO_FULL_PAGE_XSL = XSL_FILES + File.separator + "jatsToFullPage.xsl";
    private static final String TO_ABSTRACT_PAGE_XSL = XSL_FILES + File.separator + "jatsToAbstractPage.xsl";
    private static final String TO_ARTICLE_META_XSL = XSL_FILES + File.separator + "jatsToArticleMetadata.xsl";
    private static final String TO_ISSUE_METADATA_XSL = XSL_FILES + File.separator + "jatsToIssueMetadata.xsl";


    private static final String FULL_PAGE_FILE_NAME = "full.html";
    private static final String ABSTRACT_PAGE_FILE_NAME = "abstract.html";
    private static final String ARTICLE_METADATA_FILE_NAME = "articleMetadata.xml";
    private static final String ISSUE_METADATA_FILE_NAME = "issueMetadata.xml";


    // Suppresses default constructor, ensuring non-instantiability.
    private BackstageSettings() {
    }


    static {
        try {
            properties.load(new FileReader(file));
        } catch (Exception e) {
            throw new Error("Can not load settings file: " + e.getMessage());
        }
    }


    static String backstageOutputDir() {
        return properties.getProperty("BACKSTAGE_OUTPUT_DIR");
    }

    static String toFullPageXsl() {
        return TO_FULL_PAGE_XSL;
    }

    static String getToAbstractPageXsl() {
        return TO_ABSTRACT_PAGE_XSL;
    }

    static String toArticleMetadataXsl() {
        return TO_ARTICLE_META_XSL;
    }

    static String toIssueMetadataXsl() {
        return TO_ISSUE_METADATA_XSL;
    }

    static String unzipArticleSubmissionPath(String articleSubmissionPath) {
        return articleSubmissionPath.replace(".zip", "");
    }

    static String articleProcessedContentDir(String journalId, String issueId, String articleId) {
        return backstageOutputDir() + File.separator + journalId +
                File.separator + issueId + File.separator + articleId;
    }

    static String fullPagePath(String outputDir) {
        return outputDir + File.separator + FULL_PAGE_FILE_NAME;
    }

    static String fullPagePath(String journalId, String issueId, String articleId) {
        return articleProcessedContentDir(journalId, issueId, articleId) +
                File.separator + FULL_PAGE_FILE_NAME;
    }

    static String abstractPagePath(String outputDir) {
        return outputDir + File.separator + ABSTRACT_PAGE_FILE_NAME;
    }

    static String articleMetadataPath(String outputDir) {
        return outputDir + File.separator + ARTICLE_METADATA_FILE_NAME;
    }

    static String articleMetadataPath(String journalId, String issueId, String articleId) {
        return articleProcessedContentDir(journalId, issueId, articleId) +
                File.separator + ARTICLE_METADATA_FILE_NAME;
    }

    static String issueMetadataPath(String outputDir) {
        return outputDir + File.separator + ISSUE_METADATA_FILE_NAME;
    }

    static String issueMetadataPath(String journalId, String issueId, String articleId) {
        return articleProcessedContentDir(journalId, issueId, articleId) +
                File.separator + ISSUE_METADATA_FILE_NAME;
    }


}
