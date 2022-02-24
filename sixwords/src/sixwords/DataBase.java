package sixwords;

import java.sql.*;

public class DataBase {
    private String connectionUrl = "jdbc:postgresql://localhost:5432/simpledb";
    private Connection con = null;
    private ResultSet rs = null;
    private Statement stmt = null;

    public DataBase() {

    }

    public void createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionUrl, "postgres", "0000");
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeConnection() {
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException e) {

        }
    }

    public ResultSet request(String sql) {
        try {
            rs = stmt.executeQuery(sql);
            return rs;
        } catch (Exception e) {
            return null;
        }
    }
}
