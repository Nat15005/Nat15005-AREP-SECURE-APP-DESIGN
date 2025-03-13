# Property Management Secure App Design

## Project Summary
This project is a secure, scalable web application designed and deployed using AWS infrastructure. It focuses on implementing modern security practices, including authentication, password hashing, and TLS encryption for secure communication between the client and backend servers.

The application consists of two main components:

1. Frontend (Apache Server):

   - Serves an asynchronous HTML+JavaScript client.

   - Delivers client-side code over a secure connection using TLS.

   - Ensures data integrity and confidentiality during download.

2. Backend (Spring Framework):

   - Handles backend services with RESTful API endpoints.

   - Protects communication using TLS.

   - Implements secure login functionality with password hashing.

Both the frontend and backend are deployed on separate EC2 instances in AWS, ensuring a secure and scalable architecture.

### Key Features:

#### Security Features

- TLS Encryption:
   - Secure transmission of data using TLS certificates generated through Let’s Encrypt.
- Login Security:
   - Implements user authentication with passwords securely stored as hashes using BCrypt.
- Asynchronous Client:
   - The HTML+JavaScript client uses async techniques to optimize performance while maintaining secure communication.

#### Deployment

- AWS Infrastructure:
   - The frontend (Apache) and backend (Spring) are deployed on separate EC2 instances.
- Let’s Encrypt Certificates:
   - Certificates are generated and installed for both the frontend and backend to enable HTTPS.

## Deployment and Key Features Video



https://github.com/user-attachments/assets/1880f278-2fca-431d-98c8-a8d7a14dfac0


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
   git clone https://github.com/Nat15005/Nat15005-AREP-SECURE-APP-DESIGN.git
   ```
2. **Navigate to the project folder:**
   ```bash
   cd Nat15005-AREP-SECURE-APP-DESIGN.git
   ```
3. **Build the project using Maven:**
   ```bash
   mvn clean install
   ```

### Running the Server

Once the project is built, you can start the server with the following command:

```bash
mvn spring-boot:run
```

The server will start and listen on port `443`.

### Accessing the Application

Open your web browser and go to:

```
https://localhost:443/
```

You should see the main page of the application.

![image](https://github.com/user-attachments/assets/3fa1db0e-702b-4ac5-82e6-663d50943425)


## System Architecture
The system is divided into three main components:

![image](https://github.com/user-attachments/assets/7ed8fe25-f91d-486e-8aa8-42bf6efd93bb)


1. *Frontend(Apache)🌐*  

   - Hosts the HTML+JavaScript client.
   - Serves static files over HTTPS using Let’s Encrypt certificates.
   - Communicates with the backend via secure REST API calls.

2. *Backend (Spring)🛠️* 

   - Provides RESTful API endpoints for user authentication and CRUD operations.
   - Uses TLS to secure communication with the frontend.
   - Stores user passwords as hashes in the database.

3. *Database🗃️*  

   - MySQL is used to store property data. The database is accessed via JPA/Hibernate in the backend, ensuring seamless data persistence and retrieval.
     
4. *AWS EC2 Instances☁️* 

   - Frontend and backend are deployed on separate EC2 instances.
   - Security Groups are configured to restrict access to authorized traffic only.
  
### Interaction Flow 🔄
Frontend → Backend: HTTPS request for user authentication (login/register).

Backend → Database: Query to verify or store user credentials (hashed passwords).

Backend → Frontend: HTTPS response with authentication result (success/failure).

Frontend → Backend: HTTPS request for property data (CRUD operations).

Backend → Database: Query to retrieve or update property data.

Backend → Frontend: HTTPS response with property data or operation result.


## Class Design

![image](https://github.com/user-attachments/assets/59ba31dc-41eb-45ae-94c2-64f3e9a52538)


The main classes in the system are:

### 🏠 Property (Model)
- Description: Represents a property entity, mapped to the properties table. Stores and retrieves property data (id, address, price, size, description).

### 🎛️ PropertyController (Controller)
- Description: REST controller for CRUD operations on properties. Exposes endpoints for listing, searching, creating, updating, and deleting properties.

### 🛠️ PropertyService (Service)

- Description: Handles business logic for property management, including pagination, search, and CRUD operations.

### 🗄️ PropertyRepository (Repository)

- Description: Extends JpaRepository for CRUD operations and custom search queries on properties.

### 👤 User (Model)

- Description: Represents a user entity, mapped to the users table. Stores user data (id, username, hashed password).

### 🎛️ AuthController (Controller)

- Description: REST controller for user authentication. Exposes endpoints for registration and login.


### 🛠️ UserService (Service)

- Description: Manages user registration and authentication, including password hashing and credential verification.

### 🗄️ UserRepository (Repository)

- Description: Extends JpaRepository for CRUD operations and finding users by username.
  
### 🔒 SecurityConfig (Configuration)

- Description: Configures Spring Security, disabling CSRF, permitting public endpoints, and enforcing password hashing with BCryptPasswordEncoder.
  
### 🌐 CorsConfig (Configuration)

- Description: Configures CORS to allow requests from https://localhost, supporting GET, POST, PUT, DELETE, and credentials.

  
### Folder Structure 📂

```
src
├───main
│   ├───java
│      └───arep
│          └───crudsystem
│              │   🚀 CrudsystemApplication.java
│              │
│              ├───config
│              │       🌐 CorsConfig.java
│              │       🔒 SecurityConfig.java
│              │
│              ├───controller
│              │       🎛️ AuthController.java
│              │       🎛️ HelloWorldController.java
│              │       🎛️ PropertyController.java
│              │
│              ├───model
│              │       🏠 Property.java
│              │       👤 User.java
│              │
│              ├───repository
│              │       🗄️ PropertyRepository.java
│              │       🗄️ UserRepository.java
│              │
│              └───service
│                     🛠️ PropertyService.java
│                      🛠️ UserService.java
│   
│
└───test
    └───java
        └───arep
            └───crudsystem
                │   🧪 CrudsystemApplicationTests.java
                │
                ├───controller
                │       🧪 AuthControllerTest.java
                │       🧪 PropertyControllerTest.java
                │
                ├───repository
                │       🧪 PropertyRepositoryTest.java
                │       🧪 UserRepositoryTest.java
                │
                └───service
                        🧪 PropertyServiceTest.java
                        🧪 UserServiceTest.java

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
