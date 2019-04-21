
package net.codetojoy.db;

import java.sql.*;

import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DatabaseInfoTestCase {
    private DatabaseInfo databaseInfo; 
    private ResultSet resultSet;
    private Statement statement;
    private Connection connection;
    private boolean doDisconnect;

    @Test
    public void testClose_basic() {
        // test
        try (DatabaseInfo databaseInfo = new DatabaseInfo()) {
        }
    }

    @Test
    public void testClose_resultSet() throws SQLException {
        resultSet = mock(ResultSet.class);

        // test
        try (DatabaseInfo databaseInfo = new DatabaseInfo(resultSet, statement, 
                                             connection, doDisconnect)) {
        }

        verify(resultSet).close();
    }

    @Test
    public void testClose_statement() throws SQLException {
        statement = mock(Statement.class);

        // test
        try (DatabaseInfo databaseInfo = new DatabaseInfo(resultSet, statement, 
                                             connection, doDisconnect)) {
        }

        verify(statement).close();
    }

    @Test
    public void testClose_connection() throws SQLException {
        connection = mock(Connection.class);
        doDisconnect = true;

        // test
        try (DatabaseInfo databaseInfo = new DatabaseInfo(resultSet, statement, 
                                             connection, doDisconnect)) {
        }

        verify(connection).close();
    }
}
