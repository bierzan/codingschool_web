package pl.coderslab.dao;

import pl.coderslab.model.Exercise;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDao {
    private static ExerciseDao instance;

    public static ExerciseDao getInstance() {
        if (instance == null) {
            instance = new ExerciseDao();
        }
        return instance;
    }

    public void save(Exercise ex) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (ex.getId() == 0) {
            String sql = "INSERT INTO exercise (title, description) VALUES (?, ?)";
            String[] generatedColumns = {"id"};
            PreparedStatement prepStm = conn.prepareStatement(sql, generatedColumns);
            prepStm.setString(1, ex.getTitle());
            prepStm.setString(2, ex.getDescription());
            prepStm.executeUpdate();
            ResultSet rs = prepStm.getGeneratedKeys();

            if (rs.next()) {
                ex.setId(rs.getInt(1));
            }
        } else {
            update(ex);
        }
    }

    public void update(Exercise ex) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (ex.getId() > 0) {
            String sql = "UPDATE exercise SET title = ?, description = ? WHERE id = ?";
            PreparedStatement prepStm = conn.prepareStatement(sql);
            prepStm.setString(1, ex.getTitle());
            prepStm.setString(2, ex.getDescription());
            prepStm.setInt(3, ex.getId());
            prepStm.executeUpdate();
        } else {
            System.out.println("Takie ćwiczenie nie istnieje w baze danych.");
        }
    }

    public Exercise[] loadAll() throws SQLException {
        Connection conn = DBUtil.getConn();
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();
        String sql = "SELECT * FROM exercise";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        ResultSet rs = prepStm.executeQuery();
        while (rs.next()) {
            exercises.add(Exercise.getExerciseWithAttributeByResultSet(rs));
        }
        Exercise[] eArray = new Exercise[exercises.size()];
        eArray = exercises.toArray(eArray);
        return eArray;
    }

    public Exercise loadById(int id) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM exercise WHERE id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, id);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            Exercise loadedExercise = Exercise.getExerciseWithAttributeByResultSet(rs);
            return loadedExercise;
        }
        return null;
    }

}
