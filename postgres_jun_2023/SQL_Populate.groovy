
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

10.times { i ->
    def id = 100 + i
    def name = 'plan-' + i
    def status = 'active'
    sql.execute(insert, [id, name, status]);
}

// references (e.g. contacts)

def buffer = new StringBuilder()
buffer.append(" INSERT INTO reference (id, name, email) VALUES\n ")

def numRows = 500 
numRows.times { i ->
    def id = 200 + i
    def name = 'ref-' + i
    String email = "ref_${i}@codetojoy.net"
    buffer.append("(${id},'${name}','${email}')");
    if (i < numRows-1) {
        buffer.append(",")
    }
    buffer.append("\n")
}
buffer.append(";")

insert = buffer.toString()
sql.execute(insert)

// customers

insert = " INSERT INTO customer (id, name, email) VALUES (?,?,?); "

10.times { i ->
    def id = 300 + i
    def name = 'customer-' + i
    String email = "customer_${i}@codetojoy.net"
    sql.execute(insert, [id, name, email]);
}

// bridge_customer_reference

insert = " INSERT INTO bridge_customer_reference (customer_id, reference_id) VALUES (?,?); "

10.times { i ->
    def customer_id = 300 + i
    def reference_id = 200 + i
    sql.execute(insert, [customer_id, reference_id]);
}

// bridge_reference_plan

insert = " INSERT INTO bridge_reference_plan (reference_id, plan_id) VALUES (?,?); "

10.times { i ->
    def reference_id = 200 + i
    def plan_id = 100 + i
    sql.execute(insert, [reference_id, plan_id]);
}

sql.close()

