package com.atypon.domain;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String doi;
    private String title;
    private String issueDoi;
    private String subject;
    private String firstPage;
    private String lastPage;

    private List<Author> authors = new ArrayList<>();

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }


    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public String getIssueDoi() {
        return issueDoi;
    }

    public void setIssueDoi(String issueDoi) {
        this.issueDoi = issueDoi;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        return "Article{" +
                "doi='" + doi + '\'' +
                ", title='" + title + '\'' +
                ", issueDoi='" + issueDoi + '\'' +
                ", subject='" + subject + '\'' +
                ", firstPage='" + firstPage + '\'' +
                ", lastPage='" + lastPage + '\'' +
                ", authors=" + authors +
                '}';
    }

    public static class Author {
        private String givenName;
        private String surName;
        private String degrees;

        public String getGivenName() {
            return givenName;
        }

        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }

        public String getDegrees() {
            return degrees;
        }

        public void setDegrees(String degrees) {
            this.degrees = degrees;
        }

        @Override
        public String toString() {
            return "Author{" +
                    "givenName='" + givenName + '\'' +
                    ", surName='" + surName + '\'' +
                    ", degrees='" + degrees + '\'' +
                    '}';
        }
    }
}
