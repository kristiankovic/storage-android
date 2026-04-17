package com.pdm.almacenamiento.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {

    // consultas de migracion
    public static final Migration MIGRATION_1_2 = new Migration(1, 2){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase db) {
            super.migrate(db);
            db.execSQL("CREATE TABLE IF NOT EXISTS factura_temp (idFactura INTEGER NOT NULL PRIMARY KEY, idCliente INTEGER NOT NULL, date INTEGER)");
            db.execSQL("INSERT INTO factura_temp(idFactura, idCliente, date) SELECT idFactura, idCliente, CASE WHEN date IS NULL THEN NULL ELSE str('%', date)*1000 END FROM factura ");
            db.execSQL("ALTER TABLE factura_temp RENAME TO factura");
        }
    };
}
