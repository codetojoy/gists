
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
def doSeed = true

if (doSeed) {
    // associate one customer to all references x 2
    def customerId = 301
    insert = """
    INSERT INTO bridge_customer_reference
    (customer_id, reference_id)
    SELECT ${customerId}, r.id FROM reference r
    """

    sql.execute(insert);
    sql.execute(insert);

    sql.eachRow("""
        SELECT COUNT(*) as c FROM bridge_customer_reference bcr
        WHERE bcr.customer_id = 301;
    """) { row ->
        emitCount("bcr", row.c)
    }
}

sql.close();
System.exit(0);

