package com.atypon.domain;

public class Licence {
    private String name;
    private String contentLicenceDescription;
    private String userLicenceDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentLicenceDescription() {
        return contentLicenceDescription;
    }

    public void setContentLicenceDescription(String contentLicenceDescription) {
        this.contentLicenceDescription = contentLicenceDescription;
    }

    public String getUserLicenceDescription() {
        return userLicenceDescription;
    }

    public void setUserLicenceDescription(String userLicenceDescription) {
        this.userLicenceDescription = userLicenceDescription;
    }
}
