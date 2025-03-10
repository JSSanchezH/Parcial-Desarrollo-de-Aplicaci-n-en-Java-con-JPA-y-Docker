# Partial - Java Application Development with JPA and Docker

## 📌 Project Description

This project is a Java application developed using **Spring Boot** and **JPA**, with a minimum of 5 entities properly mapped with relationships in the database. The application allows full CRUD operations through a `JpaRepository`. Additionally, the database is configured using **Docker Compose**, with **MySQL** as the database engine and **Adminer** for graphical database management.

## 📌 Technical Requirements

### 🔹 1. Entities and JPA Mapping:

- At least 5 entities with properly defined relationships.
- Usage of annotations like `@OneToMany`, `@ManyToOne`, `@ManyToMany`, `@OneToOne`.
- Proper configuration of `@JoinColumn`.

### 🔹 2. Database:

- Database engine: **MySQL**.
- Connection configuration via `application.properties` or `application.yml`.
- The application should connect to the database using port 5500.

### 🔹 3. CRUD with `JpaRepository`:

- Implement **Create**, **Read**, **Update**, and **Delete** (CRUD) operations for each entity.
- Use **Spring Data JPA** with the `JpaRepository` interface.

### 🔹 4. Using Docker Compose:

- Configure the database within a **Docker Compose**.
- Include **Adminer** in the `docker-compose.yml` file and expose it on port 8000.
- The Java application should run on port 4500.

### 🔹 5. Documentation and Code:

- The code should be well-structured and commented.
- Include a `README.md` with clear instructions on how to run the application.

## 📌 Instructions to Run the Project

### 1. Clone the repository

First, clone the project repository and navigate to the project directory:

git clone https://github.com/JSSanchezH/Parcial-Desarrollo-de-Aplicaci-n-en-Java-con-JPA-y-Docker
cd Parcial-Desarrollo-de-Aplicaci-n-en-Java-con-JPA-y-Docker

### 2. Configure the database

# application.properties

spring.datasource.url=jdbc:mysql://localhost:5500/your_database_name # Change 'your_database_name' to the actual name of your database
spring.datasource.username=your_username # Replace 'your_username' with your MySQL username
spring.datasource.password=your_password # Replace 'your_password' with the appropriate password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### 3. Start Docker containers

To start the Docker containers (including MySQL and Adminer), run the following command in the root directory of the project:

docker-compose up -d

### 4. Access Adminer

http://localhost:8000
