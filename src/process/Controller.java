/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import model.Barang;
import model.Keranjang;
import model.Transaksi;
import view.*;

/**
 *
 * @author raqaelf
 */
public class Controller {

    Scanner sc = new Scanner(System.in);
    StringBuffer sb = new StringBuffer();
    LocalDateTime now = LocalDateTime.now();

    public void inputBarang() {
        System.out.println("Masukkan Nama Barang");
        String namaBarang = sc.next();
        System.out.println("Masukkan Harga Barang");
        Integer hargaBarang = sc.nextInt();
        System.out.println("Masukkan Stock Barang");
        Integer stockBarang = sc.nextInt();
        new Barang().insert(namaBarang, hargaBarang, stockBarang);
    }

    public void editBarang() {
        System.out.println("Masukkan ID Barang");
        Integer idBarang = sc.nextInt();
        System.out.println("Masukkan Nama Barang");
        String namaBarang = sc.next();
        System.out.println("Masukkan Harga Barang");
        Integer hargaBarang = sc.nextInt();
        System.out.println("Masukkan Stock Barang");
        Integer stockBarang = sc.nextInt();
//        System.out.println("id "+ idBarang +"nama " +namaBarang+"harga "+hargaBarang+"stock "+stockBarang);
        new Barang().update(idBarang, namaBarang, hargaBarang, stockBarang);
    }

    public void deleteBarang() {
        System.out.println("Masukkan ID Barang");
        Integer idBarang = sc.nextInt();
        new Barang().delete(idBarang);
    }

    public void beliBarang() {
        String beliLagi = "y";
        Integer totalTransaksi = 0;
        String idKeranjang = DateTimeFormatter.ofPattern("yyMdHHmms").format(now);
        while (beliLagi.toLowerCase().equals("y")) {
            System.out.println("Masukkan ID Barang");
            Integer idBarang = sc.nextInt();
            Map<String, String> barang = Barang.select(idBarang);
            String nmBarang = barang.get("namaBarang");
            Integer hrgBarang = Integer.valueOf(barang.get("hargaBarang"));
            System.out.println("Barang yang dibeli : " + nmBarang);
            System.out.println("Masukkan Quantity");
            Integer qtyBarang = sc.nextInt();
            Integer total = hrgBarang * qtyBarang;
            Keranjang.insert(Integer.valueOf(idKeranjang), idBarang, qtyBarang, total);
            totalTransaksi += total;
            System.out.println("Beli lagi ?");
            beliLagi = sc.next();
        }

        Keranjang.select(Integer.valueOf(idKeranjang));
        System.out.println("Uang yang di bayarkan : ");
        Integer pembayaran = sc.nextInt();
        Integer kembalian = pembayaran - totalTransaksi;
        System.out.println("Kembalian : " + kembalian);
        Transaksi.insert(Integer.valueOf(idKeranjang), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now), totalTransaksi, pembayaran, kembalian);
        
        if (!beliLagi.toLowerCase().equals("y")) {
            new MainClass().jalankan();
        }
    }

//    public static void main(String[] args) {
////        this.beliBarang();
//        Controller ctr = new Controller();
//        ctr.beliBarang();
//    }
}