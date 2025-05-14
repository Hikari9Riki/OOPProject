/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package venuereservationsystem;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VenueReservationSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        FileHandler fileHandler = new FileHandler();
        fileHandler.initializeFiles();

        ArrayList<User> users = new ArrayList<>();
        Venue[] venues = new Venue[10];
        ArrayList<Reservation> reservations = new ArrayList<>();

        users.add( new Admin("Muhammad Kamal", "kamal@live.iium", "01132123414", "password"));
        users.add( new Student("Syuib bin Sufyan", "syuib@live.iium", "01123554565", "password"));
        users.add( new Staff("Luman Abdul Rahman", "luqman@live.iium", "0193754568", "password"));
        users.add( new Staff("Nisa' Binti Syukur", "nisa@live.iium", "0148736489", "password"));

        fileHandler.saveUsersToFile(users);
        
        venues[0] = new Venue("LR1", "KICT, BLOCK B, LVL3", 35, true);
        venues[1] = new Venue("LR2", "KICT, BLOCK B, LVL3", 35, true);
        venues[2] = new Venue("LR3", "KICT, BLOCK B, LVL3", 35, true);
        venues[3] = new Venue("LR4", "KICT, BLOCK B, LVL3", 35, true);

        User currentUser = loginPage(input, users);

        if (currentUser != null && !(currentUser instanceof Admin)) {
            reservePage(input, currentUser, reservations, venues);
        } else {
            System.out.println("Only Student and Staff can make reservations.");
        }

        input.close();
    }

    public static User loginPage(Scanner input, ArrayList<User> users) {
        System.out.println("=== LOGIN PAGE ===");
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        for (User u : users) {
            if (u != null && u.email.equalsIgnoreCase(email) && u.checkPassword(password)) {
                System.out.println("Login successful! Welcome, " + u.name);
                return u;
            }
        }

        System.out.println("Login failed. Invalid email or password.");
        return null;
    }

    public static void reservePage(Scanner input, User user, ArrayList<Reservation> reservations, Venue[] venues) {
        System.out.println("\n=== RESERVATION PAGE ===");
        System.out.println("Available Venues:");

        for (int i = 0; i < venues.length; i++) {
            if (venues[i] != null && venues[i].isAvailable()) {
                System.out.println(i + ". " + venues[i]);
            }
        }

        System.out.print("Select venue by index: ");
        int venueIndex = input.nextInt();
        input.nextLine(); // Consume newline

        if (venueIndex < 0 || venueIndex >= venues.length || venues[venueIndex] == null || !venues[venueIndex].isAvailable()) {
            System.out.println("Invalid venue selected.");
            return;
        }

        try {
            System.out.print("Enter reservation date (yyyy-MM-dd): ");
            String dateStr = input.nextLine();
            System.out.print("Enter start time (HH:mm): ");
            String startStr = input.nextLine();
            System.out.print("Enter end time (HH:mm): ");
            String endStr = input.nextLine();

            SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");

            Date date = dfDate.parse(dateStr);
            Date start = dfTime.parse(startStr);
            Date end = dfTime.parse(endStr);

            Reservation r = new Reservation(date, start, end, "Reserved", venues[venueIndex]);
            reservations.add(r);
            venues[venueIndex].updateAvailability(false);

            System.out.println("Reservation successful:\n" + r);
        } catch (Exception e) {
            System.out.println("Invalid input format. Reservation failed.");
        }
    }
}