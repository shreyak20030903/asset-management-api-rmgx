# Asset Management API – RMgX Assignment

This is a Spring Boot-based RESTful API for managing company assets, asset categories, and employee assignments.  
This project is developed as part of the **RMgX Backend Development Intern Assignment**.

---

##  Features

- ✅ Add, update, delete assets  
- ✅ Assign asset to employee  
- ✅ Recover asset from employee  
- ✅ Add and update asset categories  
- ✅ Add and list employees  
- ✅ Search assets by name  
- ✅ File-based H2 database with persistent storage  

---

##  Tech Stack

- Java 11  
- Spring Boot 2.4  
- Spring Data JPA  
- H2 Database  
- Maven  
- Postman (for API testing)

---

## 📂 Project Structure

```

src/
├── controller/        # REST Controllers
├── entity/            # JPA Entities
├── repository/        # Spring Data JPA Repositories
├── service/           # Business Logic Layer
└── AssetManagementApplication.java  # Main class

````

---

##  Installation & Run

### Prerequisites
- Java 11+
- Maven

### Run Locally

```bash
git clone https://github.com/shreyak20030903/asset-management-api-rmgx.git
cd asset-management-api-rmgx
mvn spring-boot:run
````

---

##  H2 Console

* URL: `http://localhost:9090/h2-console`
* JDBC URL: `jdbc:h2:file:./data/assetdb`
* Username: `sa`
* Password: *(leave blank)*

---

## 📡 API Endpoints

### 🔹 Assets

| Operation             | Method | Endpoint                         |
| --------------------- | ------ | -------------------------------- |
| Add Asset             | POST   | `/api/assets`                    |
| Get All Assets        | GET    | `/api/assets`                    |
| Get Asset by ID       | GET    | `/api/assets/{id}`               |
| Update Asset          | PUT    | `/api/assets/{id}`               |
| Delete Asset          | DELETE | `/api/assets/{id}`               |
| Search Assets by Name | GET    | `/api/assets/search?name={name}` |

> Note: Asset cannot be deleted if it is currently assigned to an employee.

### 🔹 Assign & Recover

| Operation                | Method | Endpoint                                    |
| ------------------------ | ------ | ------------------------------------------- |
| Assign Asset to Employee | PUT    | `/api/assets/{assetId}/assign/{employeeId}` |
| Recover Asset            | PUT    | `/api/assets/{assetId}/recover`             |

### 🔹 Categories

| Operation          | Method | Endpoint               |
| ------------------ | ------ | ---------------------- |
| Add Category       | POST   | `/api/categories`      |
| Get All Categories | GET    | `/api/categories`      |
| Update Category    | PUT    | `/api/categories/{id}` |

### 🔹 Employees

| Operation         | Method | Endpoint         |
| ----------------- | ------ | ---------------- |
| Add Employee      | POST   | `/api/employees` |
| Get All Employees | GET    | `/api/employees` |

---
