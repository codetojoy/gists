
package net.codetojoy

import java.sql.*

class AddressDao {
   static final String DB_URL = "jdbc:h2:~/measter_free1b_db;AUTO_SERVER=TRUE";
   static final String JDBC_DRIVER = "org.h2.Driver";
   static final String USER = "sa";
   static final String PASS = "5150";

    def getStates() {
        def results = [] 
 
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql = "SELECT * FROM US_STATES";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("US_STATE_NM");
                results << name
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if(stmt!=null) stmt.close(); } catch (SQLException se2) {}

            try { 
                if(conn!=null) conn.close(); 
            } catch (SQLException se){
             se.printStackTrace();
            }
        }

        return results
    }
}
