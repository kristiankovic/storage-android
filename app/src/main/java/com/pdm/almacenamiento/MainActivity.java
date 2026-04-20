package com.pdm.almacenamiento;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.pdm.almacenamiento.adapters.CompraAdapter;
import com.pdm.almacenamiento.adapters.CustomAdapter;
import com.pdm.almacenamiento.daos.FacturasDAO;
import com.pdm.almacenamiento.database.AppDataBase;
import com.pdm.almacenamiento.entities.Cliente;
import com.pdm.almacenamiento.migrations.Migrations;
import com.pdm.almacenamiento.models.Producto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public TextView msg, txtTotal;
    private AppDataBase db;
    public RecyclerView rvProductos, rvComprados;
    public ArrayList<Producto> data;
    public CustomAdapter adapter;
    public CompraAdapter compraAdapter;
    public float total;

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
        rvProductos = findViewById(R.id.rvProductos);
        rvComprados = findViewById(R.id.rvComprados);
        txtTotal = findViewById(R.id.txtTotal);
        data = new ArrayList<>();

        data.add(new Producto(R.drawable.laptop, "Laptop DELL", 245.80f, 10));
        data.add(new Producto(R.drawable.monitor, "Monitor LG 27\"", 185.00f, 15));
        data.add(new Producto(R.drawable.mouse, "Mouse Gamer RGB", 25.50f, 50));
        data.add(new Producto(R.drawable.keyboard, "Teclado Mecánico", 45.99f, 30));
        data.add(new Producto(R.drawable.headset, "Auriculares Sony", 89.90f, 12));
        data.add(new Producto(R.drawable.tablet, "Tablet Samsung S9", 420.00f, 8));
        data.add(new Producto(R.drawable.printer, "Impresora HP Laser", 150.00f, 5));
        data.add(new Producto(R.drawable.disk, "Disco SSD 1TB", 75.25f, 40));
        data.add(new Producto(R.drawable.webcam, "Webcam HD Logitech", 55.00f, 20));
        data.add(new Producto(R.drawable.router, "Router Wi-Fi 6", 110.00f, 10));
        data.add(new Producto(R.drawable.smartphone, "iPhone 15 Pro", 999.99f, 7));
        data.add(new Producto(R.drawable.watch, "Smartwatch Garmin", 299.00f, 14));
        data.add(new Producto(R.drawable.cable, "Cable HDMI 4K", 12.50f, 100));
        data.add(new Producto(R.drawable.case_pc, "Gabinete ATX Cristal", 65.00f, 11));
        data.add(new Producto(R.drawable.ram, "Memoria RAM 16GB", 85.00f, 25));
        data.add(new Producto(R.drawable.power, "Fuente 750W Gold", 120.00f, 9));
        data.add(new Producto(R.drawable.fan, "Ventilador CPU RGB", 18.00f, 45));
        data.add(new Producto(R.drawable.adapter, "Adaptador USB-C", 15.00f, 60));
        data.add(new Producto(R.drawable.chair, "Silla Ergonómica", 210.00f, 4));
        data.add(new Producto(R.drawable.pad, "Mousepad XL", 19.99f, 35));

        adapter = new CustomAdapter(this, data);

        rvProductos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvProductos.setAdapter(adapter);

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
        //c.idCliente = 1; // validar que exista el indice del objeto que se esta insertando
        //c.nombreCliente = "bad bani";

        // obtener la implementacion de la interfaz DAO que Room genera
        FacturasDAO dao = db.DAOfactutas();

        // ejecutar la consulta SQL
        //dao.insertarCliente(c);

        dao.insertarCliente(c); // se usa para solo cuando se quieran cambiar
        //
        //int request = dao.deleteCliente(1);


        //Log.d("QUERY", String.valueOf(request));                        // algunos cambios

        //msg.setText(c.nombreCliente);


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

    public void finalizarCompra(View view) {
        ArrayList<Producto> productos = new ArrayList<>();

        for (Producto item : data){
            if(item.comprado){
                item.total = item.cantidadComprada * item.precio;
                total += item.total;
                productos.add(item);
                Log.d("APP_VENTAS", "Producto: " + item.nombre + " | Cantidad: " + item.cantidadComprada + " | Total: $" + item.total);
            }
        }

        compraAdapter = new CompraAdapter(this, productos);
        rvComprados.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvComprados.setAdapter(compraAdapter);

        txtTotal.setText("Total compra: $" + String.valueOf(total));
    }
}