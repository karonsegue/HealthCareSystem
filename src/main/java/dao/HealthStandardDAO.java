package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.HealthStandard;
import util.DBConnection;

public class HealthStandardDAO {

    // 基準値登録
    public boolean insertHealthStandard(
            HealthStandard healthStandard) {

        String sql =
            "INSERT INTO HealthStandard "
            + "(user_id, average_bodytemperature, average_sleeptime) "
            + "VALUES (?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =
                conn.prepareStatement(sql)
        ) {

            pstmt.setString(1,healthStandard.getUserId());

            pstmt.setDouble(2, healthStandard.getAverageBodyTemperature());

            pstmt.setInt(3, healthStandard.getAverageSleepTime());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 基準値取得
    public HealthStandard getHealthStandard(String userId) {

        String sql =
            "SELECT * FROM HealthStandard "
            + "WHERE user_id = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);

            ResultSet rs =pstmt.executeQuery();

            if (rs.next()) {

                HealthStandard hs =new HealthStandard();

                hs.setUserId(rs.getString("user_id"));

                hs.setAverageBodyTemperature(rs.getDouble("average_bodytemperature"));

                hs.setAverageSleepTime(rs.getInt("average_sleeptime"));

                return hs;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}