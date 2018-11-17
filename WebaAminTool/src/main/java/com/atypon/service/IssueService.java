package com.atypon.service;

import com.atypon.domain.Issue;

public interface IssueService {
    boolean create(Issue issue);
    boolean isExisted(String issueDao);
    boolean createIfNotExist(Issue issue);
}
