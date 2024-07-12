# Banking App

## Description
The Banking App is a Spring Boot application that allows users to manage their bank accounts. It supports operations such as creating accounts, depositing and withdrawing funds, transferring money between accounts, and viewing transaction history.

## Technologies Used
- Java (Version 17)
- Spring Boot (Version 3.3.1)
- Spring Data JPA for database interaction
- MySQL as the database
- Lombok for reducing boilerplate code
- Maven for project management

## Features
- Create a new bank account
- View account details
- Deposit and withdraw funds
- Transfer funds between accounts
- View transaction history for an account
- Delete an account

## API Endpoints

### Account Management
- **POST /account** - Create a new account
- **GET /account/{id}** - Retrieve account details by ID
- **PUT /account/{id}/deposit** - Deposit funds into an account
- **PUT /account/{id}/withdraw** - Withdraw funds from an account
- **GET /account** - Retrieve all accounts
- **DELETE /account/{id}/delete** - Delete an account

### Fund Transfer
- **POST /account/transfer** - Transfer funds from one account to another

### Transaction History
- **GET /account/{id}/transaction** - Retrieve all transactions for an account

## Setup and Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/ravi3018/Banking-App.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Banking-App
    ```

3. Update the `application.properties` file to configure your MySQL database connection.

4. Build the project using Maven:
    ```bash
    mvn clean install
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Database Configuration
Make sure to set up your MySQL database and update the connection details in the `application.properties` file. Example configuration:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

## Contributions

Contributions are welcome! Please feel free to submit a pull request or open an issue.


