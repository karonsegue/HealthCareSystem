package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.HealthData;
import util.DBConnection;

public class HealthDataDAO {

    // 今日の記録があるか確認
    public boolean existsTodayRecord(String userId, Date recordDate) {

        String sql =
            "SELECT * FROM HealthData "
            + "WHERE user_id = ? AND record_date = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);
            pstmt.setDate(2, recordDate);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 記録追加
    public boolean insertHealthData(HealthData healthData) {

        String sql =
            "INSERT INTO HealthData "
            + "(user_id, bodytemperature, sleeptime, record_date, edit_count, status) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, healthData.getUserId());
            pstmt.setDouble(2, healthData.getBodyTemperature());
            pstmt.setInt(3, healthData.getSleepTime());
            pstmt.setDate(4, Date.valueOf(healthData.getRecordDate()));
            pstmt.setInt(5, healthData.getEditCount());
            pstmt.setString(6, healthData.getStatus());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 一覧取得
    public ArrayList<HealthData> getHealthDataList(String userId) {

        ArrayList<HealthData> list = new ArrayList<>();

        String sql =
            "SELECT * FROM HealthData "
            + "WHERE user_id = ? "
            + "ORDER BY record_date DESC";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                HealthData healthData = new HealthData();

                healthData.setRecordId(rs.getInt("record_id"));
                healthData.setUserId(rs.getString("user_id"));
                healthData.setBodyTemperature(rs.getDouble("bodytemperature"));
                healthData.setSleepTime(rs.getInt("sleeptime"));
                healthData.setRecordDate(rs.getDate("record_date").toLocalDate());
                healthData.setEditCount(rs.getInt("edit_count"));
                healthData.setStatus(rs.getString("status"));

                list.add(healthData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    // recordIdで1件取得
    public HealthData getHealthDataById(int recordId) {

        String sql = "SELECT * FROM HealthData " + "WHERE record_id = ?";

        try (Connection conn = DBConnection.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setInt(1, recordId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                HealthData healthData = new HealthData();

                healthData.setRecordId(rs.getInt("record_id"));

                healthData.setUserId(rs.getString("user_id"));

                healthData.setBodyTemperature(rs.getDouble("bodytemperature"));

                healthData.setSleepTime(rs.getInt("sleeptime"));

                healthData.setRecordDate(rs.getDate("record_date").toLocalDate());

                healthData.setEditCount(rs.getInt("edit_count"));

                healthData.setStatus(rs.getString("status"));

                return healthData;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 記録更新
    public boolean updateHealthData(HealthData healthData) {

        String sql =
            "UPDATE HealthData "
            + "SET bodytemperature = ?, "
            + "sleeptime = ?, "
            + "status = ?, "
            + "edit_count = ? "
            + "WHERE record_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setDouble(1, healthData.getBodyTemperature());
            pstmt.setInt(2, healthData.getSleepTime());
            pstmt.setString(3, healthData.getStatus());
            pstmt.setInt(4, healthData.getEditCount());
            pstmt.setInt(5, healthData.getRecordId());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}