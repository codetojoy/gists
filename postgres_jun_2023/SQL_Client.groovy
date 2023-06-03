
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
CREATE TABLE plan( id bigint NOT NULL,
name VARCHAR(256) NOT NULL,
status VARCHAR(256) NOT NULL
);
ALTER TABLE plan ADD PRIMARY KEY (id);
""")

// references (e.g. contacts)

sql.execute("""
CREATE TABLE reference( id bigint NOT NULL,
name VARCHAR(256) NOT NULL,
email VARCHAR(256) NOT NULL
);
ALTER TABLE reference ADD PRIMARY KEY (id);
""")

// customers

sql.execute("""
CREATE TABLE customer( id bigint NOT NULL,
name VARCHAR(256) NOT NULL,
email VARCHAR(256) NOT NULL
);
ALTER TABLE customer ADD PRIMARY KEY (id);
""")

// bridge_customer_reference

sql.execute("""
CREATE TABLE bridge_customer_reference(
id SERIAL PRIMARY KEY,
customer_id bigint NOT NULL,
reference_id bigint NOT NULL
);

ALTER TABLE bridge_customer_reference ADD CONSTRAINT bcr_customer_fk FOREIGN KEY (customer_id) REFERENCES customer (id);
ALTER TABLE bridge_customer_reference ADD CONSTRAINT bcr_reference_fk FOREIGN KEY (reference_id) REFERENCES reference(id);
""")

// target
// bridge_reference_plan

sql.execute("""
CREATE TABLE bridge_reference_plan(
id SERIAL PRIMARY KEY,
reference_id bigint NOT NULL,
plan_id bigint NOT NULL
);

ALTER TABLE bridge_reference_plan ADD CONSTRAINT bcr_reference_fk FOREIGN KEY (reference_id) REFERENCES reference(id);
ALTER TABLE bridge_reference_plan ADD CONSTRAINT bcr_plan_fk FOREIGN KEY (plan_id) REFERENCES plan(id);
""")

sql.close()

