# 📚 Library Management System – Core Java

## 📌 Project Overview

The **Library Management System** is a console-based Java application developed to manage library operations efficiently. It allows users to add books, register members, issue and return books, search for books, track overdue books, and view library statistics.

The project is built using Core Java concepts, Object-Oriented Programming (OOP), Collections, Exception Handling, and File Handling.

## 🎯 Objectives

- Simplify library book management.
- Maintain book and member records.
- Manage book issuing and returning operations.
- Track due dates and calculate late fees.
- Store records locally using text files.
- Practise Core Java programming concepts.

## ✨ Features

### 📖 Book Management
- Add new books.
- View all books.
- Search books by ID, title, or author.
- Display available books.
- Track total and available copies.

### 👥 Member Management
- Register new library members.
- View all registered members.
- Store member IDs, names, and phone numbers.

### 🔄 Issue and Return Management
- Issue books to registered members.
- Prevent issuing unavailable books.
- Prevent duplicate active issues of the same book to the same member.
- Record issue dates and due dates.
- Process book returns.
- Calculate late fees at ₹2 per overdue day.

### 📊 Reports and Statistics
- View issued-book records.
- Identify overdue books.
- Calculate estimated late fees.
- Display library statistics, including total copies, available copies, active loans, and returned books.

### 💾 File Handling
- Save book records in `books.txt`.
- Save member records in `members.txt`.
- Save loan records in `loans.txt`.
- Load saved records when the application starts.

## 🛠️ Technologies Used

- **Programming Language:** Java
- **Java Version:** Java 8 or later
- **Concepts:** Object-Oriented Programming (OOP)
- **Collections:** ArrayList
- **Exception Handling:** try-catch
- **File Handling:** FileReader, FileWriter, BufferedReader, PrintWriter
- **Date Handling:** LocalDate
- **Interface:** Console-based user interface
- **Storage:** Local text files
- **IDE:** VS Code / IntelliJ IDEA / Eclipse

## 📂 Project Structure

```text
LibraryManagementSystem/
│
├── LibraryManagementSystem.java
├── books.txt
├── members.txt
├── loans.txt
└── README.md
```

The three text files are created automatically when data is saved. They do not need to be created manually.

## ⚙️ Installation and Execution

### Prerequisites

1. Install Java JDK 8 or later.
2. Verify that Java is installed.
3. Open Terminal or Command Prompt.

Check the Java version:

```bash
java -version
javac -version
```

### Step 1: Clone the Repository

Replace `YOUR_USERNAME` with your GitHub username.

```bash
git clone https://github.com/YOUR_USERNAME/LibraryManagementSystem.git
```

### Step 2: Navigate to the Project

```bash
cd LibraryManagementSystem
```

### Step 3: Compile the Program

```bash
javac LibraryManagementSystem.java
```

### Step 4: Run the Application

```bash
java LibraryManagementSystem
```

## 🖥️ Application Menu

```text
==========================================
       LIBRARY MANAGEMENT SYSTEM
==========================================

--------------- MAIN MENU ---------------
1.  Add New Book
2.  View All Books
3.  Search Book
4.  Register Member
5.  View All Members
6.  Issue Book
7.  Return Book
8.  View Available Books
9.  View Issued Books
10. View Overdue Books
11. Library Statistics
12. Save and Exit
-----------------------------------------
Enter your choice:
```

## 🧪 Sample Test Data

### Add a Book

```text
Book ID: 101
Book Title: Java Programming
Author Name: James Gosling
Category: Programming
Number of Copies: 5
```

### Register a Member

```text
Member ID: 1001
Member Name: Rahul
Phone Number: 9876543210
```

### Issue a Book

```text
Book ID: 101
Member ID: 1001
```

The application records the issue date and sets the due date to 14 days after the issue date.

## 🧠 Core Java Concepts Demonstrated

| Concept | Application |
|---|---|
| Classes and Objects | Book, Member, and Loan |
| Constructors | Initialize book, member, and loan records |
| ArrayList | Store library records |
| Methods | Separate application operations |
| Conditional Statements | Validate operations and user choices |
| Loops | Display and search records |
| Exception Handling | Handle invalid inputs and file errors |
| File Handling | Persist records between runs |
| LocalDate | Manage issue, due, and return dates |
| Searching | Find books and members using IDs or keywords |

## 🚀 Future Enhancements

- Integrate MySQL for database storage.
- Develop a graphical user interface using Java Swing or JavaFX.
- Add administrator and librarian login.
- Generate downloadable reports.
- Add book reservations and renewals.
- Introduce configurable late-fee policies.
- Add advanced search and sorting.
- Create a web-based version using Spring Boot.

## 🎓 Learning Outcomes

This project demonstrates practical knowledge of Core Java, OOP, collections, file handling, input validation, date calculations, and application design. It is suitable for beginners who want to practise Java programming through a real-world management application.

## 👨‍💻 Author

**Kaveri Ayyappa**

GitHub: [Ayyappa1295](https://github.com/Ayyappa1295)

## 📄 License

This project is available for educational and learning purposes. You may modify and extend it for personal practice and portfolio development.
