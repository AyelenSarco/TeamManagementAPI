# TeamManagementAPI
A REST API built with Spring Boot, designed to manage teams, members, roles, and profiles.
It demonstrates solid backend practices such as:

- Layered architecture (Controller → Service → Repository → Entity)
- DTOs and request/response validation
- MySQL integration
- JPA/Hibernate relationships (1–n, n–1, 1–1, n–n)
- Custom exception handling

# Features
- Create and manage Persons
- Create and manage Teams
- Add or remove Team Members with roles
- Manage team assignments using a clear and robust domain
- Full CRUD operations for all main resources
- Proper error handling with custom exceptions
- MySQL persistence with relationships such as:
    - One-to-Many (Team → Members)
    - Many-to-One (Member → Person)
    - One-to-One (Profiles, if needed)
 
# Tech stack
- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Lombok

# Configuration
- Enviroments variables are loaded from .env
- There are Examples files of in the repo: .env.example and application-example.properties

# Future improvements
- Pagination and filters
- Docker
