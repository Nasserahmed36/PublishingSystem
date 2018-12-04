package com.atypon.managing;

import com.atypon.context.ApplicationContext;
import com.atypon.domain.Article;
import com.atypon.domain.Issue;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;
import org.apache.commons.io.FileUtils;

import java.io.File;

import static com.atypon.managing.BackstageSettings.*;

public class ContentManager {

    private final String contentPath;

    public ContentManager(String contentPath) {
        this.contentPath = contentPath;
    }

    private IssueService issueService =
            (IssueService) ApplicationContext.getAttribute("issueService");

    private ArticleService articleService =
            (ArticleService) ApplicationContext.getAttribute("articleService");

    public String getArticlePath(String doi) {
        Article article = articleService.get(doi);
        Issue issue = issueService.get(article.getIssueDoi());
        String articleDoi = doi.split("/")[1];
        updateCacheIfNeeded(issue.getJournalPrintIssn(), issue.getDoi(), articleDoi);
        String cachedArticleDir = cachedArticleDir(contentPath, issue.getJournalPrintIssn(),
                issue.getDoi(), articleDoi);
        return fullPagePath(cachedArticleDir);
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
