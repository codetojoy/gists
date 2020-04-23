
package net.codetojoy

import javax.naming.*
import javax.sql.*

class MyDataSource {
    final static String CONTEXT = "WorklightAdminDS"
    final static String DATASOURCE = "foo"
    final static String USER = "mozart"
    final static String PASSWORD = "1756"

    def getConnection() {
        def connection = null

        try {
            println "TRACER DataSource cp 0"
            def dataSource = (DataSource) new InitialContext().lookup(CONTEXT)
            // println "TRACER DataSource cp 1 context"
            // def dataSource = (DataSource) context.lookup(DATASOURCE)
            println "TRACER DataSource cp 2 datasource"
            connection = dataSource.getConnection(USER, PASSWORD)

            if (connection != null) {
                println "TRACER DataSource cp 3 ... OK for connection"
            }
        } catch (Exception ex) {
            System.err.println("TRACER caught exception: " + ex.message)
        }

        return connection
    }
}
