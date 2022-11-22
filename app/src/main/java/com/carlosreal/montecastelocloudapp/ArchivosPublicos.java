package com.carlosreal.montecastelocloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArchivosPublicos extends AppCompatActivity {

    private ListView ListaPub;
    public static ArrayList<ArchivoPublico> ListaPublic = new ArrayList<>();
    Connection Conn;
    int Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archivos_publicos);
        ListaPub = findViewById(R.id.ListaPublico);
        Usuario = this.getIntent().getExtras().getInt("Id");
        try{
            ConnectionHelper ConnHelp = new ConnectionHelper();
            Conn = ConnHelp.ConnectionClass();
            String Query = "SELECT * FROM ArchivosPublicos";
            Statement St = Conn.createStatement();
            ResultSet RS = St.executeQuery(Query);
            ListaPublic.clear();
            if (RS.next()){
                int Id = RS.getInt(1);
                String Nombre = RS.getString(2);
                String Ruta = RS.getString(3);
                ListaPublic.add(new ArchivoPublico(BitmapFactory.decodeResource(this.getApplicationContext().getResources(), R.drawable.folder_zip), Id ,Nombre, Ruta));
            }
            AdapterPublico Adaptador = new AdapterPublico(this, ListaPublic);
            ListaPub.setAdapter(Adaptador);
            ListaPub.setClickable(true);
            ListaPub.setOnItemClickListener((adapterView, view, i, l) -> {
                Intent Intent = new Intent(this, InfoArchivo.class);
                Intent.putExtra("Archivo", i);
                Intent.putExtra("Id", Usuario);
                startActivity(Intent);
            });
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                Intent Intent = new Intent(this, ArchivosPrivados.class);
                Intent.putExtra("Id", Usuario);
                startActivity(Intent);
                return true;
            case R.id.MnuOpc2:
                Intent Intent1 = new Intent(this, ArchivosPublicos.class);
                Intent1.putExtra("Id", Usuario);
                startActivity(Intent1);
                return true;
            case R.id.MnuOpc3:
                Intent Intent2 = new Intent(this, Login.class);
                Intent2.putExtra("Id", Usuario);
                startActivity(Intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}