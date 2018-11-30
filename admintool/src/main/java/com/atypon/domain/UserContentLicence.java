package com.atypon.domain;

public class UserContentLicence {
    private int id;
    private String username;
    private int contentLicenceId;
    private long startDate;
    private String body;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
}
