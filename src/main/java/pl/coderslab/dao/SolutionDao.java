package pl.coderslab.dao;

import pl.coderslab.model.Solution;
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
