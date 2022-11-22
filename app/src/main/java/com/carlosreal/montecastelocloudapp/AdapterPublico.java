package com.carlosreal.montecastelocloudapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterPublico extends BaseAdapter {
    private android.content.Context Context;
    private ArrayList<ArchivoPublico> ListaArchivos;
    private LayoutInflater Inflater;

    public AdapterPublico(Activity Context, ArrayList<ArchivoPublico> ListaArchivos) {
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
        Adapter.ViewHolder Holder = null;
        if(view == null){
            view = Inflater.inflate(R.layout.structure2, null);
            Holder = new Adapter.ViewHolder();
            Holder.Imagen = view.findViewById(R.id.ImagenPublic);
            Holder.Id = view.findViewById(R.id.IdPublic);
            Holder.Nombre = view.findViewById(R.id.NombrePublic);
            Holder.Ruta = view.findViewById(R.id.RutaPublic);

            view.setTag(Holder);
        }
        else{
            Holder = (Adapter.ViewHolder) view.getTag();
        }

        ArchivoPublico Archivo = ListaArchivos.get(position);

        Holder.Imagen.setImageBitmap(Archivo.getImagen());
        Holder.Id.setText(String.valueOf(Archivo.getId()));
        Holder.Nombre.setText(Archivo.getNombre());
        Holder.Ruta.setText(Archivo.getRuta());

        return view;
    }
}
