
package net.codetojoy

import org.dbunit.*
import org.dbunit.dataset.*
import org.dbunit.dataset.xml.*
import org.dbunit.operation.*

import static org.dbunit.PropertiesBasedJdbcDatabaseTester.* 

class MyTestCase extends DBTestCase {
    MyTestCase() {
        super()
        System.setProperty( DBUNIT_DRIVER_CLASS, "org.h2.Driver" )
        System.setProperty( DBUNIT_CONNECTION_URL, 'jdbc:h2:~/measter_free1b_db;AUTO_SERVER=TRUE' )
        System.setProperty( DBUNIT_USERNAME, "sa" )
        System.setProperty( DBUNIT_PASSWORD, "5150" )
    }

    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("data/test.xml"));
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE;
    }

    void testSandbox() {
        def jdbcConnection = getConnection().getConnection()
        def statement = jdbcConnection.createStatement()

        def sql = "SELECT * FROM US_STATES WHERE US_STATE_CD LIKE 'Z%'"

        // test 
        def resultset = statement.executeQuery(sql)

        // TODO: fix this
        def count = 0

        while (resultset.next()) {
            count++
        }

        assertEquals(4, count)

        statement.close()
    }
}
