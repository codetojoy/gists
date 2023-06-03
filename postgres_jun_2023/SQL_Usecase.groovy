
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
insert = " INSERT INTO plan (id, name, status) VALUES (?,?,?); "

def id = 150
def name = 'target-plan'
def status = 'active'
sql.execute(insert, [id, name, status]);

// bridge_reference_plan
// for all customers, get all references and insert for plan

sql.execute("""
INSERT INTO bridge_reference_plan brp
(reference_id, plan_id)
VALUES ()
""")

sql.close()

