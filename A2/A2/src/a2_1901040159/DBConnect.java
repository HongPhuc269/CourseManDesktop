package a2_1901040159;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private static Connection con;
    private static Statement stm;
    private static String DBMS = "sqlite";
    private static String dbName = "D:\\Hanu_Learning\\Fall_2022\\JSD\\Assignment\\A2\\A2\\src\\a2_1901040159\\database.sqlite3";

    public static Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:" + DBMS + ":" + dbName);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

}
