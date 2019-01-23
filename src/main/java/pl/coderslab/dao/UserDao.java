package pl.coderslab.dao;

import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public User loadById(int id) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, id);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            return User.getUserWithAttributesFromResultSet(conn, rs);
        }
        return null;
    }

    public User[] loadAllByGroupId(int groupId) throws SQLException {
        Connection conn = DBUtil.getConn();

        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE user_group_id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, groupId);
        ResultSet rs = prepStm.executeQuery();

        while (rs.next()) {
            users.add(User.getUserWithAttributesFromResultSet(conn, rs));
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

}
