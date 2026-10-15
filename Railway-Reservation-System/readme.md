# 🚆 Railway Reservation System – Core Java Project

## 📌 Project Overview

The **Railway Reservation System** is a console-based Java application developed using Core Java. It simulates a basic railway ticket reservation system where users can register, log in, search for trains, book tickets, view booking details, and cancel reservations.

This project demonstrates important Java programming concepts, including Object-Oriented Programming (OOP), Collections Framework, exception handling, and date handling.

## 🎯 Objectives

- Develop a menu-driven railway reservation application.
- Implement user registration and login.
- Display and search available trains.
- Book tickets with passenger details.
- Generate unique PNR numbers.
- Manage seat availability.
- View booking history and ticket details.
- Cancel confirmed reservations and release seats.

## ✨ Features

- **User Registration:** Create a new passenger account.
- **User Login:** Access the passenger menu using login credentials.
- **View Trains:** Display available trains, routes, timings, fares, and seats.
- **Search Trains:** Find trains by source and destination.
- **Ticket Booking:** Enter passenger details and journey date.
- **PNR Generation:** Generate a unique booking reference for each reservation.
- **Seat Management:** Update available seats after booking and cancellation.
- **Booking History:** View all reservations associated with the logged-in user.
- **PNR Search:** Retrieve booking details using a PNR number.
- **Ticket Cancellation:** Cancel a reservation and restore the booked seats.
- **Input Validation:** Handle invalid menu choices and incorrect numeric input.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Java Concepts:** Core Java, OOP, Classes, Objects, Methods, Encapsulation
- **Collections:** ArrayList, HashMap, LinkedHashMap
- **Exception Handling:** try-catch
- **Date Handling:** LocalDate
- **Input Handling:** Scanner
- **Interface:** Command-Line Interface (CLI)
- **IDE:** VS Code / IntelliJ IDEA / Eclipse

## 📂 Project Structure

```text
RailwayReservationSystem/
│
├── RailwayReservationSystem.java
└── README.md
```

## ⚙️ Requirements

Before running this project, ensure you have:

- Java Development Kit (JDK) installed.
- A Java-compatible IDE or Terminal.
- Basic knowledge of Core Java.

Check your Java installation:

```bash
java -version
javac -version
```

## ▶️ How to Run the Project

### Step 1: Clone the repository

```bash
git clone https://github.com/Ayyappa1295/railway-reservation-system.git
```

### Step 2: Open the project folder

```bash
cd railway-reservation-system
```

### Step 3: Compile the Java program

```bash
javac RailwayReservationSystem.java
```

### Step 4: Run the application

```bash
java RailwayReservationSystem
```

## 🖥️ Application Menu

### Main Menu

```text
==============================================
       INDIAN RAILWAY RESERVATION SYSTEM
==============================================

1. Register
2. Login
3. View All Trains
4. Exit

Enter your choice:
```

### Passenger Menu

```text
========== PASSENGER MENU ==========

1. View All Trains
2. Search Trains
3. Book Ticket
4. View My Bookings
5. Search Ticket Using PNR
6. Cancel Ticket
7. Logout

Enter your choice:
```

## 🚉 Sample Train Details

The application includes sample train data such as:

| Train Number | Train Name | Source | Destination |
|---|---|---|---|
| 12625 | Karnataka Express | Bangalore | Delhi |
| 12028 | Shatabdi Express | Bangalore | Chennai |
| 12728 | Godavari Express | Hyderabad | Visakhapatnam |
| 12863 | Howrah Express | Bangalore | Howrah |
| 12603 | Hyderabad Express | Chennai | Hyderabad |

*Note: Train schedules, routes, seat counts, and fares are sample data for demonstration purposes only.*

## 📖 Core Java Concepts Demonstrated

| Concept | Application |
|---|---|
| Classes and Objects | User, Train, and Booking |
| Encapsulation | Organizing related data and operations |
| Collections Framework | Managing trains, users, and bookings |
| ArrayList | Storing train information |
| HashMap | Managing booking records |
| LinkedHashMap | Maintaining user insertion order |
| Loops and Conditions | Menu navigation and validation |
| Methods | Separating application functionality |
| Exception Handling | Handling invalid numeric and date input |
| LocalDate | Validating journey dates |
| String Handling | Comparing stations and processing input |

## 🔄 Application Workflow

1. Start the application.
2. Register a new account.
3. Log in using the registered credentials.
4. View available trains or search by route.
5. Select a train and enter passenger details.
6. Enter the journey date and number of seats.
7. Receive a ticket with a PNR number.
8. View booking history or search by PNR.
9. Cancel a booking when required.
10. Log out or exit the application.

## 🚀 Future Enhancements

- Integrate MySQL using JDBC for permanent data storage.
- Add an administrator login and train management.
- Support multiple passengers in a single reservation.
- Implement different travel classes such as Sleeper, AC 3 Tier, and AC 2 Tier.
- Add source-to-destination route filtering with journey dates.
- Generate printable tickets.
- Implement cancellation policies and refund calculations.
- Improve password security.
- Build a graphical interface using Java Swing or JavaFX.
- Develop a web-based version using Spring Boot and a frontend framework.

## ⚠️ Limitations

- Data is stored in memory and is lost when the application terminates.
- Train information and fares are predefined sample values.
- The application does not connect to Indian Railways or IRCTC.
- Online payments, real-time seat availability, and actual ticket issuance are not supported.
- Passwords are stored as plain text in this learning version.

## 🎓 Learning Outcomes

By developing this project, you can practice:

- Applying Core Java concepts to a practical use case.
- Designing a menu-driven application.
- Managing related data with Java Collections.
- Implementing booking and cancellation logic.
- Validating user input and journey dates.
- Organizing code into reusable methods and classes.
- Understanding the fundamentals of a reservation workflow.

## 👨‍💻 Author

**Kaveri Ayyappa**

GitHub: [@Ayyappa1295](https://github.com/Ayyappa1295)

## 📄 License

This project is intended for educational and portfolio purposes. You may modify it to support your Java learning and project development.

---

⭐ **If you find this project useful, consider giving the repository a star!**

*Built with Java ☕ | Core Java Project | Railway Reservation System*
