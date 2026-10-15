
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<Book> books = new ArrayList<>();
    private static final ArrayList<Member> members = new ArrayList<>();
    private static final ArrayList<Loan> loans = new ArrayList<>();

    private static final String BOOK_FILE = "books.txt";
    private static final String MEMBER_FILE = "members.txt";
    private static final String LOAN_FILE = "loans.txt";

    // ================= BOOK CLASS =================

    static class Book {
        int id;
        String title;
        String author;
        String category;
        int totalCopies;
        int availableCopies;

        Book(int id, String title, String author,
             String category, int copies) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.category = category;
            this.totalCopies = copies;
            this.availableCopies = copies;
        }
    }

    // ================= MEMBER CLASS =================

    static class Member {
        int id;
        String name;
        String phone;

        Member(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }
    }

    // ================= LOAN CLASS =================

    static class Loan {
        int bookId;
        int memberId;
        LocalDate issueDate;
        LocalDate dueDate;
        LocalDate returnDate;

        Loan(int bookId, int memberId,
             LocalDate issueDate, LocalDate dueDate) {
            this.bookId = bookId;
            this.memberId = memberId;
            this.issueDate = issueDate;
            this.dueDate = dueDate;
            this.returnDate = null;
        }
    }

    // ================= MAIN METHOD =================

    public static void main(String[] args) {
        loadData();

        System.out.println("==========================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");

        while (true) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    addMember();
                    break;
                case 5:
                    viewAllMembers();
                    break;
                case 6:
                    issueBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    viewAvailableBooks();
                    break;
                case 9:
                    viewIssuedBooks();
                    break;
                case 10:
                    viewOverdueBooks();
                    break;
                case 11:
                    viewStatistics();
                    break;
                case 12:
                    saveData();
                    System.out.println("Data saved successfully!");
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println(
                        "Invalid choice! Please select 1 to 12."
                    );
            }
        }
    }

    // ================= MENU =================

    private static void displayMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1.  Add New Book");
        System.out.println("2.  View All Books");
        System.out.println("3.  Search Book");
        System.out.println("4.  Register Member");
        System.out.println("5.  View All Members");
        System.out.println("6.  Issue Book");
        System.out.println("7.  Return Book");
        System.out.println("8.  View Available Books");
        System.out.println("9.  View Issued Books");
        System.out.println("10. View Overdue Books");
        System.out.println("11. Library Statistics");
        System.out.println("12. Save and Exit");
        System.out.println("-----------------------------------------");
    }

    // ================= ADD BOOK =================

    private static void addBook() {
        System.out.println("\n--- ADD NEW BOOK ---");

        int id = readInt("Enter book ID: ");

        if (findBook(id) != null) {
            System.out.println("Book ID already exists!");
            return;
        }

        String title = readText("Enter book title: ");
        String author = readText("Enter author name: ");
        String category = readText("Enter category: ");
        int copies = readInt("Enter number of copies: ");

        if (copies <= 0) {
            System.out.println("Copies must be greater than zero!");
            return;
        }

        books.add(new Book(id, title, author, category, copies));

        saveData();
        System.out.println("Book added successfully!");
    }

    // ================= VIEW ALL BOOKS =================

    private static void viewAllBooks() {
        System.out.println("\n--- ALL LIBRARY BOOKS ---");

        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        printBookHeader();

        for (Book b : books) {
            printBook(b);
        }
    }

    private static void printBookHeader() {
        System.out.printf(
            "%-6s %-22s %-20s %-15s %-8s %-10s%n",
            "ID", "TITLE", "AUTHOR", "CATEGORY",
            "TOTAL", "AVAILABLE"
        );
        System.out.println(
            "--------------------------------------------------------------------------------"
        );
    }

    private static void printBook(Book b) {
        System.out.printf(
            "%-6d %-22s %-20s %-15s %-8d %-10d%n",
            b.id,
            shorten(b.title, 21),
            shorten(b.author, 19),
            shorten(b.category, 14),
            b.totalCopies,
            b.availableCopies
        );
    }

    // ================= SEARCH BOOK =================

    private static void searchBook() {
        System.out.println("\n--- SEARCH BOOK ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");

        int choice = readInt("Choose search option: ");

        if (choice == 1) {
            int id = readInt("Enter book ID: ");
            Book b = findBook(id);

            if (b == null) {
                System.out.println("Book not found!");
            } else {
                printBookHeader();
                printBook(b);
            }

        } else if (choice == 2 || choice == 3) {
            String keyword = readText("Enter search keyword: ")
                    .toLowerCase();

            boolean found = false;
            printBookHeader();

            for (Book b : books) {
                String value = choice == 2 ? b.title : b.author;

                if (value.toLowerCase().contains(keyword)) {
                    printBook(b);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No matching books found.");
            }
        } else {
            System.out.println("Invalid search option!");
        }
    }

    // ================= REGISTER MEMBER =================

    private static void addMember() {
        System.out.println("\n--- REGISTER MEMBER ---");

        int id = readInt("Enter member ID: ");

        if (findMember(id) != null) {
            System.out.println("Member ID already exists!");
            return;
        }

        String name = readText("Enter member name: ");
        String phone = readText("Enter phone number: ");

        members.add(new Member(id, name, phone));

        saveData();
        System.out.println("Member registered successfully!");
    }

    // ================= VIEW MEMBERS =================

    private static void viewAllMembers() {
        System.out.println("\n--- REGISTERED MEMBERS ---");

        if (members.isEmpty()) {
            System.out.println("No registered members.");
            return;
        }

        System.out.printf(
            "%-10s %-25s %-18s%n",
            "MEMBER ID", "NAME", "PHONE"
        );

        System.out.println(
            "------------------------------------------------------"
        );

        for (Member m : members) {
            System.out.printf(
                "%-10d %-25s %-18s%n",
                m.id, shorten(m.name, 24), m.phone
            );
        }
    }

    // ================= ISSUE BOOK =================

    private static void issueBook() {
        System.out.println("\n--- ISSUE BOOK ---");

        int bookId = readInt("Enter book ID: ");
        int memberId = readInt("Enter member ID: ");

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (member == null) {
            System.out.println("Member not found!");
            return;
        }

        if (book.availableCopies <= 0) {
            System.out.println("No copies available!");
            return;
        }

        for (Loan loan : loans) {
            if (loan.bookId == bookId
                    && loan.memberId == memberId
                    && loan.returnDate == null) {
                System.out.println(
                    "This member already has this book!"
                );
                return;
            }
        }

        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(14);

        loans.add(new Loan(
            bookId, memberId, issueDate, dueDate
        ));

        book.availableCopies--;

        saveData();

        System.out.println("Book issued successfully!");
        System.out.println("Book: " + book.title);
        System.out.println("Member: " + member.name);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Due Date: " + dueDate);
    }

    // ================= RETURN BOOK =================

    private static void returnBook() {
        System.out.println("\n--- RETURN BOOK ---");

        int bookId = readInt("Enter book ID: ");
        int memberId = readInt("Enter member ID: ");

        Loan activeLoan = null;

        for (Loan loan : loans) {
            if (loan.bookId == bookId
                    && loan.memberId == memberId
                    && loan.returnDate == null) {
                activeLoan = loan;
                break;
            }
        }

        if (activeLoan == null) {
            System.out.println(
                "No active issue record found for these IDs!"
            );
            return;
        }

        activeLoan.returnDate = LocalDate.now();

        Book book = findBook(bookId);

        if (book != null) {
            book.availableCopies++;
        }

        saveData();

        System.out.println("Book returned successfully!");
        System.out.println("Return Date: " + activeLoan.returnDate);

        if (activeLoan.returnDate.isAfter(activeLoan.dueDate)) {
            long daysLate = java.time.temporal.ChronoUnit.DAYS
                    .between(activeLoan.dueDate,
                             activeLoan.returnDate);

            long fine = daysLate * 2;

            System.out.println("Late by: " + daysLate + " day(s)");
            System.out.println("Late fee at Rs. 2/day: Rs. " + fine);
        } else {
            System.out.println("No late fee!");
        }
    }

    // ================= AVAILABLE BOOKS =================

    private static void viewAvailableBooks() {
        System.out.println("\n--- AVAILABLE BOOKS ---");

        boolean found = false;
        printBookHeader();

        for (Book b : books) {
            if (b.availableCopies > 0) {
                printBook(b);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books currently available.");
        }
    }

    // ================= ISSUED BOOKS =================

    private static void viewIssuedBooks() {
        System.out.println("\n--- ISSUED BOOK RECORDS ---");

        boolean found = false;

        System.out.printf(
            "%-10s %-22s %-10s %-20s %-12s %-12s %-12s%n",
            "BOOK ID", "BOOK TITLE", "MEMBER ID",
            "MEMBER NAME", "ISSUE DATE", "DUE DATE", "STATUS"
        );

        System.out.println(
            "-----------------------------------------------------------------------------------------------"
        );

        for (Loan loan : loans) {
            Book b = findBook(loan.bookId);
            Member m = findMember(loan.memberId);

            if (b == null || m == null) {
                continue;
            }

            String status = loan.returnDate == null
                    ? "ISSUED" : "RETURNED";

            System.out.printf(
                "%-10d %-22s %-10d %-20s %-12s %-12s %-12s%n",
                b.id,
                shorten(b.title, 21),
                m.id,
                shorten(m.name, 19),
                loan.issueDate,
                loan.dueDate,
                status
            );

            found = true;
        }

        if (!found) {
            System.out.println("No issue records found.");
        }
    }

    // ================= OVERDUE BOOKS =================

    private static void viewOverdueBooks() {
        System.out.println("\n--- OVERDUE BOOKS ---");

        LocalDate today = LocalDate.now();
        boolean found = false;

        for (Loan loan : loans) {
            if (loan.returnDate == null
                    && today.isAfter(loan.dueDate)) {

                Book b = findBook(loan.bookId);
                Member m = findMember(loan.memberId);

                if (b != null && m != null) {
                    long daysLate =
                        java.time.temporal.ChronoUnit.DAYS
                        .between(loan.dueDate, today);

                    System.out.println("------------------------------------");
                    System.out.println("Book: " + b.title);
                    System.out.println("Member: " + m.name);
                    System.out.println("Due Date: " + loan.dueDate);
                    System.out.println("Days Overdue: " + daysLate);
                    System.out.println(
                        "Current Estimated Fee: Rs. " + (daysLate * 2)
                    );

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No overdue books!");
        }
    }

    // ================= STATISTICS =================

    private static void viewStatistics() {
        int totalCopies = 0;
        int availableCopies = 0;
        int activeLoans = 0;
        int returnedLoans = 0;

        for (Book b : books) {
            totalCopies += b.totalCopies;
            availableCopies += b.availableCopies;
        }

        for (Loan loan : loans) {
            if (loan.returnDate == null) {
                activeLoans++;
            } else {
                returnedLoans++;
            }
        }

        System.out.println("\n======= LIBRARY STATISTICS =======");
        System.out.println("Unique Book Titles : " + books.size());
        System.out.println("Total Book Copies  : " + totalCopies);
        System.out.println("Available Copies   : " + availableCopies);
        System.out.println("Registered Members : " + members.size());
        System.out.println("Currently Issued   : " + activeLoans);
        System.out.println("Books Returned     : " + returnedLoans);
        System.out.println("Total Loan Records : " + loans.size());
        System.out.println("==================================");
    }

    // ================= SEARCH HELPERS =================

    private static Book findBook(int id) {
        for (Book b : books) {
            if (b.id == id) {
                return b;
            }
        }
        return null;
    }

    private static Member findMember(int id) {
        for (Member m : members) {
            if (m.id == id) {
                return m;
            }
        }
        return null;
    }

    // ================= INPUT HELPERS =================

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number!");
            }
        }
    }

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            if (!input.isEmpty() && !input.contains("|")) {
                return input;
            }

            System.out.println(
                "Input cannot be empty or contain the | character."
            );
        }
    }

    private static String shorten(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }

        return text.substring(0, maxLength - 3) + "...";
    }

    // ================= SAVE DATA =================

    private static void saveData() {
        try (
            PrintWriter bookWriter =
                new PrintWriter(new FileWriter(BOOK_FILE));
            PrintWriter memberWriter =
                new PrintWriter(new FileWriter(MEMBER_FILE));
            PrintWriter loanWriter =
                new PrintWriter(new FileWriter(LOAN_FILE))
        ) {
            for (Book b : books) {
                bookWriter.println(
                    b.id + "|" + b.title + "|" + b.author + "|"
                    + b.category + "|" + b.totalCopies + "|"
                    + b.availableCopies
                );
            }

            for (Member m : members) {
                memberWriter.println(
                    m.id + "|" + m.name + "|" + m.phone
                );
            }

            for (Loan loan : loans) {
                loanWriter.println(
                    loan.bookId + "|" + loan.memberId + "|"
                    + loan.issueDate + "|" + loan.dueDate + "|"
                    + (loan.returnDate == null
                       ? "null" : loan.returnDate.toString())
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ================= LOAD DATA =================

    private static void loadData() {
        loadBooks();
        loadMembers();
        loadLoans();
    }

    private static void loadBooks() {
        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 6) {
                        continue;
                    }

                    Book b = new Book(
                        Integer.parseInt(p[0]),
                        p[1], p[2], p[3],
                        Integer.parseInt(p[4])
                    );

                    b.availableCopies = Integer.parseInt(p[5]);

                    if (b.totalCopies >= 0
                            && b.availableCopies >= 0
                            && b.availableCopies <= b.totalCopies) {
                        books.add(b);
                    }
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid book record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    private static void loadMembers() {
        File file = new File(MEMBER_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length == 3) {
                        members.add(new Member(
                            Integer.parseInt(p[0]), p[1], p[2]
                        ));
                    }
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid member record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
    }

    private static void loadLoans() {
        File file = new File(LOAN_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 5) {
                        continue;
                    }

                    Loan loan = new Loan(
                        Integer.parseInt(p[0]),
                        Integer.parseInt(p[1]),
                        LocalDate.parse(p[2]),
                        LocalDate.parse(p[3])
                    );

                    if (!p[4].equals("null")) {
                        loan.returnDate = LocalDate.parse(p[4]);
                    }

                    loans.add(loan);
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid loan record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading loans: " + e.getMessage());
        }
    }
}
