package com.atypon.domain;


public class Notification {
    private int serailNumber;
    private String journalPrintIssn;
    private String issueDoi;
    private String articleDoi;
    private Operation operation;


    public int getSerailNumber() {
        return serailNumber;
    }

    public void setSerailNumber(int serailNumber) {
        this.serailNumber = serailNumber;
    }

    public String getJournalPrintIssn() {
        return journalPrintIssn;
    }

    public void setJournalPrintIssn(String journalPrintIssn) {
        this.journalPrintIssn = journalPrintIssn;
    }

    public String getIssueDoi() {
        return issueDoi;
    }

    public void setIssueDoi(String issueDoi) {
        this.issueDoi = issueDoi;
    }

    public String getArticleDoi() {
        return articleDoi;
    }

    public void setArticleDoi(String articleDoi) {
        this.articleDoi = articleDoi;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public enum Operation {
        CREATED,
        UPDATED,
        DELETED
    }
}
