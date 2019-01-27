package pl.coderslab.controller;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PanelExercises")
public class PanelExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String exToEdit = request.getParameter("editExId");
        String newTitle = request.getParameter("newTitle");
        String newDescription = request.getParameter("newDescription");

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (exToEdit == null) {
            Exercise exercise = new Exercise(title, description);
            try {
                ExerciseDao.getInstance().save(exercise);
                response.sendRedirect("/PanelExercises");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Exercise exercise = ExerciseDao.getInstance().loadById(Integer.valueOf(exToEdit));
                exercise.setTitle(newTitle);
                exercise.setDescription(newDescription);
                ExerciseDao.getInstance().update(exercise);
                response.sendRedirect("/PanelExercises");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String exToEdit = request.getParameter("editExId");

        try {

            Exercise[] exercises = ExerciseDao.getInstance().loadAll();
            request.setAttribute("edit", exToEdit);
            request.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/admin/panelExercises.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
