/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Ahnaffaiz
 */
public class Transaksi {

    public int id_transaksi, id_keranjang, total, pembayaran, kembalian;
    public String tanggal;
    
    //contructor
    public Transaksi(int id_keranjang, String tanggal, int total) {
        this.tanggal = tanggal;
        this.id_keranjang = id_keranjang;
        this.total = total;
    }
    
    //contructor
    public Transaksi() {

    }

    public static void insert(Integer id_keranjang, String tanggal, Integer total, Integer pembayaran, Integer kembalian) {
        
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        // query sql untuk insert data transaksi
        String sql = "INSERT INTO db_transaksi (id_keranjang, tanggal, total, pembayaran, kembalian) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);

            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, id_keranjang.toString());
            statement.setString(2, tanggal);
            statement.setString(3, total.toString());
            statement.setString(4, pembayaran.toString());
            statement.setString(5, kembalian.toString());

            // jalankan query melihat baris yang terpengaruhi
            int rowsInserted = statement.executeUpdate();
            // jika ada baris yang terpengaruhi
            if (rowsInserted > 0) {
                System.out.println("Insert data Transaksi sukses");
            }
            m.close();

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data transaksi gagal");
            m.close();
        }


    }
}
