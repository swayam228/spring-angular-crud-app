# Spring Angular CRUD App

This is a full-stack CRUD (Create, Read, Update, Delete) application built using **Spring Boot** for the backend and **Angular** for the frontend.

---

## 🚀 Technologies Used

- **Backend:** Java, Spring Boot, Spring Data JPA, MySQL
- **Frontend:** Angular, Bootstrap
- **Database:** MySQL

---

## 📁 Project Structure
spring-angular-crud-app/
├── backend/ → Spring Boot application
└── frontend/ → Angular frontend


---

## 🔧 How to Run the App

### 🖥️ Backend (Spring Boot)

1. Navigate to the `backend` folder.
2. Configure your Postgres DB in `application.properties` as EmployeeDB.
3. Run the application using:

```bash
./mvnw spring-boot:run

Frontend (Angular)
Navigate to the frontend/employee-crud-frontend folder.

Install dependencies:
npm install
ng serve

✍️ Author
Swayam Singh

6. Click **“Commit new file”** ✅

---

### ✅ Step 2: Add `.gitignore` (if not already)

Same method as above:

1. Click **"Add file" → "Create new file"**
2. Name it `.gitignore`
3. Paste this content:

```gitignore
# Java
target/
*.class

# Maven
.mvn/
!**/src/main/**
!**/src/test/**

# Logs
*.log

# Environment files
*.env

# Angular
node_modules/
dist/
.angular/

# IDEs
.idea/
.vscode/
.DS_Store

