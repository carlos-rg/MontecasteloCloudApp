package com.carlosreal.montecastelocloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.commons.net.ftp.FTPClient;

public class InfoArchivo extends AppCompatActivity {

    int Index;
    ArchivoPrivado ArchivoSel;
    Button Download;
    TextView Id, Nombre, Ruta;
    Connection Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_archivo);

        Index = this.getIntent().getExtras().getInt("Archivo");
        ArchivoSel = ArchivosPrivados.ListaPrivate.get(Index);

        Id = findViewById(R.id.InfoId);
        Nombre = findViewById(R.id.InfoNombre);
        Ruta = findViewById(R.id.InfoRuta);
        Download = findViewById(R.id.buttonDownload);

        Id.setText(String.valueOf(ArchivoSel.getId()));
        Nombre.setText(ArchivoSel.getNombre());
        Ruta.setText(ArchivoSel.getRuta());

        String Nombre1 = Nombre.getText().toString();

        Download.setOnClickListener(view -> {
            try{
                ConnectionHelper ConnHelp = new ConnectionHelper();
                Conn = ConnHelp.ConnectionClass();
                String Query = "SELECT * FROM ArchivosPrivados WHERE NombreArchivo = '" + Nombre1 + "'";
                Statement St = Conn.createStatement();
                ResultSet RS = St.executeQuery(Query);
                int RsInt = RS.getInt(5);
                if (RsInt == 1){
                    try {
                        FTPClient cliente = new FTPClient();
                        FileOutputStream stream = null;

                        cliente.connect("192.168.0.14");
                        cliente.login("test", "test");

                        String archivo = Ruta.toString();
                        stream = new FileOutputStream(Nombre + ".zip");

                        cliente.retrieveFile(archivo, stream);
                        stream.close();
                        cliente.disconnect();
                    } catch (Exception ex) {
                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Sólo archivos .zip", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}