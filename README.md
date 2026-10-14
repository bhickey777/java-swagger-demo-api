# Spring Boot Product API — OpenAPI / Swagger Example

A small classroom example demonstrating how a **Spring Boot REST API** uses **OpenAPI annotations** to describe its endpoints and data models. It includes a controller, service, Java record, interactive Swagger UI, and an example bearer-token security *declaration*.

> **Learning objective:** Distinguish Spring annotations that implement an API from OpenAPI annotations that document it.

## Prerequisites

- **JDK 17 or newer** (`java -version`)
- **Apache Maven** (`mvn -version`)
- Git, if cloning the repository

## Get the code

Clone the repository (replace the placeholder with your instructor's repository URL):

```bash
git clone <REPOSITORY_URL>
cd swagger-demo-api
```

Or download and extract the repository ZIP, then open a terminal in the `product-api` directory.

## Run the application

```bash
mvn spring-boot:run
```

By default, the server listens on **http://localhost:8090**. Stop it with **Ctrl+C**.

To compile and package the project instead:

```bash
mvn clean package
java -jar target/swagger-demo-api-0.0.1-SNAPSHOT.jar
```

## Explore the API

Once the app is running:

| Resource | URL |
| --- | --- |
| Swagger UI (interactive documentation) | http://localhost:8090/swagger-ui.html |
| OpenAPI specification (JSON) | http://localhost:8090/v3/api-docs |
| Products endpoint | http://localhost:8090/api/products |

### Endpoints

| Method | Path | Description | Typical status |
| --- | --- | --- | --- |
| `GET` | `/api/products` | List products | `200 OK` |
| `GET` | `/api/products/{id}` | Find one product | `200 OK` or `404 Not Found` |
| `POST` | `/api/products` | Create a product | `201 Created` |
| `GET` | `/api/products/secured-example` | Illustrate an OpenAPI bearer-token requirement | `200 OK` |

The application starts with two products: **Laptop** and **Mouse**. Data is stored **in memory** and resets whenever the application restarts.

### Try the endpoints with curl

List all products:

```bash
curl http://localhost:8090/api/products
```

Find a product:

```bash
curl http://localhost:8090/api/products/1
```

Create a product:

```bash
curl -X POST http://localhost:8090/api/products \
  -H 'Content-Type: application/json' \
  -d '{"name":"Keyboard","price":49.99}'
```

Alternatively, open **Swagger UI**, expand an endpoint, click **Try it out**, enter any required values, and click **Execute**.

## Project structure

```text
product-api/
├── .gitignore
├── README.md
├── pom.xml
└── src/main/java/com/example/demo/
    ├── DemoApplication.java
    ├── controller/ProductController.java
    ├── model/Product.java
    └── service/ProductService.java
```

- **`DemoApplication`** starts Spring Boot and declares an OpenAPI bearer security scheme.
- **`ProductController`** defines the HTTP routes and documents them.
- **`ProductService`** contains the in-memory business logic.
- **`Product`** is the API data model, represented by a Java record.
- **`pom.xml`** defines the Spring Web and springdoc-openapi dependencies.

## Annotations to recognize

| Annotation | Library | Role |
| --- | --- | --- |
| `@SpringBootApplication` | Spring Boot | Bootstraps the application |
| `@RestController` | Spring Web | Marks a REST controller |
| `@RequestMapping`, `@GetMapping`, `@PostMapping` | Spring Web | Maps HTTP routes |
| `@PathVariable`, `@RequestBody` | Spring Web | Binds incoming request data |
| `@Service` | Spring | Marks the service component |
| `@Tag` | OpenAPI | Groups operations in documentation |
| `@Operation` | OpenAPI | Describes an endpoint |
| `@ApiResponse`, `@ApiResponses` | OpenAPI | Documents response codes |
| `@Parameter` | OpenAPI | Documents an operation parameter |
| `@Schema` | OpenAPI | Documents the model |
| `@SecurityScheme` | OpenAPI | Defines a documented security scheme |
| `@SecurityRequirement` | OpenAPI | Associates a security scheme with an operation |

**Key distinction:** Spring Web annotations make routes work; OpenAPI annotations describe those routes to documentation and client-generation tools.

## Important security limitation

`@SecurityScheme` and `@SecurityRequirement` **do not enforce authentication**. The `/api/products/secured-example` endpoint is publicly accessible in this demo, even though its OpenAPI description indicates a bearer token. This project does **not** include Spring Security or JWT validation. Do not use it as an example of implemented authentication.

## Troubleshooting

- **`java` or `mvn` not found:** Install JDK 17+ or Maven and confirm they are on your `PATH`.
- **Port 8090 already in use:** Stop the other application, or run `mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081` and use port 8081 in the URLs.
- **Swagger UI won't load:** Confirm the app started successfully and open `http://localhost:8090/swagger-ui.html`.
- **Product data disappeared:** The sample uses an in-memory map, not a database; restarting resets it.

## Tech stack

- Java 17
- Spring Boot 3.5.6
- Maven
- springdoc-openapi 2.8.9 (Swagger UI / OpenAPI 3)
