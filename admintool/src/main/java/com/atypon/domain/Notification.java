package com.atypon.domain;


public class Notification {
    private int id;
    private String type;
    private String content;
    private Operation operation;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
