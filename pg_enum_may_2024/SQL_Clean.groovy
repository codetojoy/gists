
@Grapes(
    @Grab(group='org.postgresql', module='postgresql', version='42.6.0')
)
@GrabConfig(systemClassLoader=true)

import groovy.sql.*
import org.postgresql.*

def sql = Sql.newInstance("jdbc:postgresql://127.0.0.1:5432/sandbox", "postgres","swordfish", "org.postgresql.Driver")

sql.execute("DROP TABLE IF EXISTS roster CASCADE")

sql.close()

