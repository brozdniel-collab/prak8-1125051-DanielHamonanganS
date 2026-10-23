package hy.material8database.view;

import java.util.List;
import java.util.Scanner;

import hy.material8database.App;
import hy.material8database.models.Matakuliah;
import hy.material8database.repository.MatkulRepo;

public class MatkulView {

    private Scanner scanner = new Scanner(System.in);

    private void addNewMK() {
        System.out.print("Masukkan Kode Mata kuliah: ");
        String kodeMK = scanner.next();
        scanner.nextLine();
        System.out.print("Masukkan Nama Mata Kuliah: ");
        String nama = scanner.nextLine();
        System.out.println("Masukkan Kode Jurusan: ");
        String kodeJurusan = scanner.next();
        scanner.nextLine();
        System.out.println("Masukkan Jumlah SKS: ");
        int sks = scanner.nextInt();
        Matakuliah Matakuliah = new Matakuliah(kodeJurusan, kodeMK, nama, sks);
        MatkulRepo.addMataKuliah(Matakuliah);;
    }

    private void printAllMK() {
        List<Matakuliah> allMatakuliah = MatkulRepo.getAllMataKuliah();
        System.out.println("\n📋 Daftar Mata Kuliah:");
        for (Matakuliah mk : allMatakuliah) {
            System.out.println(mk.kode_matakuliah + " - " + mk.nama + " - " + mk.sks + " - " + mk.kode_jurusan);
        }
    }

    public void menuMK() {
        boolean loop = true;
        while (loop) {
            System.out.println("=== Menu Mata Kuliah");
            System.out.println(" 1. Add Jurusan \n 2. Delete Jurusan \n 3. Edit Jurusan \n 4. Print Jurusan \n 5. Main Menu");
            System.out.print("Masukkan Menu: ");
            int pilihMenu = scanner.nextInt();
            String inputKode;
            switch (pilihMenu) {
                case 1:
                    System.out.println("\nTambahkan Mata kuliah: ");
                    addNewMK();
                    break;
                case 2:
                    System.out.println("Delete Mata Kuliah");
                    System.out.print("Masukkan Kode Mata Kuliah: ");
                    inputKode = scanner.next();

                    MatkulRepo.deleteMK(inputKode);
                    break;
                case 3:
                    System.out.println("Update Jurusan");
                    System.out.println("Masukkan Kode Jurusan: ");
                    inputKode = scanner.next();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();
                    System.out.print("Masukkan SKS Baru: ");
                    int newSKS = scanner.nextInt();
                    System.out.print("Masukkan Kode Jurusan Baru: ");
                    String newKodeJurusan = scanner.next();
                    Matakuliah mk = MatkulRepo.getMKByKode(inputKode);
                    mk = new Matakuliah(inputKode, newKodeJurusan, newName, newSKS);
                    MatkulRepo.updateMataKuliah(mk);
                    break;
                case 4:
                    printAllMK();
                    break;
                case 5:
                    new App();
                default:
                    loop = false;
                    throw new AssertionError();
            }
        }
    }
}