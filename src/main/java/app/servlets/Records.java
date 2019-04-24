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


//import java.sql.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Records extends HttpServlet {


    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    Model model = Model.getInstance();
    UserDao dao;

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        model = Model.getInstance();
        ArrayList userslist = model.getUsersTable();
        request.setAttribute("listName", userslist);

        dao = UserDao.getInstance();
        ArrayList<User> updateuserlist = dao.getAll();
        //обновляем данные в модели
        sortList(updateuserlist);
        model.setUsersTable(updateuserlist);
        userslist = updateuserlist;
        request.setAttribute("listName", userslist);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/records.jsp");
        requestDispatcher.forward(request, response);
    }
    public void sortList(ArrayList<User> list) {
        Collections.sort(list, new Comparator<User>() {
            public int compare(User o1, User o2) {
                double o1try = model.getAverageCountTry(o1);
                double o2try = model.getAverageCountTry(o2);
                if (o1try == 0){
                    return 1;
                }
                if (o2try == 0){
                    return -1;
                }
                if(o1try==o2try){
                    return 0;
                }

                if(o1try > o2try){
                    return 1;
                }else{
                    return -1;
                }
            }
        });


    }
}
