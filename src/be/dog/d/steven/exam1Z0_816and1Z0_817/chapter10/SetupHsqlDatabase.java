package be.dog.d.steven.exam1Z0_816and1Z0_817.chapter10;

import java.sql.*;

public class SetupHsqlDatabase {

    public static void main(String[] args) throws Exception {
        String url = "jdbc:hsqldb:file:zoo";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            run(conn, "DROP PROCEDURE read_e_names IF EXISTS");
            run(conn, "DROP PROCEDURE read_names_by_letter IF EXISTS");
            run(conn, "DROP PROCEDURE magic_number IF EXISTS");
            run(conn, "DROP PROCEDURE double_number IF EXISTS");
            run(conn, "DROP TABLE names IF EXISTS");
            run(conn, "DROP TABLE exhibits IF EXISTS");

            run(conn, "CREATE TABLE exhibits ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name VARCHAR(255), "
                    + "num_acres DECIMAL(4,1))");

            run(conn, "CREATE TABLE names ("
                    + "id INTEGER PRIMARY KEY, "
                    + "species_id integer REFERENCES exhibits (id), "
                    + "name VARCHAR(255))");

            run(conn, "INSERT INTO exhibits VALUES (1, 'African Elephant', 7.5)");
            run(conn, "INSERT INTO exhibits VALUES (2, 'Zebra', 1.2)");

            run(conn, "INSERT INTO names VALUES (1, 1, 'Elsa')");
            run(conn, "INSERT INTO names VALUES (2, 2, 'Zelda')");
            run(conn, "INSERT INTO names VALUES (3, 1, 'Ester')");
            run(conn, "INSERT INTO names VALUES (4, 1, 'Eddie')");
            run(conn, "INSERT INTO names VALUES (5, 2, 'Zoe')");

            String noParams = "CREATE PROCEDURE read_e_names() "
                    + "READS SQL DATA DYNAMIC RESULT SETS 1 "
                    + "BEGIN ATOMIC "
                    + "DECLARE result CURSOR WITH RETURN FOR SELECT * FROM names; "
                    + "OPEN result; "
                    + "END";

            String inParam = "CREATE PROCEDURE read_names_by_letter(IN prefix VARCHAR(10)) "
                    + "READS SQL DATA DYNAMIC RESULT SETS 1 "
                    + "BEGIN ATOMIC "
                    + "DECLARE result CURSOR WITH RETURN FOR " +
                    " SELECT * FROM names WHERE name LIKE CONCAT(prefix, '%'); "
                    + "OPEN result; "
                    + "END";

            String inOutParam = "CREATE PROCEDURE double_number(INOUT num INT) READS SQL DATA\n" +
                    "  DYNAMIC RESULT SETS 1 " +
                    "  BEGIN ATOMIC " +
                    "  SET num = num * 2; " +
                    "  END";

            String outParam = "CREATE PROCEDURE magic_number(OUT num INT) READS SQL DATA\n" +
                    "  BEGIN ATOMIC " +
                    "  SET num = 42;" +
                    "  END";

            run(conn, noParams);
            run(conn, inParam);
            run(conn, outParam);
            run(conn, inOutParam);

            printCount(conn, "SELECT count(*) FROM names");
        }
    }

    private static void run(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }

    private static void printCount(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getInt(1));
        }
    }
}
