
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
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int planId = 150;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            stmt.execute("drop table if exists temp_brp;");
            stmt.close();

            stmt = conn.createStatement();
            stmt.execute("create temporary table temp_brp ( reference_id bigint, plan_id bigint);");
            stmt.close();

            // insert all existing to temp
            ps = conn.prepareStatement(" INSERT INTO temp_brp (reference_id, plan_id) SELECT reference_id, plan_id FROM bridge_reference_plan WHERE plan_id = ?;");
            ps.setInt(1, planId);
            ps.execute();
            ps.close();

            // insert all new to temp
            ps = conn.prepareStatement(" INSERT INTO temp_brp (reference_id, plan_id) SELECT r.id, ? FROM customer c JOIN bridge_customer_reference brc on brc.customer_id = c.id JOIN reference r on r.id = brc.reference_id;");
            ps.setInt(1, planId);
            ps.execute();
            ps.close();

            // insert distinct from temp to original
            stmt = conn.createStatement();
            stmt.execute(" INSERT INTO bridge_reference_plan (reference_id, plan_id) SELECT DISTINCT reference_id, plan_id FROM temp_brp WHERE NOT EXISTS ( SELECT 'X' FROM bridge_reference_plan brp WHERE temp_brp.reference_id = brp.reference_id AND temp_brp.plan_id = brp.plan_id);");
            stmt.close();

            // sanity check read #1
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) as c from bridge_reference_plan;");
            while (rs.next()) {
               System.out.println("# brp: " + rs.getString("c"));
            }
            rs.close();
            stmt.close();

            // sanity check read #1
            ps = conn.prepareStatement(" select count(*) as c from bridge_reference_plan brp where brp.plan_id = ?");
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            while (rs.next()) {
               System.out.println("# brp: " + rs.getString("c"));
            }
            rs.close();
            ps.close();

            result = true;
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            System.err.println("caught exception ex: " + ex.getMessage());
        } finally {
            if (rs != null) { rs.close(); }
            // if (stmt != null) { stmt.close(); }
            // if (ps != null) { ps.close(); }
            if (conn != null) { conn.close(); }
        }

        return result;
    }
}
