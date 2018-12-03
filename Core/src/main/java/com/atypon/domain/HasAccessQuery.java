package com.atypon.domain;

public class HasAccessQuery {

    private String username;
    private String contentId;
    private UserRequest userRequest;
    private String inquirerName;
    private String inquirerSignature;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public String getInquirerName() {
        return inquirerName;
    }

    public void setInquirerName(String inquirerName) {
        this.inquirerName = inquirerName;
    }

    public String getInquirerSignature() {
        return inquirerSignature;
    }

    public void setInquirerSignature(String inquirerSignature) {
        this.inquirerSignature = inquirerSignature;
    }
}
