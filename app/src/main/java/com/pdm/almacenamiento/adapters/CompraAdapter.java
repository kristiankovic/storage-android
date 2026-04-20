package com.pdm.almacenamiento.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.almacenamiento.R;
import com.pdm.almacenamiento.models.Producto;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.ViewHolder> {
    private Context contexto;
    private ArrayList<Producto> comprados;

    public CompraAdapter(Context contexto, ArrayList<Producto> comprados) {
        this.contexto = contexto;
        this.comprados = comprados;
    }

    @NonNull
    @Override
    public CompraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.compra_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompraAdapter.ViewHolder holder, int position) {
        Producto p = comprados.get(position);

        holder.txtCompra.setText(p.nombre + " | Cantidad: " + String.valueOf(p.cantidadComprada) + " | Total: $" + String.valueOf(p.total));
    }

    @Override
    public int getItemCount() {
        return comprados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCompra;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCompra = itemView.findViewById(R.id.txtCompra);
        }
    }
}
