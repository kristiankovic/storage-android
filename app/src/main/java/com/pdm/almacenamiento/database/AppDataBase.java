package com.pdm.almacenamiento.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.pdm.almacenamiento.convertions.Converter;
import com.pdm.almacenamiento.daos.FacturasDAO;
import com.pdm.almacenamiento.entities.Cliente;
import com.pdm.almacenamiento.entities.Factura;

@Database(
        entities = {Cliente.class, Factura.class},
        version = 3, //cambio de version
        exportSchema = true,
        autoMigrations = {@AutoMigration(from = 2, to = 3)}) // source, destino | migracion automatica
@TypeConverters(Converter.class) // conversor de fechas
public abstract class AppDataBase extends RoomDatabase {

    // cuando una clase es abstracta funciona como plantilla base para
    // otras clases y no puede instanciarse
    public abstract FacturasDAO DAOfactutas();
}
