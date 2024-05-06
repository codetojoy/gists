
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def user = "postgres"
def password = "swordfish"
def host = "127.0.0.1"
def port = 5432
def database = "sandbox"

def sql = Sql.newInstance("jdbc:postgresql://${host}:${port}/${database}", user, password, "org.postgresql.Driver")

// roster

def myExecute(def sql, def id, def status) {
    String name = "name-" + id

    def insert

    // roster
    insert = " INSERT INTO roster (id, name, status) VALUES (?,?,?); "
    sql.execute(insert, [id, name, status]) 

    // league
    insert = " INSERT INTO league (id, name, status) VALUES (?,?,?); "
    sql.execute(insert, [id, name, status]) 
}

def id

id = 4140
myExecute(sql, id++, "ACTIVE")
myExecute(sql, id++, "INJURED")
myExecute(sql, id++, "RETIRED")
myExecute(sql, id++, "PENDING")

id = 5150
myExecute(sql, id++, "ACTIVE")
myExecute(sql, id++, "INJURED")
myExecute(sql, id++, "RETIRED")
myExecute(sql, id++, "PENDING")

id = 6160
myExecute(sql, id++, "ACTIVE")
myExecute(sql, id++, "INJURED")
myExecute(sql, id++, "RETIRED")
myExecute(sql, id++, "PENDING")

sql.close()
