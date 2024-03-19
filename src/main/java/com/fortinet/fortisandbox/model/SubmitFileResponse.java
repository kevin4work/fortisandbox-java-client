package com.fortinet.fortisandbox.model;

public class SubmitFileResponse {
    private String url;
    private Status status;
    private SubmitFileResult data;

    // Getters and setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public SubmitFileResult getData() {
        return data;
    }

    public void setData(SubmitFileResult data) {
        this.data = data;
    }
}