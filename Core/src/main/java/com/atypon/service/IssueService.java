package com.atypon.service;

import com.atypon.domain.Issue;

import java.util.List;

public interface IssueService {
    boolean create(Issue issue);

    Issue get(String doi);

    List<Issue> getBy(String journalPrintIssn);

    List<Issue> getBy(String journalPrintIssn, int volume);

    boolean isExisted(String issueDao);

    boolean createIfNotExist(Issue issue);

    int getLastVolume(String journalPrintIssn);
}
