
* see images for screenshots

### JDBC driver

* `run_v2.sh` maps `./data` to `/tmp/data` in container
* in container, copied mysql jar to 
    - `/opt/IBM/WebSphere/AppServer/profiles/AppSrv01/config/cells/DefaultCell01/nodes/DefaultNode01/servers/server1/WorklightAdmin/mysql`
* set 'classpath' as above, entire long path to driver jar file
* set 'implementation class name' to `com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource`

### Data Source

* set 'Provider' to `mysql`
* set 'Name' to `foo`
* set JNDI name to `WorklightAdminDS` per [1]
* also per [1], set these for custom properties
    - portNumber: 3306
    - relaxAutoCommit: true
    - databaseName: true
    - serverName: host.docker.internal
    - user: mozart
    - password: 1756 

### Resources

[1] - https://www.ibm.com/support/knowledgecenter/SSHSCD_6.3.0/com.ibm.worklight.installconfig.doc/admin/t_configuring_websphere_application_server_for_my_sql_manually_for_wladmin.html
