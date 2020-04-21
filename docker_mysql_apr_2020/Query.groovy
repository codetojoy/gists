
@Grab(group='mysql', module='mysql-connector-java', version='5.1.47')
@GrabConfig(systemClassLoader = true)

import groovy.sql.Sql
 
def url = "jdbc:mysql://localhost:3306/foo?useSSL=false"
def user = "mozart"
def password = "1756"
def driver = "com.mysql.jdbc.Driver"
def sql = Sql.newInstance(url, user, password, driver)
 
// table name is case-sensitive!
def rows = sql.rows("SELECT * FROM employee")

rows.each { row ->
    println "TRACER id: ${row.id} name: ${row.name}"
}

println "Ready."
 
