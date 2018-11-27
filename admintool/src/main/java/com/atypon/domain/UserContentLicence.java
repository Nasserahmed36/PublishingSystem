package com.atypon.domain;

public class UserContentLicence {
    private String username;
    private int contentLicenceId;
    private long startDate;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getContentLicenceId() {
        return contentLicenceId;
    }

    public void setContentLicenceId(int contentLicenceId) {
        this.contentLicenceId = contentLicenceId;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
