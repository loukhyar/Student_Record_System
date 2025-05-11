# Student Record Management System (Java + MySQL)

## 📖 Project Overview
The Student Record Management System is a Java-based desktop application that integrates with a MySQL database to efficiently handle student data. It provides a clean command-line interface for performing essential operations such as adding, updating, viewing, and deleting student records. The system leverages JDBC (Java Database Connectivity) to ensure seamless communication with the MySQL backend, making it a robust tool for learning database-driven application development in Java.


## 🚀 Features

- Add new student records
- Update existing records
- Delete student records
- Display all student records
- Uses JDBC to connect to a MySQL database

## 🛠️ Technologies Used

- Java (Core + JDBC)
- MySQL
- MySQL Connector/J (JDBC Driver)

## 💾 MySQL Table Setup

Run this SQL script in your MySQL database:

```sql
CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    grade FLOAT
);
```
## 🔧 Configuration
Inside your StudentRecordSystem.java, make sure to set:

java
String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
String DB_USER = "your_mysql_username";
String DB_PASSWORD = "your_mysql_password";
## 📦 How to Compile and Run
Place the JDBC JAR in a lib folder.

Compile and run with:

bash
javac -cp .;lib/mysql-connector-j-9.3.0.jar src/StudentRecordSystem.java
java -cp .;lib/mysql-connector-j-9.3.0.jar src.StudentRecordSystem
Note: On macOS/Linux use : instead of ; in the -cp (classpath).

## 📁 Project Structure
StudentRecordSystem/
├── lib/
│   └── mysql-connector-j-9.3.0.jar
├── src/
│   └── StudentRecordSystem.java
├── README.md
└── .gitignore
## 📚 License
This project is open-source and intended for educational and personal use. You are free to study, modify, and distribute the code with proper attribution. Commercial use is not permitted without prior permission.
