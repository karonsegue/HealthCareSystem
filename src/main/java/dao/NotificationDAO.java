package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Notification;
import util.DBConnection;

public class NotificationDAO {

    // 今日通知済みか確認
    public boolean existsNotification(String userId,Date sendDate) {

        String sql =
            "SELECT * FROM Notification "
            + "WHERE user_id = ? "
            + "AND send_date = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);
            pstmt.setDate(2, sendDate);

            ResultSet rs =pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 通知登録
    public boolean insertNotification(Notification notification) {

        String sql =
            "INSERT INTO Notification "
            + "(user_id, send_date, message_type) "
            + "VALUES (?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt =conn.prepareStatement(sql)
        ) {

            pstmt.setString(1,notification.getUserId());

            pstmt.setDate(2,Date.valueOf(notification.getSendDate()));

            pstmt.setString(3,notification.getMessageType());

            int result =pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}