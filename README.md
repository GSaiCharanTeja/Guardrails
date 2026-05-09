# Guardrails Backend System
Guardrails is a Spring Boot backend system designed to manage posts, comments, likes, virality tracking, bot moderation, and notification scheduling using Redis and MySQL.

The system demonstrates:

* REST API development
* MySQL database integration
* Redis atomic counters
* Background schedulers
* Bot moderation logic
* Virality scoring engine
* Global exception handling
| Technology      | Usage                            |
| --------------- | -------------------------------- |
| Java 17         | Backend Development              |
| Spring Boot     | REST APIs                        |
| Spring Data JPA | Database Operations              |
| MySQL           | Persistent Storage               |
| Redis           | Caching, Counters, Notifications |
| Maven           | Dependency Management            |
| Postman         | API Testing                      |

Users can:

* Create posts
* Store posts in MySQL

Endpoint:

```http
POST /api/posts
```

---

## 2. Comment System

Users and bots can:

* Add comments
* Track nesting depth
* Update virality score

Endpoint:

```http
POST /api/posts/{postId}/comments
```

---

## 3. Virality Engine

Virality score is stored in Redis.

### Score Rules

| Action        | Score |
| ------------- | ----- |
| Human Comment | +50   |
| Bot Comment   | +1    |
| Like          | +20   |

Redis Key Example:

```text
post:1:virality_score
```

---

# Redis Guardrails

## Horizontal Cap

Limits bot replies per post.

Redis Key:

```text
post:{postId}:bot_count
```

If bot replies exceed 100:

```text
Too many bot replies
```

---

## Vertical Cap

Restricts excessive comment nesting.

Condition:

```java
if(depthLevel > 20)
```

Error:

```text
Depth level exceeded
```

---

## Cooldown Protection

Prevents a bot from repeatedly replying to the same human user within cooldown duration.

Redis Key Example:

```text
cooldown:bot_10:human_2
```

Cooldown Duration:

```text
10 minutes
```

---

# Like System

Users can like posts.

Endpoint:

```http
POST /api/posts/{postId}/like
```

Each like increases virality score by:

```text
+20
```

---

# Notification Scheduler

A background scheduler checks post virality every 30 seconds.

If virality score exceeds 100:

* Notification is generated
* Stored in Redis list

Redis List:

```text
notifications
```

Example Notification:

```text
Post 1 is trending with score 120
```

---

# Duplicate Notification Prevention

To avoid repeated notifications:

Redis key:

```text
notification_sent:{postId}
```

is stored after first notification.

---

# Global Exception Handling

Implemented using:

```java
@RestControllerAdvice
```

Returns clean JSON responses.

Example:

```json
{
  "message": "Bot cooldown active",
  "status": 400
}
```

---

# Project Structure

```text
src/main/java
│
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── scheduler
└── exception
```

---

# API Endpoints

Method  Endpoint                     Description 

POST    /api/posts                   Create post 
POST    /api/posts/{postId}/comments Add comment 
POST    /api/posts/{postId}/like     Like post   

## 1. Clone Repository

```bash
git clone <your-github-link>

 2. Configure MySQL

Update:

```properties
application.properties
```

Example:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/guardrails
spring.datasource.username=root
spring.datasource.password=Root
```

---

## 3. Start Redis Server

```bash
redis-server.exe
```

---

## 4. Run Spring Boot Application

Run:

```text
GuardrailsApplication.java

```bash
GET post:1:virality_score
```

## Check Notifications

```bash
LRANGE notifications 0 -1
```

## Clear Redis

```bash
FLUSHALL
```

## Show All Keys

```bash
KEYS *

# Future Improvements

* Docker Compose setup
* Swagger API documentation
* JWT Authentication
* WebSocket notifications
* Kafka event streaming
* Redis Streams
* Kubernetes deployment

---

# Author

Sai Charan Teja Gavidi

Backend Developer | Java | Spring Boot | Redis | MySQL
