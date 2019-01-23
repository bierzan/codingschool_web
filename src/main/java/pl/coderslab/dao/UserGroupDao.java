package pl.coderslab.dao;

import pl.coderslab.model.UserGroup;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static pl.coderslab.model.UserGroup.getUserGroupWithAttributesByResultSet;

public class UserGroupDao {
    private static UserGroupDao instance;

    public static UserGroupDao getInstance() {
        if (instance == null) {
            instance = new UserGroupDao();
        }
        return instance;
    }

    public void save(UserGroup group) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (group.getId() == 0) {
            String sql = "INSERT INTO user_group (name) VALUES (?)";
            String[] generatedColumns = {"id"};
            PreparedStatement prepStm = conn.prepareStatement(sql, generatedColumns);
            prepStm.setString(1, group.getName());
            prepStm.executeUpdate();
            ResultSet rs = prepStm.getGeneratedKeys();

            if (rs.next()) {
                group.setId(rs.getInt(1));
            }
        } else {
            update(group);
        }
    }

    public void update(UserGroup group) throws SQLException {
        Connection conn = DBUtil.getConn();
        if (group.getId() > 0) {
            String sql = "UPDATE user_group SET name = ? WHERE id = ?";
            PreparedStatement prepStm = conn.prepareStatement(sql);
            prepStm.setString(1, group.getName());
            prepStm.setInt(2, group.getId());
            prepStm.executeUpdate();
        } else {
            System.out.println("Taka grupa nie istnieje w baze danych.");
        }
    }

    public UserGroup[] loadAll() throws SQLException {
        Connection conn = DBUtil.getConn();

        ArrayList<UserGroup> groups = new ArrayList<UserGroup>();
        String sql = "SELECT * FROM user_group";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        ResultSet rs = prepStm.executeQuery();
        while (rs.next()) {
            groups.add(getUserGroupWithAttributesByResultSet(rs));
        }
        UserGroup[] gArray = new UserGroup[groups.size()];
        gArray = groups.toArray(gArray);
        return gArray;
    }

    public UserGroup loadById(int id) throws SQLException {
        Connection conn = DBUtil.getConn();
        String sql = "SELECT * FROM user_group WHERE id = ?";
        PreparedStatement prepStm = conn.prepareStatement(sql);
        prepStm.setInt(1, id);
        ResultSet rs = prepStm.executeQuery();

        if (rs.next()) {
            UserGroup loadedGroup = getUserGroupWithAttributesByResultSet(rs);
            return loadedGroup;
        }
        return null;
    }
}
