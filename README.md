# GraphQL Demo Application

A comprehensive GraphQL API demonstration built with Spring Boot and Netflix DGS (Domain Graph Service) framework, showcasing a problem-solving and knowledge-sharing platform with user authentication, problem management, and solution tracking.

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [GraphQL API](#graphql-api)
- [Project Components](#project-components)
- [Configuration](#configuration)
- [Development](#development)
- [License](#license)

## Overview

This project is a demonstration of a GraphQL-based API for a problem-solving platform. It showcases various GraphQL concepts including queries, mutations, subscriptions, type relationships, interfaces, and custom scalars. The application uses a PostgreSQL database to manage users, problems, solutions, and related data.

The platform allows users to:
- Create and manage user accounts with authentication
- Post problems they need help with
- Provide solutions to problems
- Vote on solutions
- Search problems and solutions
- Subscribe to real-time problem updates

## Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 17 | Programming language |
| **Spring Boot** | 3.5.14 | Application framework |
| **Spring Data JPA** | Latest | Database ORM |
| **Netflix DGS** | 10.0.0 | GraphQL framework |
| **PostgreSQL** | Latest | Database |
| **Gradle** | Latest | Build tool |
| **DataFaker** | 1.7.0 | Fake data generation |
| **BouncyCastle** | 1.68 | Cryptography |
| **PrettyTime** | 5.0.1 | Human-readable date formatting |
| **Apache Commons Lang3** | 3.17.0 | Utility library |

## Project Structure

```
graphqldemo/
├── src/
│   ├── main/
│   │   ├── java/com/keto/graphqldemo/
│   │   │   ├── component/
│   │   │   │   └── problemz/
│   │   │   │       ├── ProblemDataResolver.java      # Problem queries/mutations
│   │   │   │       ├── SolutionDataResolver.java     # Solution operations
│   │   │   │       ├── UserDataResolver.java         # User operations
│   │   │   │       └── ItemSearchDataResolver.java   # Search functionality
│   │   │   ├── datasource/
│   │   │   │   ├── FakeBookDataSource.java           # Sample book data
│   │   │   │   ├── FakeHelloDataSource.java          # Sample hello data
│   │   │   │   ├── FakerDataSourceConfig.java        # Data generation config
│   │   │   │   ├── entity/
│   │   │   │   │   ├── Problemz.java                 # Problem entity
│   │   │   │   │   ├── Solutionz.java                # Solution entity
│   │   │   │   │   ├── Userz.java                    # User entity
│   │   │   │   │   └── UserzToken.java               # Auth token entity
│   │   │   │   └── repository/
│   │   │   │       ├── ProblemzRepository.java
│   │   │   │       ├── SolutionzRepository.java
│   │   │   │       ├── UserzRepository.java
│   │   │   │       └── UserzTokenRepository.java
│   │   │   ├── resolver/
│   │   │   │   ├── FakeBookResolver.java             # Book GraphQL resolver
│   │   │   │   └── FakeHelloResolver.java            # Hello GraphQL resolver
│   │   │   ├── util/
│   │   │   │   └── GraphqlBeanMapper.java            # Data mapping utilities
│   │   │   └── GraphqldemoApplication.java           # Main application class
│   │   └── resources/
│   │       ├── schema/
│   │       │   ├── Hello.graphql                     # Hello schema
│   │       │   ├── book.graphql                      # Book schema
│   │       │   ├── course.graphql                    # Course schema
│   │       │   └── problemz.graphql                  # Problem domain schema
│   │       └── application.yml                       # Application configuration
│   └── test/                                         # Unit and integration tests
├── build.gradle                                      # Gradle build configuration
├── gradlew / gradlew.bat                            # Gradle wrapper scripts
├── settings.gradle                                   # Gradle settings
├── HELP.md                                          # Auto-generated Spring Boot help
└── README.md                                        # This file
```

## Features

### Core Features

✅ **User Management**
- User registration and authentication
- User profile management
- Authentication token generation and validation
- User activation/deactivation

✅ **Problem Management**
- Create new problems with tags
- View problem details
- Get latest problems
- Search problems by keyword
- Real-time subscriptions for new problems

✅ **Solution Management**
- Post solutions to problems
- Categorize solutions (Explanation, Reference)
- Vote on solutions (good/bad)
- Track solution metrics

✅ **Search Functionality**
- Search across problems and solutions
- Filter by keywords
- Implement `SearchableItem` interface for unified search

✅ **GraphQL Features**
- Queries for data retrieval
- Mutations for data modification
- Subscriptions for real-time updates
- Custom scalar types (Date, DateTime, Url, NonNegativeInt)
- Interfaces and type relationships
- Input types for complex mutations

### Demo Features

📚 **Book Management** - Sample GraphQL schema demonstrating nested types and relationships

👋 **Hello World** - Simple GraphQL queries to verify setup

## Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 17** or higher
- **Gradle 8.0** or higher (or use the included gradle wrapper)
- **PostgreSQL 12** or higher
- **Git** (optional, for cloning)

### PostgreSQL Setup

1. Ensure PostgreSQL is running on `localhost:5432`
2. Create a database named `postgres` with schema `problemz`:

```sql
-- Connect to PostgreSQL and create schema
CREATE SCHEMA IF NOT EXISTS problemz;
```

3. Update connection details in `src/main/resources/application.yml` if needed:
   - Default username: `postgres`
   - Default password: `root`

## Getting Started

### 1. Clone the Repository

```bash
cd "i:\Switch 26\SOLID"
git clone <repository-url>
cd graphqldemo
```

### 2. Configure Database Connection

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema=problemz
    username: postgres
    password: root
    jpa:
      hibernate:
        ddl-auto: none
```

Update with your PostgreSQL credentials if different.

### 3. Build the Project

```bash
# Using gradle wrapper on Windows
gradlew.bat build

# Or using gradle directly
gradle build
```

### 4. Run the Application

```bash
# Using gradle wrapper
gradlew.bat bootRun

# Or using built JAR
java -jar build/libs/graphqldemo-0.0.1-SNAPSHOT.jar
```

The application starts on `http://localhost:8080` by default.

## Building the Project

### Full Build with Tests

```bash
gradlew clean build
```

### Build without Tests

```bash
gradlew build -x test
```

### Generate Code from GraphQL Schemas

The DGS codegen automatically generates Java types from GraphQL schemas:

```bash
gradlew generateJava
```

Generated classes are placed in `src/main/java/com/keto/generated/` directory.

## Running the Application

### Start the Application

```bash
gradlew bootRun
```

### Access GraphQL Playground

After the application starts, open your browser:

- **GraphQL Endpoint**: `http://localhost:8080/graphql`
- **GraphQL Playground**: `http://localhost:8080/playground` (if enabled)

### Example GraphQL Queries

#### Query: Get All Hello Messages

```graphql
query {
  allHellos {
    text
    randomNumber
  }
}
```

#### Query: Get One Hello

```graphql
query {
  oneHello {
    text
    randomNumber
  }
}
```

#### Query: Get Latest Problems

```graphql
query {
  problemLatestList {
    id
    title
    content
    tags
    author {
      id
      username
      displayName
    }
    solutionCount
  }
}
```

#### Query: Get Problem Details

```graphql
query {
  problemDetail(id: "123") {
    id
    title
    content
    createdDate
    prettyCreatedDate
    tags
    author {
      id
      username
      email
      displayName
    }
    solutions {
      id
      content
      category
      voteGoodCount
      voteBadCount
      author {
        username
      }
    }
  }
}
```

#### Mutation: Create User

```graphql
mutation {
  userCreate(user: {
    username: "johndoe"
    email: "john@example.com"
    password: "securePassword123"
    displayName: "John Doe"
  }) {
    user {
      id
      username
      email
      displayName
    }
    authtoken {
      authToken
      expiryTime
    }
  }
}
```

#### Mutation: Create Problem

```graphql
mutation {
  problemCreate(problem: {
    title: "How to learn GraphQL?"
    content: "I want to learn GraphQL with Spring Boot"
    tags: ["graphql", "spring-boot", "learning"]
  }) {
    problem {
      id
      title
      createdDate
      author {
        username
      }
    }
  }
}
```

#### Subscription: Listen for New Problems

```graphql
subscription {
  problemAdded {
    id
    title
    author {
      username
    }
  }
}
```

## GraphQL API

### Query Operations

| Operation | Description | Parameters |
|-----------|-------------|-----------|
| `allHellos` | Get all hello messages | None |
| `oneHello` | Get a single hello message | None |
| `problemLatestList` | Get latest problems | None |
| `problemDetail` | Get specific problem | `id: ID!` |
| `searchItems` | Search problems/solutions | `filter: SearchItemFilter` |

### Mutation Operations

| Operation | Description | Input |
|-----------|-------------|-------|
| `userCreate` | Register new user | `UserCreateInput` |
| `userLogin` | Login user | `UserLoginInput` |
| `userActivate` | Activate/deactivate user | `UserActivationInput` |
| `problemCreate` | Create new problem | `ProblemCreateInput` |
| `solutionCreate` | Post solution | `SolutionCreateInput` |
| `solutionVote` | Vote on solution | `SolutionVoteInput` |

### Subscription Operations

| Operation | Description |
|-----------|-------------|
| `problemAdded` | Notifies when new problem is created |

### Custom Scalar Types

- **Date**: ISO 8601 date format
- **DateTime**: ISO 8601 datetime with timezone
- **Url**: Valid URL format
- **NonNegativeInt**: Integer >= 0

### Type Relationships

```
User
├── problems: [Problem!]
├── tokens: [UserAuthToken]
└── avatar: Url

Problem (implements SearchableItem)
├── author: User!
├── solutions: [Solution!]
└── tags: [String!]

Solution (implements SearchableItem)
├── author: User!
└── category: SolutionCategory (EXPLANATION | REFERENCE)

SearchableItem (interface)
├── id: ID!
├── createdDate: DateTime!
├── prettyCreatedDate: String
├── content: String!
└── author: User!
```

## Project Components

### Data Resolvers

**ProblemDataResolver.java**
- Handles problem queries and mutations
- Manages problem CRUD operations
- Supports real-time problem subscriptions

**SolutionDataResolver.java**
- Manages solution-related operations
- Handles voting and solution creation

**UserDataResolver.java**
- User authentication and profile management
- User activation/deactivation

**ItemSearchDataResolver.java**
- Unified search across problems and solutions
- Filter and keyword-based searching

### Data Sources

**FakerDataSourceConfig.java**
- Provides Faker bean for generating fake data
- Useful for testing and demo purposes

**Entity Classes**
- `Userz.java`: User entity with credentials
- `Problemz.java`: Problem entity
- `Solutionz.java`: Solution entity
- `UserzToken.java`: Authentication token storage

### Repositories

Spring Data JPA repositories for database operations:
- `UserzRepository`: User data access
- `ProblemzRepository`: Problem data access
- `SolutionzRepository`: Solution data access
- `UserzTokenRepository`: Token management

## Configuration

### Application Properties (application.yml)

```yaml
spring:
  main:
    banner-mode: OFF          # Disable Spring Boot banner
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres?currentSchema=problemz
    username: postgres
    password: root
    jpa:
      hibernate:
        ddl-auto: none        # Database schema managed externally
      show-sql: false         # Log SQL statements

dgs:
  graphql:
    schema-locations:
      - classpath*:schema/**/*.graphql*    # GraphQL schema files
      - classpath*:schema/**/*.gql
```

### Gradle Configuration (build.gradle)

- **Java Toolchain**: Java 17
- **DGS Code Generation**: Generates types from GraphQL schemas
- **Custom Type Mappings**:
  - `Date` → `java.time.LocalDate`
  - `NonNegativeInt` → `java.lang.Integer`
  - `Url` → `java.net.URL`

## Development

### Code Generation from Schemas

The project uses Netflix DGS code generation to create Java types from GraphQL schemas. Generated code is located in `com.keto.generated` package.

To regenerate code after modifying `.graphql` files:

```bash
gradlew generateJava
```

### Adding New GraphQL Types

1. Create a new `.graphql` file in `src/main/resources/schema/`
2. Define your types, queries, and mutations
3. Run `gradlew generateJava` to generate Java classes
4. Implement resolvers using `@DgsComponent` and `@DgsData` annotations

### Testing

```bash
# Run all tests
gradlew test

# Run tests with coverage
gradlew test jacocoTestReport

# Run specific test class
gradlew test --tests TestClassName
```

### Development Best Practices

1. **Use @DgsComponent** for GraphQL resolver classes
2. **Use @DgsQuery** for query fields
3. **Use @DgsMutation** for mutation fields
4. **Use @DgsSubscription** for subscription fields
5. **Use @InputArgument** for parsing GraphQL input arguments
6. **Use @RequestHeader** for authentication tokens
7. Implement Spring Data JPA repositories for database access
8. Use `GraphqlBeanMapper` utility for object mapping

### IDE Setup

**IntelliJ IDEA**
1. Install GraphQL plugin for syntax highlighting
2. Enable inspections for `.graphql` files
3. Use built-in run configurations for Gradle tasks

**VS Code**
1. Install GraphQL extension
2. Install REST Client for API testing
3. Configure Gradle extension

## Dependencies Overview

```gradle
// Spring Boot Framework
- spring-boot-starter-web (REST and web support)
- spring-boot-starter-data-jpa (ORM and database)
- spring-boot-devtools (Hot reload)
- spring-boot-starter-test (Testing framework)

// GraphQL
- graphql-dgs-spring-graphql-starter (Netflix DGS)
- graphql-dgs-extended-scalars (Custom scalar types)

// Utilities
- datafaker (Fake data generation)
- commons-lang3 (Common utilities)
- prettytime (Human-readable dates)
- bcprov-jdk15on (Cryptography)

// Database
- postgresql (PostgreSQL JDBC driver)
```

## Troubleshooting

### Database Connection Issues

**Error: Cannot connect to PostgreSQL**

- Verify PostgreSQL is running: `psql -U postgres`
- Check connection URL in `application.yml`
- Ensure `problemz` schema exists
- Verify username and password

### GraphQL Endpoint Not Found

- Ensure application started successfully
- Check logs for startup errors
- Verify dependencies are correctly downloaded
- Clean and rebuild: `gradlew clean build`

### Code Generation Issues

**Generated classes not found**

- Run `gradlew generateJava` after modifying `.graphql` files
- Check build output directory: `build/generated/sources/dgs`
- Refresh IDE project

### Port Already in Use

The application defaults to port 8080. To use a different port:

```yaml
server:
  port: 8090
```

Or pass via command line:

```bash
gradlew bootRun --args='--server.port=8090'
```

## Building Docker Image

Create a `Dockerfile`:

```dockerfile
FROM openjdk:17-slim
COPY build/libs/graphqldemo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

Build and run:

```bash
docker build -t graphql-demo .
docker run -p 8080:8080 graphql-demo
```

## Additional Resources

- [Netflix DGS Documentation](https://netflix.github.io/dgs/)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [GraphQL Official](https://graphql.org/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

## License

This project is provided as-is for educational and demonstration purposes. Please check with your organization for any licensing requirements.

## Contributing

To contribute to this project:

1. Create a feature branch (`git checkout -b feature/amazing-feature`)
2. Commit changes (`git commit -m 'Add amazing feature'`)
3. Push to branch (`git push origin feature/amazing-feature`)
4. Open a Pull Request

## Support

For issues, questions, or suggestions, please open an issue in the repository.

---

**Last Updated**: 2024
**Application Version**: 0.0.1-SNAPSHOT
**Java Version Required**: 17+
