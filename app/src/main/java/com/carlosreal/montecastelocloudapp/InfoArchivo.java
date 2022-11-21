package com.carlosreal.montecastelocloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.net.ftp.FTPClient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InfoArchivo extends AppCompatActivity {

    int Index, IdUsuario;
    ArchivoPrivado ArchivoSel;
    Button Delete;
    TextView Id, Nombre, Ruta;
    Connection Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_archivo);

        Index = this.getIntent().getExtras().getInt("Archivo");
        IdUsuario = this.getIntent().getExtras().getInt("Id");
        ArchivoSel = ArchivosPrivados.ListaPrivate.get(Index);

        Id = findViewById(R.id.InfoId);
        Nombre = findViewById(R.id.InfoNombre);
        Ruta = findViewById(R.id.InfoRuta);
        Delete = findViewById(R.id.buttonDelete);

        Id.setText(String.valueOf(ArchivoSel.getId()));
        Nombre.setText(ArchivoSel.getNombre());
        Ruta.setText(ArchivoSel.getRuta());

        Delete.setOnClickListener(view -> {
            try{
                ConnectionHelper ConnHelp = new ConnectionHelper();
                Conn = ConnHelp.ConnectionClass();
                PreparedStatement PS = Conn.prepareStatement("DELETE FROM ArchivosPrivados WHERE NombreArchivo = ? AND IDUsuario = ?");
                PS.setString(1, Nombre.getText().toString());
                PS.setInt(2, IdUsuario);
                PS.executeUpdate();
                Intent Intent1 = new Intent(this, ArchivosPrivados.class);
                Intent1.putExtra("Id", IdUsuario);
                startActivity(Intent1);
            }
            catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}