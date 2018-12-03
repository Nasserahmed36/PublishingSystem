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
    public int save(ArticleSubmission articleSubmission, InputStream fileInputStream) {
        int result = service.save(articleSubmission, fileInputStream);
        if (result != -1) {
            try {
                creationQueue.put(get(result));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        return service.updateStatus(id, status);
    }

    @Override
    public ArticleSubmission get(int id) {
        return service.get(id);
    }


    @Override
    public List<ArticleSubmission> getAll() {
        return service.getAll();
    }
}
