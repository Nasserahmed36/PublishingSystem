package com.atypon.domain.dao;

import com.atypon.domain.Issue;

public interface IssueDao {
    boolean create(Issue issue);
    boolean isExisted(String issueDao);
}
