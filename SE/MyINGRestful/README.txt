Setup/Installation instructions:

a. Make sure no other application running that uses 8080 port.
b. Copy and paste jar to a local folder. In cmd window, run:
java -jar INGRestfulApplication-1.0.0.jar
c. Access via 
 1. to get customer with id and year-month, GET from http://localhost:8080/customer/{id}/{yyyyMM} where date in yyyyMM format
d. Setup database. 
 1. install oracle xe and run server service and listener
 2. update application.properties to point to oracle db url
 3. the application will connect to running local oracle instance

Architecture:
This application uses Controller - Service - Repository architecture.
It separates distinct responsibilities into layers:
The Controller layer exposes an interface to external applications.
The Service layer handles validation, and other business logic. 
It is the intermediary between Controller and Repository.
The Repository layer deals with the database directly.
Each module will have its own set of these classes.

Frameworks:
This application uses 
a. Maven to package the application and manage library dependencies
b. Spring Boot to run the application 
c. Spring MVC to implement RESTful API
d. Spring Data to annotate model classes and connect to MongoDB
e. JPA via Oracle for persistence
h. Spring RestTemplate to test RESTful Services

Description of solution:
This application used latest and most popular Java stack:
a. Maven supports dependency management and building/packaging
b. Spring Boot was chosen so that no server is needed to run the application.
c. Spring MVC was chosen to simplify RESTful service implementation
d. Spring Data was chosen to simplify ORM and simpler than Hibernate due to the Repository inteeface
e. MongoDB was chosen as it requires no schema, no sql
f. Cloud persistence for less local setup and admin, high availability
g. Assertj for more readable test code.
h. Spring @SpringBootTest and RestTemplate to be able to use objects instead of dealing with json directly.




Points of Interest:
On the date field:
a. used @DateTimeFormat to convert String path variable to LocalDate using format yyyyMMdd
b. used Java8 java.time.LocalDate to store Date instead of java.util.Date
c. used @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd") to set json date format
d. in NoSQL Manager, make sure to set "Date and Time format" to Local (instead of UTC) to see the dates correctly.
 
On limiting related tags to 10:
a. used LinkedHashSet to preserve order of the related tags and to eliminate duplicates.
b. used List to easily get sublist to limit element size to 10.

On Comments: 
a. Add comments only if necessary.

On Testing:
a. Write Integration Test at the minimum.
b. Write unit test if integration fails and we want to rule out problems in repository
or if different people working on the Service/Repository and the Controller layer.  
c. Write tests only if they are useful.



Assumptions:
a. Id field in the Article json will be used as id.
This means if the same id is used with different details, it will override existing data.
b. For the last 10 articles requirement, assuming id is chronological and so will be sorting on the id.
c. no validations. If invalid id is given, no output shown.  
