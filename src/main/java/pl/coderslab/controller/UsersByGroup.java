package pl.coderslab.controller;

import pl.coderslab.dao.UserDao;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UsersByGroup")
public class UsersByGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupId = request.getParameter("groupId");

        try {
            User[] users = UserDao.getInstance().loadAllByGroupId(Integer.valueOf(groupId));
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/usersByGroup.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
