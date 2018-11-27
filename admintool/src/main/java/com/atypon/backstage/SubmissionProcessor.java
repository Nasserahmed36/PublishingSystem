package com.atypon.backstage;

import com.atypon.context.ApplicationContext;
import com.atypon.domain.ArticleSubmission;
import com.atypon.domain.Notification;
import com.atypon.service.ArticleSubmissionService;
import com.atypon.notification.NotificationService;

import javax.xml.transform.TransformerException;
import java.io.IOException;

import static com.atypon.domain.Notification.Operation.*;


public class SubmissionProcessor implements Processor<ArticleSubmission> {

    private final ProcessedContentProcessor processedContentProcessor;
    private final ArticleSubmissionService submissionService;
    private final NotificationService notificationService;
    private final StaticResourcesCopier copier;

    public SubmissionProcessor() {
        processedContentProcessor = new ProcessedContentProcessor();
        submissionService = (ArticleSubmissionService) ApplicationContext.getAttribute("articleSubmissionService");
        notificationService = (NotificationService) ApplicationContext.getAttribute("notificationService");
        copier = new StaticResourcesCopierImpl();
    }

    @Override
    public void process(ArticleSubmission articleSubmission) throws ProcessingException {
        try {
            processArticleSubmission(articleSubmission);
            notify(articleSubmission, true);
        } catch (Exception e) {
            notify(articleSubmission, false);
            throw new ProcessingException(e);
        }
    }

    private void processArticleSubmission(ArticleSubmission submission) throws IOException, TransformerException, ProcessingException {
        ArticleSubmissionNavigator navigator = new ZipSubmissionNavigator(submission.getPath());
        MainFileTransformer transformer = new MainFileTransformer();
        String transformedFilesDir = transformer.transform(navigator, submission.getSeriesIssn());
        copier.copy(navigator, transformedFilesDir);
        processedContentProcessor.process(transformedFilesDir);
        notificationService.create(createNotification(submission, navigator));
    }

    private Notification createNotification(ArticleSubmission submission, ArticleSubmissionNavigator navigator) {
        Notification notification = new Notification();
        notification.setType("Article");
        notification.setContent(navigator.getArticleDoi());
        notification.setOperation(CREATED);
        return notification;
    }


    private void notify(ArticleSubmission articleSubmission, boolean success) {
        String status;
        if (success) {
            status = "Success";
        } else {
            status = "Failed";
        }
        submissionService.updateStatus(articleSubmission.getId(), status);
    }

}
