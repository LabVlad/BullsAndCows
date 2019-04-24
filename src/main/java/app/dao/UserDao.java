package app.dao;



import app.entities.User;
import app.model.Model;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static Model model = Model.getInstance();
    private static UserDao instance = new UserDao();

    public static UserDao getInstance() {
        return instance;
    }


    public static void tableCreate(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulls-and-cows?serverTimezone=UTC&useSSL=false",
                    "root", "root");
            preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user (iduser INT(11) PRIMARY KEY auto_increment, login VARCHAR(45), password VARCHAR(60), counttry INTEGER(10), countgame INTEGER(10))");
            preparedStatement.execute();
            System.out.println("Таблица создана или уже существует.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static User getUser(String login, String password){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulls-and-cows?serverTimezone=UTC&useSSL=false",
                    "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                User result = new User(resultSet.getString("login"),
                        resultSet.getInt("counttry"),
                        resultSet.getInt("countgame"));
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return null;
    }

    public static ArrayList<User> getAll(){
        ArrayList<User> result = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulls-and-cows?serverTimezone=UTC&useSSL=false",
                    "root", "root");
            preparedStatement = connection.prepareStatement("SELECT login, counttry, countgame FROM user");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User temp = new User(resultSet.getString("login"),
                        resultSet.getInt("counttry"), resultSet.getInt("countgame"));
                result.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;
    }

    public static boolean insertUser(String login, String password){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulls-and-cows?serverTimezone=UTC&useSSL=false",
                    "root", "root");
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE login = ?");
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return false;
            }
            else{
                preparedStatement = connection.prepareStatement("INSERT INTO user (login, password,counttry,countgame) VALUES (?,?,0,0)");
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.execute();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try { connection.close(); } catch(SQLException se) { se.printStackTrace(); }
            try { preparedStatement.close(); } catch(SQLException se) {se.printStackTrace();  }
            try { resultSet.close(); } catch (SQLException se){se.printStackTrace();}
        }
        return false;
    }

    public static void updateUser(String login, int countTry, int countGame){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bulls-and-cows?serverTimezone=UTC&useSSL=false",
                    "root", "root");
            preparedStatement = connection.prepareStatement("UPDATE user SET counttry = ?,countgame = ? WHERE login=?");
            preparedStatement.setInt(1, countTry);
            preparedStatement.setInt(2, countGame);
            preparedStatement.setString(3, login);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try { connection.close(); } catch(SQLException se) { se.printStackTrace(); }
            try { preparedStatement.close(); } catch(SQLException se) {se.printStackTrace();  }
            try { resultSet.close(); } catch (SQLException se){se.printStackTrace();}
        }
    }
  /*  public PreparedStatement getPreparedStatement(String sqlquery){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

