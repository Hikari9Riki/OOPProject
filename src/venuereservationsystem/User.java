/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venuereservationsystem;

import java.util.Date;

/**
 *
 * @author kanek
 */
public class User {
   
    static int counter = 0;
    protected String userID;
    protected String name;
    protected String email;
    protected String phone;
    protected boolean login = false;
    protected String password;
    protected String role;
    protected boolean isLoggedIn;
    
    public User(String name, String email, String phone, String password, String role) {
        counter++;
        this.userID = "USER"+ counter;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role  = role;
        
    }
    
    public boolean login(String pass) {
        isLoggedIn = pass.equals(this.password);
        System.out.println(name + (isLoggedIn ? " logged in successfully" : " login failed"));
        return isLoggedIn;
    }
    
    public void logout() {
        isLoggedIn = false;
        System.out.println(name + (" logged out successfully"));
    }

    public void makeReservation(Date date, Date startTime, Date endTime, String status, Venue venue) {
        
    }

    public void cancelReservation() {
        // Implementation
    }
    
    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "counter=" + counter + ", userID=" + userID + ", name=" + name + ", email=" + email + ", phone=" + phone + ", login=" + login + ", password=" + password + ", role=" + role + '}';
    }

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }
    
    public String toDataString() {
    return role + "," + userID + "," + name + "," + email + "," + phone + "," + password;
}

}


