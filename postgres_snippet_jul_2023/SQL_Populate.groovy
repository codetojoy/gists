
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

// config

def insert
insert = " INSERT INTO config (id, snippet, template) VALUES (?,?,?); "

def id = 5150
def snippet = '<script>let id = 555;</script>'
def template = '<script>let id = TOKEN;</script>'
sql.execute(insert, [id, snippet, template]);

sql.close()
