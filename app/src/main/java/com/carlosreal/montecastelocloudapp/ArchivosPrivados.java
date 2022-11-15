package com.carlosreal.montecastelocloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArchivosPrivados extends AppCompatActivity {


    private ListView ListaPriv;
    public static ArrayList<ArchivoPrivado> ListaPrivate = new ArrayList<>();
    private Button AddArchivoPriv;
    Connection Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos_privados);
        ListaPriv = findViewById(R.id.ListaPrivado);
        int Usuario = this.getIntent().getExtras().getInt("Id");
        try{
            ConnectionHelper ConnHelp = new ConnectionHelper();
            Conn = ConnHelp.ConnectionClass();
            String Query = "SELECT * FROM ArchivosPrivados WHERE IDUsuario = '" + Usuario + "'";
            Statement St = Conn.createStatement();
            ResultSet RS = St.executeQuery(Query);
            if (RS.next()){
                int Id = RS.getInt(1);
                String Nombre = RS.getString(2);
                String Ruta = RS.getString(3);
                ListaPrivate.add(new ArchivoPrivado(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.folder_zip), Id ,Nombre, Ruta));
            }
            Adapter Adaptador = new Adapter(this, ListaPrivate);
            ListaPriv.setAdapter(Adaptador);
            ListaPriv.setClickable(true);
            ListaPriv.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent Intent = new Intent(this, InfoArchivo.class);
                Intent.putExtra("Archivo", i);
                startActivity(Intent);
            });
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}