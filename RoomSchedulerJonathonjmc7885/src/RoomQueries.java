/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JACK
 */
public class RoomQueries {

    private static Connection connection;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;

    public static ArrayList<String> getAllPossibleRooms(int seats) {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try {
            getAllPossibleRooms = connection.prepareStatement("select name from rooms where seats >= (?) order by seats");
            getAllPossibleRooms.setInt(1, seats);
            resultSet = getAllPossibleRooms.executeQuery();

            while (resultSet.next()) {
                rooms.add(resultSet.getString(1));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }

    public static void addRoom(String name, int seats) {
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into rooms (name,seats) values (?,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static void dropRoom(String name) {
        connection = DBConnection.getConnection();
        try {
            dropRoom = connection.prepareStatement("delete from rooms where name = ?");
            dropRoom.setString(1, name);
            dropRoom.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }
}
