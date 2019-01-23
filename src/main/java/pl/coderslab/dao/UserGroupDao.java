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

    public static UserGroupDao getInstance(){
        if(instance == null){
            instance = new UserGroupDao();
        }
        return instance;
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

    public static UserGroup loadById(int id) throws SQLException {
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
