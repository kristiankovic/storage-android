package com.pdm.almacenamiento.models;

public class Producto {
    public int imagen;
    public String nombre;
    public float precio;
    public int existencias;
    public int cantidadComprada = 0;
    public boolean comprado = false;
    public float total;

    public Producto() {
    }

    public Producto(int imagen, String nombre, float precio, int existencias) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", existencias=" + existencias +
                '}';
    }
}
