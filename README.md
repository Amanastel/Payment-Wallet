# Payment System Backend

Welcome to the Payment System Backend project! This project provides backend functionalities for managing user accounts, wallets, and transactions.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- - [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- [Acknowledgements](#acknowledgements)

## Introduction

This backend application is designed to handle user registration, wallet management, and transaction recording for a payment system.

## Features

- User registration with roles
- Wallet creation and management
- Adding and withdrawing money from wallets
- Viewing transaction history

## Getting Started

### Prerequisites

- Java 8 or higher
- Spring Boot
- MySQL 
- Maven (for building)

### Installation

1. Clone the repository: `git clone https://github.com/amanastel/payment-system-backend.git`
2. Navigate to the project directory: `cd payment-system-backend`
3. Customize the database configuration in `src/main/resources/application.properties` (if needed)
4. Build the project: `mvn clean install`

## Usage

1. Run the application: `mvn spring-boot:run`
2. Access the API endpoints using tools like Postman or a browser.

## API Endpoints

- `GET /users/hello`: Test endpoint to check if the server is running.
- `POST /users`: Register a new user.
- `GET /users/{email}`: Get user details by email.
- `GET /admin/users`: Get details of all users.
- `PATCH /users/{email}`: Update user details by email.
- `DELETE /users/{email}`: Delete a user by email.
- `GET /admin/role/{role}`: Get details of all users with a specific role.
- `GET /signIn`: Get details of the logged-in user.
- Swagger UI Documentation: [http://localhost:8888/swagger-ui/index.html#/](http://localhost:8888/swagger-ui/index.html#/)

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them.
4. Push the changes to your fork.
5. Create a pull request explaining your changes.

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For any questions or suggestions, feel free to contact us at amankumar.ak0012@gmail.com.

## Acknowledgements

Special thanks to the open-source community for their contributions.
