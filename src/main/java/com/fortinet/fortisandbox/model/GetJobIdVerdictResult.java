package com.fortinet.fortisandbox.model;

public class GetJobIdVerdictResult {
    private String jid;
    private int start_ts;
    private int finish_ts;
    private int untrusted;
    private int now;
    private String rating;
    private int score;
    private String sha256;
    private String sha1;
    private String malware_name;
    private int vid;
    private String infected_os;
    private String detection_os;
    private String rating_source;
    private String category;
    private String detail_url;
    private String download_url;
    private int false_positive_negative;
    private String file_name;

    // Getter and setter methods

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public int getStart_ts() {
        return start_ts;
    }

    public void setStart_ts(int start_ts) {
        this.start_ts = start_ts;
    }

    public int getFinish_ts() {
        return finish_ts;
    }

    public void setFinish_ts(int finish_ts) {
        this.finish_ts = finish_ts;
    }

    public int getUntrusted() {
        return untrusted;
    }

    public void setUntrusted(int untrusted) {
        this.untrusted = untrusted;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getSha1() {
        return sha1;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public String getMalware_name() {
        return malware_name;
    }

    public void setMalware_name(String malware_name) {
        this.malware_name = malware_name;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getInfected_os() {
        return infected_os;
    }

    public void setInfected_os(String infected_os) {
        this.infected_os = infected_os;
    }

    public String getDetection_os() {
        return detection_os;
    }

    public void setDetection_os(String detection_os) {
        this.detection_os = detection_os;
    }

    public String getRating_source() {
        return rating_source;
    }

    public void setRating_source(String rating_source) {
        this.rating_source = rating_source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public int getFalse_positive_negative() {
        return false_positive_negative;
    }

    public void setFalse_positive_negative(int false_positive_negative) {
        this.false_positive_negative = false_positive_negative;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}