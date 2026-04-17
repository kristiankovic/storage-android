package com.pdm.almacenamiento.entities.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.pdm.almacenamiento.entities.Cliente;
import com.pdm.almacenamiento.entities.Factura;

import java.util.List;

public class ClienteFactura {

    @Embedded
    public Cliente cliente;
    @Relation(parentColumn = "idCliente", entityColumn = "idCliente")
    public List<Factura> facturas;
}
