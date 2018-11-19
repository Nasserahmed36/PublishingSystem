package com.atypon.domain.dao;

import com.atypon.domain.Issue;

import java.util.List;

public interface IssueDao {
    boolean create(Issue issue);
    boolean isExisted(String issueDao);

    List<Issue> getAll();

    List<Issue> getByVolume(int volume);

    int getMaxVolume(String journalPrintIssn);
}
