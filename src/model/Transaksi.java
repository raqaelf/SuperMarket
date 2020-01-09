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
 * @author raqaelf
 */
public class Transaksi {
    public int id_transaksi, id_keranjang,total;
    public String nama_pembeli;
    
    public Transaksi(int id_keranjang, String nama_pembeli, int total) {
        this.nama_pembeli = nama_pembeli;
        this.id_keranjang = id_keranjang;
        this.total = total;
    }
    public Transaksi(){
        
    }
    public static void insert(Integer id_keranjang, String nama_pembeli, Integer total) {
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        // query sql untuk insert data transaksi
        String sql = "INSERT INTO db_transaksi (id_keranjang, nama_pembeli, total) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);

            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, id_keranjang.toString());
            statement.setString(2, nama_pembeli);
            statement.setString(3, total.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
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
// delete data barang berdasarkan id_barang

    public static void delete(Integer id_transaksi) {

        // query sql untuk hapus data buku berdasarkan id_barang
        String sql = "DELETE FROM db_transaksi WHERE id_transaksi=?";
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);

            // mapping nilai parameter dari query sql nya
            statement.setString(1, id_transaksi.toString());

            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Transaksi berhasil dihapus");
            }
            m.close();
        } catch (SQLException ex) {
            System.out.println("Hapus data Transaksi gagal");
            m.close();
        }
    }
    // update data barang berdasarkan id_barang
    public static void update(Integer id_transaksi, Integer id_keranjang, String nama_pembeli, Integer total){
        
        // query sql untuk update data transaksi
        String sql = "UPDATE db_transaksi SET id_keranjang=?, nama_pembeli=?, total=? WHERE id_transaksi=?";
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, id_keranjang.toString());
            statement.setString(2, nama_pembeli);
            statement.setString(3, total.toString());
            statement.setString(4, id_transaksi.toString());
      

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data transaksi sukses");
            }
            m.close();
        } catch (SQLException ex) {
             System.out.println("Update data transaksi gagal");
             m.close();
        }
    }
    // tampilkan semua data buku
    public static void select(Integer id_keranjang){
        
        // query sql untuk select all data buku
        String sql = "SELECT t.id_transaksi, t.nama_pembeli,GROUP_CONCAT(k.qty_barang,' ',b.nama_barang SEPARATOR ', ') barang, t.total from db_transaksi t, db_keranjang k, db_barang b where t.id_keranjang=k.id_keranjang and k.id_barang=b.id_barang and t.id_keranjang = "+id_keranjang+" GROUP BY id_transaksi";
        
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
            System.out.println(String.format(header, "ID", "NAMA PEMBELI", "BARANG", "TOTAL"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
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
        } catch (SQLException ex){
            System.out.println("Tampil data Barang gagal");
            m.close();
        }
        
    }
    
    public static void select(){
        
        // query sql untuk select all data buku
        String sql = "SELECT t.id_transaksi, t.nama_pembeli,GROUP_CONCAT(k.qty_barang,' ',b.nama_barang SEPARATOR ', ') barang, t.total from db_transaksi t, db_keranjang k, db_barang b where t.id_keranjang=k.id_keranjang and k.id_barang=b.id_barang GROUP BY id_transaksi";
        
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
            System.out.println(String.format(header, "ID", "NAMA PEMBELI", "BARANG", "TOTAL"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
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
        } catch (SQLException ex){
            System.out.println("Tampil data Barang gagal");
            m.close();
        }
        
    }
    public static void main(String[] args) {
        Transaksi.select();
    }
}
