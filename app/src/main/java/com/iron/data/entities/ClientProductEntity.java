package com.iron.data.entities;

import androidx.annotation.Nullable;
import androidx.room.Embedded;
import androidx.room.Relation;

/**
 * @Author: XNGEL
 * @Date: 09/10/21
 */
public class ClientProductEntity {

    @Embedded
    public ClientEntity client;

    @Relation(
            parentColumn = "icClient",
            entityColumn = "idProduct"
    )
    @Nullable
    public ProductEntity product;


}
