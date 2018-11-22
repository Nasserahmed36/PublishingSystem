package com.atypon.backstage;

import com.atypon.commons.FileUtils;
import com.atypon.domain.ArticleSubmission;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static com.atypon.backstage.BackstageSettings.*;

public class SubmissionProcessor implements Processor<ArticleSubmission> {


    private final FileTransformer jatsToFullPageTransformer;
    //    private final FileTransformer jatsToAbstractPageTransformer;
    private final FileTransformer jatsToArticleMetaTransformer;

    private final ArticleMetadataProcessor articleMetadataProcessor;

    public SubmissionProcessor() {
        jatsToFullPageTransformer =
                new XsltTransformer(toFullPageXsl());
        jatsToArticleMetaTransformer =
                new XsltTransformer(toArticleMetadataXsl());


        articleMetadataProcessor = new ArticleMetadataProcessor(null);
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
        String submissionPath = articleSubmission.getPath();
        String destDir = unzipArticleSubmissionPath(submissionPath);
        FileUtils.unzip(submissionPath, destDir);
        return destDir;
    }

    private boolean validate(String unzipDir) {
        return true;
    }

    private void process(ArticleSubmission submission, String unzipDir) throws ProcessingException, IOException, TransformerException {
        ArticleSubmissionNavigator navigator = new ArticleSubmissionNavigator(unzipDir);
        File articleFile = navigator.getArticleFile();
        File issueMetadataFile = navigator.getIssueMetadataFile();
        String outputDir = articleProcessedContentDir(submission.getSeriesIssn(),
                navigator.getIssueId(), navigator.getArticleDoi());
        createMissingDirs(outputDir);
        jatsToArticleMetaTransformer.transform(articleFile.getPath(), articleMetadataPath(outputDir));
//        jatsToFullPageTransformer.transform(articleFile.getPath(), fullPagePath(outputDir));
//        jatsToArticleMetaTransformer.transform(articleFile.getPath(), fullPagePath(outputDir));

//        articleMetadataProcessor.process(fullPagePath(outputDir));
    }

    private boolean createMissingDirs(String outputDir) {
        return new File(outputDir).mkdirs();
    }


    private void notify(boolean success) {

    }

}
