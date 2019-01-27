package pl.coderslab.controller;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class Homepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String num = getServletContext().getInitParameter("solutions-limit");

        try {
            Solution[] solutions = SolutionDao.getInstance().loadAll(Integer.valueOf(num));
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}


//todo komunikaty o istniejacym juz uzytkowniku przy dodawaniu nowego lub edycji - metoda znajdujaca duplikaty?
