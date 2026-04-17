package com.pdm.almacenamiento.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.pdm.almacenamiento.entities.Cliente;
import com.pdm.almacenamiento.entities.Factura;
import com.pdm.almacenamiento.entities.relations.ClienteFactura;

import java.util.List;

// transaction -> conjunto de instrucciones que se ejecutan en una unidad unica
@Dao
public interface FacturasDAO {

    @Insert
    long insertarCliente(Cliente cliente);

    @Insert
    long insertarFactura(Factura factura);

    @Query("SELECT * FROM clientes")
    List<Cliente> getClientes();

    @Query("SELECT * FROM factura")
    List<Factura> getFacturas();

    @Transaction
    @Query("SELECT * FROM clientes")
    List<ClienteFactura> getFacturasCliente();

    // no se necesita migrar si se van agregar pocos metodos
    // update: toma todo el objeto y hace una busqueda por llave primaria *El Entity debe tener una llave primaria*
    @Update
    int updateCliente(Cliente cliente);

    // otro metodo para hacer update
    @Query("UPDATE clientes SET nombreCliente=:nombre WHERE idCliente=:idCliente")
    int updateCliente(String nombre, int idCliente);

    //primera forma para eliminar un elemento
    @Delete()
    int deleteCliente(Cliente cliente);

    //segunda forma
    @Query("DELETE FROM clientes WHERE idCliente=:idCliente")
    int deleteCliente(int idCliente);
}
