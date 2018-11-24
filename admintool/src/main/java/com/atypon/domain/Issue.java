package com.atypon.domain;

import java.util.ArrayList;
import java.util.List;

public class Issue {

    private String doi;
    private String journalPrintIssn;
    private int volume;
    private int number;
    private int month;
    private int year;
    private List<String> subjects = new ArrayList<>();


    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getJournalPrintIssn() {
        return journalPrintIssn;
    }

    public void setJournalPrintIssn(String journalPrintIssn) {
        this.journalPrintIssn = journalPrintIssn;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addSubject(String subject) {
        subjects.add(subject);
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "doi='" + doi + '\'' +
                ", journalPrintIssn='" + journalPrintIssn + '\'' +
                ", volume=" + volume +
                ", number=" + number +
                ", month=" + month +
                ", year=" + year +
                ", subjects=" + subjects +
                '}';
    }
}
