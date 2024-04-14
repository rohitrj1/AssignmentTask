# AssignmentTask
Introduction
This project is designed to demonstrate the capabilities of Spring Boot combined with Spring Security, JWT for security, and an in-memory H2 database for managing user logins and order processing. The application provides APIs to handle user authentication, create orders, and manage order items dynamically.

Features
. User Login and JWT Token Authentication
. CRUD operations for orders and order items
. Integration with H2 In-memory Database
. Exception handling and validation

Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites
. Java 11 or higher
. Maven
. Any IDE like IntelliJ IDEA, Eclipse or Spring Tool Suite

Usage
Once the application is running, 
you can use any API client like Postman or curl to interact with the API.
The application provides endpoints for user authentication, creating orders, and managing order items.

Example Request
1. To signUp The user or create user
   POST http://localhost:9090/api/auth/signUp
   Content-Type: application/json
   {
    "name":"Rohit ",
    "mobileNo":"1234567890",
    "password":"rohit"    // remember the password it will store in db decrypt form
}

2. To create an order:
    POST /api/orders/user_id    // here you can give the userid beacuse which user should order or mapped with a particular order
    Authorization: Bearer <Your_JWT_Token>
    Content-Type: application/json
{
     "items": [
        {
            "itemName": "Apple",
            "price": 0.99
        },
        {
            "itemName": "Orange",
            "price": 1.29
        }
    ]
}

3. If user wants to get his order Then go throuh this api
   GET  /api/orders/user_id
   Authorization: Bearer <Your_JWT_Token>


Configuration
Configuration settings can be found in src/main/resources/application.properties.
Adjust database settings, JWT secrets, and other configurations as needed.
