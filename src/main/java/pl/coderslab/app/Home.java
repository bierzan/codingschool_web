package pl.coderslab.app;

import pl.coderslab.model.Exercise;
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
public class Home extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Exercise exercise = new Exercise("testowe", "cwiczenie testowe");
        try(Connection conn = DBUtil.getConn()){
            exercise.save(conn);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
