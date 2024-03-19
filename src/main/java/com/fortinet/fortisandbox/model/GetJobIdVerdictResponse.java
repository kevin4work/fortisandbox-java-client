package com.fortinet.fortisandbox.model;

public class GetJobIdVerdictResponse {
    private String url;
    private Status status;
    private GetJobIdVerdictResult data;

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
    
    public GetJobIdVerdictResult getData() {
        return data;
    }

    public void setData(GetJobIdVerdictResult data) {
        this.data = data;
    }
}