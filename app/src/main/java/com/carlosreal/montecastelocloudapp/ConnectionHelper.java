package com.carlosreal.montecastelocloudapp;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    @SuppressLint("NewApi")
    String Username, Pass, IP, Port, Database;

    public Connection ConnectionClass(){
        Database = "montecastelocloud";
        Username = "sa";
        Pass = "superadmin";
        Port = "1433";

        StrictMode.ThreadPolicy Policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(Policy);
        Connection Connect = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://192.168.0.14:1433/montecastelocloud";
            Connect = DriverManager.getConnection(ConnectionURL, Username, Pass);

        }
        catch (Exception ex){
            Log.e("Error: ", ex.getMessage());
        }

        return Connect;
    }
}
