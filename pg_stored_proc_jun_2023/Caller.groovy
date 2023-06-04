
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import java.sql.*
import org.postgresql.Driver

def url = "jdbc:postgresql://127.0.0.1:5432/sandbox"
def user = "postgres"
def password = "swordfish"
def conn 
def planId = 150

try {
    Class.forName("org.postgresql.Driver")
    conn = DriverManager.getConnection(url, user, password)

    def ps =  conn.prepareStatement("SELECT s2(?) as c");
    ps.setInt(1, planId);
    def rs = ps.executeQuery();
    while (rs.next()) {
        println "TRACER result from s2(${planId}): " + rs.getInt("c")
    }
    rs.close()
    ps.close()

} catch (Exception ex) {
    System.err.println "caught ex: " + ex.message
} finally {
    conn?.close()
}

println "Ready."

