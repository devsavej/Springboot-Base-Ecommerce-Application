# 🛒 E-Commerce Backend

A RESTful E-Commerce backend application built using **Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, and Spring Security**.

The project provides APIs for customer management, products, categories, shopping carts, orders, and checkout functionality.

## 🚀 Features

* Customer registration and management
* Spring Security authentication & authorization
* Role-based access control
* Product & category management
* Shopping cart and cart items
* Order and order item management
* Checkout functionality
* Input validation
* Global exception handling
* RESTful APIs
* MySQL database integration

## 🛠️ Technologies

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Spring Security
* MySQL
* Maven
* Postman
* Git & GitHub

## 🏗️ Architecture

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
JPA / Hibernate
    ↓
MySQL
```

## 📦 Main Modules

```text
Customer
Category
Product
Cart
CartItem
Order
OrderItem
```

## 🔄 Checkout Flow

```text
Customer
   ↓
Cart
   ↓
Cart Items
   ↓
Calculate Total
   ↓
Create Order
   ↓
Create Order Items
```

## ⚙️ Setup

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
```

### 2. Configure MySQL

Create a database and update your local `application.properties` with your database credentials.

### 3. Run the project

```bash
mvn spring-boot:run
```

The application will start on the configured Spring Boot port.

## 🔐 Security

Spring Security is implemented for:

* Authentication
* Authorization
* Password encryption
* Role-based access control
* Protected REST endpoints

## 📌 Future Improvements

* JWT Authentication
* Swagger/OpenAPI documentation
* Payment Gateway
* Product search & filtering
* Order tracking
* Docker deployment

## 👨‍💻 Author

Mohammad Savej

GitHub: `https://github.com/devsavej`

LinkedIn: https://www.linkedin.com/in/mohammad-savej-5bb9ab32a/
