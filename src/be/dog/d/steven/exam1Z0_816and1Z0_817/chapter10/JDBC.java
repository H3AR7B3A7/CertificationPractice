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
                System.out.println(rs.getString(1) + ", "  // Starts at 1!
                        + rs.getString("name") + ", "  // We can use column label or index.
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

        try (var ps = conn.prepareStatement(sql)) {
            ps.setInt(2, type);

            for (var name : names) {
                ps.setInt(1, nextIndex);  // Starts at 1! We can only use column index.
                ps.setString(3, name);
                ps.addBatch();

                nextIndex++;
            }
            int[] result = ps.executeBatch();
            System.out.println(Arrays.toString(result));
        }
    }
}

// RESULT SET

class Results {
    private static final String SQL1 = "SELECT count(*) FROM exhibits";
    private static final String SQL2 = "SELECT count(*) AS count FROM exhibits";
    private static final String SQL3 = "SELECT * FROM exhibits WHERE name = ?";

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:derby:zoo";

        try (var conn = DriverManager.getConnection(url);
             var ps = conn.prepareStatement(SQL1);
             var rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Number of exhibits: " + count);
            }
        }

        try (var conn = DriverManager.getConnection(url);
             var ps = conn.prepareStatement(SQL2);
             var rs = ps.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt("count");
                System.out.println("Number of exhibits: " + count);
            }
        }

        try (var conn = DriverManager.getConnection(url);
             var ps = conn.prepareStatement(SQL3)) {  // Select query with bind variable
            ps.setString(1, "Zebra");  // Code needs to execute before ResultSet is initialized
            try (var rs = ps.executeQuery()) {  // Nested try with resources for ResultSet
                while (rs.next()) {
                    int id = rs.getInt("id");
                    double acres = rs.getDouble("num_acres");
                    System.out.println("id: " + id + ", acres: " + acres);
                }
            }
        }
    }
}

