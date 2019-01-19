package pl.coderslab.controller;

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

@WebServlet("/")
public class Homepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String num = getServletContext().getInitParameter("solutions-limit");
        try(Connection conn = DBUtil.getConn()){
            Solution[] solutions = Solution.loadAll(conn, Integer.valueOf(num));
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (SQLException e){
            e.printStackTrace();
        }
        //TODO STOP NA ZADANIU 2 STRONA 28

    }
}
