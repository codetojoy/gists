
package net.codetojoy

import groovy.sql.Sql

class EmployeeDAO {
    final def DB_SERVER = "host.docker.internal"
    final def DB_URL = "jdbc:mysql://${DB_SERVER}:3306/foo?useSSL=false"
    final def DB_USER = "mozart"
    final def DB_PASSWORD = "1756"
    final def DRIVER = "com.mysql.jdbc.Driver"

    List<String> findEmployees() {
        List<String> results = new ArrayList<String>()
        def sql = Sql.newInstance(DB_URL, DB_USER, DB_PASSWORD, DRIVER)

        // table name is case-sensitive!
        def rows = sql.rows("SELECT * FROM employee")

        rows.each { row ->
            String result = "id: ${row.id} name: ${row.name}" as String
            results << result
        }

        return results
    }
}
