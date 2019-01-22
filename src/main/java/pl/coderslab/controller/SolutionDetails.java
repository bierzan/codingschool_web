package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;
import pl.coderslab.utils.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/SolutionDetails")
public class SolutionDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String solId = request.getParameter("solId");
        try(Connection conn = DBUtil.getConn()){
            Solution sol = SolutionDao.getInstance().loadById(Integer.valueOf(solId));
            request.setAttribute("sol", sol);
            getServletContext().getRequestDispatcher("/solutionDetails.jsp").forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
