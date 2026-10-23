package hy.material8database;

import java.util.Scanner;

import hy.material8database.view.JurView;
import hy.material8database.view.MahaView;
import hy.material8database.view.MatkulView;

public class App {

    private void menuMahasiswa() {
        new MahaView().menuMahasiswa();
    }

    private void menuJurusan() {
        new JurView().menuJurusan();
    }
    private void menuMataKuliah() {
        new MatkulView().menuMK();
    }

    private Scanner scanner = new Scanner(System.in);

    public App() {
        boolean check = true;

        while (check) {
            System.out.println("=== Menu Utama===");

            System.out.println("List Menu \n 1. Menu Mahasiswa \n 2. Menu Jurusan \n 3. Menu Mata Kuliah");
            System.out.println("Masukkan Menu: ");
            int mainMenu = scanner.nextInt();

            switch (mainMenu) {
                case 1:
                    menuMahasiswa();
                    break;
                case 2:
                    menuJurusan();
                case 3:
                    menuMataKuliah();
                default:
                    throw new AssertionError();
            }
        }
    }

    public static void main(String[] args) {
        new App();
    }
}