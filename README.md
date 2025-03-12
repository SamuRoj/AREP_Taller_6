# Secure Application Design

This project is a simple CRUD system for managing real estate properties. It has a basic web application that 
allows users to manage property listings, a secure design to ensure integrity and confidentiality and a scalable 
application by using AWS infrastructure. 

The architecture has three primary components:

- **Server 1:** Apache Server
The Apache server serves an asynchronous HTML + JavaScript client over a secure connection using TLS. Client-side 
code will be delivered through encrypted channels, ensuring data integrity and confidentiality.

- **Server 2:** Spring Framework
The Spring server will handle backend services, offering RESTful API endpoints. These services will also be 
protected using TLS, ensuring secure communication between the client and the backend.

- **Server 3:** MySql
The MySql server will receive the petitions from the backend and store them in a database to persist the received
information.

## Getting Started

### Prerequisites

### 1. **Java Development Kit (JDK)**

- To check if Java is installed, run:

```
java -version
```

- If Java is not installed, download it from the official Oracle [website](https://www.oracle.com/co/java/technologies/downloads/).

### 2. **Maven**

- To check if Maven is installed, run:

```
mvn --version
```

- If Maven is not installed, download it from the official Apache Maven [website](https://maven.apache.org/download.cgi).

### 3. Git

- To check if Git is installed, run:

```
git --version
```

- If Git is not installed, download it from the official Git [website](https://git-scm.com/downloads).

### 4. Docker

- To check if Docker is installed, run:

```
docker --version
```

- If Docker is not installed, download it from the official Docker [website](https://www.docker.com/products/docker-desktop/).

### Installing and deploying locally

1. Clone the repository to your local machine using Git.

```
git clone https://github.com/SamuRoj/AREP_Taller_6.git
```

2. Navigate to the project directory.

```
cd AREP_Taller_6
```

3. Run a container with a mysql image, it can be done with the following command and wait for it to be completely 
deployed.

```
docker run --name mysqlpropertydb -e MYSQL_ROOT_PASSWORD=secretProperty -e MYSQL_DATABASE=properties -e MYSQL_USER=userProperty -e MYSQL_PASSWORD=secretProperty -p 3306:3306 -d mysql:latest
```

4. Once the container is completely running, execute the next command:

```
mvn spring-boot:run
```

5. Once the application is running, open your web browser and visit:

```
https://localhost:8080/index.html
```

## Features of the application

**Note:** {springurl} represents the following string "http://localhost:8080"

**Get all properties:** Retrieves all properties stored in the database.

**Endpoint:** GET /properties

![Get_All_Properties.png](src/main/resources/img/Get_All_Properties.png)

**Get property by id:** Retrieves a specific property by its ID. 

**Endpoint:** GET /properties/{id}

![Get_Property_By_Id.png](src/main/resources/img/Get_Property_By_Id.png)

**Filter property by address:** Returns properties that match the given address.

**Endpoint:** GET /properties/address?address=

![Filter_By_Address.png](src/main/resources/img/Filter_By_Address.png)

**Filter property by price:** Returns properties that match the given price.

**Endpoint:** GET /properties/price?price=

![Filter_By_Price.png](src/main/resources/img/Filter_By_Price.png)

**Filter property by size:** Returns properties that match the given size.

**Endpoint:** GET /properties/size?size=

![Filter_By_Size.png](src/main/resources/img/Filter_By_Size.png)

**Create a property:** Adds a new property to the database.

**Endpoint:** POST /properties

![Create_Property.png](src/main/resources/img/Create_Property.png)

**Update a property:** Updates an existing property’s details.

**Endpoint:** PUT /properties/{id}

![Update_Property.png](src/main/resources/img/Update_Property.png)

**Delete a property:** Deletes a property from the database.

**Endpoint:** DELETE /properties/{id}

![Delete_Property.png](src/main/resources/img/Delete_Property.png)

![After_Delete.png](src/main/resources/img/After_Delete.png)

## Architecture

### Project Structure

```
├───main
│   ├───java
│   │   └───eci
│   │       └───arep
│   │           └───property
│   │               │   PropertyApplication.java # Runs the app through the port 8080
│   │               │
│   │               ├───config # Configures the app to receives cross origin requests
│   │               │       SecurityConfig.java
│   │               │
│   │               ├───controller # Handles the request from the clients to the endpoints
│   │               │       PropertyController.java
│   │               │       UserController.java
│   │               │
│   │               ├───dto # Maps the values of the user into a data transfer object
│   │               │       PropertyDto.java
│   │               │       UserDto.java
│   │               │
│   │               ├───exception # Custom exception to handle errors
│   │               │       PropertyNotFound.java
│   │               │
│   │               ├───model # Entity being used at the database
│   │               │       Property.java
│   │               │       UserEntity.java
│   │               │
│   │               ├───repository # Communicates the app with the database
│   │               │       PropertyRepository.java
│   │               │       UserRepository.java
│   │               │
│   │               └───service # Logic of each endpoint
│   │                       PropertyService.java
│   │                       UserService.java
│   │
│   └───resources # Setup of the application
│       │   application.properties
│       │
│       ├───img # Images used in the README
│       │       After_Delete.png
│       │       ClassDiagram.png
│       │       Create_Property.png
│       │       Delete_Property.png
│       │       DeploymentDiagram.png
│       │       Filter_By_Address.png
│       │       Filter_By_Price.png
│       │       Filter_By_Size.png
│       │       Get_All_Properties.png
│       │       Get_Property_By_Id.png
│       │       PropertyServiceTests.png
│       │       Update_Property.png
│       │
│       ├───keystore
│       │       srpropcert.cer
│       │       srpropkeystore.p12
│       │
│       ├───static # Static files that will be served to the user through the Apache Server. 
│       │   │   index.html
│       │   │   properties.html
│       │   │   register.html
│       │   │
│       │   ├───css
│       │   │       index.css
│       │   │       login.css
│       │   │       register.css
│       │   │
│       │   └───js 
│       │           api.js
│       │           login.js
│       │           properties.js
│       │           register.js
│       │
│       ├───truststore
│       │       myTrustStore
│       │
│       └───vid # Videos used in the README
│
└───test
    └───java
        └───eci
            └───arep
                └───property
                    │   PropertyApplicationTests.java
                    │
                    └───service # Unit tests for the services
                            PropertyServiceTests.java
                            UserServiceTests.java
```

### Class Diagram

![ClassDiagram.png](src/main/resources/img/ClassDiagram.png)

### Classes

* **PropertyApplication:** A class that initiates and sets up the entire application. 
* **PropertyController:** Application that handles incoming requests related to real estate properties. Acts as an 
interface between the client and the backend logic. 
* **UserController:** Application that handles incoming requests related to the users.
* **PropertyDto:** It's used to transfer property data between the layers of the application, in this case the controller,
service and model.
* **UserDto:** It's used to transfer user data between the layers of the application.
* **PropertyNotFound:** Exception showing that the requested property could not be found in the database.
* **Property:** Entity that represents a real-world property with some of his features like the address, price, size and
description inside the application.
* **UserEntity:** Entity that represents a real user with some features like the email and password.
* **PropertyRepository:** Component responsible for managing the data access layer related to properties, handles database
operations. 
* **UserRepository:** Component responsible for managing the data access layer related to users.
* **PropertyService:** Contains the business logic related to properties. It acts as an intermediary between the 
controller and the repository.
* **UserService:** Contains the business logic related to users and hashes the passwords for security.

### Deployment Diagram

![DeploymentDiagram.png](src/main/resources/img/DeploymentDiagram.png)

* **EC2 Instances:** Virtual servers provided by Amazon Web Services (AWS) that allow you to run applications and services
in the cloud.
* **Docker Engine:** Software platform that enables developers to build, deploy and run applications inside containers.
* **MySql Container:** Instance of the MySQL database management system, deployed with Docker.
* **Backend Server:** Server with the main application that handles the server-side logic data processing for 
the property listings and user management and handles basic security of the app.
* **Apache Server:** Server that answers requests related to the static files of the app.
* **HTML, CSS and JS:** Files required to render the webpage in the client browser. 

## AWS Deployment

### Local Deployment

![Local_Demo.gif](src/main/resources/vid/Local_Demo.gif)

### Video with AWS Deployment working

![AWS_Test.gif](src/main/resources/vid/AWS_Test.gif)

## Running the tests

**Note:** To execute them properly run a container with a mysql image  locally, it  can be done with the following 
command:

```
docker run --name mysqlpropertydb -e MYSQL_ROOT_PASSWORD=secretProperty -e MYSQL_DATABASE=properties -e MYSQL_USER=userProperty -e MYSQL_PASSWORD=secretProperty -p 3306:3306 -d mysql:latest
```

- Execute the tests by running the following command:

```
mvn test
```

### PropertyServiceTests

The tests in this file check the functionality of the logic within the service class. 

- Image of the results:

  ![PropertyServiceTests.png](src/main/resources/img/PropertyServiceTests.png)

### UserServiceTests

The tests in this file check the functionality of the logic within the user service class.

- Image of the results:

  ![UserServiceTests.png](src/main/resources/img/UserServiceTests.png)

## Built With

* [Java Development Kit](https://www.oracle.com/co/java/technologies/downloads/) - Software Toolkit
* [Maven](https://maven.apache.org/) - Dependency Management
* [Docker](https://www.docker.com/) - Platform for building, running, and managing containerized applications
* [AWS](https://aws.amazon.com/es/) - Cloud computing platform for hosting, storage, and computing services
* [Git](https://git-scm.com/) - Distributed Version Control System

## Authors

* **Samuel Rojas** - [SamuRoj](https://github.com/SamuRoj)

## License

This project is licensed under the GNU License - see the [LICENSE.txt](LICENSE.txt) file for details.
