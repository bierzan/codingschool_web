package pl.coderslab.model;

import pl.coderslab.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()){
            Exercise exercise = new Exercise("aaa", "bbb");
            exercise.save(conn);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
