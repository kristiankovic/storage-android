package com.pdm.almacenamiento.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "factura", foreignKeys = @ForeignKey(
        entity = Cliente.class,
        parentColumns = "idCliente",
        childColumns = "idCliente",
        onDelete = ForeignKey.RESTRICT))

public class Factura {

    @PrimaryKey(autoGenerate = true)
    public int idFactura;
    public int idCliente;
    public Date date;
}
