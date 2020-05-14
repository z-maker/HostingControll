package com.iron.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hosting_table")
public class HostingEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String clientName;

    private String domainUrl;
    private int domainStatus;
    private String domainCreated;
    private String domainUpdated;
    private String domainExpires;

    private int paymentDelay;
    private int paymentStatus;
    private float hostingPrice;
    private float domainPrice;
    private String lastDomainPayment;
    private String lastHostingPayment;

    private int hostingStatus;
    private long created;

    public HostingEntity(String clientName, String domainUrl, int domainStatus, String domainCreated, String domainUpdated, String domainExpires, int paymentDelay, int paymentStatus, float hostingPrice, float domainPrice, String lastDomainPayment, String lastHostingPayment, int hostingStatus, long created) {
        this.clientName = clientName;
        this.domainUrl = domainUrl;
        this.domainStatus = domainStatus;
        this.domainCreated = domainCreated;
        this.domainUpdated = domainUpdated;
        this.domainExpires = domainExpires;
        this.paymentDelay = paymentDelay;
        this.paymentStatus = paymentStatus;
        this.hostingPrice = hostingPrice;
        this.domainPrice = domainPrice;
        this.lastDomainPayment = lastDomainPayment;
        this.lastHostingPayment = lastHostingPayment;
        this.hostingStatus = hostingStatus;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDomainUrl() {
        return domainUrl;
    }

    public int getDomainStatus() {
        return domainStatus;
    }

    public String getDomainCreated() {
        return domainCreated;
    }

    public String getDomainUpdated() {
        return domainUpdated;
    }

    public String getDomainExpires() {
        return domainExpires;
    }

    public int getPaymentDelay() {
        return paymentDelay;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public float getHostingPrice() {
        return hostingPrice;
    }

    public float getDomainPrice() {
        return domainPrice;
    }

    public String getLastDomainPayment() {
        return lastDomainPayment;
    }

    public String getLastHostingPayment() {
        return lastHostingPayment;
    }

    public int getHostingStatus() {
        return hostingStatus;
    }

    public long getCreated() {
        return created;
    }
}
