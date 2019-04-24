package app.servlets;

import app.dao.UserDao;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;



public class Game extends HttpServlet {
    Model model = Model.getInstance();
    UserDao dao;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    model.setNumberInGame(genNumber());
        System.out.println("ЧИСЛО:" + model.getNumberInGame());
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/game.jsp");
    requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Integer> inputnumber = new ArrayList<Integer>();
        inputnumber.add(Integer.parseInt(request.getParameter("usernumber")));
        inputnumber.add(Integer.parseInt(request.getParameter("usernumber2")));
        inputnumber.add(Integer.parseInt(request.getParameter("usernumber3")));
        inputnumber.add(Integer.parseInt(request.getParameter("usernumber4")));
        int[] res = chek(inputnumber);
        inputnumber.add(res[0]);
        inputnumber.add(res[1]);
        model.getHistoryTry().add(inputnumber);
        model.plusTryNow(1);
        request.setAttribute("bulls", res[0]);
        request.setAttribute("cows", res[1]);
        dao = UserDao.getInstance();
        if(res[0]==4){
            int countGame = model.getActionUser().getCountGame();
            int countTry = model.getActionUser().getCountTry();
            String login = model.getActionUser().getLogin();
            dao.updateUser(login, countTry+model.getTryNow(),countGame+1);
            model.getActionUser().plusOneGame();
            model.getActionUser().plusTry(model.getTryNow());
            request.setAttribute("input", inputnumber);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/winner.jsp");
            requestDispatcher.forward(request, response);
        }
     RequestDispatcher requestDispatcher = request.getRequestDispatcher("page/game.jsp");
     requestDispatcher.forward(request, response);
    }

    public ArrayList<Integer> genNumber(){
     ArrayList<Integer> number = new ArrayList<Integer>();
     while (number.size() < 4){
         int temp = 0 + (int) (Math.random() * 9);
         if(!number.contains(temp)){
             number.add(temp);
         }
     }
     return number;
    }

    public int[] chek (ArrayList<Integer> chekList){
       ArrayList<Integer> number = model.getNumberInGame();
        int [] res = {0,0};
        if(chekList.size() == number.size()) {
            for (int i = 0; i < chekList.size(); i++) {
                if (chekList.get(i) == number.get(i)) {
                    res[0] += 1; //быки
                    continue;
                }
                if (number.contains(chekList.get(i))) {
                    res[1] += 1; //коровы
                }
            }
        }
        return res;
    }

}
