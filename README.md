# Property Management Secure App Design 🏢🔒

## Project Summary 📝
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

#### Security Features 🔐

- TLS Encryption:
   - Secure transmission of data using TLS certificates generated through Let’s Encrypt.
- Login Security:
   - Implements user authentication with passwords securely stored as hashes using BCrypt.
- Asynchronous Client:
   - The HTML+JavaScript client uses async techniques to optimize performance while maintaining secure communication.

#### Deployment 🚀

- AWS Infrastructure:
   - The frontend (Apache) and backend (Spring) are deployed on separate EC2 instances.
- Let’s Encrypt Certificates:
   - Certificates are generated and installed for both the frontend and backend to enable HTTPS.

## Deployment and Key Features Video 🎥


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

To log in, you need to register a new user by sending a POST request to the following endpoint with a username and password:

```
https://localhost/auth/register
```

![image](https://github.com/user-attachments/assets/9e956032-8a94-4751-a894-ed79976de410)


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

![image](https://github.com/user-attachments/assets/3c6d0f9a-4e02-46e2-8a02-576b4b4f8251)



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
## Deployment and Security Steps

### Backend Deployment (Spring)

1. Step 1: Launch an EC2 Instance for the Backend

   ![image](https://github.com/user-attachments/assets/b7f3f2c8-fd69-41c9-9653-143f4bbd2f16)

2. Step 2: Configure the Security Group

   ![image](https://github.com/user-attachments/assets/0ba9f351-cd49-4022-83d5-409f9fdd9a08)
           
3. Step 3: Connect to the EC2 Instance
   ```
   ssh -i path/to/your-key.pem ec2-user@<EC2-public-IP>
   ```
4. Step 4: Install Java
   ```
   sudo yum install java-17-amazon-corretto-devel -y
   ```
5. Step 5: Upload the JAR File
      ```
      scp -i /path/to/your-key.pem /path/to/your-app.jar ec2-user@<public-ip-of-instance>:/home/ec2-user/
      ```
6. Step 6: Install Certbot
      ```
      sudo yum install certbot -y 
      ```
7. Step 7: Associate Elastic IPs
      
   ![image](https://github.com/user-attachments/assets/dbd5321b-f143-41d0-bbb1-808e739e9b98)

### Generate Certificates (Backend)

1. Step 1: Run Certbot to generate SSL certificates:
      ```
      sudo certbot certonly --standalone -d your-backend-domain.com
      ```
2. Step 2: Convert Certificates to PKCS12 Format:
      ```
      sudo openssl pkcs12 -export -in /etc/letsencrypt/live/your-backend-domain.com/fullchain.pem \
   -inkey /etc/letsencrypt/live/your-backend-domain.com/privkey.pem \
   -out /home/ec2-user/keystore.p12 -name your-alias -passout pass:your-password
      ```
3. Step 3: Move the Keystore and Adjust Permissions:
      ```
      sudo mv /home/ec2-user/keystore.p12 /etc/ssl/certs/
      sudo chmod 600 /etc/ssl/certs/keystore.p12
      ```
4. Step 4: Configure application.properties:
      ```
      server.port=443
      server.ssl.enabled=true
      server.ssl.key-store=/etc/ssl/certs/keystore.p12
      server.ssl.key-store-password=your-password
      server.ssl.key-alias=your-alias
      ```
 5. Step 5: Start the Spring Boot application:
      ```
      java -jar your-app.jar
      ```     
### Frontend Deployment (Apache)

1. Step 1: Launch an EC2 Instance for the Frontend

   ![image](https://github.com/user-attachments/assets/3c9cf18d-f189-4bfe-aa06-d5ec99d53702)


2. Step 2: Configure the Security Group

   ![image](https://github.com/user-attachments/assets/a35abb44-6d93-438f-b8fb-fc495a238104)

           
3. Step 3: Connect to the EC2 Instance
   ```
   ssh -i /path/to/your-key.pem ec2-user@<public-ip-of-instance>
   ```
4. Step 4: Install Apache:
   ```
   sudo yum install httpd -y
   ```

5. Step 5: Enable Apache to Start Automatically:
   ```
   sudo systemctl enable httpd
   ```

6. Step 6: Start Apache:
   ```
   sudo yum install httpd -y
   ```

7. Step 7: Create a directory for your frontend files and change ownership:
   ```
   sudo mkdir /var/www/html/frontend
   sudo chown -R ec2-user:ec2-user /var/www/html/frontend
   ```
8. Step 8: Upload Frontend Files:
   ```
   scp -i /path/to/your-key.pem -r /path/to/frontend/* ec2-user@<public-ip-of-instance>:/var/www/html/frontend/
   ```

9. Step 9: Adjust File Permissions:
   ```
   sudo chmod -R 755 /var/www/html/frontend
   ```

10. Step 10: Create a Configuration File for the Domain:
      ```
      sudo nano /etc/httpd/conf.d/frontend.conf
      ```

   - Add the following configuration:
   
      ```
        <VirtualHost *:80>
          ServerName your-frontend-domain.com
          DocumentRoot /var/www/html/frontend
          <Directory /var/www/html/frontend>
              AllowOverride All
              Require all granted
          </Directory>
      </VirtualHost>
      ```
11. Step 11: Restart Apache:
      ```
      sudo systemctl restart httpd
      ```

### Generate Certificates (Frontend)

1. Step 1: Install Certbot:
   ```
   sudo yum install certbot-apache -y
   ```

2. Step 2: Generate Let’s Encrypt certificates for your frontend domain:
   ```
   sudo certbot --apache -d your-frontend-domain.com
   ``` 

## Screenshots

Here are some screenshots of the system in action:

- 📜 Frontend Certificate

  ![Imagen de WhatsApp 2025-03-12 a las 19 41 26_09ebb73b](https://github.com/user-attachments/assets/7af80b41-cdff-4145-b930-1b555e2868dc)

- 🔐 Login

   ![Imagen de WhatsApp 2025-03-12 a las 19 41 59_91a1fff1](https://github.com/user-attachments/assets/15a1f2de-50ce-4486-91a1-f6bdc1aff14e)

- 🏠 Home Page: Displays a list of properties.

  ![Imagen de WhatsApp 2025-03-12 a las 19 42 19_827796f1](https://github.com/user-attachments/assets/d3ea1f20-878d-48e2-aa87-185729d749e4)

- ➕ Create Property: Form for adding a new property.
  
   ![Imagen de WhatsApp 2025-03-12 a las 19 43 28_3ac93b39](https://github.com/user-attachments/assets/0f2ddcbd-da40-4ba4-a0f7-c4a8f3ed92a3)


- ✏️ Edit Property: Form for updating an existing property.

   ![Imagen de WhatsApp 2025-03-12 a las 19 43 50_a88b564e](https://github.com/user-attachments/assets/9c300acb-7b8c-4ffa-8b1c-473dd00c370d)


   ![Imagen de WhatsApp 2025-03-12 a las 19 44 01_6090482e](https://github.com/user-attachments/assets/e56578b3-9168-48dd-9f0b-79dfeacbb651)


- 🗑️ Delete Property: Confirmation dialog for deleting a property.

   ![Imagen de WhatsApp 2025-03-12 a las 19 44 26_75b960aa](https://github.com/user-attachments/assets/f0849819-4d11-4c10-a56d-8406ac730928)

- 🔄 Back Working:

   ![Imagen de WhatsApp 2025-03-12 a las 19 45 06_304f762a](https://github.com/user-attachments/assets/7332a319-3443-4a74-8b98-652afca7d852)

- 🖥️ Backend Certificate:

   ![Imagen de WhatsApp 2025-03-12 a las 19 45 20_116eac78](https://github.com/user-attachments/assets/a645652f-eb08-4dea-a597-1bfeb0fe68b4)


- 🔑 Hashed Passwords:

   ![Imagen de WhatsApp 2025-03-12 a las 19 46 07_44517938](https://github.com/user-attachments/assets/6dacccdd-7eee-4e5d-aadf-c6bec3993e3e)

## Running Tests

To run the unit tests, use the following command:

```bash
mvn test
```
![image](https://github.com/user-attachments/assets/c3e70b8a-79b1-4026-b8d0-cc079d45536a)


### PropertyControllerTest 🎛️

- testGetAllProperties: Verifies that the GET /api/properties endpoint returns a paginated list of properties.

- testCreateProperty: Ensures that the POST /api/properties endpoint successfully creates a new property.

- testGetPropertyById: Checks that the GET /api/properties/{id} endpoint retrieves a property by its ID.

- testUpdateProperty: Validates that the PUT /api/properties/{id} endpoint updates an existing property.

- testDeleteProperty: Confirms that the DELETE /api/properties/{id} endpoint deletes a property.

### AuthControllerTest 🎛️

- testRegisterUserSuccess: Verifies that the POST /register endpoint successfully registers a new user.

- testRegisterUserFailure: Ensures that the POST /register endpoint fails when the username already exists.

- testLoginSuccess: Checks that the POST /login endpoint successfully authenticates a user and returns a redirect URL.

- testLoginFailureInvalidCredentials: Validates that the POST /login endpoint fails with invalid credentials.

- testLoginFailureException: Confirms that the POST /login endpoint handles exceptions correctly.

### PropertyRepositoryTest 🗄️

- testFindAll: Verifies that the findAll method retrieves all properties with pagination.

- testSearchByQuery: Ensures that the search method filters properties by address or description.

- testSearchByMaxPrice: Checks that the search method filters properties by maximum price.

- testSearchByMaxSize: Validates that the search method filters properties by maximum size.

- testFindById: Confirms that the findById method retrieves a property by its ID.

- testSaveProperty: Tests that the save method correctly persists a new property.

- testDeleteProperty: Ensures that the deleteById method removes a property from the database.

### UserRepositoryTest 🗄️

- testFindByUsername: Verifies that the findByUsername method retrieves a user by their username.

- testFindByUsernameNotFound: Ensures that the findByUsername method returns an empty result when the user is not found.

### PropertyServiceTest 🛠️

- testCreateProperty: Verifies that the createProperty method saves a new property.

- testGetAllProperties: Ensures that the getAllProperties method retrieves a paginated list of properties.

- testGetPropertyById: Checks that the getPropertyById method retrieves a property by its ID.

- testUpdateProperty: Validates that the updateProperty method updates an existing property.

- testDeleteProperty: Confirms that the deleteProperty method removes a property.

### UserServiceTest 🛠️

- testRegisterUserSuccess: Tests that the registerUser method successfully registers a new user with an encoded password.

- testRegisterUserFailureUsernameExists: Ensures that the registerUser method throws an exception when the username already exists.

- testAuthenticateSuccess: Verifies that the authenticate method successfully authenticates a user with valid credentials.

- testAuthenticateFailureUserNotFound: Checks that the authenticate method throws an exception when the user is not found.

- testAuthenticateFailureInvalidPassword: Validates that the authenticate method fails when the password is incorrect.

### Technologies Used

- Java: Main programming language.

- Spring Boot: Backend framework.

- MySQL: Database management system.

- HTML/CSS/JavaScript: Frontend development.

- Maven: Dependency management and build tool.

- JUnit: For unit testing.

### Author

Developed by [Natalia Rojas](https://github.com/Nat15005)

## Acknowledgments

- Spring Boot Documentation: For providing comprehensive guides on building RESTful APIs.

- Open Source Community: For tools and resources that helped in the development of this project.
