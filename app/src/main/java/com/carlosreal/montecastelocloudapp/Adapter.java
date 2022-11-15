package com.carlosreal.montecastelocloudapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private android.content.Context Context;
    private ArrayList<ArchivoPrivado> ListaArchivos;
    private LayoutInflater Inflater;

    public Adapter(Activity Context, ArrayList<ArchivoPrivado> ListaArchivos) {
        this.Context = Context;
        this.ListaArchivos = ListaArchivos;
        Inflater = LayoutInflater.from(Context);
    }

    static class ViewHolder{
        ImageView Imagen;
        TextView Id;
        TextView Nombre;
        TextView Ruta;
    }

    //Getters del adaptador.
    @Override
    public int getCount() {
        return ListaArchivos.size();
    }

    @Override
    public Object getItem(int posicion) {
        return ListaArchivos.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    //Adaptación de estructura.
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder Holder = null;
        if(view == null){
            view = Inflater.inflate(R.layout.structure1, null);
            Holder = new ViewHolder();
            Holder.Imagen = view.findViewById(R.id.Imagen);
            Holder.Id = view.findViewById(R.id.Id);
            Holder.Nombre = view.findViewById(R.id.Nombre);
            Holder.Ruta = view.findViewById(R.id.Ruta);

            view.setTag(Holder);
        }
        else{
            Holder = (ViewHolder) view.getTag();
        }

        ArchivoPrivado Archivo = ListaArchivos.get(position);

        Holder.Imagen.setImageBitmap(Archivo.getImagen());
        Holder.Id.setText(String.valueOf(Archivo.getId()));
        Holder.Nombre.setText(Archivo.getNombre());
        Holder.Ruta.setText(Archivo.getRuta());

        return view;
    }
}
