package pl.coderslab.controller;

import pl.coderslab.dao.UserGroupDao;
import pl.coderslab.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PanelUserGroups")
public class PanelUserGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupName = request.getParameter("groupName");
        String groupToEdit = request.getParameter("editGroupId");
        String newGroupName = request.getParameter("newGroupName");

        if (groupToEdit.length() > 0) {
            try {
                UserGroup group = UserGroupDao.getInstance().loadById(Integer.valueOf(groupToEdit));
                group.setName(newGroupName);
                UserGroupDao.getInstance().update(group);
                response.sendRedirect("/PanelUserGroups");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            UserGroup group = new UserGroup(groupName);

            try {
                UserGroupDao.getInstance().save(group);
                response.sendRedirect("/PanelUserGroups");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groupToEdit = request.getParameter("editGroupId");

        try {
            UserGroup[] groups = UserGroupDao.getInstance().loadAll();
            request.setAttribute("edit", groupToEdit);
            request.setAttribute("groups", groups);
            getServletContext().getRequestDispatcher("/admin/panelUserGroups.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
