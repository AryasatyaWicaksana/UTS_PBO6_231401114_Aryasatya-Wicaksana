package Soal1;

import java.util.*;

class Karyawan {
    private String id;
    private String nama;
    private String posisi;
    private double gaji;

    // Constructor
    public Karyawan(String id, String nama, String posisi, double gaji) {
        this.id = id;
        this.nama = nama;
        this.posisi = posisi;
        setGaji(gaji); // validasi gaji
    }

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPosisi() {
        return posisi;
    }

    public double getGaji() {
        return gaji;
    }

    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }

    public void setGaji(double gaji) {
        if (gaji < 0) {
            System.out.println("Gaji tidak boleh negatif!");
        } else {
            this.gaji = gaji;
        }
    }

    public void displayInfo() {
        System.out.println("ID: " + id + ", Nama: " + nama + ", Posisi: " + posisi + ", Gaji: " + gaji);
    }
}

class Perusahaan {
    private List<Karyawan> daftarKaryawan;

    public Perusahaan() {
        daftarKaryawan = new ArrayList<>();
    }

    public boolean cekDuplikatID(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) return true;
        }
        return false;
    }

    public void tambahKaryawan(Karyawan karyawan) {
        if (cekDuplikatID(karyawan.getId())) {
            System.out.println("ID karyawan sudah ada. Tidak bisa menambahkan.");
        } else {
            daftarKaryawan.add(karyawan);
            System.out.println("Karyawan berhasil ditambahkan.");
        }
    }

    public void hapusKaryawan(String id) {
        Iterator<Karyawan> iterator = daftarKaryawan.iterator();
        while (iterator.hasNext()) {
            Karyawan k = iterator.next();
            if (k.getId().equalsIgnoreCase(id)) {
                iterator.remove();
                System.out.println("Karyawan dengan ID " + id + " telah dihapus.");
                return;
            }
        }
        System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
    }

    public void ubahPosisi(String id, String posisiBaru) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                k.setPosisi(posisiBaru);
                System.out.println("Posisi karyawan berhasil diubah.");
                return;
            }
        }
        System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
    }

    public void ubahGaji(String id, double gajiBaru) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                k.setGaji(gajiBaru);
                System.out.println("Gaji karyawan berhasil diubah.");
                return;
            }
        }
        System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
    }

    public void tampilkanSemuaKaryawan() {
        if (daftarKaryawan.isEmpty()) {
            System.out.println("Belum ada karyawan.");
            return;
        }
        for (Karyawan k : daftarKaryawan) {
            k.displayInfo();
        }
    }

    public void cariKaryawan(String id) {
        for (Karyawan k : daftarKaryawan) {
            if (k.getId().equalsIgnoreCase(id)) {
                k.displayInfo();
                return;
            }
        }
        System.out.println("Karyawan dengan ID " + id + " tidak ditemukan.");
    }

    public void totalGaji() {
        double total = 0;
        for (Karyawan k : daftarKaryawan) {
            total += k.getGaji();
        }
        System.out.println("Total Gaji Semua Karyawan: " + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Perusahaan perusahaan = new Perusahaan();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tambah Karyawan");
            System.out.println("2. Hapus Karyawan");
            System.out.println("3. Ubah Posisi");
            System.out.println("4. Ubah Gaji");
            System.out.println("5. Tampilkan Semua Karyawan");
            System.out.println("6. Cari Karyawan");
            System.out.println("7. Total Gaji");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = sc.nextInt();
            sc.nextLine(); // Buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nama: ");
                    String nama = sc.nextLine();
                    System.out.print("Posisi: ");
                    String posisi = sc.nextLine();
                    System.out.print("Gaji: ");
                    double gaji = sc.nextDouble();
                    sc.nextLine();
                    perusahaan.tambahKaryawan(new Karyawan(id, nama, posisi, gaji));
                    break;
                case 2:
                    System.out.print("ID Karyawan yang ingin dihapus: ");
                    perusahaan.hapusKaryawan(sc.nextLine());
                    break;
                case 3:
                    System.out.print("ID Karyawan: ");
                    String idUbahPosisi = sc.nextLine();
                    System.out.print("Posisi Baru: ");
                    String posisiBaru = sc.nextLine();
                    perusahaan.ubahPosisi(idUbahPosisi, posisiBaru);
                    break;
                case 4:
                    System.out.print("ID Karyawan: ");
                    String idUbahGaji = sc.nextLine();
                    System.out.print("Gaji Baru: ");
                    double gajiBaru = sc.nextDouble();
                    sc.nextLine();
                    perusahaan.ubahGaji(idUbahGaji, gajiBaru);
                    break;
                case 5:
                    perusahaan.tampilkanSemuaKaryawan();
                    break;
                case 6:
                    System.out.print("Masukkan ID yang dicari: ");
                    perusahaan.cariKaryawan(sc.nextLine());
                    break;
                case 7:
                    perusahaan.totalGaji();
                    break;
                case 0:
                    System.out.println("Terima kasih. Program selesai.");
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
