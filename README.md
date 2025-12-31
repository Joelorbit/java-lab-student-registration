# 🎓 Student Registration servlet web-app.

A modern, full-stack Java Web Application built with **Servlets**, **JDBC**, and **MySQL**. This project adheres to the **MVC** architecture to manage student registration and data persistence.

<img width="1690" height="878" alt="image" src="https://github.com/user-attachments/assets/7f9a3bd4-e3ca-46cf-935d-feca1a1121bb" />

<img width="1623" height="841" alt="image" src="https://github.com/user-attachments/assets/914cbe4e-dd01-449f-831b-878b5945fb4c" />



## 🚀 How to Run the Project

### 1. Database Setup

Before running the application, you must set up the database.

1. Open **MySQL Workbench** or your terminal.
2. Run the following script:

```sql
CREATE DATABASE student_registration;
USE student_registration;

CREATE TABLE students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    year INT NOT NULL
);

```

### 2. Configure Database Credentials

You must link the code to your local MySQL user.

1. In NetBeans, open `DBConnection.java`.
2. Locate the following lines and change them to match your MySQL setup:

- **USER**: Change `"root"` to your username.
- **PASS**: Change `"Joelget@4"` to your actual MySQL password.

### 3. Fixing Maven Build Errors (Important!)

We updated the `pom.xml` to fix a common "API Incompatibility" error caused by using modern JDKs (like JDK 17/23).

- The `maven-war-plugin` version was changed to **3.3.2**.
- This ensures the project compiles correctly on modern systems.

### 4. Running in NetBeans

1. **Clean and Build**: Right-click the project name in the "Projects" tab and select **Clean and Build**.
2. **Run**: Right-click the project and select **Run**.
3. **Server Selection**: Select your **Apache Tomcat** or **GlassFish** server when prompted.
4. **Browser**: The app will automatically open at `http://localhost:8080/student-registration/`.

---

## 🛠️ Key Features

- **Duplicate Prevention**: The system checks the database for existing emails before saving, preventing primary key/unique constraint crashes.
- **Modern UI**: Designed with a **shadcn/ui** aesthetic using high-quality system fonts and minimalist components.
- **Offline Ready**: All CSS is inline; no external internet connection (like Tailwind CDN or Google Fonts) is required.
- **Auto-Redirect**: Upon successful registration, the user is immediately taken to the student list.
- **Delete-students**: On the show all tab, the user can remove registered students.

---

## 📁 Project Structure

- `index.html`: The modern registration form.
- `RegisterServlet.java`: Handles `POST /register` (Validation & Insertion).
- `ShowAllServlet.java`: Handles `GET /show_all` (Retrieval & Table Display).
- `DBConnection.java`: Centralized JDBC connection management.
- `DeleteServlet.java`: Handles the removal icon in the show student tab.
- `pom.xml`: Project dependencies (MySQL Connector & Maven Plugins).

---

### 📝 Note for Examiners

If you encounter a `SQLSyntaxErrorException`, please ensure the database `student_registration` has been created. If the page doesn't load, ensure the Tomcat server is started in the NetBeans **Services** tab.

---
