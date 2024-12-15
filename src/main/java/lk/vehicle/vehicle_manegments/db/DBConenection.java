package lk.vehicle.vehicle_manegments.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConenection {

    private static DBConenection dbConenection;
    private final Connection connection;
    private DBConenection() throws ClassNotFoundException, SQLException {
        Class.forName ("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection ("jdbc:mysql://localhost:3306/ls", "root", "1234");
    }
    public static DBConenection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConenection == null) {
            dbConenection = new DBConenection ();
        }
        return dbConenection;
    }
    public Connection getConnection() {
        return connection;
    }
}
