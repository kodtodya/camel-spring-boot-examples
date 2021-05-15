# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel, Spring Boot along with Postgres database.

### Building

The example can be built with

    mvn clean install

###Check Hawtio

All the resource utilization can be monitored using Hawtio

    http://localhost:8081/hawtio


### Check Database

Connect to PostgreSQL database and check your records

    - open PGAdmin
    - Configure the local database in it
    - Fill up details for connection as below:
        - Host name/Address: 0.0.0.0
        - Post: 5432
        - Database-Name: training-db
        - Username: postgres
        - Password: postgres


** If `training-db` is not available inside the postgres database, please create it using below path in PGAdmin.

    servers -> select your db server -> Databases -> right-click -> create -> Database -> `training-db`

** once table is created, pls open it in PGAdmin using below path:

    servers -> select your db server -> Databases -> training-db -> schemas -> public -> Tables -> Organizations

** once you find the table, then:

    right-click on table -> Query Tool

** Use below query to search inserted records:

    SELECT * FROM organizations;
