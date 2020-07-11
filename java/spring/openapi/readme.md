# OpenApi

## Contents :

- [Intro](#intro)
- [Info Object](#info-object)
- [Server Object](#server-object)
- [Security Schema](#security-schema)
- [External Documentation Object](#external-documentation-object)
- [Schemas](#schemas)
- [Request Parameters](#request-parameters)
- [Requests](#requests)

---

## Intro

The maven dependency for OpenApi to add to the pom is:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.4.2</version> <!-- current version -->
</dependency>
```

This dependency allow to use specific annotations and configurations to add all the info information available for 
in the open Api specification, a page where is possible to see the graphic representation of this information 
(AKA Swagger), and the correspondent yaml file.

The swagger gui is located at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

---

## Info Object

In order to create an info object like:
```yaml
info:
    version: '1.0'
    title: 'Basic Example'
    description: 'Basic specification example'
    termsOfService: http://example.com/terms/
    contact:
      name: API Support
      url: http://www.example.com/support
      email: support@example.com
    license:
      name: Apache 2.0
      url: https://www.apache.org/licenses/LICENSE-2.0.html
```
There two possibilities:
- define it inside `@OpenApiDefinition`:
```java
@OpenAPIDefinition(
        info = @Info(
                version = "1.0",
                title = "Basic Example",
                description = "Basic specification example",
                termsOfService = "http://example.com/terms/",
                contact = @Contact(
                        name = "API Support",
                        url = "http://www.example.com/support",
                        email = "support@example.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
@Configuration
public class OpenApiConfiguration {
}
```
- or use a specific bean:
```java
@Configuration
public class OpenApiBeanConfiguration {
    @Bean
    public OpenAPI getOpenApiConfiguration() {
        Contact contact = new Contact()
                        .name("API Support")
                        .url("http://www.example.com/support")
                        .email("support@example.com");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .version ("1.0")
                .title("Basic Example")
                .description("Basic specification example")
                .termsOfService("http://example.com/terms/")
                .contact(contact)
                .license(license);

        return new OpenAPI().info(info);
    }
}
```

---

## Server Object

In order to create a server object like:
```yaml
servers: 
  - url: http://example.com/api
    description: My example server 
```
There two possibilities:
- define it inside `@OpenApiDefinition`:
```java
@OpenAPIDefinition(
        servers = {
                @Server(
                        url = "http://example.com/api",
                        description = "My example server"
                )
        }
)
@Configuration
public class OpenApiAnnotationConfiguration {
}
```
- or use a specific bean:
```java
@Configuration
public class OpenApiBeanConfiguration {
    @Bean
    public OpenAPI getOpenApiConfiguration() {
        Server server = new Server()
                .url("http://example.com/api")
                .description("My example server");

        return new OpenAPI().servers(List.of(server));
    }
}
```

---

## Security Schema

In order to create a Security schema like:
```yaml
security: 
  - BasicAuth: []
  - JwtAuthToken: []
components:
  securitySchemes:
    BasicAuth:
      type: http
      scheme: basic
    JwtAuthToken:
      type: http
      scheme: bearer
      bearerFormat: JWT
```
There two possibilities:
- define it inside `@OpenApiDefinition`:
```java
@OpenAPIDefinition(
        info = @Info(
                version = "1.0",
                title = "Basic Example",
                description = "Basic specification example",
                termsOfService = "http://example.com/terms/",
                contact = @Contact(
                        name = "API Support",
                        url = "http://www.example.com/support",
                        email = "support@example.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(
                        url = "http://example.com/api",
                        description = "My example server"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "BasicAuth"
                ),
                @SecurityRequirement(
                        name = "JwtAuthToken"
                )
        }
)
@SecuritySchemes({
        @SecurityScheme(
                name = "BasicAuth",
                type = SecuritySchemeType.HTTP,
                scheme = "basic"
        ),
        @SecurityScheme(
                name = "JwtAuthToken",
                type = SecuritySchemeType.HTTP,
                scheme = "bearer",
                bearerFormat = "JWT"
        )
})
@Configuration
public class OpenApiAnnotationConfiguration {
}
```
- or use a specific bean:
```java
@Configuration
public class OpenApiBeanConfiguration {
    @Bean
    public OpenAPI getOpenApiConfiguration() {
        SecurityScheme basicAuthSecurityScheme = new SecurityScheme()
                .name("BasicAuth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");

        SecurityScheme jwtAuthTokenSecurityScheme = new SecurityScheme()
                .name("JwtAuthToken")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement basicAuth = new SecurityRequirement().addList("BasicAuth");
        SecurityRequirement jwtAuthToken = new SecurityRequirement().addList("JwtAuthToken");

        return new OpenAPI()
                .security(List.of(basicAuth, jwtAuthToken))
                .schemaRequirement("BasicAuth", basicAuthSecurityScheme)
                .schemaRequirement("JwtAuthToken", jwtAuthTokenSecurityScheme);
    }
}
```

---

## External Documentation Object

In order to create an External Documentation object like:
```yaml
externalDocs:
  description: External Documentation
  url: http://example.com/external
```
There two possibilities:
- define it inside `@OpenApiDefinition`:
```java
@OpenAPIDefinition(
        externalDocs = @ExternalDocumentation(
                url = "http://example.com/external",
                description = "External Documentation"
        )
)
@Configuration
public class OpenApiAnnotationConfiguration {
}
```
- or use a specific bean:
```java
@Configuration
public class OpenApiBeanConfiguration {
    @Bean
    public OpenAPI getOpenApiConfiguration() {
        ExternalDocumentation documentation = new ExternalDocumentation()
                .url("http://example.com/external")
                .description("External Documentation");

        return new OpenAPI()
                .externalDocs(documentation);
    }
}
```

---

## Schemas

In order to create a Schema, that can be used as request or response body, like:
```yaml
components:
  schemas:
    NestedExample:
      type: object
      properties:
        dateExample:
          type: string
          format: date
          example: '2012-12-12'
        booleanExample:
          type: boolean
          example: true
    Example:
      type: object
      properties:
        uuidExample:
          type: string
          format: uuid
        titleExample: 
          type: string
          example: My Example
        numberExample:
          type: number
          format: float
          example: 4.20
        emailExample:
          type: string
          format: email
        stateEnum:
          type: string
          enum: 
            - open
            - in progress
            - closed
          example: open
        colorEnum:
          type: string
          enum: [red, green, blue]
          example: red
        nestedExample:
          $ref: "#/components/schemas/NestedExample"
    ExampleList:
      type: array
      minLength: 1
      maxLength: 20
      items:
        $ref: "#/components/schemas/Example"
    PagedResponseExample:
      type: object
      allOf:
        - $ref: "#/components/schemas/PagedResponse"
      properties:
        content:
          $ref: "#/components/schemas/ExampleList"
    PagedResponse:
      type: object
      properties:
        curPage:
          type: number
          format: int32
          example: 0
        totalPages:
          type: number
          format: int32
          example: 10
        elementsForPage:
          type: number
          format: int32
          example: 25
```
The different properties can be defined in the different classes using the `schema` tag:
```java
public class NestedExample {
    @Schema(format = "date", example = "2012-12-12")
    private LocalDate dateExample;

    @Schema(example = "true")
    private boolean booleanExample;
}

public class Example {
    @Schema(format = "uuid", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String uuidExample;

    @Schema(example = "My Example")
    private String titleExample;

    @Schema(format = "float")
    private Float numberExample;

    @Schema(format = "email")
    private String emailExample;

    @Schema(example = "open")
    private StateEnum stateEnum;

    @Schema(example = "red")
    private ColorEnum colorEnum;

    private NestedExample nestedExample;
}

public class PagedResponseExample extends PagedResponse {
    @Schema(minLength = 1, maxLength = 20)
    private List<Example> content;
}

public class PagedResponse {
    @Schema(format = "int32", example = "0")
    private Integer curPage;

    @Schema(format = "int32", example = "10")
    private Integer totalPages;

    @Schema(format = "int32", example = "25")
    private Integer elementsForPage;
}
```

---

## Request Parameters

In order to create a Request Parameter like:
```yaml
components:
  parameters:
    UuidExampleParam:
      name: uuidExample
      in: path
      description: Example uuid
      required: true
      schema:
        type: string
        format: uuid
    CurPageParam:
      name: curPage
      in: query
      description: Current page number
      schema:
        type: number
        format: int32
        default: 0
    PageSizeParam:
      name: pageSize
      in: query
      description: Max page elements
      required: false
      schema:
        type: number
        format: int32
        default: 25
```
It is enough define it in the request:
```java
public PagedResponseExample getAllExamplesV1(@RequestParam(required = false, defaultValue = "0") Integer curPage,
                                             @RequestParam(required = false, defaultValue = "25") Integer pageSize) {
    return new PagedResponseExample();
}
```

---

## Requests

In order to create a Request documentation like:
```yaml
paths:
  /examples:
    get:
      summary: Get all the Examples
      description: Return a paged list of examples.
      tags: 
        - Example
      operationId: getAllExamplesV1
      parameters:
        - $ref: "#/components/parameters/CurPageParam"
        - $ref: "#/components/parameters/PageSizeParam"
      responses:
        '200':
          description: A list of examples.
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/PagedResponseExample"
    post:
      summary: New Example
      description: Create a new Example.
      tags: 
        - Example
      operationId: createExampleV1
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Example"
      responses:
        '201':
          description: Example created.
          headers:
            location:
              description: Location of the created Example
              schema:
                type: string
                format: uri
                example: http://example.com/examples/{generatedUuid}
        '400':
          description: Bad request.
        '409':
          description: Conflict.
  /examples/{uuidExample}:
    get:
      summary: Get an Examples
      description: Return a specific examples using its **uuid**.
      tags: 
        - Example
      operationId: getAnExampleByUuidV1
      parameters:
        - $ref: "#/components/parameters/UuidExampleParam"
      responses:
        '200':
          description: An examples.
          content:
            application/json:
              schema:
                $ref: "#/components/schemas/Example"
        '404':
          description: Not Found.
    put:
      summary: Update an Examples
      description: Update a specific examples using its **uuid**.
      tags: 
        - Example
      operationId: updateAnExampleByUuidV1
      parameters:
        - $ref: "#/components/parameters/UuidExampleParam"
      requestBody:
        required: true
        content:
          application/json:
            schema:
              $ref: "#/components/schemas/Example"
      responses:
        '204':
          description: Costumer Updated.
        '400':
          description: Bad request.
        '404':
          description: Not Found.
    delete:
      summary: Delete an Examples
      description: Delete a specific examples using its **uuid**.
      tags: 
        - Example
      operationId: deleteAnExampleByUuidV1
      parameters:
        - $ref: "#/components/parameters/UuidExampleParam"
      responses:
        '200':
          description: Costumer Deleted.
        '404':
          description: Not Found.
```
It is necessary tag the RestController and the Requests in this way:
```java
@RestController("examples")
@Tag(name = "Example", description = "Operations to manage an example.")
public class ExampleController {

    @Operation(
            summary = "Get all the Examples",
            description = "Return a paged list of examples.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of examples."
                    )
            }
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResponseExample getAllExamplesV1(@RequestParam(required = false, defaultValue = "0") Integer curPage,
                                                 @RequestParam(required = false, defaultValue = "25") Integer pageSize) {
        return new PagedResponseExample();
    }

    @Operation(
            summary = "New Example",
            description = "Create a new Example.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Example created.",
                            headers = {
                                    @Header(
                                            name = "location",
                                            description = "Location of the created Example",
                                            schema = @Schema(
                                                    type = "string",
                                                    format = "uri",
                                                    example = "http://example.com/examples/{generatedUuid"
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflicts."
                    )
            }
    )
    @PostMapping
    public void createExampleV1(@RequestBody @Valid Example example) {}

    @Operation(
            summary = "Get an Examples",
            description = "Return a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "An examples."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @GetMapping(path = "{uuidExample}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Example getAnExampleByUuidV1(@PathVariable String uuidExample) {
        return new Example();
    }

    @Operation(
            summary = "Update an Examples",
            description = "Update a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Costumer Updated."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @PutMapping(path = "{uuidExample}")
    public void updateAnExampleByUuidV1(@PathVariable String uuidExample,
                                        @RequestBody @Valid Example example) { }

    @Operation(
            summary = "Delete an Examples",
            description = "Delete a specific examples using its **uuid**.",
            responses = {
                    @ApiResponse(
                            responseCode = "20",
                            description = "Costumer Deleted."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found."
                    )
            }
    )
    @DeleteMapping(path = "{uuidExample}")
    public void deleteAnExampleByUuidV1(@PathVariable String uuidExample) { }
}
```


---

##  Object

In order to create a  object like:
```yaml
```
There two possibilities:
- define it inside `@OpenApiDefinition`:
```java

```
- or use a specific bean:
```java

```

---

## 


