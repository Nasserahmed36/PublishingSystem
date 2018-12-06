package com.atypon.domain.dao;

import com.atypon.domain.Issue;

import java.util.List;

public interface IssueDao {
    boolean create(Issue issue);

    boolean isExisted(Issue issue);

    Issue get(String journalPrintIssn, String doi);


    List<Issue> getBy(String journalPrintIssn);

    List<Issue> getBy(String journalPrintIssn, int volume);

    int getMaxVolume(String journalPrintIssn);
}
