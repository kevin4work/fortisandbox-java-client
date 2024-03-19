package com.fortinet.fortisandbox.model;

public class GetJobIdResponse {
    private String url;
    private Status status;
    private GetJobIdResult data;

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
    
    public GetJobIdResult getData() {
        return data;
    }

    public void setData(GetJobIdResult data) {
        this.data = data;
    }
}