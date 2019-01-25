package pl.coderslab.controller;

import pl.coderslab.dao.UserDao;
import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.User;
import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PanelUsers")
public class PanelUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String groupId = request.getParameter("group");

        String editUserId = request.getParameter("editUserId");
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        String newEmail = request.getParameter("newEmail");
        String newGroupId = request.getParameter("newGroupId");


        if (editUserId != null) {
            try {
                User user = UserDao.getInstance().loadById(Integer.valueOf(editUserId));
                user.setUsername(newUsername);
                user.setPassword(newPassword);
                user.setEmail(newEmail);
                if (!"0".equals(newGroupId)) {
                    user.setGroup(Integer.valueOf(newGroupId));
                }
                UserDao.getInstance().update(user);
                response.sendRedirect("/PanelUsers");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            User user = new User(username, password, email);
            if (!"0".equals(groupId)) {
                user.setGroup(Integer.valueOf(groupId));
            }
            try {
                UserDao.getInstance().save(user);
                response.sendRedirect("/PanelUsers");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String userToEdit = request.getParameter("editUserId");

        try {
            UserGroup[] groups = UserGroupDao.getInstance().loadAll();
            User[] users = UserDao.getInstance().loadAll();
            request.setAttribute("edit", userToEdit);
            request.setAttribute("users", users);
            request.setAttribute("groups", groups);
            getServletContext().getRequestDispatcher("/admin/panelUsers.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}