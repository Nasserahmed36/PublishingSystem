package com.atypon.backstage;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class StaticResourcesCopierImpl implements StaticResourcesCopier {

    @Override
    public void copy(ArticleSubmissionNavigator navigator, String destinationDir) throws IOException {
        File graphicsDir = navigator.getArticleGraphicsDir();

        if (graphicsDir.exists()) {
            FileUtils.copyDirectory(graphicsDir, new File(destinationDir +
                    File.separator + graphicsDir.getName()));
        }
        File articlePdf = navigator.getArticlePdfFile();
        if (articlePdf.exists()) {
            FileUtils.copyFile(articlePdf, new File(destinationDir +
                    File.separator + articlePdf.getName()));
        }
        File issueCover = navigator.getIssueCover();
        if (issueCover.exists()) {
            FileUtils.copyFile(issueCover, new File(destinationDir +
                    File.separator + issueCover.getName()));
        }
    }

}
