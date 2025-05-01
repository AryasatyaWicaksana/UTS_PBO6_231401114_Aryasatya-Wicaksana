package Soal2;

import java.util.Scanner;

class Kendaraan {
    private String jenis;
    private int lamaParkir;

    public Kendaraan(String jenis) {
        this.jenis = jenis;
    }

    // Method overloading: input manual durasi
    public int hitungBiaya(int durasi) {
        this.lamaParkir = durasi;
        return hitungTotalBiaya();
    }

    // Method overloading: input jam masuk dan keluar
    public int hitungBiaya(int jamMasuk, int jamKeluar) {
        this.lamaParkir = jamKeluar - jamMasuk;
        return hitungTotalBiaya();
    }

    private int getTarifPerJam() {
        switch (jenis.toLowerCase()) {
            case "motor":
                return 2000;
            case "mobil":
                return 5000;
            case "truk":
                return 8000;
            default:
                return 0;
        }
    }

    private int hitungTotalBiaya() {
        int tarif = getTarifPerJam();
        int total = lamaParkir * tarif;

        if (lamaParkir > 5) {
            total -= total * 0.1; // diskon 10%
        }

        return total;
    }

    public void tampilRingkasan(int biaya) {
        System.out.println("\n--- Ringkasan Parkir ---");
        System.out.println("Jenis Kendaraan : " + jenis);
        System.out.println("Lama Parkir     : " + lamaParkir + " jam");
        System.out.println("Total Biaya     : Rp" + biaya);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totalKendaraan = 0;
        int totalBiayaSemua = 0;
        String lanjut = "y"; // inisialisasi agar tidak error

        do {
            System.out.print("Masukkan jenis kendaraan (motor/mobil/truk): ");
            String jenis = sc.next();

            Kendaraan k = new Kendaraan(jenis);

            System.out.println("Pilih cara input lama parkir:");
            System.out.println("1. Input langsung jumlah jam");
            System.out.println("2. Input jam masuk dan jam keluar");
            System.out.print("Pilihan (1/2): ");
            int pilihan = sc.nextInt();

            int biaya = 0;

            if (pilihan == 1) {
                System.out.print("Masukkan lama parkir (jam): ");
                int lama = sc.nextInt();
                biaya = k.hitungBiaya(lama);
            } else if (pilihan == 2) {
                System.out.print("Masukkan jam masuk: ");
                int masuk = sc.nextInt();
                System.out.print("Masukkan jam keluar: ");
                int keluar = sc.nextInt();
                biaya = k.hitungBiaya(masuk, keluar);
            } else {
                System.out.println("Pilihan tidak valid.");
                continue;
            }

            k.tampilRingkasan(biaya);

            totalKendaraan++;
            totalBiayaSemua += biaya;

            System.out.print("\nTambah kendaraan lagi? (y/n): ");
            lanjut = sc.next();
        } while (lanjut.equalsIgnoreCase("y"));

        System.out.println("\n===== Ringkasan Akhir =====");
        System.out.println("Total Kendaraan : " + totalKendaraan);
        System.out.println("Total Biaya     : Rp" + totalBiayaSemua);
    }
}
