package com.atypon.managing;

import com.atypon.domain.Article;
import com.atypon.domain.Issue;

import org.apache.commons.io.FileUtils;

import java.io.File;

import static com.atypon.managing.BackstageSettings.*;

public class ContentManager {

    private final String contentPath;

    public ContentManager(String contentPath) {
        this.contentPath = contentPath;
    }


    public String getArticlePath(Article article, Issue issue) {

        String articleDoi = article.getDoi().split("/")[1];
        updateCacheIfNeeded(issue.getJournalPrintIssn(), issue.getDoi(), articleDoi);
        String cachedArticleDir = cachedArticleDir(contentPath, issue.getJournalPrintIssn(),
                issue.getDoi(), articleDoi);
        return fullPagePath(cachedArticleDir);
    }

    public String getIssueCoverPath(Article article, Issue issue) {

        String articleDoi = article.getDoi().split("/")[1];
        updateCacheIfNeeded(issue.getJournalPrintIssn(), issue.getDoi(), articleDoi);
        String cachedArticleDir = cachedArticleDir(contentPath, issue.getJournalPrintIssn(),
                issue.getDoi(), articleDoi);
        return issueCover(cachedArticleDir, issue.getDoi());
    }


    public String getArticlePdf(Article article, Issue issue) {

        String articleDoi = article.getDoi().split("/")[1];
        updateCacheIfNeeded(issue.getJournalPrintIssn(), issue.getDoi(), articleDoi);
        String cachedArticleDir = cachedArticleDir(contentPath, issue.getJournalPrintIssn(),
                issue.getDoi(), articleDoi);
        return articlePdf(cachedArticleDir, articleDoi);
    }


    private void updateCacheIfNeeded(String journalIssn, String issueDoi, String articleDoi) {
        try {
            File articleSourceDirFile = new File(articleProcessedContentDir(journalIssn, issueDoi, articleDoi));
            File cachedArticleDir = new File(cachedArticleDir(contentPath, journalIssn, issueDoi, articleDoi));
            if (!cachedArticleDir.exists()) {
                FileUtils.copyDirectory(articleSourceDirFile, cachedArticleDir);
            } else if (articleSourceDirFile.lastModified() >
                    cachedArticleDir.lastModified()) {
                FileUtils.copyDirectory(articleSourceDirFile, cachedArticleDir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
