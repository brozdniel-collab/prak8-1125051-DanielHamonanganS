package hy.material8database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hy.material8database.database.Database;
import hy.material8database.models.Matakuliah;
public class MatkulRepo {
    private static final Connection conn;

    static {
        conn = Database.connect();
    }

    // CREATE
    public static void addMataKuliah(Matakuliah mk) {
        String sql = "INSERT INTO matakuliah (kode_matakuliah, nama, sks, kode_jurusan) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mk.kode_matakuliah);
            preparedStatement.setString(2, mk.nama);
            preparedStatement.setInt(3, mk.sks);
            preparedStatement.setString(4, mk.kode_jurusan);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static List<Matakuliah> getAllMataKuliah() {
        List<Matakuliah> result = new ArrayList<>();
        String sql = "SELECT * FROM matakuliah";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                result.add(
                    new Matakuliah(
                        resultSet.getString("kode_matakuliah"),
                        resultSet.getString("nama"),
                        resultSet.getString("kode_jurusan"),
                        resultSet.getInt("sks")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // READ BY Kode_Matakuliah
    public static Matakuliah getMKByKode(String kode) {
        String sql = "SELECT FROM matakuliah WHERE kode_matakuliah = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, kode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Matakuliah(
                    resultSet.getString("kode_matakuliah"),
                        resultSet.getString("nama"),
                        resultSet.getString("kode_jurusan"),
                        resultSet.getInt("sks")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public static void updateMataKuliah(Matakuliah mk) {
        String sql = "UPDATE MataKuliah SET nama = ?, sks = ? WHERE kode_matakuliah = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mk.nama);
            preparedStatement.setInt(2, mk.sks);
            preparedStatement.setString(3, mk.kode_matakuliah);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteMK(String kode) {
        String sql = "DELETE FROM matakuliah WHERE kode_matakuliah = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, kode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}