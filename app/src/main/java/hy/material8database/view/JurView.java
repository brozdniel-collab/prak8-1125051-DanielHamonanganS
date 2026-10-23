package hy.material8database.view;

import java.util.List;
import java.util.Scanner;

import hy.material8database.App;
import hy.material8database.models.Jurusan;
import hy.material8database.repository.JurusanRepository;
public class JurView {

    private Scanner scanner = new Scanner(System.in);

    private void addNewJurusan() {
        System.out.print("Masukkan Kode Jurusan: ");
        String kodeJurusan = scanner.next();
        scanner.nextLine();
        System.out.print("Masukkan Nama Jurusan: ");
        String nama = scanner.nextLine();
        Jurusan jurusan = new Jurusan(kodeJurusan, nama);
        JurusanRepository.addJurusan(jurusan);
    }

    private void printAllJurusan() {
        List<Jurusan> allJurusan = JurusanRepository.getAllJurusan();
        System.out.println("\n📋 Daftar Jurusan:");
        for (Jurusan m : allJurusan) {
            System.out.println(m.kode_jurusan + " - " + m.nama);
        }
    }

    public void menuJurusan() {
        boolean loop = true;
        while (loop) {
            System.out.println("=== Menu Jurusan");
            System.out.println(" 1. Add Jurusan \n 2. Delete Jurusan \n 3. Edit Jurusan \n 4. Print Jurusan \n 5. Main Menu");
            System.out.print("Masukkan Menu: ");
            int pilihMenu = scanner.nextInt();
            String inputKode;
            switch (pilihMenu) {
                case 1:
                    System.out.println("\nTambahkan Jurusan: ");
                    System.out.println();
                    addNewJurusan();
                    break;
                case 2:
                    System.out.println("Delete Jurusan");
                    System.out.print("Masukkan Kode Jurusan: ");
                    inputKode = scanner.next();

                    JurusanRepository.deleteJurusan(inputKode);
                    break;
                case 3:
                    System.out.println("Update Jurusan");
                    System.out.println("Masukkan Kode Jurusan: ");
                    inputKode = scanner.next();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();

                    Jurusan jurusan = JurusanRepository.getJurusanByKode(inputKode);
                    jurusan.nama = newName;
                    JurusanRepository.updateJurusan(jurusan);
                    break;
                case 4:
                    printAllJurusan();
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