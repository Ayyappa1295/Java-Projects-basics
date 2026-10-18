
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class VehicleRentalSystem {

    private static final Scanner sc = new Scanner(System.in);

    private static final String VEHICLE_FILE = "vehicles.txt";
    private static final String CUSTOMER_FILE = "customers.txt";
    private static final String RENTAL_FILE = "rentals.txt";

    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Rental> rentals = new ArrayList<>();

    private static int nextVehicleId = 1001;
    private static int nextCustomerId = 2001;
    private static int nextRentalId = 3001;

    // ==================== VEHICLE CLASS ====================

    static class Vehicle {
        int id;
        String brand;
        String model;
        String type;
        String registration;
        double pricePerDay;
        boolean available;

        Vehicle(int id, String brand, String model, String type,
                String registration, double pricePerDay, boolean available) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.type = type;
            this.registration = registration;
            this.pricePerDay = pricePerDay;
            this.available = available;
        }

        void display() {
            System.out.printf(
                "%-7d %-14s %-14s %-10s %-15s Rs.%-10.2f %-12s%n",
                id, brand, model, type, registration,
                pricePerDay, available ? "Available" : "Rented"
            );
        }
    }

    // ==================== CUSTOMER CLASS ====================

    static class Customer {
        int id;
        String name;
        String phone;
        String license;

        Customer(int id, String name, String phone, String license) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.license = license;
        }

        void display() {
            System.out.printf("%-8d %-22s %-16s %-18s%n",
                    id, name, phone, license);
        }
    }

    // ==================== RENTAL CLASS ====================

    static class Rental {
        int id;
        int customerId;
        int vehicleId;
        int days;
        LocalDate rentalDate;
        LocalDate returnDate;
        double totalCost;
        boolean returned;

        Rental(int id, int customerId, int vehicleId, int days,
               LocalDate rentalDate, LocalDate returnDate,
               double totalCost, boolean returned) {
            this.id = id;
            this.customerId = customerId;
            this.vehicleId = vehicleId;
            this.days = days;
            this.rentalDate = rentalDate;
            this.returnDate = returnDate;
            this.totalCost = totalCost;
            this.returned = returned;
        }
    }

    // ==================== MAIN METHOD ====================

    public static void main(String[] args) {
        loadData();

        System.out.println("\n==============================================");
        System.out.println("       VEHICLE RENTAL MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        while (true) {
            showMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    viewVehicles();
                    break;
                case 3:
                    searchVehicle();
                    break;
                case 4:
                    registerCustomer();
                    break;
                case 5:
                    viewCustomers();
                    break;
                case 6:
                    rentVehicle();
                    break;
                case 7:
                    returnVehicle();
                    break;
                case 8:
                    viewRentals(false);
                    break;
                case 9:
                    viewRentals(true);
                    break;
                case 10:
                    showDashboard();
                    break;
                case 11:
                    saveData();
                    System.out.println("Data saved successfully.");
                    break;
                case 0:
                    saveData();
                    System.out.println("\nThank you for using our system!");
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // ==================== MENU ====================

    private static void showMenu() {
        System.out.println("\n--------------- MAIN MENU ----------------");
        System.out.println("1.  Add New Vehicle");
        System.out.println("2.  View All Vehicles");
        System.out.println("3.  Search Vehicle");
        System.out.println("4.  Register Customer");
        System.out.println("5.  View All Customers");
        System.out.println("6.  Rent a Vehicle");
        System.out.println("7.  Return a Vehicle");
        System.out.println("8.  View Active Rentals");
        System.out.println("9.  View Rental History");
        System.out.println("10. Dashboard & Reports");
        System.out.println("11. Save Data");
        System.out.println("0.  Exit");
        System.out.println("------------------------------------------");
    }

    // ==================== ADD VEHICLE ====================

    private static void addVehicle() {
        System.out.println("\n--- ADD NEW VEHICLE ---");

        String brand = readText("Enter brand: ");
        String model = readText("Enter model: ");
        String type = readText("Enter type (Car/Bike/Van): ");
        String registration = readText("Enter registration number: ")
                .toUpperCase();

        for (Vehicle v : vehicles) {
            if (v.registration.equalsIgnoreCase(registration)) {
                System.out.println("This registration number already exists.");
                return;
            }
        }

        double price = readDouble("Rental price per day (Rs.): ");

        if (price <= 0) {
            System.out.println("Price must be greater than zero.");
            return;
        }

        Vehicle v = new Vehicle(nextVehicleId++, brand, model,
                type, registration, price, true);

        vehicles.add(v);
        saveData();

        System.out.println("\nVehicle added successfully!");
        System.out.println("Vehicle ID: " + v.id);
    }

    // ==================== VIEW VEHICLES ====================

    private static void viewVehicles() {
        System.out.println("\n--- VEHICLE INVENTORY ---");

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered yet.");
            return;
        }

        printVehicleHeader();

        for (Vehicle v : vehicles) {
            v.display();
        }
    }

    private static void printVehicleHeader() {
        System.out.printf("%-7s %-14s %-14s %-10s %-15s %-15s %-12s%n",
                "ID", "Brand", "Model", "Type",
                "Registration", "Price/Day", "Status");
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    // ==================== SEARCH VEHICLE ====================

    private static void searchVehicle() {
        System.out.println("\n--- SEARCH VEHICLE ---");

        int id = readInt("Enter vehicle ID: ");
        Vehicle v = findVehicle(id);

        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        printVehicleHeader();
        v.display();
    }

    // ==================== REGISTER CUSTOMER ====================

    private static void registerCustomer() {
        System.out.println("\n--- CUSTOMER REGISTRATION ---");

        String name = readText("Enter customer name: ");
        String phone = readText("Enter phone number: ");
        String license = readText("Enter driving license number: ")
                .toUpperCase();

        for (Customer c : customers) {
            if (c.phone.equals(phone)) {
                System.out.println("This phone number is already registered.");
                return;
            }
        }

        Customer c = new Customer(nextCustomerId++, name, phone, license);
        customers.add(c);
        saveData();

        System.out.println("\nCustomer registered successfully!");
        System.out.println("Customer ID: " + c.id);
    }

    // ==================== VIEW CUSTOMERS ====================

    private static void viewCustomers() {
        System.out.println("\n--- REGISTERED CUSTOMERS ---");

        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }

        System.out.printf("%-8s %-22s %-16s %-18s%n",
                "ID", "Name", "Phone", "License");
        System.out.println("----------------------------------------------------------------");

        for (Customer c : customers) {
            c.display();
        }
    }

    // ==================== RENT VEHICLE ====================

    private static void rentVehicle() {
        System.out.println("\n--- RENT A VEHICLE ---");

        int customerId = readInt("Enter customer ID: ");
        Customer customer = findCustomer(customerId);

        if (customer == null) {
            System.out.println("Customer not found. Register the customer first.");
            return;
        }

        viewVehicles();

        int vehicleId = readInt("\nEnter vehicle ID to rent: ");
        Vehicle vehicle = findVehicle(vehicleId);

        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        if (!vehicle.available) {
            System.out.println("Sorry, this vehicle is already rented.");
            return;
        }

        int days = readInt("Number of rental days: ");

        if (days <= 0) {
            System.out.println("Rental days must be greater than zero.");
            return;
        }

        double total = vehicle.pricePerDay * days;

        System.out.println("\n----------- RENTAL SUMMARY -----------");
        System.out.println("Customer       : " + customer.name);
        System.out.println("Vehicle        : " + vehicle.brand + " " + vehicle.model);
        System.out.println("Rental days    : " + days);
        System.out.printf("Price per day  : Rs. %.2f%n", vehicle.pricePerDay);
        System.out.printf("Total amount   : Rs. %.2f%n", total);

        String confirm = readText("Confirm booking? (yes/no): ");

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Booking cancelled.");
            return;
        }

        LocalDate today = LocalDate.now();

        Rental rental = new Rental(
                nextRentalId++, customerId, vehicleId, days,
                today, today.plusDays(days), total, false
        );

        rentals.add(rental);
        vehicle.available = false;
        saveData();

        printInvoice(rental);
    }

    // ==================== RETURN VEHICLE ====================

    private static void returnVehicle() {
        System.out.println("\n--- RETURN VEHICLE ---");

        boolean found = false;

        for (Rental r : rentals) {
            if (!r.returned) {
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("There are no active rentals.");
            return;
        }

        viewRentals(false);

        int rentalId = readInt("\nEnter rental ID to return: ");
        Rental rental = findRental(rentalId);

        if (rental == null || rental.returned) {
            System.out.println("Active rental not found.");
            return;
        }

        Vehicle vehicle = findVehicle(rental.vehicleId);

        rental.returned = true;

        if (vehicle != null) {
            vehicle.available = true;
        }

        saveData();

        System.out.println("\nVehicle returned successfully!");
        System.out.println("Rental ID: " + rental.id);
        System.out.println("Vehicle ID: " + rental.vehicleId);
        System.out.printf("Rental charge: Rs. %.2f%n", rental.totalCost);
        System.out.println("Return processed on: " + LocalDate.now());
        System.out.println("Note: This demo does not process payments or late fees.");
    }

    // ==================== RENTAL REPORTS ====================

    private static void viewRentals(boolean showAll) {
        System.out.println(showAll
                ? "\n--- COMPLETE RENTAL HISTORY ---"
                : "\n--- ACTIVE RENTALS ---");

        boolean found = false;

        System.out.printf("%-9s %-12s %-11s %-6s %-13s %-13s %-13s %-10s%n",
                "Rental ID", "Customer ID", "Vehicle ID", "Days",
                "Start Date", "Due Date", "Amount", "Status");

        System.out.println("-------------------------------------------------------------------------------------------");

        for (Rental r : rentals) {
            if (!showAll && r.returned) {
                continue;
            }

            found = true;

            System.out.printf("%-9d %-12d %-11d %-6d %-13s %-13s Rs.%-10.2f %-10s%n",
                    r.id, r.customerId, r.vehicleId, r.days,
                    r.rentalDate, r.returnDate, r.totalCost,
                    r.returned ? "Returned" : "Active");
        }

        if (!found) {
            System.out.println("No matching rentals found.");
        }
    }

    // ==================== DASHBOARD ====================

    private static void showDashboard() {
        int available = 0;
        int rented = 0;
        int activeRentals = 0;
        int completedRentals = 0;
        double totalRevenue = 0;

        for (Vehicle v : vehicles) {
            if (v.available) {
                available++;
            } else {
                rented++;
            }
        }

        for (Rental r : rentals) {
            if (r.returned) {
                completedRentals++;
            } else {
                activeRentals++;
            }

            totalRevenue += r.totalCost;
        }

        System.out.println("\n========== BUSINESS DASHBOARD ==========");
        System.out.println("Total vehicles       : " + vehicles.size());
        System.out.println("Available vehicles   : " + available);
        System.out.println("Currently rented     : " + rented);
        System.out.println("Registered customers : " + customers.size());
        System.out.println("Active rentals       : " + activeRentals);
        System.out.println("Completed rentals    : " + completedRentals);
        System.out.printf("Total rental charges : Rs. %.2f%n", totalRevenue);
        System.out.println("========================================");
        System.out.println("Note: Total charges are not verified cash receipts.");
    }

    // ==================== INVOICE ====================

    private static void printInvoice(Rental r) {
        Customer customer = findCustomer(r.customerId);
        Vehicle vehicle = findVehicle(r.vehicleId);

        System.out.println("\n========================================");
        System.out.println("          VEHICLE RENTAL INVOICE");
        System.out.println("========================================");
        System.out.println("Rental ID     : " + r.id);
        System.out.println("Invoice Date  : " + r.rentalDate);

        if (customer != null) {
            System.out.println("Customer      : " + customer.name);
            System.out.println("Phone         : " + customer.phone);
        }

        if (vehicle != null) {
            System.out.println("Vehicle       : " + vehicle.brand + " " + vehicle.model);
            System.out.println("Registration  : " + vehicle.registration);
            System.out.printf("Daily Rate    : Rs. %.2f%n", vehicle.pricePerDay);
        }

        System.out.println("Rental Days   : " + r.days);
        System.out.println("Due Date      : " + r.returnDate);
        System.out.printf("Total Amount  : Rs. %.2f%n", r.totalCost);
        System.out.println("----------------------------------------");
        System.out.println("Thank you for choosing our service!");
        System.out.println("========================================");
    }

    // ==================== SEARCH HELPERS ====================

    private static Vehicle findVehicle(int id) {
        for (Vehicle v : vehicles) {
            if (v.id == id) {
                return v;
            }
        }
        return null;
    }

    private static Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.id == id) {
                return c;
            }
        }
        return null;
    }

    private static Rental findRental(int id) {
        for (Rental r : rentals) {
            if (r.id == id) {
                return r;
            }
        }
        return null;
    }

    // ==================== INPUT HELPERS ====================

    private static String readText(String message) {
        while (true) {
            System.out.print(message);
            String value = sc.nextLine().trim();

            if (!value.isEmpty() && !value.contains("|")) {
                return value;
            }

            System.out.println("Input cannot be empty or contain '|'. Try again.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Display validation message below.
            }

            System.out.println("Please enter a valid numeric amount.");
        }
    }

    // ==================== SAVE DATA ====================

    private static void saveData() {
        try {
            try (PrintWriter out = new PrintWriter(new FileWriter(VEHICLE_FILE))) {
                for (Vehicle v : vehicles) {
                    out.println(v.id + "|" + v.brand + "|" + v.model + "|"
                            + v.type + "|" + v.registration + "|"
                            + v.pricePerDay + "|" + v.available);
                }
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(CUSTOMER_FILE))) {
                for (Customer c : customers) {
                    out.println(c.id + "|" + c.name + "|" + c.phone + "|"
                            + c.license);
                }
            }

            try (PrintWriter out = new PrintWriter(new FileWriter(RENTAL_FILE))) {
                for (Rental r : rentals) {
                    out.println(r.id + "|" + r.customerId + "|" + r.vehicleId
                            + "|" + r.days + "|" + r.rentalDate + "|"
                            + r.returnDate + "|" + r.totalCost + "|"
                            + r.returned);
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // ==================== LOAD DATA ====================

    private static void loadData() {
        loadVehicles();
        loadCustomers();
        loadRentals();

        System.out.println("Data loading completed.");
        System.out.println("Vehicles: " + vehicles.size()
                + " | Customers: " + customers.size()
                + " | Rentals: " + rentals.size());
    }

    private static void loadVehicles() {
        File file = new File(VEHICLE_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 7) {
                        continue;
                    }

                    Vehicle v = new Vehicle(
                            Integer.parseInt(p[0]), p[1], p[2], p[3],
                            p[4], Double.parseDouble(p[5]),
                            Boolean.parseBoolean(p[6])
                    );

                    vehicles.add(v);
                    nextVehicleId = Math.max(nextVehicleId, v.id + 1);
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid vehicle record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading vehicles: " + e.getMessage());
        }
    }

    private static void loadCustomers() {
        File file = new File(CUSTOMER_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 4) {
                        continue;
                    }

                    Customer c = new Customer(
                            Integer.parseInt(p[0]), p[1], p[2], p[3]
                    );

                    customers.add(c);
                    nextCustomerId = Math.max(nextCustomerId, c.id + 1);
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid customer record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading customers: " + e.getMessage());
        }
    }

    private static void loadRentals() {
        File file = new File(RENTAL_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                try {
                    String[] p = line.split("\\|", -1);

                    if (p.length != 8) {
                        continue;
                    }

                    Rental r = new Rental(
                            Integer.parseInt(p[0]),
                            Integer.parseInt(p[1]),
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]),
                            LocalDate.parse(p[4]),
                            LocalDate.parse(p[5]),
                            Double.parseDouble(p[6]),
                            Boolean.parseBoolean(p[7])
                    );

                    rentals.add(r);
                    nextRentalId = Math.max(nextRentalId, r.id + 1);
                } catch (RuntimeException e) {
                    System.out.println("Skipping invalid rental record.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading rentals: " + e.getMessage());
        }
    }
}
