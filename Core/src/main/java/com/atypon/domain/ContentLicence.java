package com.atypon.domain;

public class ContentLicence {

    private int id;
    private String contentId;
    private String licenceName;
    private String body;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getLicenceName() {
        return licenceName;
    }

    public void setLicenceName(String licenceName) {
        this.licenceName = licenceName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
