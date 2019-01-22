package pl.coderslab.model;

import pl.coderslab.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Solution {

    private int id = 0;
    private String created = dateNow(); //fixme zmiana stringów na localdatetime
    private String updated; //fixme zmiana stringow na ldf
    private String description;
    private Exercise exercise;
    private User user;

    public Solution() {
    }

    public Solution(String description) {
        this.description = description;

    }

    public static String dateNow() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated = dateNow();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    ////////////////////////////////////


    public static Solution[] loadAll(Connection conn) throws SQLException {
        String sql = "SELECT * FROM solution";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        ResultSet rs = prepStm.executeQuery();

        Solution[] sArray = getSolutions(conn, rs);
        return sArray;
    }

    public static Solution[] loadAllByUserId(Connection conn, int userId) throws SQLException {

        User user = UserDao.getInstance().loadById(userId);
        Exercise[] exercises = Exercise.loadAll(conn);

        ArrayList<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE user_id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, userId);
        ResultSet rs = prepStm.executeQuery();
        while (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.created = rs.getString("created");
            loadedSolution.updated = rs.getString("updated");
            loadedSolution.description = rs.getString("description");
            int exId = rs.getInt("exercise_id");

            if (exId > 0) {
                for (int i = 0; i < exercises.length; i++) {
                    if (exercises[i].getId() == exId) {
                        loadedSolution.exercise = exercises[i];
                        break;
                    }
                }
            }
            loadedSolution.user = user;
            solutions.add(loadedSolution);
        }
        Solution[] sArray = new Solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }

    public static Solution[] loadAllByExerciseId(Connection conn, int exerciseId) throws SQLException {

        User[] users = User.loadAll(conn);
        Exercise exercise = Exercise.loadById(conn, exerciseId);

        ArrayList<Solution> solutions = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE exercise_id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, exerciseId);
        ResultSet rs = prepStm.executeQuery();
        while (rs.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = rs.getInt("id");
            loadedSolution.created = rs.getString("created");
            loadedSolution.updated = rs.getString("updated");
            loadedSolution.description = rs.getString("description");
            loadedSolution.exercise = exercise;

            int userId = rs.getInt("user_id");

            if (userId > 0) {
                for (int i = 0; i < users.length; i++) {
                    if (users[i].getId() == userId) {
                        loadedSolution.user = users[i];
                        break;
                    }
                }
            }

            solutions.add(loadedSolution);
        }
        Solution[] sArray = new Solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }


    private static Solution[] getSolutions(Connection conn, ResultSet rs) throws SQLException {
        User[] users = User.loadAll(conn);
        Exercise[] exercises = Exercise.loadAll(conn);
        ArrayList<Solution> solutions = new ArrayList<Solution>();
        while (rs.next()) {
            Solution loadedSolution = getSolutionWithAttributesFromResultSet(conn, rs);
            solutions.add(loadedSolution);
        }
        Solution[] sArray = new Solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }

    public static Solution getSolutionWithAttributesFromResultSet(Connection conn, ResultSet rs) throws SQLException {
        Solution loadedSolution = new Solution();
        loadedSolution.id = rs.getInt("id");
        loadedSolution.created = rs.getString("created");
        loadedSolution.updated = rs.getString("updated");
        loadedSolution.description = rs.getString("description");
        int exId = rs.getInt("exercise_id");
        int userId = rs.getInt("user_id");

        if (exId > 0) {
            loadedSolution.exercise = Exercise.loadById(conn, exId); // todo przerobic na exercise DAO
        }

        if (userId > 0) {
            loadedSolution.user = UserDao.getInstance().loadById(userId);
        }
        return loadedSolution;
    }
}

