
package net.codetojoy

import java.sql.*

class EmployeeDaoWithDataSource {
    final static def SQL = "SELECT * FROM employee"

    List<String> findEmployees() {
        List<String> results = new ArrayList<String>()

        def connection = null
        def statement = null
        def resultSet = null

        try {
            connection = new MyDataSource().getConnection() 
            statement = connection.prepareStatement(SQL)
            resultSet = statement.executeQuery()

            while (resultSet.next()) {
                def id = resultSet.getInt("id")
                def name = resultSet.getString("name")
                String result = "id: ${id} name: ${name}" as String
                results << result
            }
        } catch (Exception ex) {
            System.err.println "TRACER caught exception: " + ex.message
        } finally {
            resultSet?.close()
            statement?.close()
            connection?.close()
        }

        return results
    }
}
