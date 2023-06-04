
package net.codetojoy.usecase;

import java.sql.*;

public class Usecase {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/sandbox";
    static final String USER = "postgres";
    static final String PASS = "swordfish";
    static final String QUERY = "SELECT name FROM customer";

    void execute(Connection conn, String query) {
        try (var stmt = conn.createStatement()) {
            stmt.execute(query);
        } catch (Exception ex) {
            System.err.println("execute: caught ex: " + ex.getMessage());
        }
    }

    void doCount(String banner, Connection conn, int planId) throws Exception {
        System.out.println("---------");
        System.out.println(banner);

        // sanity check read #1
        try (var stmt1 = conn.createStatement();
                var rs1 = stmt1.executeQuery("select count(*) as c from bridge_reference_plan;")) {
            while (rs1.next()) {
            System.out.println("# brp: " + rs1.getString("c"));
            }
        }

        // sanity check read #1
        try (var ps1 = conn.prepareStatement(" select count(*) as c from bridge_reference_plan brp where brp.plan_id = ?");) {
            ps1.setInt(1, planId);
            var rs = ps1.executeQuery();
            while (rs.next()) {
                System.out.println("# brp-150 : " + rs.getString("c"));
            }
            rs.close();
        }
    }

    public boolean apply() throws Exception {
        boolean result = false;

        Class.forName("org.postgresql.Driver");
        Connection conn = null;

        int planId = 150;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            doCount("before", conn, planId);

            execute(conn, "drop table if exists temp_brp;");

            execute(conn, "create temporary table temp_brp ( reference_id bigint, plan_id bigint);");

            StringBuilder builder = null;
            PreparedStatement ps = null;

            // insert all existing to temp
            builder = new StringBuilder();
            builder.append("INSERT INTO temp_brp (reference_id, plan_id) ");
            builder.append("SELECT reference_id, plan_id FROM bridge_reference_plan ");
            builder.append("WHERE plan_id = ?;");
            ps = conn.prepareStatement(builder.toString());
            ps.setInt(1, planId);
            ps.execute();
            ps.close();

            // insert all new to temp
            builder = new StringBuilder();
            builder.append("INSERT INTO temp_brp (reference_id, plan_id) ");
            builder.append("SELECT r.id, ? FROM customer c ");
            builder.append("JOIN bridge_customer_reference brc on brc.customer_id = c.id ");
            builder.append("JOIN reference r on r.id = brc.reference_id;");
            ps = conn.prepareStatement(builder.toString());
            ps.setInt(1, planId);
            ps.execute();
            ps.close();

            // insert distinct from temp to original
            builder = new StringBuilder();
            builder.append("INSERT INTO bridge_reference_plan (reference_id, plan_id) ");
            builder.append("SELECT DISTINCT reference_id, plan_id FROM temp_brp ");
            builder.append("WHERE NOT EXISTS ( ");
            builder.append("    SELECT 'X' FROM bridge_reference_plan brp ");
            builder.append("    WHERE temp_brp.reference_id = brp.reference_id AND temp_brp.plan_id = brp.plan_id");
            builder.append(");");
            execute(conn, builder.toString());

            doCount("after", conn, planId);

            result = true;
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            System.err.println("apply caught exception ex: " + ex.getMessage());
        } finally {
            if (conn != null) { conn.close(); }
        }

        return result;
    }
}
