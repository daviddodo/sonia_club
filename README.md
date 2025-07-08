# Contextual information for the project

## The assignment
This project, as part of a deliverable for the masters course MTI825 at École de Technologie Supérieure, consists of applying Lean Six Management to identify and eliminate waste.

## The client : SONIA
SONIA is a scientific student club à École de Technologie Supérieure (ÉTS) that specializes constructing autonomous submarines. [Visit their website](https://sonia.etsmtl.ca/) for more information on their ongoing projects and their team.

## The context
To fund their project activities, the scientific club depends on investments. A part of it originates from ÉTS, but a bigger percentage of it comes from external organisations, referred to as sponsors. A sponsor's contribution can be of various types outside of monetary, such as the following:
- Material, like parts or equipements;
- Environment, like lending a swimming pool for tests;
- Service, like verifying a circuit board.

Considering the various types of contributions, SONIA usually quantifies their value in terms of monetary value or time saved in terms of progressing a project. The issue is the informal process within the scientific club for persisting the data using various inadapted tools: various Excel files, emails or simply a club reps remembering in their own head.

## The challenge
Due to the simple nature of the information, it is easy to default to using simple and accessible tools such as the three mentioned previously. However, that informal process becomes problematic as the amount of informations increases and the tools cannot scale with the club's needs. As a result, it becomes difficult to find past contributions to quantify their value.

Moreover, some sponsors demand some follow ups or feedback regarding a contribution that they have made, yet it often happens that their contact information is very difficult to find or even lost as it cannot be found within the many tools. With delayed or incomplete follow ups, partnership renewals may not be renewed.

All in all, _Extra Processing_ was identified as the main waste at the heart of the issue. Having to move back and forth between Excel files, emails and club reps to find contribution or contact information adds unnecessary steps for what should ideally be an easy information query. The additional processes also lead to other wastes like _Waiting_, _Inventory_ and _Motion_.

## The solution
To help mitigate the _Extra Processing_ waste, a prototype was devised to tackle the issue at its base. The idea is to standardize the process for recording a contribution and contact information for a sponsor, all while facilitating the information research. Making a tool that becomes the single source of information for SONIA would allow the club reps to prevent having to go through extra steps of checking through many tools and people, with the potential outcome of not finding the desired information.

And so, the following solution was created : a simple Spring Boot API that allows its users to record information in a PostgreSQL database. Considering that this is a prototype, there is no frontend app, nor any rigorous testing nor complete functionalities. The basics for recording information was prioritized, with the idea that the scientific club would take the effort in tailoring the solution as they need to.

# The project
spring boot app blabla
postgresql
docker containers
## Environment variables
For simplicity, a _.env_ file should be created at the root of the project for the proper connection from the Spring Boot app to the container with a PostgreSQL database. The following variables are required, which are used in the _docker-compose.yml_ file:
- POSTGRES_DB: name of the database;
- POSTGRES_USER: preferred username for the database, useful for connecting;
- POSTGRES_PASSWORD: preferred password;
- SPRING_DATASOURCE_URL: database URL to allow the Spring Boot app to connect to the database, should use the following format: *jdbc:postgresql://`host`:`port`/`db_name`*
  - host: referring to _docker-compose.yml_, it should be named *postgres*;
  - port: the value is usually *5432*;
  - db_name: the value should match POSTGRES_DB.