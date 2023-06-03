
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

def planId = 150
sql.execute("DELETE FROM bridge_reference_plan where plan_id = " + planId)
sql.execute("DELETE FROM plan where id = " + planId)

// new plan
def insert
insert = " INSERT INTO plan (id, name, status) VALUES (?,?,?); "

def name = 'target-plan'
def status = 'active'
sql.execute(insert, [planId, name, status]);

// associate one customer to all references
def customerId = 301
insert = """
INSERT INTO bridge_customer_reference
(customer_id, reference_id)
SELECT ${customerId}, r.id FROM reference r
"""

sql.execute(insert);

// write to bridge_reference_plan
// for all customers, get all references and insert for plan

sql.execute("""
INSERT INTO bridge_reference_plan
(reference_id, plan_id)
SELECT r.id, ${planId} FROM customer c
JOIN bridge_customer_reference brc on brc.customer_id = c.id
JOIN reference r on r.id = brc.reference_id
""")

println "TRACER results for plan " + planId

sql.eachRow("""
SELECT p.name as planName, r.name as refName FROM bridge_reference_plan brp
JOIN reference r on r.id = brp.reference_id
JOIN plan p on p.id = brp.plan_id
WHERE brp.plan_id = ${planId}
""") { row ->
    println "plan: ${row.planName} reference: ${row.refName}"
}

println "TRACER cross-check: all references"

sql.eachRow("""
SELECT r.name as refName FROM customer c
JOIN bridge_customer_reference bcr on bcr.customer_id = c.id
JOIN reference r on r.id = bcr.reference_id
""") { row ->
    println "reference: ${row.refName}"
}

sql.close()

