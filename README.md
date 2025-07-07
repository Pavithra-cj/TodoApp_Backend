# 🛠️ Todo App – Backend (Spring Boot + MySQL)

This is the backend service for the Todo Application. It is built with **Java, Spring Boot, MySQL**, and uses **Docker** for containerization.

> 🔗 **Frontend Repo**: [https://github.com/Pavithra-cj/Todo-App-Frontend]

---

## 📦 Features

- Create, update, complete, and delete tasks
- Support for:
  - ✅ Priority (LOW, MEDIUM, HIGH)
  - ✅ Due date
  - ✅ Completed state
- RESTful API with Swagger documentation
- Dockerized for easy setup
- MySQL as database

---

## 🚀 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/Pavithra-cj/TodoApp_Backend.git
cd todo-backend
```

### 2. Run using Docker
- Make sure Docker is installed and running.

```bash
docker-compose up --build
```

## 📌 This will start:

 - Spring Boot backend on http://localhost:8080
 - MySQL (containerized)

### 3. API Docs (Swagger UI)

```bash
http://localhost:8080/swagger-ui.html
```

## ⚙️ Profiles and Configuration
| Profile | Purpose                                                  |
|---------|----------------------------------------------------------|
| `dev`   | Local development (connects to localhost MySQL)         |
| `docker`| Runs inside Docker (connects to MySQL container)        |

- The app uses Spring profiles with separate configuration files:
  - application-dev.properties
  - application-docker.properties

## 📂 Folder Structure

```matlabsrc/
├── main/
│   ├── java/
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-docker.properties
```

## 🔗 Related
- Frontend Repo: [https://github.com/Pavithra-cj/Todo-App-Frontend]
