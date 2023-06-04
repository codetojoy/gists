
package net.codetojoy.usecase;

import java.sql.*;

public class Usecase {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/sandbox";
    static final String USER = "postgres";
    static final String PASS = "swordfish";
    static final String QUERY = "SELECT name FROM customer";

    public boolean apply() throws Exception {
        boolean result = false;

        Class.forName("org.postgresql.Driver");

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            conn.setAutoCommit(false);
            while (rs.next()) {
               System.out.println("name: " + rs.getString("name"));
            }
            result = true;
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            System.err.println("caught exception ex: " + ex.getMessage());
        }

        return result;
    }
}
