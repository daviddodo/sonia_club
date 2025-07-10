# Lean Six Management Project for an IT System

This project, as part of a deliverable for the masters course MTI825 at École de Technologie Supérieure, consists of applying Lean Six Management to identify and eliminate waste.

## Table of Contents
- [Overview](#overview)
  - [Backend tools](#backend-tools)
  - [Tools](#tools)
  - [Environment variables](#environment-variables)
- [Contextual information for the project](#context)
  - [The assignment](#assignment)
  - [The client : SONIA](#client)
  - [The challenge](#challenge)
  - [The solution](#solution)

## Overview
### Backend tools
- **Java 17** - Runtime environment for the application;
- **Spring Boot** - Main framework used for building the API and managing application configuration;
- **Lombok** - Reduces boilerplate code by auto-generating getters, setters and constructors;
- **JPA (Jakarta)** - ORM specification used to map Java classes to database tables;
- **PostgreSQL** - Relational database used to store application data.

**Note** : there is currently no frontend. API interaction is handled via Postman using the request file in /docs.

### Tools
- Docker - Containerization platform used to package the app and its dependencies;
- Docker Compose - Used to orchestrate and run the app and database together;
- Postman - API client used to send HTTP requests for testing and interacting with the backend.

### Running the app
#### Prerequisites
Before running the app, ensure the following tools are installed:
- **Docker Desktop**, which includes Docker Engine and Docker Compose:
  - [Mac installation guide](https://docs.docker.com/desktop/setup/install/mac-install/);
  - [Windows installation guide](https://docs.docker.com/desktop/setup/install/windows-install/);
- [**Postman**](https://www.postman.com/downloads/) - for testing API requests;

#### Setup instructions:
1. **Clone the project** to your machine;
2. **Open the project** with your preferred IDE (I used Visual Studio Code);
3. **Create a `.env`** file at the root of the project and define the following variables:
  ```env
  POSTGRES_DB=your_database_name
  POSTGRES_USER=your_username
  POSTGRES_PASSWORD=your_password
  SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<db_name>
  ```
  - `host` should match the service name in docker-compose.yml (postgres);
  - `port` is usually 5432;
  - `db_name` should match the value assigned to POSTGRES_DB.
4. Open a terminal within your IDE (Ctrl+` for Visual studio code);
    - Ensure that the terminal points at the root of the project;
5. Run `docker compose up --build`;
    - Wait for the project to build: if you look at the logs carefully, you should be able to spot *`Tomcat started on port 8080 (http) with context path '/'`*;
    - Optional:
      - Open Docker Desktop and verify that the Compose stack `sonia_club` has containers `postgres_sponsors` and `sponsors_app`;
      - Verify that [localhost](http://localhost:8080) displays the message *`Welcome to Sonia!`*
6. Open Postman and import [the Postman requests collection](docs/Sonia.postman_collection.json) into Postman;
7. Use the requests by following the order of the numbered collections.
    - Start by creating sponsors and interact with them;
    - Proceed with creating other entities by following the numbered collections.


# Contextual information for the project
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