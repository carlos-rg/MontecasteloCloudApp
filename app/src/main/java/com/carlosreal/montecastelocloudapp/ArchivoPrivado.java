package com.carlosreal.montecastelocloudapp;

import android.graphics.Bitmap;

public class ArchivoPrivado {
    public Bitmap Imagen = null;
    public int Id;
    public String Nombre;
    public String Ruta;

    public ArchivoPrivado(Bitmap imagen, int id, String nombre, String ruta) {
        Imagen = imagen;
        Id = id;
        Nombre = nombre;
        Ruta = ruta;
    }

    public Bitmap getImagen() {
        return Imagen;
    }

    public void setImagen(Bitmap imagen) {
        Imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String ruta) {
        Ruta = ruta;
    }
}
