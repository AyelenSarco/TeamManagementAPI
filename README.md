# TeamManagementAPI
A REST API built with Spring Boot, designed to manage teams, people, memberships, and profiles.
It demonstrates solid backend practices such as:

- Layered architecture (Controller → Service → Repository → Entity)
- DTOs and request/response validation
- MySQL integration
- MySQL integration using the ORM JPA/Hibernate with relationships (1–n, n–1, 1–1, n–n)
- Custom exception handling

# Domain
- Each person has a single profile with detailed information.
- A person can have multiple contacts: phone number, alternative email, LinkedIn, etc. 
- People can belong to multiple teams; teams have multiple members. 
- Memberships consists on the role in  the team, the join date, and whether it is active are stored.

# Features
- Create and manage People
- Create and manage Teams
- Add or remove Team Members with roles
- Assign or remove Memberships 
- Full CRUD operations for all main resources
- Proper error handling with custom exceptions
- MySQL persistence with relationships such as:
    - Many-to-Many (Team → Person)
    - Many-to-One (Contact → Person)
    - One-to-One (Person → Profile, if needed)
 
# Tech stack
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Lombok

# Configuration
- Environments variables are loaded from .env
- There are examples files in the repo you can follow: `.env.example` and `application-example.properties`

# Future improvements
- Pagination and filters
- Add Docker support
- Logging
- Authentication and Authorization

# Postman collection
- A Postman collection with example requests is included in the repository.
- You can import it directly from: postman/TeamManagerAPI.postman_collection.json

