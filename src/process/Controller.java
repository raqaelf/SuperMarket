/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
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
        CommandLineTable st = new CommandLineTable();
        st.setShowVerticalLines(true);
        String beliLagi = "y";
        Integer totalTransaksi = 0;
        Integer noNota = 0;
        Integer idKeranjang = new Random().nextInt();
        while (beliLagi.toLowerCase().equals("y")) {
            noNota += 1;
            System.out.println("Masukkan ID Barang");
            Integer idBarang = sc.nextInt();
            Map<String, String> barang = Barang.select(idBarang);
            String nmBarang = barang.get("namaBarang");
            Integer hrgBarang = Integer.valueOf(barang.get("hargaBarang"));
            Integer stkBarang = Integer.valueOf(barang.get("stockBarang"));
            System.out.println("Barang yang dibeli : " + nmBarang);
            System.out.println("Masukkan Quantity");
            Integer qtyBarang = sc.nextInt();
            Integer total = hrgBarang * qtyBarang;
            Keranjang.insert(idKeranjang, idBarang, qtyBarang, total);
            totalTransaksi += total;
            //header nota
            st.setHeaders("No","Barang", "Qty", "Harga");
            //isi nota
            st.addRow(noNota.toString(), nmBarang, qtyBarang.toString(), total.toString());
            System.out.println("Beli lagi ?");
            beliLagi = sc.next();
            Integer sisa = stkBarang - qtyBarang;
            new Barang().update(idBarang, nmBarang, hrgBarang ,sisa );
        }

        Keranjang.select(Integer.valueOf(idKeranjang));
        System.out.print("Uang yang di bayarkan : ");
        Integer pembayaran = sc.nextInt();
        Integer kembalian = pembayaran - totalTransaksi;
        System.out.println("Kembalian : " + kembalian);
        Transaksi.insert(idKeranjang, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now), totalTransaksi, pembayaran, kembalian);
        System.out.println("                  Nota Pembayaran                    ");
        System.out.println("=====================================================");
        System.out.print("ID Transaksi: ");
        Transaksi.selectId(idKeranjang);
        System.out.print("Tanggal Transaksi: ");
        Transaksi.selectTanggal(idKeranjang);
        System.out.println(" ");
        st.print();
        System.out.println("=====================================================");
        if (!beliLagi.toLowerCase().equals("y")) {
            new MainClass().jalankan();
        }
    }
}