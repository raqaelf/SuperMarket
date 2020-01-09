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
 * @author Maryam Fadhillah
 */
public class Barang {
    
    
     // insert data buku
    public void insert(String nama_barang, Integer harga_barang, Integer stock_barang){
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO db_barang (nama_barang, harga_barang, stock_barang) VALUES (?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, nama_barang);
            statement.setString(2, harga_barang.toString());
            statement.setString(3, stock_barang.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert barang sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert barang gagal");
        }
    }
   
   
    // delete data buku berdasarkan idbook
    public void delete(Integer id_barang){
        
        // query sql untuk hapus data buku berdasarkan idbook
        String sql = "DELETE FROM db_barang WHERE id_barang=?";
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, id_barang.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Barang berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus Barang gagal");
        }
        
    }
    
    // update data buku berdasarkan idbook
    public void update(Integer id_barang, String nama_barang, Integer harga_barang, Integer stock_barang){
        
        // query sql untuk update data buku berdasarkan idbook
        String sql = "UPDATE db_barang SET nama_barang=?, harga_barang=?, stock_barang=?, WHERE id_barang=?";
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, nama_barang);
            statement.setString(2, harga_barang.toString());
            statement.setString(3, stock_barang.toString());
            statement.setString(4, id_barang.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update barang sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update barang gagal");
        }
    }
    
    // tampilkan semua data buku
    public void select(){
        
        // query sql untuk select all data buku
        String sql = "SELECT * FROM db_barang";
        
        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %20s";
            System.out.println(String.format(header, "ID", "NAMA BARANG", "HARGA BARANG", "STOCK"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idBrg = result.getString("id_barang");
                String namaBrg = result.getString("nama_barang");
                String hargaBrg = result.getString("harga_barang");
                String stockBrg = result.getString("stock_barang");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %20s";
                System.out.println(String.format(output, idBrg, namaBrg, hargaBrg, stockBrg));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil barang gagal");
        }
        
    
    }
    
//    public void select(Integer id_barang, String nama_barang, Integer harga_barang, Integer stock_barang){  
//        
//        // query sql untuk select all data buku
//        String sql = "SELECT * FROM db_barang WHERE id_barang=?";
//        
//        // lakukan koneksi ke mysql
//        MySQLConnection m = new MySQLConnection();
//        Connection koneksi = m.conn;
//        
//        try {
//            Statement statement = koneksi.createStatement();
//            // jalankan query
//            ResultSet result = statement.executeQuery(sql);
//
//            // membuat header table untuk output
//            System.out.println("==============================================================================");
//            String header = "%3s %20s %20s %20s";
//            System.out.println(String.format(header, "ID", "NAMA BARANG", "HARGA BARANG", "STOCK"));
//            System.out.println("------------------------------------------------------------------------------");
//            
//            // looping untuk baca data per record
//            while (result.next()){
//                // baca data buku per record
//                String id_barang = result.getString("id_barang");
//                String nama_barang = result.getString("nama_barang");
//                String harga_barang = result.getString("harga_barang");
//                String stock_barang = result.getString("stock_barang");
//                // tampilkan data buku per record
//                String output = "%3s %20s %20s %20s %4s";
//                System.out.println(String.format(output, id_barang, nama_barang, harga_barang, stock_barang));
//            }
//            
//            System.out.println("==============================================================================");
//            
//        } catch (SQLException ex){
//            System.out.println("Tampil barang gagal");
//        }}
    
    public static void main(String[] args) {
        
        Barang brg = new Barang();
//        brg.insert("makanan", 2000, 20);
//        brg.select();
          brg.update(2, "gelas", 20000, 52);
    }
    
}