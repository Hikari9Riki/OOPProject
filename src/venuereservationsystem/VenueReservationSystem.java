/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package venuereservationsystem;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VenueReservationSystem {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        Scanner input = new Scanner(System.in);

        FileHandler fileHandler = new FileHandler();
        fileHandler.initializeFiles();

        ArrayList<User> users = new ArrayList<>();
        ArrayList<Venue> venues = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();
        
        
        users = fileHandler.readUser();
        System.out.println(users.toString());
        
        reservations = fileHandler.readReservation();
        System.out.println(reservations.toString());
        // kamal@live.iium
        //password
        //2023-12-12
        //12:22
        //13:33
        venues.add(new Venue("LR1", "KICT BLOCK B LVL3", 35, true));
        venues.add(new Venue("LR2", "KICT BLOCK B LVL3", 35, true));
        venues.add(new Venue("LR3", "KICT BLOCK B LVL3", 35, true));
        venues.add(new Venue("LR4", "KICT BLOCK B LVL3", 35, true));
        
        fileHandler.saveVenueToFile(venues);
        
        venues = fileHandler.readVenue();
        System.out.println(venues.toString());
        
        User currentUser = loginPage(input, users);
        

        if (currentUser != null && !(currentUser instanceof Admin)) {
            reservePage(input, currentUser, reservations, venues);
        } else {
            System.out.println("Only Student and Staff can make reservations.");
        }

        input.close();
        reservations = fileHandler.readReservation();
        System.out.println(reservations.toString());
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

    public static void reservePage(Scanner input, User user, ArrayList<Reservation> reservations, ArrayList<Venue> venues) {
        System.out.println("\n=== RESERVATION PAGE ===");
        System.out.println("Available Venues:");
        int counter = 1;
        for (Venue venue : venues){
            if (venue != null && venue.isAvailable()) {
                System.out.println(counter + ". " + venue);
                counter++;
            }
        }

        System.out.print("Select venue by index: ");
        int venueIndex = input.nextInt();
        input.nextLine(); // Consume newline

        if (venueIndex < 0 || venueIndex >= venues.size() || venues.get(venueIndex) == null || !venues.get(venueIndex).isAvailable()) {
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

            Reservation r = new Reservation(date, start, end, "Reserved", venues.get(venueIndex).getVenueID(), user.getUserID() );
            reservations.add(r);
           venues.get(venueIndex).updateAvailability(false);
            System.out.println(r.toString());
            FileHandler fileHandler = new FileHandler();
            
            fileHandler.saveReservationToFile(reservations,r);
            System.out.println("Reservation successful:\n" + r);
        } catch (Exception e) {
            System.out.println("Invalid input format. Reservation failed.");
        }
    }
}