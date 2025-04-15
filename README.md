# Sales Analytics System

## Overview

The **Sales Analytics System** allows for the management and analysis of sales data. The system enables the upload of CSV files containing sales information, stores this data in a database, and provides APIs to analyze the total revenue, revenue by product, category, and region over specific date ranges.

This project is built using **Spring Boot** and uses **RESTful APIs** to handle requests and responses.

## Prerequisites

Before running this project, ensure the following are installed:

- **Java**: Version 11 or higher (Recommended version: 17)
- **Maven**: For building and running the Spring Boot application
- **PostgreSQL Database**: A running instance of PostgreSQL (database configuration is located in `src/main/resources/application.properties`)
- **IDE**: IntelliJ IDEA or Eclipse (optional but recommended)
- **JDK**: Java Development Kit (JDK) 11 or higher

### Required Software Versions:
- **Java**: 11 or higher
- **Spring Boot**: 2.x or higher
- **Maven**: 3.6 or higher
- **MySQL** or **PostgreSQL**

## Getting Started

### 1. Clone the Repository

Clone the project repository to your local machine:

### 2. Install Dependencies
Navigate to the project directory and install the necessary dependencies using Maven:
```bash
mvn install
```

### 3. Database Setup
Make sure that you have a PostgreSQL database up and running. Create a database named postgres and configure it in src/main/resources/application.properties:

### 4. Running the Application
To run the Spring Boot application, use Maven:

```bash
mvn spring-boot:run
```

### 5. Testing the API Endpoints
Once the application is running, you can test the API endpoints using Postman or any other API testing tool.


### Swagger UI
Swagger provides an interactive documentation for the API endpoints. To access the Swagger UI:

Run the Spring Boot application.

Open a browser and go to the following URL:
```bash
http://localhost:8080/swagger-ui.html
This will display the Swagger UI where you can test the API endpoints directly.
```

## API Endpoints
1. Refresh Data API - Automatically loads csv from resource folder and refresh data.
Route: /api/v1/refresh-data

Method: GET
Request: http://localhost:8080/api/v1/refresh-data
Sample Response:
{
  "message": "Data refresh completed"
}

2. Total Revenue API
Route: /api/v1/analysis/total-revenue
Method: GET
Request: http://localhost:8080/api/v1/analysis/total-revenue?startDate=2023-01-01&endDate=2023-12-31
Sample Response:
{
  "totalRevenue": 123456.78
}

4. Revenue by Product API
Route: /api/v1/analysis/revenue-by-product
Method: GET
Request: http://localhost:8080/api/v1/analysis/revenue-by-product?startDate=2023-01-01&endDate=2023-12-31
Sample Response:
{
  "Product A": 50000.00,
  "Product B": 30000.00,
  "Product C": 40000.00
}

5. Revenue by Category API
Route: /api/v1/analysis/revenue-by-category
Method: GET
Request: http://localhost:8080/api/v1/analysis/revenue-by-category?startDate=2023-01-01&endDate=2023-12-31
Sample Response:
{
  "Electronics": 80000.00,
  "Furniture": 50000.00,
  "Clothing": 40000.00
}

5. Revenue by Region API
Route: /api/v1/analysis/revenue-by-region
Method: GET
Request: http://localhost:8080/api/v1/analysis/revenue-by-region?startDate=2023-01-01&endDate=2023-12-31
Sample Response:
{
  "North": 60000.00,
  "South": 70000.00,
  "East": 50000.00,
  "West": 40000.00
}


