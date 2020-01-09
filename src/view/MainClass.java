/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;

/**
 *
 * @author raqaelf
 */
public class MainClass {
     public void jalankan(){
        Tampilan view = new Tampilan();
        view.menu_Utama();
        Scanner cutama = new Scanner(System.in);
        int ccutama = cutama.nextInt();
         switch (ccutama) {
             case 1:
                 Tampilan.menu_barang();
                 break;
             case 2:
                 view.pembelian();
                 break;
             case 3:
                 System.exit(0);
             default:
                 break;
         }
    }
    public static void main(String[] args) {
        new MainClass().jalankan();
    }
}
