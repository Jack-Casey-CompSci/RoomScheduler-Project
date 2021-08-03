
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JACK
 */
public class WaitlistQueries {

    private static Connection connection;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static PreparedStatement getWaitlistByDate;
    private static ResultSet resultSet;

    public static ArrayList<WaitlistEntry> getWaitlist() {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try {
            getWaitlist = connection.prepareStatement("select * from waitlist order by date");
            resultSet = getWaitlist.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry waitlistEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlist.add(waitlistEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlist;
    }

    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByFaculty = new ArrayList<WaitlistEntry>();
        try {
            getWaitlistByFaculty = connection.prepareStatement("select * from waitlist where faculty = ?");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry waitlistEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByFaculty.add(waitlistEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlistByFaculty;
    }
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlistByDate = new ArrayList<WaitlistEntry>();
        try {
            getWaitlistByDate = connection.prepareStatement("select * from waitlist where date = ? order by date");
            getWaitlistByDate.setDate(1, date);
            resultSet = getWaitlistByDate.executeQuery();

            while (resultSet.next()) {
                WaitlistEntry waitlistEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlistByDate.add(waitlistEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitlistByDate;
    }

    public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist (faculty,date,seats,timestamp) values (?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, timestamp);
            addWaitlistEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static void deleteWaitlistEntry(String faculty, Date date) {
        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = ? and date = ?");
            deleteWaitlistEntry.setString(1, faculty);
            deleteWaitlistEntry.setDate(2, date);
            deleteWaitlistEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
