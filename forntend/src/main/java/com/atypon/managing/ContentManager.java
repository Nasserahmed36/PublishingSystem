package com.atypon.managing;

import com.atypon.context.ApplicationContext;
import com.atypon.domain.Article;
import com.atypon.domain.Issue;
import com.atypon.service.ArticleService;
import com.atypon.service.IssueService;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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

    public String getArticlePath(String doi) throws IOException {
        Article article = articleService.get(doi);
        Issue issue = issueService.get(article.getIssueDoi());
        String articleDoi = doi.split("/")[1];
        updateCacheIfNeeded(issue.getJournalPrintIssn(), issue.getDoi(), articleDoi);
        String articleDir = articleDir(contentPath, issue.getJournalPrintIssn(),
                issue.getDoi(), articleDoi);
        return fullPagePath(articleDir);
    }


    public void updateCacheIfNeeded(String journalIssn, String issueDoi, String articleDoi) throws IOException {
        File articleSourceDirFile = new File(articleProcessedContentDir(journalIssn, issueDoi, articleDoi));
        File articleDirFile = new File(articleDir(contentPath, journalIssn, issueDoi, articleDoi));
        if (!articleDirFile.exists()) {
            FileUtils.copyDirectory(articleSourceDirFile, articleDirFile);
        } else if (articleSourceDirFile.lastModified() >
                articleDirFile.lastModified()) {
            articleDirFile.delete();
            FileUtils.copyDirectory(articleSourceDirFile, articleDirFile);

        }

    }

}
