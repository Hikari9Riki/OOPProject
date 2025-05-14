/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venuereservationsystem;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
/**
 *
 * @author kanek
 */
public class FileHandler {
    
    public void initializeFiles() {
        createFile("users.txt");
        createFile("venues.txt");
        createFile("reservations.txt");
    }

    private void createFile(String filename) {
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println(filename + " created.");
            } else {
                System.out.println(filename + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + filename);
            e.printStackTrace();
        }
    }

    // Method to save users to file
    public void saveUsersToFile(List<User> users) {
        try (FileWriter writer = new FileWriter("users.txt", false)) { // overwrite existing file
            for (User user : users) {
                writer.write(user.toDataString() + "\n");
            }
            System.out.println("Users saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users to file.");
            e.printStackTrace();
        }
    }
}
