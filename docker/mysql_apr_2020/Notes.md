
### get image

* `docker pull mysql`

### start database container

* `./run_first_time.sh`

### create database

* `docker exec -it sandbox2-mysql bash`
    - `mysql -u mozart -p`
        - enter password at prompt
    - `use foo;`
    - create:
```
CREATE TABLE employee ( id smallint unsigned not null, name varchar(256) not null, constraint pk_employee primary key (id) );
INSERT INTO employee ( id, name ) VALUES ( 20, 'Bach' );
INSERT INTO employee ( id, name ) VALUES ( 30, 'Chopin' );
INSERT INTO employee ( id, name ) VALUES ( 40, 'Mozart' );
```

### Resources

* [1] - https://www.a2hosting.ca/kb/developer-corner/mysql/managing-mysql-databases-and-users-from-the-command-line
* [2] - https://dev.to/sandrogiacom/run-mysql-on-docker-and-use-in-your-java-app-jpn
