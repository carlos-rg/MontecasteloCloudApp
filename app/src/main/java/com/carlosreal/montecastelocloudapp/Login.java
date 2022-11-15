package com.carlosreal.montecastelocloudapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends AppCompatActivity {
    EditText Username, Password;
    String Usuario, Pass;
    Connection Conn;
    Button LoginClick, RegisterClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.UsernameLogin);
        Password = findViewById(R.id.PasswordLogin);
        LoginClick = findViewById(R.id.ButtonLogin);
        RegisterClick = findViewById(R.id.ButtonRegister);

        LoginClick.setOnClickListener(view -> {
            Usuario = Username.getText().toString();
            Pass = Password.getText().toString();

            try{
                MessageDigest MD = MessageDigest.getInstance("md5");
                MD.reset();
                MD.update(Pass.getBytes());
                byte[] Digest = MD.digest();
                BigInteger BigInt = new BigInteger(1, Digest);
                String HashPassword = BigInt.toString();
                ConnectionHelper ConnHelp = new ConnectionHelper();
                Conn = ConnHelp.ConnectionClass();
                String Query = "SELECT * FROM Usuarios WHERE NombreUsuario = '" + Usuario + "' AND Pass = '" + Pass + "'";
                Statement St = Conn.createStatement();
                ResultSet RS = St.executeQuery(Query);
                if (RS.next()){
                    int Id = RS.getInt(1);
                    Intent Intent1 = new Intent(this, ArchivosPrivados.class);
                    Intent1.putExtra("Id", Id);
                    startActivity(Intent1);
                }
            }
            catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RegisterClick.setOnClickListener(view -> {
            Usuario = Username.getText().toString();
            Pass = Password.getText().toString();


            try{
                MessageDigest MD = MessageDigest.getInstance("MD5");
                MD.reset();
                MD.update(Pass.getBytes());
                byte[] Digest = MD.digest();
                BigInteger BigInt = new BigInteger(1, Digest);
                String HashPassword = BigInt.toString();
                ConnectionHelper ConnHelp = new ConnectionHelper();
                Conn = ConnHelp.ConnectionClass();
                String Query = "SELECT * FROM Usuarios WHERE NombreUsuario = '" + Usuario + "' AND Pass = '" + Pass + "'";
                Statement St = Conn.createStatement();
                ResultSet RS = St.executeQuery(Query);
                if (RS == null){
                    Statement St1 = Conn.createStatement();
                    St1.executeUpdate("INSERT INTO Usuarios (NombreUsuario, Pass, IfAdmin) VALUES ('" + Username + "', '" + HashPassword + "', '" + 0 + ")");
                    Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}