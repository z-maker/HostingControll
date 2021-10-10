package com.iron.data.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */

public class ProductServiceEntity {

    @Embedded
    public ProductEntity product;

    @Relation(
            entity = ServiceEntity.class,
            parentColumn = "idProduct",
            entityColumn = "idService"
    )
    public ServiceEntity service;
}
