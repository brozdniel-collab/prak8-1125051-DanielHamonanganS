package hy.material8database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hy.material8database.database.Database;
import hy.material8database.models.Mahasiswa;
import hy.material8database.models.MahasiswaMK;

public class MahasiswaRepository {

    private static final Connection conn;

    static {
        conn = Database.connect();
    }

    // CREATE
    public static void addMahasiswa(Mahasiswa mhs) {
        String sql = "INSERT INTO mahasiswa (nim, nama) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mhs.nim);
            preparedStatement.setString(2, mhs.nama);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> result = new ArrayList<>();
        String sql = "SELECT nim, nama FROM mahasiswa";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                result.add(
                    new Mahasiswa(
                        resultSet.getString("nim"),
                        resultSet.getString("nama")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // READ BY NIM
    public static Mahasiswa getMahasiswaByNim(String nim) {
        String sql = "SELECT nim, nama FROM mahasiswa WHERE nim = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nim);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Mahasiswa(
                    resultSet.getString("nim"),
                    resultSet.getString("nama")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public static void updateMahasiswa(Mahasiswa mhs) {
        String sql = "UPDATE mahasiswa SET nama = ? WHERE nim = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mhs.nama);
            preparedStatement.setString(2, mhs.nim);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteMahasiswa(String nim) {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nim);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<MahasiswaMK> getMahasiswaMK(Mahasiswa mhs) {
        List<MahasiswaMK> result = new ArrayList<>();
        String sql = "select * from mahasiswa_matakuliah where nim = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, mhs.nim);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                result.add(
                    new MahasiswaMK(
                        resultSet.getString("nim"),
                        resultSet.getString("kode_matakuliah"),
                        resultSet.getString("indeks_nilai")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}