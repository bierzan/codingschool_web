package pl.coderslab.model;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.UserDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {

    private int id = 0;
    private String created = dateNow();
    private String updated;
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

    public static Solution getSolutionWithAttributesFromResultSet(Connection conn, ResultSet rs) throws SQLException {
        Solution loadedSolution = new Solution();
        loadedSolution.id = rs.getInt("id");
        loadedSolution.created = rs.getString("created");
        loadedSolution.updated = rs.getString("updated");
        loadedSolution.description = rs.getString("description");
        int exId = rs.getInt("exercise_id");
        int userId = rs.getInt("user_id");

        if (exId > 0) {
            loadedSolution.exercise = ExerciseDao.getInstance().loadById(exId);
        }

        if (userId > 0) {
            loadedSolution.user = UserDao.getInstance().loadById(userId);
        }
        return loadedSolution;
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
}

