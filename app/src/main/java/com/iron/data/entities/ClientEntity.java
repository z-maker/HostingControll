package com.iron.data.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
@Entity(tableName = "hc_clients", indices = {@Index(value = {"idClient"}, unique = true)})
public class ClientEntity {

    @PrimaryKey(autoGenerate = true)
    private int idClient;

    private String manager;

    private String brand;

    private String phone;

    private String email;

    private long joined;

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getJoined() {
        return joined;
    }

    public void setJoined(long joined) {
        this.joined = joined;
    }
}
