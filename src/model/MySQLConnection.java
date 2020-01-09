/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Daniel
 */
public class MySQLConnection implements config.Database {
    
    public Connection conn;

    public MySQLConnection (){
        try {
            String dbURL = "jdbc:mysql://"+ this.dbHost +":3306/" + this.dbName;
            this.conn = DriverManager.getConnection(dbURL, this.dbUser, this.dbPass);
            if (this.conn != null) {
//                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void close(){
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.out.println("Penutupan koneksi gagal");
        }
    }


}