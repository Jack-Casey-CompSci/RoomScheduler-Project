
import java.sql.Date;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JACK
 */
public class WaitlistEntry {
    private String faculty;
    private Date date;
    private int seats;
    private Timestamp timestamp;

    public WaitlistEntry(String faculty, Date date, int Seats, Timestamp timestamp) {
        this.faculty = faculty;
        this.date = date;
        this.seats = Seats;
        this.timestamp = timestamp;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
            
    
}
