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
public class Reservation {

    

    static int counter = 0;
    private String reservationID;
    private Date date;
    private Date startTime;
    private Date endTime;
    private String status;
    private boolean active;
    private Venue venue;
    private String userID;
    
    
    public Reservation(Date date, Date startTime, Date endTime, String status, Venue venue) {
        counter++;
        this.reservationID = "REV"+counter;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.venue = venue;
        
    }

    @Override
    public String toString() {
        return "Reservation{" + "counter=" + counter + ", reservationID=" + reservationID + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", active=" + active + ", venue=" + venue + ", userID=" + userID + '}';
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

    public boolean checkAvailability() {
        // Implementation
        return true;
    }

}
