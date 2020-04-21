
package net.codetojoy

import groovy.sql.Sql

class EmployeeDAO {
    def url = "jdbc:mysql://localhost:3306/foo?useSSL=false"
    def user = "mozart"
    def password = "1756"
    def driver = "com.mysql.jdbc.Driver"

    List<String> findEmployees() {
        List<String> results = new ArrayList<String>()
        def sql = Sql.newInstance(url, user, password, driver)

        // table name is case-sensitive!
        def rows = sql.rows("SELECT * FROM employee")

        rows.each { row ->
            String result = "id: ${row.id} name: ${row.name}" as String
            results << result
        }

        return results
    }
}
