
package net.codetojoy;

import java.sql.*;
import java.util.*;
import java.io.*;

import org.dbunit.*;
import org.dbunit.dataset.*;
import org.dbunit.dataset.xml.*;
import org.dbunit.operation.*;

import static org.dbunit.PropertiesBasedJdbcDatabaseTester.*;

public class MyTestCase extends DBTestCase {
    public MyTestCase() {
        super();
        System.setProperty( DBUNIT_DRIVER_CLASS, "org.h2.Driver" );
        System.setProperty( DBUNIT_CONNECTION_URL, "jdbc:h2:~/measter_free1b_db;AUTO_SERVER=TRUE" );
        System.setProperty( DBUNIT_USERNAME, "sa" );
        System.setProperty( DBUNIT_PASSWORD, "5150" );
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

    public void testSandbox() throws Exception {
        Connection connection = getConnection().getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM US_STATES WHERE US_STATE_CD LIKE 'Z%'";

        // test 
        ResultSet resultset = statement.executeQuery(sql);

        // TODO: fix this
        int count = 0;

        while (resultset.next()) {
            count++;
        }

        assertEquals(4, count);

        statement.close();
    }
}
