# CASHIER APP
This project focuses on developing a grocery shopping system using Spring Boot, utilizing a microservices architecture.
## **Table of Contents**
* [About the Project](#about-the-project)
* [Used Technologies](#used-technologies)
* [Important Points](#important-points)
* [Desing of Database](#design-of-database)
* [Microservice Architecture](#microservice-architecture)
   - [API Gateway](#api-gateway)
   - [Discovery Server](#discovery-server)
   - [Authentication Service](#authentication-service)
   - [Product Service](#product-service)
   - [Report Service](#report-service)
   - [Sale Service](#sale-service)
   - [User Management Service](#user-management-service)
* [Unit Tests](#unit-tests)
* [Monitoring](#monitoring)
   - [Prometheus](#prometheus)
   - [Grafana](#grafana)
   - [Zipkin](#zipkin)
* [Continuous Integration](#continuous-integration)
   - [Jenkins](#jenkins)
* [How to Run](#how-to-run)
   - [Running Locally](#running-locally)
   - [Running with Docker](#running-with-docker)
* [Future Improvements/Roadmap](#future-improvementsroadmap)
* [Contact](#contact)

## **About the Project**
This project is based on a general grocery shopping system. This system consists of different roles and operations based on these roles. There are 3 different role definitions in the system. Admin, store manager and cashier. The store manager is responsible for reporting, the cashier is responsible for shopping transactions, and the admin is responsible for operations such as adding and deleting roles. There are 5 different service which have different functionalities to provide users to be able to do their jobs.

In summary, the project, which contains different roles and different transaction permissions for these roles, is a market project developed with spring boot framework and microservice architecture.

## **Used Technologies**
* JDK17
* Spring Boot 2.7.18
   - Spring Web
   - Spring Data JPA
* Maven 4.0.0
* PostgreSQL
* Lombok
* Log4j2
* JUnit5
* Jaspersoft
* Prometheus & Grafana
* Jenkins
* Docker
* Spring Cloud (Api-Gateway, Eureka, Zipkin)

## **Important Points**
* Using Spring Boot Framework
* N-tier Architecture
* Object Oriented Programming
* Compatible with SOLID principles
* Token based authentication and authorization
* Logging
* Unit Tests
* Java Doc
* Microservice Architecture and Dockerization

## **Design of Database**
- User and Role Tables:
  - There are 3 roles in the system and there can be more than one user. A user can have one or more roles, and a role can belong to more than one user. Therefore, there must be a many-to-many relationships between these tables. While the users table keeps users' name, email, password, activeness and username information, the roles table only keeps role name information.
- Sale, Products, Categories, Campaigns and Sold_Product Tables:
  - We will examine the remaining 5 tables here. First table is sale table. Sale table holds cashier name, change amount, payment type, received amount, total amount, sale time and time information of sales while sold_product table holds quantity, campaign id, product id and sale id information. In one sale there can be more than one sold product. For this reason we are establishing a one-to-many relationship between sale and sold_product table.
  - Product table holds barcode, brand, expiration date, image, description, name, price, stock and category id while campaings table holds description, name and category id information. There can be more than one sold  product which belongs to one product or one campaign. So we are establishing again a one-to-many relationship between products & sold_product and campaigns & sold_product tables.
  - Last table is categories table. Categories table holds description and name information. Because of a category can contain multiple different products and multiple different campaigns we are establishing a one-to-many relationship between categories & products and categories & campaigns tables.

     ![Ekran görüntüsü 2024-06-20 185119](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/063bdc05-c1dc-4933-800e-687d0ea82837)

## **Microservice Architecture**

![Ekran görüntüsü 2024-06-19 191759](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/d4c1449c-6657-436d-9610-9ad0a69f67ca)

### API Gateway

An API Gateway is a server that acts as an entry point for external clients to access microservices. It is responsible for routing requests, aggregating responses, and often includes functionalities such as authentication, authorization, load balancing, rate limiting, and monitoring. Essentially, the API Gateway provides a unified interface for clients to interact with a complex microservices architecture, simplifying client-side code and managing cross-cutting concerns.

### Discovery Server

A Discovery Server, also known as a Service Registry, is a centralized server that maintains a registry of all the microservices in the application. Each microservice registers itself with the Discovery Server at startup, providing its network location (IP address and port). The Discovery Server enables service discovery, allowing microservices to find and communicate with each other without hard-coding network locations, thus facilitating dynamic scalability and resilience.

### Authentication Service

The Authentication Service is the central component where security transactions are performed. When a user logs in, the service verifies the user's credentials to ensure they are correct. The authentication and authorization structure is built using the JWT (JSON Web Token) library.

Upon successful login, a unique token is generated for the user. This token has a validity period, allowing the user to make requests to protected endpoints as long as the token remains unexpired. Additionally, the Authentication Service is responsible for embedding user roles within the token, which are then checked during role-based access control in the API Gateway.

### Product Service

This service is used to list the products in the system. No authorization is required for this service. It handles operations related to products, including listing, adding, and deleting products. Essentially, it coordinates all activities related to products.

### Report Service

This service lists the sales transactions. It should also be able to regenerate the receipt for any specific sale as a PDF. This service requires the role of a store manager. Jaspersoft studio is used to generate reports.

![Ekran görüntüsü 2024-07-01 030604](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/a3caadc9-93f3-44b6-97c2-73ce19245d42)

### Sale Service

Sold products, total amount, and applied promotions should be recorded in the database by the sale service. Sales transactions can be conducted. The cashier role is required to perform these operations.

### User Management Service

There are three roles defined in the system: Cashier, Store Manager, and Admin. Roles are fixed, and a person can have one or multiple roles. It is mandatory for each person to have at least one role. So in this service there are operations for adding, updating, and deleting users. For deletions, a "soft delete" approach are applied. Only users with the Admin role can access the user management services.

## **Unit Tests**


A unit test is a type of software testing where individual units or components of a software are tested. The purpose is to validate that each unit of the software performs as expected. Unit tests are implemented for all services of the project using the JUnit5 library. To make the tests effective, an abstract fixture class and other fixture classes extending this abstract class are created. In these fixture classes, test data are generated using the GitHub Faker library. Consequently, all test data are generated randomly and falsely. This approach aims to make test classes less complex and more readable, and generate test data in a more centralized and organized manner.

![Ekran görüntüsü 2024-06-30 033113](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/0c40d3e6-e2c6-4239-b84d-1abff1d4ae73)

## **Monitoring**

### Prometheus

Prometheus is an open-source monitoring and alerting toolkit originally built at SoundCloud. It is designed for reliability, scalability, and easy deployment, making it a popular choice for monitoring microservices architectures. Prometheus is used in this project for comprehensive monitoring by collecting detailed metrics from each microservice. 


![Ekran görüntüsü 2024-06-23 010428](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/8012d43f-0d94-48e1-95fc-0fad427050f2)

### Grafana

Grafana is an open-source analytics and visualization platform that integrates with various data sources, including Prometheus. It provides a web-based interface for creating, exploring, and sharing dashboards with interactive charts and graphs. Grafana is used in this project to effectively visualize the metrics collected by Prometheus, providing real-time insights into the status of my microservices and to create custom dashboards that help track key performance indicators and quickly identify any issues.


![Ekran görüntüsü 2024-06-23 010633](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/71004281-53db-42de-85d1-9639fb5715b9)

### Zipkin

Zipkin, an open-source distributed tracing system, tracks requests across microservices, offering insights into performance and dependencies. Zipkin is used in this project to track and analyze the flow of requests across various microservices in order to pinpoint performance issues and improve overall system reliability and performance.


![Ekran görüntüsü 2024-06-30 025523](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/2440b564-2401-48e8-8ab6-40e8366f103a)

## **Continuous Integration**

### Jenkins

Jenkins is an open-source automation server widely used for continuous integration (CI) and continuous delivery (CD) pipelines. It automates the process of building, testing, and deploying software projects, making it easier for development teams to collaborate and deliver code changes more efficiently. Jenkins is used in this project to reduce manual errors and accelerate the release cycle. Jenkins’ extensive plugin ecosystem allows for integration with various tools and technologies, enhancing its flexibility and capability.


  ![Ekran görüntüsü 2024-06-19 183532](https://github.com/ahmetalicc/kasiyerapp/assets/117573659/cd3a3ea0-b5d5-40b9-b413-d6321b563e0f)

## **How to Run**
### Running Locally
**Prerequisites:**

- Ensure you have Java JDK 17 installed.
- Ensure you have Maven installed.
- Ensure you have PostgreSQL installed and running.

**Clone the Repository:**

```bash
git clone https://github.com/ahmetalicc/kasiyerapp.git
cd kasiyerapp
```

**Set Up Database and Environment Variables:**

  - Create a PostgreSQL database with a name of your choice.
  - Create a .env file in each micro service of the project and add the necessary environment variables. Refer to the .env.example file for the required variables. Make sure to update the database credentials and name according to your setup. 

**Build the Project:**

In the root directory of the project, run:

`mvn clean install`

**Run the Application:**

`mvn spring-boot:run`

### Running with Docker
**Prerequisites:**

- Ensure you have Docker installed.
- Ensure you have Docker Compose installed.

**Set Up Database and Environment Variables:**

  - Create a PostgreSQL database with a name of your choice.
  - Create a .env file in each micro service of the project and add the necessary environment variables. Refer to the .env.example file for the required variables. Make sure to update the database credentials and name according to your setup.

**Build Jar Files and Docker Images for Each Service:**

In the root directory of the project, run:

`mvn clean install`

```bash
cd service1
docker build -t service1 .
cd ..
```
Repeat the above steps for each service (service2, service3, ..., api-gateway, discovery-server).

**Build and Run with Docker Compose:**

Make sure your docker-compose.yml file includes the definitions for all your services. Then run:

`docker-compose up --build`

**Stop the Running Application:**

`docker-compose down`

## **Future Improvements/Roadmap**

The following improvements and features are planned for future releases to enhance the functionality, performance, and user experience of the project:

### **New Features**

- **User Notification System**
  - Implement an email and SMS notification system to keep users informed about important events such as low stock alerts, sales promotions, and system updates.

### **Performance Enhancements**

- **Database Optimization**
  - Refactor database schema and optimize queries to improve performance, especially for high-traffic scenarios.

- **Caching**
  - Implement caching mechanisms to reduce database load and improve response times for frequently accessed data.

### **Security Enhancements**

- **Audit Logging**
  - Enhance the logging mechanism to include detailed audit logs for critical operations, helping to track changes and ensure compliance with security policies.

### **Scalability Improvements**

- **Microservice Scaling**
  - Implement auto-scaling for microservices using Kubernetes to ensure the system can handle increased load efficiently.

- **Load Balancing**
  - Enhance load balancing strategies to distribute traffic more effectively across microservices.

### **Package Structuring**

- **Modularization**
  - Organize packages into modules to reduce redundancy and improve maintainability across services.

- **Centralization of Core Packages**
  - Centralize common packages such as core response handling and aspect logging to ensure consistency and reusability across all services.

### **Developer Experience**

- **Improved Documentation**
  - Expand the documentation to include more detailed setup guides, API documentation, and best practices for contributors.

### **Monitoring and Observability**

- **Advanced Monitoring**
  - Integrate additional monitoring tools such as ELK Stack (Elasticsearch, Logstash, Kibana) for more comprehensive log analysis and visualization.

## **Contact**

This project is developed by Ahmet Alıç. Contact for the missing parts, different ideas, questions, and suggestions. 
(ahmetalicswe@gmail.com)

