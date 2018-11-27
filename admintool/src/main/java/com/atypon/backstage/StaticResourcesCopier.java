package com.atypon.backstage;

import java.io.IOException;

public interface StaticResourcesCopier {
    void copy(ArticleSubmissionNavigator navigator, String destinationDir) throws IOException;
}
