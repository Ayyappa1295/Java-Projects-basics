
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class RailwayReservationSystem {

    private static final Scanner sc = new Scanner(System.in);

    private static final Map<String, User> users = new LinkedHashMap<>();
    private static final Map<String, Booking> bookings = new LinkedHashMap<>();
    private static final List<Train> trains = new ArrayList<>();

    private static User currentUser;
    private static int nextBookingNumber = 1001;

    // ===================== USER CLASS =====================

    static class User {
        String name;
        String username;
        String password;

        User(String name, String username, String password) {
            this.name = name;
            this.username = username;
            this.password = password;
        }
    }

    // ===================== TRAIN CLASS ====================

    static class Train {
        int trainNumber;
        String trainName;
        String source;
        String destination;
        String departure;
        String arrival;
        int totalSeats;
        int availableSeats;
        double fare;

        Train(int trainNumber, String trainName,
              String source, String destination,
              String departure, String arrival,
              int totalSeats, double fare) {

            this.trainNumber = trainNumber;
            this.trainName = trainName;
            this.source = source;
            this.destination = destination;
            this.departure = departure;
            this.arrival = arrival;
            this.totalSeats = totalSeats;
            this.availableSeats = totalSeats;
            this.fare = fare;
        }

        void display() {
            System.out.printf(
                "%-8d %-24s %-15s %-15s %-10s %-10s %-8d Rs.%.2f%n",
                trainNumber, trainName, source, destination,
                departure, arrival, availableSeats, fare
            );
        }
    }

    // ===================== BOOKING CLASS ==================

    static class Booking {
        String pnr;
        String username;
        String passengerName;
        int age;
        String gender;
        Train train;
        LocalDate journeyDate;
        int seats;
        double totalFare;
        String status;

        Booking(String pnr, String username,
                String passengerName, int age,
                String gender, Train train,
                LocalDate journeyDate, int seats) {

            this.pnr = pnr;
            this.username = username;
            this.passengerName = passengerName;
            this.age = age;
            this.gender = gender;
            this.train = train;
            this.journeyDate = journeyDate;
            this.seats = seats;
            this.totalFare = train.fare * seats;
            this.status = "CONFIRMED";
        }
    }

    // ===================== MAIN METHOD ====================

    public static void main(String[] args) {

        loadTrains();

        System.out.println("==============================================");
        System.out.println("       INDIAN RAILWAY RESERVATION SYSTEM");
        System.out.println("==============================================");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. View All Trains");
            System.out.println("4. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    displayTrains(trains);
                    break;
                case 4:
                    System.out.println("Thank you for using Railway Reservation System!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ===================== TRAIN DATA =====================

    private static void loadTrains() {
        trains.add(new Train(12625, "Karnataka Express",
                "Bangalore", "Delhi", "19:20", "10:30", 100, 950));

        trains.add(new Train(12028, "Shatabdi Express",
                "Bangalore", "Chennai", "06:00", "11:00", 80, 650));

        trains.add(new Train(12728, "Godavari Express",
                "Hyderabad", "Visakhapatnam", "17:30", "06:00", 100, 750));

        trains.add(new Train(12863, "Howrah Express",
                "Bangalore", "Howrah", "22:00", "05:00", 90, 1100));

        trains.add(new Train(12603, "Hyderabad Express",
                "Chennai", "Hyderabad", "16:45", "06:15", 70, 800));

        trains.add(new Train(12785, "Kacheguda Express",
                "Bangalore", "Hyderabad", "18:00", "06:30", 100, 600));

        trains.add(new Train(12840, "Chennai Mail",
                "Chennai", "Bangalore", "22:30", "05:30", 80, 550));

        trains.add(new Train(12709, "Simhapuri Express",
                "Hyderabad", "Bangalore", "21:00", "09:00", 90, 700));
    }

    // ===================== REGISTRATION ===================

    private static void register() {
        System.out.println("\n========== USER REGISTRATION ==========");

        System.out.print("Enter full name: ");
        String name = sc.nextLine().trim();

        System.out.print("Create username: ");
        String username = sc.nextLine().trim();

        System.out.print("Create password: ");
        String password = sc.nextLine();

        if (name.isEmpty() || username.isEmpty() || password.trim().isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try another.");
            return;
        }

        users.put(username, new User(name, username, password));
        System.out.println("Registration successful! Please log in.");
    }

    // ===================== LOGIN ==========================

    private static void login() {
        System.out.println("\n============== USER LOGIN ==============");

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = users.get(username);

        if (user != null && user.password.equals(password)) {
            currentUser = user;
            System.out.println("\nWelcome, " + currentUser.name + "!");
            userMenu();
            currentUser = null;
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    // ===================== USER MENU ======================

    private static void userMenu() {
        while (currentUser != null) {
            System.out.println("\n========== PASSENGER MENU ==========");
            System.out.println("1. View All Trains");
            System.out.println("2. Search Trains");
            System.out.println("3. Book Ticket");
            System.out.println("4. View My Bookings");
            System.out.println("5. Search Ticket Using PNR");
            System.out.println("6. Cancel Ticket");
            System.out.println("7. Logout");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    displayTrains(trains);
                    break;
                case 2:
                    searchTrains();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    viewMyBookings();
                    break;
                case 5:
                    searchByPNR();
                    break;
                case 6:
                    cancelTicket();
                    break;
                case 7:
                    System.out.println("Logged out successfully.");
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ===================== DISPLAY TRAINS =================

    private static void displayTrains(List<Train> list) {
        if (list.isEmpty()) {
            System.out.println("No trains found.");
            return;
        }

        System.out.println("\n================ AVAILABLE TRAINS ================");
        System.out.printf("%-8s %-24s %-15s %-15s %-10s %-10s %-8s %s%n",
                "Number", "Train Name", "Source", "Destination",
                "Departure", "Arrival", "Seats", "Fare");

        System.out.println("-----------------------------------------------------------------------------------------------");

        for (Train train : list) {
            train.display();
        }
    }

    // ===================== SEARCH TRAINS ==================

    private static void searchTrains() {
        System.out.print("Enter source station: ");
        String source = sc.nextLine().trim();

        System.out.print("Enter destination station: ");
        String destination = sc.nextLine().trim();

        List<Train> results = new ArrayList<>();

        for (Train train : trains) {
            if (train.source.equalsIgnoreCase(source)
                    && train.destination.equalsIgnoreCase(destination)) {
                results.add(train);
            }
        }

        displayTrains(results);
    }

    // ===================== BOOK TICKET ====================

    private static void bookTicket() {
        displayTrains(trains);

        int trainNumber = readInt("\nEnter train number: ");
        Train selectedTrain = findTrain(trainNumber);

        if (selectedTrain == null) {
            System.out.println("Train not found.");
            return;
        }

        if (selectedTrain.availableSeats <= 0) {
            System.out.println("Sorry, no seats are available.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = sc.nextLine().trim();

        if (passengerName.isEmpty()) {
            System.out.println("Passenger name is required.");
            return;
        }

        int age = readInt("Enter passenger age: ");

        if (age < 1 || age > 120) {
            System.out.println("Please enter a valid age.");
            return;
        }

        System.out.print("Enter gender: ");
        String gender = sc.nextLine().trim();

        if (gender.isEmpty()) {
            System.out.println("Gender is required.");
            return;
        }

        int seats = readInt("Enter number of seats: ");

        if (seats < 1 || seats > selectedTrain.availableSeats) {
            System.out.println("Invalid number of seats or insufficient availability.");
            return;
        }

        LocalDate journeyDate;

        try {
            System.out.print("Enter journey date (YYYY-MM-DD): ");
            journeyDate = LocalDate.parse(sc.nextLine().trim());

            if (journeyDate.isBefore(LocalDate.now())) {
                System.out.println("Journey date cannot be in the past.");
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Use YYYY-MM-DD.");
            return;
        }

        String pnr = "PNR" + nextBookingNumber++;

        Booking booking = new Booking(
                pnr, currentUser.username, passengerName,
                age, gender, selectedTrain, journeyDate, seats
        );

        selectedTrain.availableSeats -= seats;
        bookings.put(pnr, booking);

        System.out.println("\nTicket booked successfully!");
        printTicket(booking);
    }

    // ===================== PRINT TICKET ===================

    private static void printTicket(Booking b) {
        System.out.println("\n========================================");
        System.out.println("           RAILWAY E-TICKET");
        System.out.println("========================================");
        System.out.println("PNR Number     : " + b.pnr);
        System.out.println("Passenger Name : " + b.passengerName);
        System.out.println("Age            : " + b.age);
        System.out.println("Gender         : " + b.gender);
        System.out.println("Train Number   : " + b.train.trainNumber);
        System.out.println("Train Name     : " + b.train.trainName);
        System.out.println("From           : " + b.train.source);
        System.out.println("To             : " + b.train.destination);
        System.out.println("Departure      : " + b.train.departure);
        System.out.println("Arrival        : " + b.train.arrival);
        System.out.println("Journey Date   : " + b.journeyDate);
        System.out.println("Seats Booked   : " + b.seats);
        System.out.printf("Total Fare     : Rs.%.2f%n", b.totalFare);
        System.out.println("Status         : " + b.status);
        System.out.println("========================================");
    }

    // ===================== MY BOOKINGS ===================

    private static void viewMyBookings() {
        boolean found = false;

        System.out.println("\n========== MY BOOKINGS ==========");

        for (Booking booking : bookings.values()) {
            if (booking.username.equals(currentUser.username)) {
                printTicket(booking);
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have no bookings yet.");
        }
    }

    // ===================== SEARCH BY PNR ==================

    private static void searchByPNR() {
        System.out.print("Enter PNR number: ");
        String pnr = sc.nextLine().trim();

        Booking booking = bookings.get(pnr);

        if (booking != null
                && booking.username.equals(currentUser.username)) {
            printTicket(booking);
        } else {
            System.out.println("Booking not found for your account.");
        }
    }

    // ===================== CANCEL TICKET ==================

    private static void cancelTicket() {
        System.out.print("Enter PNR number to cancel: ");
        String pnr = sc.nextLine().trim();

        Booking booking = bookings.get(pnr);

        if (booking == null
                || !booking.username.equals(currentUser.username)) {
            System.out.println("Booking not found for your account.");
            return;
        }

        if (booking.status.equals("CANCELLED")) {
            System.out.println("This ticket is already cancelled.");
            return;
        }

        System.out.print("Are you sure you want to cancel? (yes/no): ");
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("yes")) {
            booking.status = "CANCELLED";
            booking.train.availableSeats += booking.seats;

            System.out.println("Ticket cancelled successfully.");
            System.out.println("PNR: " + booking.pnr);
            System.out.println("Seats released: " + booking.seats);
            System.out.println("Refunds are not processed by this demo.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    // ===================== FIND TRAIN =====================

    private static Train findTrain(int trainNumber) {
        for (Train train : trains) {
            if (train.trainNumber == trainNumber) {
                return train;
            }
        }
        return null;
    }

    // ===================== SAFE INTEGER INPUT ============

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}
