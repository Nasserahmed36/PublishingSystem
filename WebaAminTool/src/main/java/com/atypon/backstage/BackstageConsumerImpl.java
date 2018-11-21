package com.atypon.backstage;

import com.atypon.domain.ArticleSubmission;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackstageConsumerImpl implements BackstageConsumer {
    private volatile boolean keepRunning = true;
    private final BlockingQueue<ArticleSubmission> creationQueue;
    private final BlockingQueue<ArticleSubmission> deletionQueue;
    private final Processor<ArticleSubmission> submissionProcessor =
            new SubmissionProcessor();
    private final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public BackstageConsumerImpl(BlockingQueue<ArticleSubmission> creationQueue,
                                 BlockingQueue<ArticleSubmission> deletionQueue) {
        this.creationQueue = creationQueue;
        this.deletionQueue = deletionQueue;
    }

    @Override
    public void start() {
        singleThreadExecutor.execute(() -> {
            while (keepRunning) {
                try {
                    ArticleSubmission articleSubmission = creationQueue.take();
                    submissionProcessor.process(articleSubmission);
                } catch (ProcessingException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void stop() {
        keepRunning = false;
        singleThreadExecutor.shutdownNow();
    }

}
