package com.fortinet.fortisandbox.model;

public class GetJobIdResult {
    private String[] jids;
    private int total_jids;

    public String[] getJids() {
        return jids;
    }

    public void setJids(String[] jids) {
        this.jids = jids;
    }

    public int getTotal_jids() {
        return total_jids;
    }

    public void setTotal_jids(int total_jids) {
        this.total_jids = total_jids;
    }
}