
package net.codetojoy.db;

import java.sql.*;

public class DatabaseInfo implements AutoCloseable {
    private ResultSet resultSet = null;
    private Statement statement = null;
    private Connection connection = null;
    private boolean doDisconnect = false;

    public DatabaseInfo() {
    }

    public DatabaseInfo(ResultSet resultSet, Statement statement, 
                        Connection connection, boolean doDisconnect) {
        this.resultSet = resultSet;
        this.statement = statement;
        this.connection = connection;
        this.doDisconnect = doDisconnect;
    }

    @Override 
    public void close() {
        try { 
            if (resultSet != null) { resultSet.close(); }
        } catch (Exception ex) {
        } 
        try { 
            if (statement != null) { statement.close(); }
        } catch (Exception ex) {
        } 
        try { 
            if (doDisconnect && (connection != null)) { connection.close(); }
        } catch (Exception ex) {
        } 
    }
}
