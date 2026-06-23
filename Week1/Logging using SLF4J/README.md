# Logging using SLF4J

This folder contains a Java application demonstrating logging error messages, warnings, and other log levels using SLF4J and Logback.

## Exercise 1: Logging Error Messages and Warning Levels

### Scenario
You need to write a Java application that demonstrates logging error messages and warning levels using SLF4J.

### Steps
1. Add SLF4J and Logback dependencies to your `pom.xml` file.
2. Create a `logback.xml` file under `src/main/resources/` to configure console and rolling file output.
3. Use SLF4J `Logger` in your Java code to log at various levels (`trace`, `debug`, `info`, `warn`, `error`), including parameterized and exception logging.

### Project Structure
```text
LoggingExample/
 └── src/
      └── main/
           ├── java/
           │    └── LoggingExample.java
           └── resources/
                └── logback.xml
```
