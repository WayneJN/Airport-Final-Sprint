# ✈️ Airport API

A Spring Boot REST API for managing cities, airports, passengers, and aircraft. This backend powers a CLI application used to query flight and airport data.

## 📦 Project Overview

This API allows clients to:

- Retrieve all airports within a city
- Track aircraft flown by passengers
- List passengers who have used an airport
- Track flights between airports

It supports all standard HTTP verbs (`GET`, `POST`, `PUT`, `DELETE`) and is backed by a relational database such as MySQL or H2 for testing.

---

## 📚 Entities & Relationships

| Entity      | Fields                                                                 |
|-------------|------------------------------------------------------------------------|
| **City**    | `id`, `name`, `state`, `population`                                    |
| **Airport** | `id`, `name`, `code`, belongs to one City                              |
| **Passenger** | `id`, `firstName`, `lastName`, `phoneNumber`, lives in one City     |
| **Aircraft**  | `id`, `type`, `airlineName`, `numberOfPassengers`                    |

### 🔗 Relationships

- A **City** has many **Airports**
- An **Airport** belongs to one **City**
- A **Passenger** lives in one **City** and can fly on many **Aircraft**
- An **Aircraft** can carry many **Passengers** and use many **Airports**

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL (or use H2 for testing)
- (Optional) Docker for containerization

## Run Locally

 - bash
 - ./mvnw spring-boot:run
 - The API will start at http://localhost:8080

## 🧪 Testing

Tests include:

- Endpoint tests using MockMvc
- Repository layer tests with @DataJpaTest
- CLI integration tested separately in companion repo

## Run all tests:

./mvnw test


## 🌐 Companion CLI App

The CLI frontend that consumes this API can be found here: 👉 [airport-CLI GitHub Repository](https://github.com/WayneJN/Spring-Sprint-1-airport-CLI)

## 🛠 Technologies

Java 17

- Spring Boot
- Spring Data JPA
- MySQL / H2 (test)
- JUnit 5 / MockMvc
- Maven

## 📽️ Demo Video
A recorded demo video is available in the submission link or docs/ folder.

## 👥 Team
Wayne N.

