/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package venuereservationsystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 *
 * @author kanek
 */
public class Reservation {

    

    static int counter = 0;
    private String reservationID;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String status;
    private boolean active;
    private Venue venue;
    private String venueID;
    private String userID;
    
    
    public Reservation(Date date, Date startTime, Date endTime, String status, String venueID, String userID) {
        counter++;
        this.reservationID = "REV"+counter;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.venueID =  venueID;
        this.userID = userID;
       
    }
    
    public Reservation(String id, Date date, Date startTime, Date endTime, String status, Boolean active,String venueID, String userID ) {
        counter++;
        this.reservationID = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.active = active;
        this.venueID =  venueID;
        this.userID = userID;
        
    }

    @Override
    public String toString() {
        return "Reservation{" + "counter=" + counter + ", reservationID=" + reservationID + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", active=" + active + ", venue=" + venueID + ", userID=" + userID + '}';
    }
    
    public String getReservationID() {
        return reservationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void createReservation() {
        
    }

    public void updateReservation() {
        // Implementation
    }

    public void cancelReservation(Reservation reserve) {
        reserve.active = false;
        reserve.status = "cancel";
    }
    
    public void declineReservation(Reservation reserve) {
        reserve.active = false;
        reserve.status = "decline";
    }
    
    public void approveReservation(Reservation reserve) {
        reserve.active = false;
        reserve.status = "approve";
    }

    public boolean checkAvailability() {
        // Implementation
        return true;
    }
    
    public String toDataString() {
        SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
        return reservationID + "," + dfDate.format(date) + "," + dfTime.format(startTime) + "," + dfTime.format(endTime) + "," + status + "," + Boolean.toString(active) + "," + venueID + "," + userID;
    }
}
