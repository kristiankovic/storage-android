package com.pdm.almacenamiento.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.almacenamiento.R;
import com.pdm.almacenamiento.models.Producto;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Producto> data;

    public CustomAdapter(Context context, ArrayList<Producto> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
        Producto p = data.get(position);
        holder.imagen.setImageResource(p.imagen);
        holder.txtNombre.setText(p.nombre);
        holder.txtPrecio.setText("Precio: $" + String.valueOf(p.precio));
        holder.txtExistencias.setText("Existencias: " + String.valueOf(p.existencias));

        holder.cbComprado.setOnCheckedChangeListener(null);

        holder.cbComprado.setOnCheckedChangeListener(((buttonView, isChecked) -> {

            if(!holder.txtCantidad.getText().toString().isEmpty()){

                int cantidad = Integer.parseInt(holder.txtCantidad.getText().toString().trim());

                if(cantidad > p.existencias || cantidad < 1){
                    holder.txtCantidad.setError("No hay existencias.");
                    holder.txtCantidad.setText("");
                    holder.cbComprado.setChecked(false);
                }
                else{
                    p.comprado = true;
                    holder.txtCantidad.setText("");
                    holder.cbComprado.setChecked(false);
                    p.cantidadComprada = cantidad;

                    p.existencias -= cantidad;
                    holder.txtExistencias.setText("Existencias: " + String.valueOf(p.existencias));
                }

                Toast.makeText(context, "Producto: " + p.nombre + " agregado al carrito.", Toast.LENGTH_SHORT).show();
            }
            else{
                holder.txtCantidad.setError("Ingrese un dato.");
                holder.cbComprado.setChecked(false);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView txtNombre, txtPrecio, txtExistencias;
        EditText txtCantidad;
        CheckBox cbComprado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagen = itemView.findViewById(R.id.imageView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtExistencias = itemView.findViewById(R.id.txtExistencia);
            txtCantidad = itemView.findViewById(R.id.txtCompra);
            cbComprado = itemView.findViewById(R.id.checkBox);
        }
    }
}
