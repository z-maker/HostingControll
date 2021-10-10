package com.iron.data.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
@Entity(tableName = "hc_products", indices = {@Index( value = {"idProduct"}, unique = true)})
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int idProduct;

    private String name;

    private String description;

    private String created;

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
