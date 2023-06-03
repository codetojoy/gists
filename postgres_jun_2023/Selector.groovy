
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def sql = Sql.newInstance("jdbc:postgresql://127.0.0.1:5432/sandbox", "postgres","swordfish", "org.postgresql.Driver")

assert args.size() > 0 
def table = args[0]
println "table : " + table
sql.eachRow("SELECT * FROM " + table) { println it }

sql.close()

