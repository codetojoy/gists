
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

// plan

def insert
insert = " INSERT INTO plan (id, name, status, payload) VALUES (?,?,?,?::json); "

10.times { i ->
    def id = 100 + i
    def name = 'plan-' + i
    def status = 'active'
    def isFoo = i % 2 == 0
    def obj = /{"isFoo":/ + isFoo + /, "bar": / + (id+18) + "}"
    println obj
    String payload = "[${obj},${obj},${obj},${obj}]"
    sql.execute(insert, [id, name, status, payload]);
}

sql.close()
