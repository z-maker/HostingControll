package com.iron.v1.data.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "hosting_table")
public class HostingEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Expose
    @SerializedName("User Name")
    private String clientName;

    @Expose
    @SerializedName("Domain")
    private String domainUrl;

    @Expose
    @SerializedName("Domain status")
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

    @Expose
    @SerializedName("Is Suspended")
    private int hostingStatus;

    @Expose
    @SerializedName("Suspension Reason")
    private String hostingSuspensionReason;

    @Expose
    @SerializedName("Suspension Date")
    private String hostingSuspensionDate;

    @Expose
    @SerializedName("Start Date")
    private String hostingStartDate;

    private long created;

    @Ignore
    public HostingEntity() {
        //
    }

    public HostingEntity(String clientName, String domainUrl, int domainStatus, String domainCreated, String domainUpdated, String domainExpires, int paymentDelay, int paymentStatus, float hostingPrice, float domainPrice, String lastDomainPayment, String lastHostingPayment, int hostingStatus, String hostingSuspensionReason, String hostingSuspensionDate, String hostingStartDate, long created) {
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
        this.hostingSuspensionReason = hostingSuspensionReason;
        this.hostingSuspensionDate = hostingSuspensionDate;
        this.hostingStartDate = hostingStartDate;
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

    public String getHostingSuspensionReason() {
        return hostingSuspensionReason;
    }

    public String getHostingSuspensionDate() {
        return hostingSuspensionDate;
    }

    public String getHostingStartDate() {
        return hostingStartDate;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setDomainUrl(String domainUrl) {
        this.domainUrl = domainUrl;
    }

    public void setDomainStatus(int domainStatus) {
        this.domainStatus = domainStatus;
    }

    public void setDomainCreated(String domainCreated) {
        this.domainCreated = domainCreated;
    }

    public void setDomainUpdated(String domainUpdated) {
        this.domainUpdated = domainUpdated;
    }

    public void setDomainExpires(String domainExpires) {
        this.domainExpires = domainExpires;
    }

    public void setPaymentDelay(int paymentDelay) {
        this.paymentDelay = paymentDelay;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setHostingPrice(float hostingPrice) {
        this.hostingPrice = hostingPrice;
    }

    public void setDomainPrice(float domainPrice) {
        this.domainPrice = domainPrice;
    }

    public void setLastDomainPayment(String lastDomainPayment) {
        this.lastDomainPayment = lastDomainPayment;
    }

    public void setLastHostingPayment(String lastHostingPayment) {
        this.lastHostingPayment = lastHostingPayment;
    }

    public void setHostingStatus(int hostingStatus) {
        this.hostingStatus = hostingStatus;
    }

    public void setHostingSuspensionReason(String hostingSuspensionReason) {
        this.hostingSuspensionReason = hostingSuspensionReason;
    }

    public void setHostingSuspensionDate(String hostingSuspensionDate) {
        this.hostingSuspensionDate = hostingSuspensionDate;
    }

    public void setHostingStartDate(String hostingStartDate) {
        this.hostingStartDate = hostingStartDate;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
