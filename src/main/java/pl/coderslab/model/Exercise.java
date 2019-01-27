package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exercise {

    private int id = 0;
    private String title;
    private String description;

    public Exercise() {
    }

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static Exercise getExerciseWithAttributeByResultSet(ResultSet rs) throws SQLException {
        Exercise lodadeExercise = new Exercise();
        lodadeExercise.id = rs.getInt("id");
        lodadeExercise.title = rs.getString("title");
        lodadeExercise.description = rs.getString("description");
        return lodadeExercise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM exercise WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

}
