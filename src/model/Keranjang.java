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
 * @author ASUS
 */
public class Keranjang {
    public int id_keranjang, id_barang, qty_barang, total;



    //Constructor

    public Keranjang(int id_keranjang, int id_barang, int qty_barang, int total) {

        this.id_keranjang = id_keranjang;

        this.id_barang = id_barang;

        this.qty_barang = qty_barang;

        this.total = total;

    }

    public static void insert(Integer id_keranjang, Integer id_barang, Integer qty_barang, Integer total) {

        // lakukan koneksi ke mysql

        MySQLConnection m = new MySQLConnection();

        Connection koneksi = m.conn;



        // query sql untuk insert data keranjang

        String sql = "INSERT INTO db_keranjang (id_keranjang, id_barang, qty_barang, total) VALUES (?, ?, ?,?)";



        try {

            PreparedStatement statement = koneksi.prepareStatement(sql);



            // mapping nilai parameter dari query sql nya (sesuai urutan)

            statement.setString(1, id_keranjang.toString());

            statement.setString(2, id_barang.toString());

            statement.setString(3, qty_barang.toString());

            statement.setString(4, total.toString());



            // jalankan query (baca jumlah row affectednya)

            int rowsInserted = statement.executeUpdate();

            // jika ada row affected nya, maka status sukses

            if (rowsInserted > 0) {

                System.out.println("Berhasil Masukan Keranjang sukses");

            }

            m.close();



        } catch (SQLException ex) {

            // jika query gagal

            System.out.println("Gagal Masukan Keranjang sukses");

            m.close();

        }

    }



    public static void delete(Integer id_keranjang) {



        // query sql untuk hapus data buku berdasarkan id_barang

        String sql = "DELETE FROM db_keranjang WHERE id_keranjang=?";

        // lakukan koneksi ke mysql

        MySQLConnection m = new MySQLConnection();

        Connection koneksi = m.conn;



        try {

            PreparedStatement statement;

            statement = koneksi.prepareStatement(sql);



            // mapping nilai parameter dari query sql nya

            statement.setString(1, id_keranjang.toString());



            // jalankan query, dan lihat jumlah row affected nya

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {

                System.out.println("berhasil dihapus");

            }

            m.close();

        } catch (SQLException ex) {

            System.out.println("Hapus gagal");

            m.close();

        }

    }



    // update data barang berdasarkan id_barang

    public static void update(Integer id_barang, String nama_barang, Integer harga_barang, Integer stock_barang) {



        // query sql untuk update data keranjang berdasarkan id_barang

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



    // tampilkan data barang berdasarkan id_keranjang

    public static void select(Integer id_keranjang) {



        // query sql untuk select data barang

        String sql = "SELECT b.nama_barang, b.harga_barang, k.qty_barang, k.total from db_keranjang k, db_barang b where k.id_barang = b.id_barang and k.id_keranjang =" + id_keranjang;



        // lakukan koneksi ke mysql

        MySQLConnection m = new MySQLConnection();

        Connection koneksi = m.conn;

        Integer totalTrx = 0;

        try {

            Statement statement = koneksi.createStatement();

            // jalankan query

            ResultSet result = statement.executeQuery(sql);



            // membuat header table untuk output

            System.out.println("==============================================================================");

            String header = "%3s %20s %20s %20s";

            System.out.println(String.format(header, "NAMA BARANG", "HARGA BARANG", "QUANTITY", "TOTAL"));

            System.out.println("------------------------------------------------------------------------------");



            // looping untuk baca data per record

            while (result.next()) {

                // baca data barang per record

                String nmBarang = result.getString("nama_barang");

                String hrgBarang = result.getString("harga_barang");

                String qtyBarang = result.getString("qty_barang");

                String total = result.getString("total");

                totalTrx += Integer.valueOf(total);

                // tampilkan data barang per record

                String output = "%3s %25s %20s %20s";

                System.out.println(String.format(output, nmBarang, hrgBarang, qtyBarang, total));

            }



            System.out.println("==============================================================================");

            System.out.println(String.format("%74s", totalTrx));



            m.close();

        } catch (SQLException ex) {

            System.out.println("Tampil data Barang gagal");

            m.close();

        }



    }
}

