package com.iron.data.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
@Entity(tableName = "hc_services", indices = {@Index(value = {"idService"}, unique = true)})
public class ServiceEntity {

    @PrimaryKey(autoGenerate = true)
    private int idService;

    private int type;

    private int status;

    private float price;

    private int paymentType;

    private long lastPayment;

    private long nextPayment;

    private long created;

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public long getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(long lastPayment) {
        this.lastPayment = lastPayment;
    }

    public long getNextPayment() {
        return nextPayment;
    }

    public void setNextPayment(long nextPayment) {
        this.nextPayment = nextPayment;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }
}
