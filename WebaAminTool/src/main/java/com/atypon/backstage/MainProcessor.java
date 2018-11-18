package com.atypon.backstage;

import com.atypon.commons.FileUtils;
import com.atypon.context.ApplicationContext;
import com.atypon.domain.ArticleSubmission;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class MainProcessor implements Processor<ArticleSubmission> {

    private static final String RESOURCES_PATH = MainProcessor.class.getResource("/").getPath();
    private static final String XSL_FILES = RESOURCES_PATH + File.separator + "xsl";
    private static final String JATS_HTML_XSL_FILE = XSL_FILES + File.separator + "jatsToHtml.xsl";
    private static final String OUTPUT_PATH = "/mnt/ssd/ideaProjects/Literatum/processedContent";

    private final FileTransformer jatsToHtmlTransformer;
    private final Processor<String> issueMetadataProcessor;
    private final Processor<String> articleMetadataProcessor;

    public MainProcessor() {
        jatsToHtmlTransformer = new JatsToHtmlTransformer(JATS_HTML_XSL_FILE);
        issueMetadataProcessor = new IssueMetadataProcessor((IssueService)
                ApplicationContext.getAttribute("issueService"));
        articleMetadataProcessor = new ArticleMetadataProcessor((ArticleService)
                ApplicationContext.getAttribute("articleService"));
        // gather data
        // add them all at once

    }


    @Override
    public void process(ArticleSubmission articleSubmission) throws ProcessingException {
        String unzipDir = unzip(articleSubmission);
        if (validate(unzipDir)) {
            try {
                process(articleSubmission, unzipDir);
                notify(true);
            } catch (IOException | TransformerException e) {
                throw new ProcessingException(e);
            }
        } else {
            notify(false);
        }
    }

    private String unzip(ArticleSubmission articleSubmission) {
        String articlePath = articleSubmission.getPath();
        String destDir = articlePath.replace(".zip", "");
        FileUtils.unzip(articlePath, destDir);
        return destDir;
    }

    private boolean validate(String unzipDir) {
        return true;
    }

    private void process(ArticleSubmission articleSubmission, String unzipDir) throws ProcessingException, IOException, TransformerException {
        ArticleSubmissionNavigator navigator = new ArticleSubmissionNavigator(unzipDir);
        File article = navigator.getArticleFile();
        File issueMetadataFile = navigator.getIssueMetadataFile();

        File outputDir = new File(OUTPUT_PATH + File.separator +
                articleSubmission.getArticleFileName().replace(".zip", ""));
        outputDir.mkdirs();
        addDtd(navigator);
//        jatsToHtmlTransformer.transform(article.getPath(),
//                outputDir.getPath() + File.separator + navigator.getArticleDoi() + ".html");

        issueMetadataProcessor.process(issueMetadataFile.getPath());
        articleMetadataProcessor.process(article.getPath());
    }

    private void addDtd(ArticleSubmissionNavigator navigator) throws IOException {
        File dtdFile = new File(navigator.getArticleDir().getAbsolutePath() + File.separator + "JATS-archivearticle1.dtd");
        dtdFile.createNewFile();
    }

    private void notify(boolean success) {

    }

}
