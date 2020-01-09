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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Maryam Fadhillah
 */
public class Barang extends AbsModel {
    
    public void insert(String nama_barang, Integer harga_barang, Integer stock_barang){
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;
        
        String sql = "INSERT INTO db_barang (nama_barang, harga_barang, stock_barang) VALUES (?, ?, ?)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            statement.setString(1, nama_barang);
            statement.setString(2, harga_barang.toString());
            statement.setString(3, stock_barang.toString());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insert barang sukses");
            }

        } catch (SQLException ex) {
            System.out.println("Insert barang gagal");
        }
    }
   
   
    public void delete(Integer id_barang){
        
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
    
    public void update(Integer id_barang, String nama_barang, Integer harga_barang, Integer stock_barang) {

        String sql = "UPDATE db_barang SET nama_barang=?, harga_barang=?, stock_barang=? WHERE id_barang=?";
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
                System.out.println("Update data barang sukses");
            }
            m.close();
        } catch (SQLException ex) {
            System.out.println("Update data barang gagal");
            m.close();
        }
    }
    
    public void select(){
        
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
    
    public static Map<String, String> select(Integer id_barang) {
        String sql = "SELECT * FROM db_barang WHERE id_barang=" + id_barang + " LIMIT 1";

        // lakukan koneksi ke mysql
        MySQLConnection m = new MySQLConnection();
        Connection koneksi = m.conn;

        try {
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // jalankan query
            if (result.next()) {
                String idBarang = result.getString("id_barang");
                String namaBarang = result.getString("nama_barang");
                String hargaBarang = result.getString("harga_barang");
                String stockBarang = result.getString("stock_barang");

                Map<String, String> data = new HashMap<>();
                data.put("idBarang", idBarang);
                data.put("namaBarang", namaBarang);
                data.put("hargaBarang", hargaBarang);
                data.put("stockBarang", stockBarang);
                m.close();
                return data;
            }
        } catch (SQLException ex) {
            m.close();
            return null;
        }
        return null;
    }
    
    
}