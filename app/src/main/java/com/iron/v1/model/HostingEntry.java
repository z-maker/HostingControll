package com.iron.v1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

public class HostingEntry implements Serializable {

    private String id;

    private String domain;
    private String username;
    private String adminEmail;
    private long startDate;
    private boolean suspended;
    private String notes;

    public HostingEntry() {
        id = UUID.randomUUID().toString();
        suspended = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "HostingEntry{" +
                "id=" + id +
                ", domain='" + domain + '\'' +
                ", username='" + username + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", startDate=" + startDate +
                ", suspended=" + suspended +
                ", notes='" + notes + '\'' +
                '}';
    }
}
