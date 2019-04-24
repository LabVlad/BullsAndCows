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
import java.sql.*;


public class Login extends HttpServlet {

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private Model model;
    private UserDao dao;

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            dao = UserDao.getInstance();
            dao.tableCreate();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/login.jsp");
            requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        model = Model.getInstance();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        password.concat("соль");
        password = model.MD5hash(password);

        dao = UserDao.getInstance();
        User u = dao.getUser(login, password);
        if(u instanceof User){
            model.setActionUser(new User(u.getLogin(), u.getCountTry(), u.getCountGame()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/menu.jsp");
            requestDispatcher.forward(request, response);
        }
        else{
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/login.jsp");
            response.setStatus(404);
            requestDispatcher.forward(request, response);
            }
        }
    }

