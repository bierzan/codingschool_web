package pl.coderslab.dao;

import pl.coderslab.model.User;
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

    public void save(User user) throws SQLException {
        Connection conn = DBUtil.getConn();


        if (user.getId() == 0) {
            PreparedStatement prepStm;
            try {
                String sql = "INSERT INTO users (username, password, email, user_group_id) VALUES (?, ?, ?,?)";
                String[] generatedColumns = {"id"};
                prepStm = conn.prepareStatement(sql, generatedColumns);
                prepStm.setString(1, user.getUsername());
                prepStm.setString(2, user.getPassword());
                prepStm.setString(3, user.getEmail());
                prepStm.setInt(4, user.getGroup().getId());
                prepStm.executeUpdate();
            } catch (NullPointerException e) { //kiedy nie ma przypisanej grupy

                String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
                String[] generatedColumns = {"id"};
                prepStm = conn.prepareStatement(sql, generatedColumns);
                prepStm.setString(1, user.getUsername());
                prepStm.setString(2, user.getPassword());
                prepStm.setString(3, user.getEmail());
                prepStm.executeUpdate();
            }

            ResultSet rs = prepStm.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } else {
            update(user);
        }

    }

    public void update(User user) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (user.getId() > 0) {
            PreparedStatement prepStm;
            try {
                String sql = "UPDATE users SET username = ?, password = ?, email = ?, user_group_id = ? WHERE id = ?";
                prepStm = conn.prepareStatement(sql);
                prepStm.setString(1, user.getUsername());
                prepStm.setString(2, user.getPassword());
                prepStm.setString(3, user.getEmail());
                prepStm.setInt(4, user.getGroup().getId());
                prepStm.setInt(5, user.getId());
                prepStm.executeUpdate();
            } catch (NullPointerException e) {
                String sql = "UPDATE users SET username = ?, password = ?, email = ? WHERE id = ?";
                prepStm = conn.prepareStatement(sql);
                prepStm.setString(1, user.getUsername());
                prepStm.setString(2, user.getPassword());
                prepStm.setString(3, user.getEmail());
                prepStm.setInt(4, user.getId());
                prepStm.executeUpdate();
            }

        } else {
            System.out.println("Taki użytkownik nie istnieje w baze danych."); //todo zostawić te printy do konsoli?
        }
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

    public User[] loadAll() throws SQLException {
        Connection conn = DBUtil.getConn();

        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        ResultSet rs = prepStm.executeQuery();
        while (rs.next()) {

            users.add(User.getUserWithAttributesFromResultSet(conn, rs));
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
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
