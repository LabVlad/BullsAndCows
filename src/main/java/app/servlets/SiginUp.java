package app.servlets;

import app.dao.UserDao;
import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class SiginUp extends HttpServlet {
    UserDao dao;
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/siginUp.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Model model = Model.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password.concat("соль");
        password = model.MD5hash(password);

        dao = UserDao.getInstance();
       // dao.insertUser(login, password);

            if(dao.insertUser(login, password)) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/siginUp.jsp");
                response.setStatus(40);
                requestDispatcher.forward(request, response);
            }
            else{
                model.setActionUser(new User(login));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/menu.jsp");
                requestDispatcher.forward(request, response);
            }
    }
}
