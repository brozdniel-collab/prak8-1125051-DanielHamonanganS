package hy.material8database.view;

import java.util.List;
import java.util.Scanner;

import hy.material8database.App;
import hy.material8database.models.Mahasiswa;
import hy.material8database.models.MahasiswaMK;
import hy.material8database.repository.MahasiswaRepository;
public class MahaView {

    private Scanner scanner = new Scanner(System.in);

    private void addNewMahasiswa() {
        System.out.print("Masukkan NIM: ");
        String nim = scanner.next();
        scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        Mahasiswa mhs = new Mahasiswa(nim, nama);
        MahasiswaRepository.addMahasiswa(mhs);
    }

    private void printAllMahasiswa() {
        List<Mahasiswa> allMahasiswa = MahasiswaRepository.getAllMahasiswa();
        System.out.println("\n📋 Daftar Mahasiswa:");
        for (Mahasiswa m : allMahasiswa) {
            System.out.println(m.nim + " - " + m.nama);
        }
    }

    private void printAllMhsMK(List<MahasiswaMK> mhsMK) {
        System.out.println("\n📋 Daftar Mata Kuliah diambil:");
        for (MahasiswaMK m : mhsMK) {
            System.out.println(m.nim + " - " + m.kodeMK + " - " + m.indeksNilai);
        }
    }

    public void menuMahasiswa() {
        boolean loop = true;
        while (loop) {
            System.out.println("=== Menu Mahasiswa");
            System.out.println(" 1. Add Mahasiswa \n 2. Delete Mahasiswa \n 3. Edit Mahasiswa \n 4. Print Mahasiswa \n 5. Mahasiswa Ambil MK \n 6. Main Menu");
            System.out.print("Masukkan Menu: ");
            int pilihMenu = scanner.nextInt();

            switch (pilihMenu) {
                case 1:
                    System.out.println("\nTambahkan Mahasiswa: ");
                    System.out.println();
                    addNewMahasiswa();
                    break;
                case 2:
                    System.out.println("Delete Mahasiswa");
                    System.out.print("Masukkan NIM: ");
                    String inputNim = scanner.next();

                    MahasiswaRepository.deleteMahasiswa(inputNim);
                    break;
                case 3:
                    System.out.println("Update Mahasiswa");
                    System.out.println("Masukkan NIM");
                    inputNim = scanner.next();
                    scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String newName = scanner.nextLine();

                    Mahasiswa mhs = MahasiswaRepository.getMahasiswaByNim(inputNim);
                    mhs.nama = newName;
                    MahasiswaRepository.updateMahasiswa(mhs);
                    break;
                case 4:
                    printAllMahasiswa();
                    break;
                case 5:
                    System.out.println("Masukkan NIM");
                    inputNim = scanner.next();
                    
                    List<MahasiswaMK> mahasiswaMK = MahasiswaRepository.getMahasiswaMK(MahasiswaRepository.getMahasiswaByNim(inputNim));
                    printAllMhsMK(mahasiswaMK);
                    break;
                case 6:
                    new App();
                default:
                    loop = false;
                    throw new AssertionError();
            }
        }
    }
}