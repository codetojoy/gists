
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def emitCount = { table, count ->
    String emitFormat = "# %-20s : %d"
    println String.format(emitFormat, table, count)
}

def user = "postgres"
def password = "swordfish"
def host = "127.0.0.1"
def port = 5432
def database = "sandbox"

def sql = Sql.newInstance("jdbc:postgresql://${host}:${port}/${database}", user, password, "org.postgresql.Driver")

println "TRACER operation start"
def planId = 150

sql.eachRow("SELECT COUNT(*) as c FROM bridge_reference_plan") { emitCount("brp", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM bridge_reference_plan WHERE plan_id = ${planId}") { emitCount("brp ${planId}", it.c) }

def doSeed = false

if (doSeed) {
    // delete plan

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
}

// sql.close();
// System.exit(0);

// write to bridge_reference_plan
// for all customers, get all references and insert for plan

// mode 1
/*
def keys = []
keys = sql.executeInsert("""
INSERT INTO bridge_reference_plan
(reference_id, plan_id)
SELECT r.id, ${planId} FROM customer c
JOIN bridge_customer_reference brc on brc.customer_id = c.id
JOIN reference r on r.id = brc.reference_id
""")
*/

sql.execute("""
drop table if exists temp_brp;

create temporary table temp_brp (
	reference_id bigint,
	plan_id bigint
);

-- insert all existing to temp
-- todo: qualify by plan_id
INSERT INTO temp_brp
(reference_id, plan_id)
SELECT reference_id, plan_id FROM bridge_reference_plan
WHERE plan_id = ${planId};

-- insert all new to temp
INSERT INTO temp_brp
(reference_id, plan_id)
SELECT r.id, ${planId} FROM customer c
JOIN bridge_customer_reference brc on brc.customer_id = c.id
JOIN reference r on r.id = brc.reference_id;

-- insert distinct from temp to original
INSERT INTO bridge_reference_plan (reference_id, plan_id)
     SELECT DISTINCT reference_id, plan_id
     FROM temp_brp
     WHERE NOT EXISTS (
         SELECT 'X'
         FROM bridge_reference_plan brp
         WHERE
             temp_brp.reference_id = brp.reference_id
             AND temp_brp.plan_id = brp.plan_id
     );
""")

println "TRACER operation complete"

sql.eachRow("SELECT COUNT(*) as c FROM bridge_reference_plan") { emitCount("brp", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM bridge_reference_plan WHERE plan_id = ${planId}") { emitCount("brp ${planId}", it.c) }

sql.close()

