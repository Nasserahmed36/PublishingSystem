package com.atypon.service;

import com.atypon.domain.Issue;

import java.util.List;

public interface IssueService {
    boolean create(Issue issue);
    List<Issue> getAll();
    List<Issue> getByVolume(int volume);
    boolean isExisted(String issueDao);
    boolean createIfNotExist(Issue issue);
    int getLastVolume(String journalPrintIssn);
}
