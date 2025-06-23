# sonia_club
S.O.N.I.A. is a scientific student club à École de Technologie Supérieure. This project, as part of a deliverable for the masters course MTI825, is for developing to help members of the club track sponsor and contribution information.

# Environment variables
For simplicity, a _.env_ file should be created at the root of the project for the proper connection from the Spring Boot app to the container with a PostgreSQL database. The following variables are required, which are used in the _docker-compose.yml_ file:
- POSTGRES_DB: name of the database;
- POSTGRES_USER: preferred username for the database, useful for connecting;
- POSTGRES_PASSWORD: preferred password;
- SPRING_DATASOURCE_URL: database URL to allow the Spring Boot app to connect to the database, should use the following format: *jdbc:postgresql://`host`:`port`/`db_name`*
  - host: referring to _docker-compose.yml_, it should be named *postgres*;
  - port: the value is usually *5432*;
  - db_name: the value should match POSTGRES_DB.