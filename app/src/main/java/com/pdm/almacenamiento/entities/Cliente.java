package com.pdm.almacenamiento.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clientes")
public class Cliente {

    @PrimaryKey(autoGenerate = true)
    public int idCliente;
    @ColumnInfo(name = "nombreCliente")
    public String nombreCliente;

    //PRIMERA MIGRACION: A V2
    // modificaciones

    public String email; // por defecto admite valores nulos

    //@Nullable
    //public Integer edad; // no admite valores nulos
}
