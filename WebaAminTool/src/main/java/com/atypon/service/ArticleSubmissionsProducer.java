package com.atypon.service;

import com.atypon.domain.ArticleSubmission;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class ArticleSubmissionsProducer implements ArticleSubmissionService {
    private final ArticleSubmissionService service;
    private final BlockingQueue<ArticleSubmission> creationQueue;
    private final BlockingQueue<ArticleSubmission> deletionQueue;

    public ArticleSubmissionsProducer(ArticleSubmissionService service,
                                      BlockingQueue<ArticleSubmission> creationQueue,
                                      BlockingQueue<ArticleSubmission> deletionQueue) {
        this.service = service;
        this.creationQueue = creationQueue;
        this.deletionQueue = deletionQueue;
    }

    @Override
    public boolean save(ArticleSubmission articleSubmission, InputStream fileInputStream) {
        boolean result = service.save(articleSubmission, fileInputStream);
        if (result) {
            try {
                creationQueue.put(articleSubmission);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public List<ArticleSubmission> getAll() {
        return null;
    }
}
