
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
public class ReservationQueries {

    private static Connection connection;
    private static PreparedStatement getReservationsByDate; //parameter date
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationsByRoom;
    private static PreparedStatement deleteReservation;
    private static PreparedStatement deleteReservationEntry;
    private static ResultSet resultSet;

    public static ArrayList<ReservationEntry> getReservationsByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reserveByDates = new ArrayList<ReservationEntry>();
        try {
            getReservationsByDate = connection.prepareStatement("select * from reservations where date = ? order by room");
            getReservationsByDate.setDate(1, date);
            resultSet = getReservationsByDate.executeQuery();

            while (resultSet.next()) {
                ReservationEntry reserveEntry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reserveByDates.add(reserveEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reserveByDates;

    }
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reserveByRoom = new ArrayList<ReservationEntry>();
        try {
            getReservationsByRoom = connection.prepareStatement("select * from reservations where room = ? order by room");
            getReservationsByRoom.setString(1, room);
            resultSet = getReservationsByRoom.executeQuery();

            while (resultSet.next()) {
                ReservationEntry reserveEntry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                reserveByRoom.add(reserveEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return reserveByRoom;

    }
    public static void addReservationEntry(String faculty, String room, Date date, int seats, Timestamp timestamp) {
        connection = DBConnection.getConnection();
        try {
            addReservationEntry = connection.prepareStatement("insert into reservations (faculty,room,date,seats,timestamp) values (?,?,?,?,?)");
            addReservationEntry.setString(1, faculty);
            addReservationEntry.setString(2, room);
            addReservationEntry.setDate(3, date);
            addReservationEntry.setInt(4, seats);
            addReservationEntry.setTimestamp(5, timestamp);
            addReservationEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static ArrayList<String> getRoomsReservedByDate(Date date) {
        connection = DBConnection.getConnection();
        ArrayList<String> room = new ArrayList<String>();
        try {
            getRoomsReservedByDate = connection.prepareStatement("select room from reservations where date = ? order by room");
            getRoomsReservedByDate.setDate(1, date);
            resultSet = getRoomsReservedByDate.executeQuery();

            while (resultSet.next()) {
                room.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return room;
    }

    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty) {
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> faculties = new ArrayList<ReservationEntry>();
        try {
            getReservationsByFaculty = connection.prepareStatement("select * from reservations where faculty = ?");
            getReservationsByFaculty.setString(1, faculty);
            resultSet = getReservationsByFaculty.executeQuery();

            while (resultSet.next()) {
                ReservationEntry reserveEntry = new ReservationEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getTimestamp(5));
                faculties.add(reserveEntry);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return faculties;
    }

    public static void deleteReservation(String faculty, Date date) {
        connection = DBConnection.getConnection();
        try {
            deleteReservation = connection.prepareStatement("delete from reservations where faculty = ? and date = ?");
            deleteReservation.setString(1, faculty);
            deleteReservation.setDate(2, date);
            deleteReservation.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    public static void deleteReservationEntry(String room) {
        connection = DBConnection.getConnection();
        try {
            deleteReservationEntry = connection.prepareStatement("delete from reservations where room = ?");
            deleteReservationEntry.setString(1, room);
            deleteReservationEntry.executeUpdate();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
