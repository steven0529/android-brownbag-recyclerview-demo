package com.sample.recyclerviewbrownbag.model;

import java.util.Date;

/**
 * Created by S. Reyes on 2/1/16.
 */
public class Email {

    public enum Status {
        Read,
        Unread
    }

    private String sender;
    private String subj;
    private String content;
    private String date;
    private Status status;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubj() {
        return subj;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
