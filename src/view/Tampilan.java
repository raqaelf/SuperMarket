/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;

/**
 *
 * @author Asus
 */
public class Tampilan {
    
    static Scanner sc = new Scanner(System.in);
    static Controller ctr = new Controller();

    public void menu_Utama() {
        System.out.println("================================");
        System.out.println("Selamat Datang di Supermarket!!!");
        System.out.println("================================");
        System.out.println("         1. Menu Barang");
        System.out.println("         2. Pembelian");
        System.out.println("================================");
    }

    public static void menu_barang() {
        System.out.println("================================");
        System.out.println("          Menu Barang           ");
        System.out.println("================================");
        System.out.println("         1. Lihat Barang");
        System.out.println("         2. Tambah Barang");
        System.out.println("         3. Edit Barang");
        System.out.println("         4. Hapus Barang");
        System.out.println("         5. Kembali");

        System.out.println("================================");
        int pilihan;
        pilihan = sc.nextInt();
        Barang brg = new Barang();
        switch (pilihan) {
            case 1:
                brg.select();
                Tampilan.menu_barang();
                break;
            case 2:
                ctr.inputBarang();
                Tampilan.menu_barang();
                break;
            case 3:
                brg.select();
                ctr.editBarang();
                Tampilan.menu_barang();
                break;
            case 4:
                brg.select();
                ctr.deleteBarang();
                Tampilan.menu_barang();
                break;
            case 5:
                new MainClass().jalankan();
                break;
            default:
                break;
        }
    }

    public void pembelian() {
        System.out.println("================================");
        System.out.println("           Pembelian            ");
        System.out.println("================================");
        ctr.beliBarang();
        System.out.println("================================");
    }
}
