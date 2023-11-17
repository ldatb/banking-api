# Banking API

This is a sample banking API that I used to learn Kotlin, Spring and Gradle.

## Usage

### Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3](https://maven.apache.org/)

### Database

To run this project you'll need a MySQL instance running. There's a [Docker Compose](docker-compose.yaml)
file that creates that for you.

### How to run

First of all, **remember to check your [configurations](src/main/resources/application.yaml)**

There are several ways to run a Spring Boot application on your local machine.
One way is to execute the main method in the 
[BankingApiApplication](src/main/kotlin/com/ldatb/learn/banking/BankingApiApplication.kt)
class from your IDE.

You can also manually build and run the application from the command line:

```bash
./gradlew build && java -jar build/libs/<jar-file-name>
```

## Copyright

This project is licensed under the [MIT License](LICENSE)
