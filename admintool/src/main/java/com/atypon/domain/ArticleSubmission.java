package com.atypon.domain;

public class ArticleSubmission {
    private int id;
    private String seriesIssn;
    private String fileName;
    private String status;
    private long timestamp;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeriesIssn() {
        return seriesIssn;
    }

    public void setSeriesIssn(String seriesIssn) {
        this.seriesIssn = seriesIssn;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ArticleSubmission{" +
                "seriesIssn='" + seriesIssn + '\'' +
                ", fileName='" + fileName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
