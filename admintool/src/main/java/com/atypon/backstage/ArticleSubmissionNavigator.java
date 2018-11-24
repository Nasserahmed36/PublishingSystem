package com.atypon.backstage;

import java.io.File;

public interface ArticleSubmissionNavigator {
    File getManifestFile();

    File getIssueDir();

    File getIssueFilesDir();

    File getArticleDir();

    File getIssueMetadataFile();

    File getIssueCover();

    File getArticleFile();

    File getArticlePdfFile();

    File getArticleGraphicsDir();

    String getArticleDoi();

    String getIssueId();

    String getIssueDoi();


}
