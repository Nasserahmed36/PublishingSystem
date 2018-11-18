package com.atypon.service;

import com.atypon.domain.Journal;
import com.atypon.domain.dao.JournalDao;

import java.util.List;

public class JournalServiceImpl implements JournalService {
    private final JournalDao dao;

    public JournalServiceImpl(JournalDao journalDao) {
        dao = journalDao;
    }

    @Override
    public boolean create(Journal journal) {
        return dao.create(journal);
    }

    @Override
    public Journal get(String issn) {
        return dao.get(issn);
    }

    @Override
    public List<Journal> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Journal> getByDiscipline(String discipline) {
        return dao.getByDiscipline(discipline);
    }

    @Override
    public boolean update(Journal journal) {
        return dao.update(journal);
    }

    @Override
    public boolean delete(String issn) {
        return dao.delete(issn);
    }


}
