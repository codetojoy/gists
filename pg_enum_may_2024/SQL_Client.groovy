
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

sql.execute("""
CREATE TABLE roster ( id bigint NOT NULL,
name VARCHAR(256) NOT NULL,
status VARCHAR(256) NOT NULL
);
ALTER TABLE roster ADD PRIMARY KEY (id);
""")

sql.close()

