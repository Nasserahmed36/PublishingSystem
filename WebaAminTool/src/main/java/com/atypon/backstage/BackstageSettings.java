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

    private static ClassLoader classLoader = BackstageSettings.class.getClassLoader();
    private static File file = new File(classLoader.getResource("config/settings.properties").getFile());
    private static Properties properties = new Properties();


    private static final String RESOURCES_PATH = BackstageSettings.class.getResource("/").getPath();
    private static final String XSL_FILES = RESOURCES_PATH + File.separator + "xsl";
    private static final String JATS_HTML_XSL_FILE = XSL_FILES + File.separator + "jatsToHtml.xsl";
    private static final String FULL_PAGE_FILE_NAME = "full.html";
    private static final String ABSTRACT_PAGE_FILE_NAME = "abstract.html";
    private static final String ARTICLE_METADATA_FILE_NAME = "articleMetadata.xml";


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

    static String toHtmlXslFile() {
        return JATS_HTML_XSL_FILE;
    }

    static String toArticleMetadataXslFile() {
        return JATS_HTML_XSL_FILE;
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

    static String abstractPagePath(String outputDir) {
        return outputDir + File.separator + ABSTRACT_PAGE_FILE_NAME;
    }

    static String articleMetadataPath(String outputDir) {
        return outputDir + File.separator + ARTICLE_METADATA_FILE_NAME;
    }

}
