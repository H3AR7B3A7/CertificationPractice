package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter10;

import java.sql.*;
import java.util.Arrays;

// EXAMPLE

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

// CONNECTION

class Connect {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        System.out.println(conn);
    }
}

// EXECUTE QUERY

class Prepared {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");
        try (var ps = conn.prepareStatement("SELECT * FROM exhibits");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next())
                System.out.println(rs.getString(1) + ", "
                        + rs.getString(2) + ", "
                        + rs.getString(3));
        }
    }
}

// EXECUTE PREPARED STATEMENTS

class Executions {
    private static final String URL = "jdbc:derby:zoo";

    private final static String INSERT = "INSERT INTO exhibits VALUES (10, 'Deer', 3)";
    private final static String UPDATE = "UPDATE exhibits SET name = 'Bambi' WHERE name = 'Deer'";
    private final static String DELETE = "DELETE FROM exhibits WHERE id = 10";

    private final static String SELECT = "SELECT name FROM names";

    public static void main(String[] args) throws SQLException {

        // EXECUTE UPDATE

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

        // EXECUTE

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(SELECT)) {
            if (ps.execute()) {
                System.out.println("Execute query and return results set if present: ");
                ResultSet rs = ps.getResultSet();
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
        }
    }

    // BIND VARIABLES

    public static void register(Connection conn, int key, int type, String name) throws SQLException {
        String sql = "INSERT INTO names VALUES(?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, key);
            ps.setInt(2, type);
            ps.setString(3, name);
            ps.executeUpdate();
        }
    }

    // BATCH STATEMENTS

    public static void register(Connection conn, int firstKey, int type, String... names) throws SQLException {
        var sql = "INSERT INTO names VALUES(?, ?, ?)";
        var nextIndex = firstKey;

        try (var ps = conn.prepareStatement(sql)){
            ps.setInt(2, type);

            for (var name: names) {
                ps.setInt(1, nextIndex);
                ps.setString(3, name);
                ps.addBatch();

                nextIndex++;
            }
            int[] result = ps.executeBatch();
            System.out.println(Arrays.toString(result));
        }
    }
}

