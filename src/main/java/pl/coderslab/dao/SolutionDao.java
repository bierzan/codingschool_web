package pl.coderslab.dao;

import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SolutionDao {
    private static SolutionDao instance;

    public static SolutionDao getInstance() {
        if (instance == null) {
            instance = new SolutionDao();
        }
        return instance;
    }
//DAO METHODS

    public void save(Solution sol) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (sol.getId() == 0) {
            String sql = "INSERT INTO solution (created, updated, description, exercise_id, user_id) " +
                    "VALUES (?, ?, ?,?,?)";
            String[] generatedColumns = {"id"};
            PreparedStatement prepStm = conn.prepareStatement(sql, generatedColumns);
            prepStm.setString(1, sol.getCreated());
            prepStm.setString(2, sol.getUpdated());
            prepStm.setString(3, sol.getDescription());
            prepStm.setInt(4, sol.getExercise().getId());
            prepStm.setInt(5, sol.getUser().getId());
            prepStm.executeUpdate();
            ResultSet rs = prepStm.getGeneratedKeys();

            if (rs.next()) {
                sol.setId(rs.getInt(1));
            }
        } else {
            update(sol);
        }
    }


    public void update(Solution sol) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (sol.getId() > 0) {
            String sql = "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, user_id = ? WHERE id = ?";
            PreparedStatement prepStm = conn.prepareStatement(sql);
            prepStm.setString(1, Solution.dateNow());
            prepStm.setString(2, sol.getDescription());
            prepStm.setInt(3, sol.getExercise().getId());
            prepStm.setInt(4, sol.getUser().getId());
            prepStm.setInt(5,sol.getId());
            prepStm.executeUpdate();
        } else {
            System.out.println("Takie rozwiązanie nie istnieje w baze danych."); //todo przerobic na komunikat w htmlu
        }
    }

    public Solution delete(Solution sol, int id) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (sol.getId()!= 0) {
            String sql = "DELETE FROM solution WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, sol.getId());
            preparedStatement.executeUpdate();
            sol.setId(0);
        }
        return sol;
    }

    public Solution loadById(int id) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM solution WHERE id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, id);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            return Solution.getSolutionWithAttributesFromResultSet(conn, rs);
        }
        return null;
    }

    public Solution[] loadAll(int limit) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM solution ORDER BY updated DESC, created DESC LIMIT ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, limit);
        ResultSet rs = prepStm.executeQuery();

        Solution[] sArray = getSolutionsFromResultSet(rs);
        return sArray;
    }

    //additional methods
    private Solution[] getSolutionsFromResultSet(ResultSet rs) throws SQLException {
        Connection conn = DBUtil.getConn();
        ArrayList<Solution> solutions = new ArrayList<>();
        while (rs.next()) {
            solutions.add(Solution.getSolutionWithAttributesFromResultSet(conn, rs));
        }
        Solution[] sArray = new Solution[solutions.size()];
        sArray = solutions.toArray(sArray);
        return sArray;
    }
}
