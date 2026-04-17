package com.pdm.almacenamiento;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import androidx.room.migration.Migration;

import com.pdm.almacenamiento.daos.FacturasDAO;
import com.pdm.almacenamiento.database.AppDataBase;
import com.pdm.almacenamiento.entities.Cliente;
import com.pdm.almacenamiento.migrations.Migrations;

public class MainActivity extends AppCompatActivity {
    public TextView msg;
    private AppDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout();

        // almacenamiento local
        SharedPreferences miArchivo = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miArchivo.edit();

        editor.putString("API_KEY", "123");
        editor.apply();

        msg = findViewById(R.id.txt);

        msg.setText(miArchivo.getString("API_KEY", "No encontrado"));

        // INSTANCIA DE LA BASE DE DATOS
        // constructor de la base de datos      -> databaseBuilder
        // clase que define la base de datos    -> AppDataBase.class

        // allowMainThreadQueries: sirve para que la base de datos opere en el mismo hilo que la UI *NO SE RECOMIENDA*

        // siempre debe conectarse de esta manera
        db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,
                "db_facturas")
                .addMigrations(Migrations.MIGRATION_1_2)
                .allowMainThreadQueries().build();

        // insertar datos en la db
        Cliente c = new Cliente();
        //c.nombreCliente = "bad bani";
        c.idCliente = 1; // validar que exista el indice del objeto que se esta insertando
        c.nombreCliente = "maik towers";

        // obtener la implementacion de la interfaz DAO que Room genera
        FacturasDAO dao = db.DAOfactutas();

        // ejecutar la consulta SQL
        //dao.insertarCliente(c);

        dao.insertarCliente(c); // se usa para solo cuando se quieran cambiar
        //int request = dao.deleteCliente(c);
        //dao.deleteCliente(6);


        //Log.d("QUERY", String.valueOf(request));                        // algunos cambios

        msg.setText(c.nombreCliente);
    }

    public void layout(){
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}