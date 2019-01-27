package pl.coderslab.model;


import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.utils.BCrypt;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    protected String email;
    private int id = 0;
    private UserGroup group;

    public User() {

    }

    public User(String username, String password, String email) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
    }

    public User(String username, String password, String email, int groupId) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        try {
            Connection conn = DBUtil.getConn();
            this.group = UserGroupDao.getInstance().loadById(groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    public static User getUserWithAttributesFromResultSet(Connection conn, ResultSet rs) throws SQLException {
        User loadedUser = new User();
        loadedUser.id = rs.getInt("id");
        loadedUser.username = rs.getString("username");
        loadedUser.password = rs.getString("password");
        loadedUser.email = rs.getString("email");
        int groupId = rs.getInt("user_group_id");
        if (groupId > 0) {
            loadedUser.group = UserGroupDao.getInstance().loadById(groupId);
        }
        return loadedUser;
    }

    //TODO sprawdzenie czy mail sie nie powtarza (setter na mailu?) - czy sprawdza to sql?

    //GETTERY I SETTERY

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UserGroup getGroup() {
        return group;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroup(int groupId) {
        try {
            Connection conn = DBUtil.getConn();
            this.group = UserGroupDao.getInstance().loadById(groupId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGroup(UserGroup group) {
        this.group = group;
    }

    public void setId(int id) {
        this.id = id;
    }
}
