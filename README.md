# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data Elasticsearch (Access+Driver)](https://docs.spring.io/spring-boot/docs/2.4.5/reference/htmlsingle/#boot-features-elasticsearch)

### Steps to run
The following guides illustrate how to run the project:

Run below command from the root directory of project
* docker-compose -f ./src/main/docker/docker-compose.yml up -d 

once the application is up browse : http://localhost:6012/product-service/swagger-ui.html

You do not need a build before running as application image is already built and pushed to docker hub.
If you need to build the image again run build.bat file

![product-service-architecture](https://user-images.githubusercontent.com/5157624/116121653-e4af4700-a6d1-11eb-8a5a-3315c4e32919.png)
