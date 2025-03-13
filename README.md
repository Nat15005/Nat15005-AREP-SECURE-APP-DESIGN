# Property Management System

## Project Summary
The **Property Management System** is a web application designed to manage real estate properties. It provides CRUD (Create, Read, Update, Delete) functionality for property listings, allowing users to add, view, update, and delete properties. The system also supports pagination and search functionality to filter properties by address, price, and size.

The application is deployed on AWS using two EC2 instances: one for the backend and frontend, and another for the MySQL database. The backend, built with Spring Boot, handles the business logic and exposes RESTful APIs for CRUD operations. The frontend, developed with HTML, CSS, and JavaScript, provides a user-friendly interface for interacting with the system. The MySQL database stores all property-related data and is accessed by the backend

### Key Features:
- **Create Property**: Add new property listings with details such as address, price, size, and description.
- **Read Property**: View a list of all properties or search for specific properties using filters.
- **Update Property**: Modify existing property details.
- **Delete Property**: Remove properties from the system.
- **Pagination**: Browse properties in pages for better usability.
- **Search**: Filter properties by address, price range, and size.

## Deployment Video


https://github.com/user-attachments/assets/2f8a48fd-57fe-447a-a082-b20c9e934cc6


## Getting Started

These instructions will guide you to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this project, you need the following software installed on your local machine:

- **Java 17+**: The project is built using Java. 
- **Maven**: Used for dependency management.
- **IDE (optional)**: An Integrated Development Environment like IntelliJ IDEA can be used for development.

### Installing

Follow these steps to get the development environment running:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Nat15005/AREP_CRUD_SYSTEM.git
   ```
2. **Navigate to the project folder:**
   ```bash
   cd AREP_CRUD_SYSTEM
   ```
3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

### Running the Server

Once the project is built, you can start the server with the following command:

```bash
java -jar target/crudsystem-0.0.1-SNAPSHOT.jar
```

The server will start and listen on port `8080`.

### Accessing the Application

Open your web browser and go to:

```
http://localhost:8080/
```

You should see the main page of the application.

![Captura de pantalla 2025-03-06 000624](https://github.com/user-attachments/assets/58e1e4d4-0112-4327-b7f6-d57774a6e2b8)


## System Architecture
The system is divided into three main components:

![image](https://github.com/user-attachments/assets/04358f66-6b33-43b5-993c-f29593e5460b)

1. *Frontend:* Built with HTML, CSS, and JavaScript. It provides a user-friendly interface for interacting with the property management system. 🌐

2. *Backend:* Implemented with Spring Boot, it exposes RESTful APIs for CRUD operations on properties. The backend handles all the business logic and communicates with the database. 🛠️

3. *Database:* MySQL is used to store property data. The database is accessed via JPA/Hibernate in the backend, ensuring seamless data persistence and retrieval. 🗃️

### Deployment on AWS EC2 Instances ☁️

The system is deployed on two separate EC2 instances for better performance and security:

- *EC2 Instance 1:* Hosts the Frontend and Backend (Spring Boot application). This instance runs the web application and handles user requests. 🖥️

   - The frontend sends HTTP requests (GET, POST, PUT, DELETE) to the backend.

   - The backend processes these requests and interacts with the database.

- *EC2 Instance 2:* Hosts the MySQL Database. This instance is dedicated to storing and managing property data. 🗄️

   - The backend communicates with the database via JDBC to perform CRUD operations.

   - The database stores and retrieves property data as requested by the backend.
  
### Interaction Flow 🔄
Frontend → Backend: The frontend sends HTTP requests (GET, POST, PUT, DELETE) to the backend. 📤

Backend → Database: The backend processes the requests, performs the necessary operations, and interacts with the MySQL database. 🛠️

Database → Backend: The database stores and retrieves property data as requested by the backend. 📥

Backend → Frontend: The backend sends responses back to the frontend, which displays the results to the user. 📤


## Class Design

![image](https://github.com/user-attachments/assets/59ba31dc-41eb-45ae-94c2-64f3e9a52538)


The main classes in the system are:

### 🏠 Property (Model)
- Description: Represents a property entity in the system. This class is mapped to the properties table in the database using JPA annotations.

- Attributes:

   - id: The unique identifier for the property (auto-generated by the database).

   - address: The address of the property (required).

   - price: The price of the property (required).

   - size: The size of the property (e.g., in square meters, required).

   - description: A description of the property (optional).

- Functionality: This class is used to store and retrieve property data from the database.

### 🎛️ PropertyController (Controller)
- Description: This is the REST controller that handles HTTP requests for CRUD operations on properties. It exposes endpoints for creating, reading, updating, and deleting properties, as well as searching and pagination.

- Key Endpoints:

   - GET /api/properties: Retrieves a paginated list of all properties.

   - GET /api/properties/search: Searches for properties based on filters (address, price, size) and returns paginated results.

   - GET /api/properties/{id}: Retrieves a single property by its ID.

   - POST /api/properties: Creates a new property.

   - PUT /api/properties/{id}: Updates an existing property.

   - DELETE /api/properties/{id}: Deletes a property by its ID.

- Functionality: Acts as the interface between the frontend and the backend, processing requests and returning appropriate responses.

### 🛠️ PropertyService (Service)

- Description: This class contains the business logic for managing properties. It interacts with the PropertyRepository to perform CRUD operations, pagination, and search functionality.

- Key Methods:

   - getAllProperties(int page, int size): Retrieves a paginated list of all properties.

   - searchProperties(String query, Double maxPrice, Double maxSize, int page, int size): Searches for properties based on filters and returns paginated results.

   - getPropertyById(Long id): Retrieves a property by its ID.

   - createProperty(Property property): Creates a new property.

   - updateProperty(Long id, Property propertyDetails): Updates an existing property.

   - deleteProperty(Long id): Deletes a property by its ID.

- Functionality: Handles the core logic for property management and ensures data consistency.

### 🗄️ PropertyRepository (Repository)

- Description: This interface extends JpaRepository and provides CRUD operations for the Property entity. It also includes a custom query method for searching properties based on filters.

- Key Method:

   - search(String query, Double maxPrice, Double maxSize, Pageable pageable): Searches for properties based on address, price, and size filters, and returns paginated results.

- Functionality: Acts as the data access layer, interacting directly with the database to perform CRUD operations and custom queries.
### Folder Structure 📂

```
src
├───main
│   ├───java
│   │   └───arep
│   │       └───crudsystem
│   │           │   🚀 CrudsystemApplication.java
│   │           │
│   │           ├───controller
│   │           │       🎛️ PropertyController.java
│   │           │
│   │           ├───model
│   │           │       🏠 Property.java
│   │           │
│   │           ├───repository
│   │           │       🗄️ PropertyRepository.java
│   │           │
│   │           └───service
│   │                   🛠️ PropertyService.java
│   │
│   └───resources
│       └───static
│               app.js
│             
│               index.html
│               
│               styles.css
│
└───test
    └───java
        └───arep
            └───crudsystem
                │   🧪 CrudsystemApplicationTests.java
                │
                ├───controller
                │       🧪 PropertyControllerTest.java
                │
                ├───repository
                │       🧪 PropertyRepositoryTest.java
                │
                └───service
                        🧪 PropertyServiceTest.java

```                        
## Deployment Instructions

### BackEnd

1. Add the Dockerfile to the Root of the Project
2. Configure the application.properties File

   Before building the Docker image, update the application.properties file to connect to the MySQL database using the public IP of the EC2 instance where the database is hosted. Add the following configuration:
   ```
   spring.datasource.url=jdbc:mysql://<PUBLIC_IP_OF_EC2>:3306/property_db
   spring.datasource.username=<YOUR_DB_USERNAME>
   spring.datasource.password=<YOUR_DB_PASSWORD>
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```
3. Build the Docker Image
   
   ```
   docker build --tag areptaller5 .
   ```
4. Create a Repository on Docker Hub

   ![image](https://github.com/user-attachments/assets/c27251af-16ae-468f-9117-964035dac2bb)

5. Tag the Local Image
   ```
   docker tag areptaller5 nat1505/areptaller5
   ```
6. Push the Image to Docker Hub
    ```
    docker push nat1505/areptaller5:latest
    ```
7. Launch the EC2 Instance

   ![image](https://github.com/user-attachments/assets/d582cf0b-4a65-4acd-8125-a662bbb015ab)

8. Configure AWS Security Group to Allow External Access
   - Go to the AWS EC2 Console.
      - Find your instance and open the Security Group settings.
      - Edit Inbound Rules and add a rule to allow traffic:
         - Type: Custom TCP
         - Port Range: 8080
         - Source: Anywhere (0.0.0.0/0)

   ![image](https://github.com/user-attachments/assets/cd897fb3-09f3-4b92-af97-54c7dd8d3b25)

           
9. Connect to the EC2 Instance
   ```
   ssh -i path/to/your-key.pem ec2-user@<EC2-public-IP>
   ```
10. Install Docker on the EC2 Instance
   ```
   sudo yum update -y
   sudo yum install docker -y
   sudo service docker start
   sudo usermod -a -G docker ec2-user
   ```
11. Download the Image from Docker Hub
      ```
      docker pull nat1505/areptaller5:latest
      ```
12. Run the Container
      ```
      docker run -d -p 8080:8080 --name areptaller5 nat1505/areptaller5
      ```
### EC2 Instance for MySQL

1. Create an EC2 Instance

   ![image](https://github.com/user-attachments/assets/04407574-5fcd-427a-968f-4e8389284a7c)

2. Configure AWS Security Group to Allow External Access
   - Go to the AWS EC2 Console.
      - Find your instance and open the Security Group settings.
      - Edit Inbound Rules and add a rule to allow traffic:
         - Type: Custom TCP
         - Port Range: 3306
         - Source: Anywhere (0.0.0.0/0)

   ![image](https://github.com/user-attachments/assets/7d49c039-aae5-4b41-a448-ebb341da3edd)

3. Connect to the EC2 Instance
   ```
   ssh -i path/to/your-key.pem ec2-user@<EC2-public-IP>
   ```
4. Install Docker on the EC2 Instance
      ```
      sudo yum update -y
      sudo yum install docker -y
      sudo service docker start
      sudo usermod -a -G docker ec2-user
      ```
5. Run a MySQL Container
   ```
   docker run --name database-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=property_db -p 3307:3306 -d mysql:latest
   ```
6. Use the following command to access the MySQL console
   ```
   docker exec -it database-container mysql -u root -p
   ```
## Screenshots

Here are some screenshots of the system in action:

- 🏠 Home Page: Displays a list of properties.
  
   ![Captura de pantalla 2025-03-06 000624](https://github.com/user-attachments/assets/e47c2180-819d-4e50-8bec-07d88b393563)

- ➕ Create Property: Form for adding a new property.
  
   ![pruebaCRUD](https://github.com/user-attachments/assets/64415f76-6854-49c4-925a-cb6130f0acce)

- ✏️ Edit Property: Form for updating an existing property.

   ![pruebaUpdate1](https://github.com/user-attachments/assets/bf12963f-b74b-452b-a52f-2b10bb870ea7)
  
   ![Update2](https://github.com/user-attachments/assets/a49e6595-9e71-45d1-aef3-ef8322e8b83d)


- 🗑️ Delete Property: Confirmation dialog for deleting a property.

   ![pruebaDELETE](https://github.com/user-attachments/assets/ec5cbd30-9856-4009-98aa-2d5d97c343eb)

- 🔍 Filter Properties: Filter properties by
  
   - name
     
     ![FiltroNombre](https://github.com/user-attachments/assets/4db1b83d-165b-40a1-a800-7e950f89fe47)

   - size
     
     ![FiltroTamaño](https://github.com/user-attachments/assets/4e5d029a-d495-4991-8043-c6423ad2db56)

   - price
     
     ![filtroPrecio](https://github.com/user-attachments/assets/bb4acaa2-eebe-4e0c-997b-051470248de6)

- ✅ Success Messages: User feedback for successful operations (e.g., property created, updated, or deleted).
  
     ![MensajeVerde](https://github.com/user-attachments/assets/03f726ea-5f9b-4ad3-b9c4-5d568f574eff)

- ❌ Error Messages: User feedback for failed operations (e.g., invalid input or property not found).
  
     ![MensajeRojo](https://github.com/user-attachments/assets/c81e1c94-5a1a-4104-8a17-b60f072823bc)

## Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```
![image](https://github.com/user-attachments/assets/aec5719c-d399-40f1-9ba4-3ab6bfb99bac)

### PropertyControllerTest 🎛️

- testGetAllProperties: Verifies that the GET /api/properties endpoint returns a paginated list of properties.

- testCreateProperty: Ensures that the POST /api/properties endpoint successfully creates a new property.

- testGetPropertyById: Checks that the GET /api/properties/{id} endpoint retrieves a property by its ID.

- testUpdateProperty: Validates that the PUT /api/properties/{id} endpoint updates an existing property.

- testDeleteProperty: Confirms that the DELETE /api/properties/{id} endpoint deletes a property.

### PropertyRepositoryTest 🗄️

- testFindAll: Verifies that the findAll method retrieves all properties with pagination.

- testSearchByQuery: Ensures that the search method filters properties by address or description.

- testSearchByMaxPrice: Checks that the search method filters properties by maximum price.

- testSearchByMaxSize: Validates that the search method filters properties by maximum size.

- testFindById: Confirms that the findById method retrieves a property by its ID.

- testSaveProperty: Tests that the save method correctly persists a new property.

- testDeleteProperty: Ensures that the deleteById method removes a property from the database.

### PropertyServiceTest 🛠️

- testCreateProperty: Verifies that the createProperty method saves a new property.

- testGetAllProperties: Ensures that the getAllProperties method retrieves a paginated list of properties.

- testGetPropertyById: Checks that the getPropertyById method retrieves a property by its ID.

- testUpdateProperty: Validates that the updateProperty method updates an existing property.

- testDeleteProperty: Confirms that the deleteProperty method removes a property.

## Technologies Used

- Java: Main programming language.

- Spring Boot: Backend framework.

- MySQL: Database management system.

- HTML/CSS/JavaScript: Frontend development.

- Maven: Dependency management and build tool.

- JUnit: For unit testing.

## Author

Developed by [Natalia Rojas](https://github.com/Nat15005)

## Acknowledgments

- Spring Boot Documentation: For providing comprehensive guides on building RESTful APIs.

- MySQL Documentation: For detailed information on database management.

- Open Source Community: For tools and resources that helped in the development of this project.
