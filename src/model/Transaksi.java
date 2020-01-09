/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void select(Integer id_keranjang) {

        // query sql untuk select data transaksi
        String sql = "SELECT t.id_transaksi, t.tanggal,GROUP_CONCAT(k.qty_barang,' ',b.nama_barang SEPARATOR '/n ') barang, t.total from db_transaksi t, db_keranjang k, db_barang b where t.id_keranjang=k.id_keranjang and k.id_barang=b.id_barang and t.id_keranjang = " + id_keranjang + " GROUP BY id_transaksi";

        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %15s %20s %20s";
            System.out.println(String.format(header, "ID", "TANGGAL", "BARANG", "TOTAL"));
            System.out.println("------------------------------------------------------------------------------");

            // looping untuk baca data per record
            while (result.next()) {
                // baca data transaksi per record
                String idTransaksi = result.getString("id_transaksi");
                String namaPembeli = result.getString("tanggal");
                String namaBarang = result.getString("barang");
                String totalHarga = result.getString("total");

                // tampilkan data transaksi per record
                String output = "%2s %10s %35s %20s";
                System.out.println(String.format(output, idTransaksi, namaPembeli, namaBarang, totalHarga));
            }

            System.out.println("==============================================================================");
            m.close();
        } catch (SQLException ex) {
            System.out.println("Tampil data Barang gagal");
            m.close();
        }

    }

    public static void select() {

        // query sql untuk select all data transaksi
        String sql = "SELECT t.id_transaksi, t.tanggal,GROUP_CONCAT(k.qty_barang,' ',b.nama_barang SEPARATOR ', ') barang, t.total from db_transaksi t, db_keranjang k, db_barang b where t.id_keranjang=k.id_keranjang and k.id_barang=b.id_barang GROUP BY id_transaksi";

        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %15s %20s %20s";
            System.out.println(String.format(header, "ID", "TANGGAL", "BARANG", "TOTAL"));
            System.out.println("------------------------------------------------------------------------------");

            // looping untuk baca data per record
            while (result.next()) {
                // baca data buku per record
                String idTransaksi = result.getString("id_transaksi");
                String namaPembeli = result.getString("nama_pembeli");
                String namaBarang = result.getString("barang");
                String totalHarga = result.getString("total");

                // tampilkan data buku per record
                String output = "%2s %10s %35s %20s";
                System.out.println(String.format(output, idTransaksi, namaPembeli, namaBarang, totalHarga));
            }

            System.out.println("==============================================================================");
            m.close();
        } catch (SQLException ex) {
            System.out.println("Tampil data Barang gagal");
            m.close();
        }

    }
}
