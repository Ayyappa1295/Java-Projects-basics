# Vehicle Rental Management System

A console-based Vehicle Rental Management System developed using Core Java. This project helps manage vehicles, customers, rental bookings, returns, and rental billing.

## Features

- Add and view vehicles
- Search vehicles by ID
- Register and view customers
- Rent available vehicles
- Calculate rental charges automatically
- Return rented vehicles
- View active rentals and rental history
- Generate rental invoices in the console
- Dashboard with vehicle and rental statistics
- Persistent storage using text files
- Input validation and exception handling

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- ArrayList and Collections
- File Handling
- Exception Handling
- Java Date and Time API
- Scanner for console input

## Project Structure

```text
vehicle-rental-system/
├── VehicleRentalSystem.java
├── vehicles.txt
├── customers.txt
├── rentals.txt
└── README.md
```

The text data files are created automatically when the application saves its data.

## Requirements

- JDK 8 or later
- Terminal or Command Prompt
- Any Java-compatible IDE (optional)

## How to Run

1. Clone or download this repository.
2. Open a terminal in the project directory.
3. Compile the source file:

   ```bash
   javac VehicleRentalSystem.java
   ```

4. Run the application:

   ```bash
   java VehicleRentalSystem
   ```

## Rental Charge Calculation

Rental Charge = Daily Rental Price × Number of Days

Example:

- Daily rental price: ₹2,000
- Rental period: 3 days
- Total rental charge: ₹6,000

## Data Storage

The application stores vehicle, customer, and rental records in separate text files. Records are loaded when the program starts and saved when changes occur.

## Learning Outcomes

This project demonstrates:

- Classes and objects
- Encapsulation through class-based data organization
- ArrayList-based record management
- Methods and control structures
- File reading and writing
- Exception handling
- Date handling
- Basic business logic and billing

## Future Enhancements

- JDBC and MySQL database integration
- Login and administrator authentication
- Java Swing or JavaFX graphical interface
- Spring Boot REST API
- Web-based vehicle booking
- Rental extensions, late fees, and payment tracking
- Booking availability by date

## Disclaimer

This is an educational console application. It does not process actual payments, verify driving licences, calculate late fees, or provide production-grade security.

## Author

Kaveri Ayyappa

https://github.com/Ayyappa1295
