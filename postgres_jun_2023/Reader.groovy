
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def sql = Sql.newInstance("jdbc:postgresql://127.0.0.1:5432/sandbox", "postgres","swordfish", "org.postgresql.Driver")

sql.eachRow("""
SELECT brp.id as id from bridge_reference_plan brp
JOIN plan p on p.id = brp.plan_id
JOIN reference r on r.id = brp.reference_id
JOIN bridge_customer_reference bcr on bcr.reference_id = r.id
""") { row ->
    println row
}

sql.close()

