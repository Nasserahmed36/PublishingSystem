package com.atypon.service;

import com.atypon.domain.Issue;
import com.atypon.domain.dao.IssueDao;

public class IssueServiceImpl implements IssueService {
    private final IssueDao dao;

    public IssueServiceImpl(IssueDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean create(Issue issue) {
            return dao.create(issue);
    }

    @Override
    public boolean isExisted(String issueDao) {
        return dao.isExisted(issueDao);
    }


    @Override
    public boolean createIfNotExist(Issue issue) {
        if (!dao.isExisted(issue.getDoi())) {
            return dao.create(issue);
        }
        return false;
    }


}
