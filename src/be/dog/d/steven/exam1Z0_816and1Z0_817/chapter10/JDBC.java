package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter10;

import java.sql.*;

public class JDBC {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT name FROM names");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1));
        }

    }
}

class Connect {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);
    }
}

class Prepared {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        try (var ps = conn.prepareStatement("SELECT * FROM exhibits");
             ResultSet rs = ps.executeQuery()){
            while (rs.next())
                System.out.println(rs.getString(1) + ", "
                        + rs.getString(2) + ", "
                        + rs.getString(3));
        }
    }
}

class Executions {
    private static final String URL = "jdbc:derby:zoo";

    private final static String INSERT = "INSERT INTO exhibits VALUES (10, 'Deer', 3)";
    private final static String UPDATE = "UPDATE exhibits SET name = 'Bambi' WHERE name = 'Deer'";
    private final static String DELETE = "DELETE FROM exhibits WHERE id = 10";

    public static void main(String[] args) throws SQLException {

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     INSERT)) {
            int result = ps.executeUpdate();
            System.out.println("Rows inserted: " + result);
        }

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     UPDATE)) {
            int result = ps.executeUpdate();
            System.out.println("Rows updated: " + result);
        }

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                     DELETE)) {
            int result = ps.executeUpdate();
            System.out.println("Rows deleted: " + result);
        }
    }
}

