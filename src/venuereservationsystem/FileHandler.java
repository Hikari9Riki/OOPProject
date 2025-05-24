/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venuereservationsystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.List;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
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
    
    public ArrayList<User> readUser() throws FileNotFoundException{
        ArrayList<User> users = new ArrayList();
        File file = new File("users.txt");
        Scanner read = new Scanner(file);
        
        while(read.hasNext()){
            String line = read.nextLine();
            String[] parts = line.split(",");
            String role = parts[0].trim();
            String userID = parts[1].trim();
            String name = parts[2].trim();
            String email = parts[3].trim();
            String phone = parts[4].trim();
            String password = parts[5].trim();
            
            User user = new User(name, email, phone,  password, role, userID);
            users.add(user);
                    
        }
        return users;
    }

   
}
