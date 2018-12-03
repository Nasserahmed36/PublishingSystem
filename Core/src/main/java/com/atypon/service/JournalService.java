package com.atypon.service;

import com.atypon.domain.Journal;

import java.util.List;

public interface JournalService {
    boolean create(Journal journal);

    Journal get(String issn);

    List<Journal> getAll();

    List<Journal> getByDiscipline(String discipline);


    boolean update(Journal journal);

    boolean delete(String issn);
}
