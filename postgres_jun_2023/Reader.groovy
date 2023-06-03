
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def sql = Sql.newInstance("jdbc:postgresql://127.0.0.1:5432/sandbox", "postgres","swordfish", "org.postgresql.Driver")

def emitCount = { table, count ->
    String emitFormat = "# %-20s : %d"
    println String.format(emitFormat, table, count)
}

sql.eachRow("SELECT COUNT(*) as c FROM plan") { emitCount("plans", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM reference") { emitCount("references", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM customer") { emitCount("customers", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM bridge_customer_reference") { emitCount("bcrs", it.c) }
sql.eachRow("SELECT COUNT(*) as c FROM bridge_reference_plan") { emitCount("brps", it.c) }

println "----"

sql.eachRow("""
SELECT p.name as plan_name, r.name as reference_name from bridge_reference_plan brp
JOIN plan p on p.id = brp.plan_id
JOIN reference r on r.id = brp.reference_id
ORDER BY p.name, r.name
""") { row ->
    println "plan: ${row.plan_name} reference: ${row.reference_name}"
}

sql.close()

