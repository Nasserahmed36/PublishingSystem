package com.atypon.backstage;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

import static com.atypon.backstage.BackstageSettings.*;

public class MainFileTransformer {

    private final FileTransformer jatsToFullPageTransformer;
    private final FileTransformer jatsToArticleMetaTransformer;
    private final FileTransformer jatsToIssueMetaTransformer;

    public MainFileTransformer() {
        jatsToFullPageTransformer =
                new XsltTransformer(toFullPageXsl());
        jatsToArticleMetaTransformer =
                new XsltTransformer(toArticleMetadataXsl());
        jatsToIssueMetaTransformer =
                new XsltTransformer(toIssueMetadataXsl());
    }

    public String transform(ArticleSubmissionNavigator navigator, String journalPrintIssn) throws IOException, TransformerException {
        String issueDoi = navigator.getIssueDoi();
        String articleDoi = navigator.getArticleDoi();
        String outputDir = articleProcessedContentDir(journalPrintIssn, issueDoi, articleDoi);
        makeNeededDirs(outputDir);
        jatsToIssueMetaTransformer.transform(navigator.getIssueMetadataFile().getPath(),
                issueMetadataPath(outputDir));
        jatsToArticleMetaTransformer.transform(navigator.getArticleFile().getPath(),
                articleMetadataPath(outputDir));
        jatsToFullPageTransformer.transform(navigator.getArticleFile().getPath(),
                fullPagePath(outputDir));

        return outputDir;
    }

    private void makeNeededDirs(String outputDir) {
        new File(outputDir).mkdirs();
    }
}
