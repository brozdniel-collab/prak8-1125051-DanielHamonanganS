package hy.material8database.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hy.material8database.database.Database;
import hy.material8database.models.Jurusan;
public class JurusanRepository {
    private static final Connection conn;

    static {
        conn = Database.connect();
    }

    // CREATE
    public static void addJurusan(Jurusan jurusan) {
        String sql = "INSERT INTO jurusan (kode_jurusan, nama) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, jurusan.kode_jurusan);
            preparedStatement.setString(2, jurusan.nama);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ ALL
    public static List<Jurusan> getAllJurusan() {
        List<Jurusan> result = new ArrayList<>();
        String sql = "SELECT kode_jurusan, nama FROM jurusan";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                result.add(
                    new Jurusan(
                        resultSet.getString("kode_jurusan"),
                        resultSet.getString("nama")
                    )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // READ BY kode_Jurusan
    public static Jurusan getJurusanByKode(String kode) {
        String sql = "SELECT kode_jurusan, nama FROM jurusan WHERE kode_jurusan = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, kode);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Jurusan(
                    resultSet.getString("kode_jurusan"),
                    resultSet.getString("nama")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // UPDATE
    public static void updateJurusan(Jurusan jurusan) {
        String sql = "UPDATE jurusan SET nama = ? WHERE kode_jurusan = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(2, jurusan.kode_jurusan);
            preparedStatement.setString(1, jurusan.nama);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteJurusan(String kode) {
        String sql = "DELETE FROM jurusan WHERE kode_jurusan = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, kode);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}