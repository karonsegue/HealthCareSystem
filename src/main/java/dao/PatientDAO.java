package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Patient;
import util.DBConnection;

public class PatientDAO {

    // ID重複確認
    public boolean existsUserId(String userId) {

        String sql = "SELECT * FROM PATIENT WHERE USER_ID = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // IDあり
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // IDなし
    }

    // 患者登録
    public boolean insertPatient(Patient patient) {

        String sql =
            "INSERT INTO PATIENT(USER_ID, PASSWORD, USER_PHONE_NUMBER, FAMILY_PHONE_NUMBER) "
            + "VALUES(?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, patient.getUserId());
            pstmt.setString(2, patient.getPassword());
            pstmt.setString(3, patient.getUserPhoneNumber());
            pstmt.setString(4, patient.getFamilyPhoneNumber());

            int result = pstmt.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ログイン確認
    public boolean login(String userId, String password) {

        String sql =
            "SELECT * FROM PATIENT "
            + "WHERE USER_ID = ? AND PASSWORD = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            pstmt.setString(1, userId);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true; // ログイン成功
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // ログイン失敗
    }
}