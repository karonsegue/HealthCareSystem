package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            Class.forName("org.h2.Driver");

            System.out.println(System.getProperty("user.home"));
            
            Connection conn =
                    DriverManager.getConnection(
                            "jdbc:h2:~/healthcare;AUTO_SERVER=TRUE",
                            "sa",
                            "");

            System.out.println("DB接続成功");

            return conn;

        } catch (Exception e) {

            System.out.println("DB接続失敗");
            e.printStackTrace();

            return null;
        }
    }
}