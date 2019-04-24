package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends HttpServlet {
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Model.getInstance().setTryNow(0);
        Model.getInstance().setHistoryTry(new ArrayList<ArrayList<Integer>>());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/menu.jsp");
        requestDispatcher.forward(request, response);
    }
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/menu.jsp");
        requestDispatcher.forward(request, response);
    }
}
