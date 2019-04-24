package app.model;

import app.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;



public class Model {
    private static Model instance = new Model();

    private ArrayList<User> usersTable;
    private User actionUser;
    private int tryNow;
    public ArrayList<Integer> numberInGame;
    public ArrayList<ArrayList<Integer>> historyTry;



    public static Model getInstance() {
        return instance;
    }

    private Model() {
        this.usersTable = new ArrayList<User>();
        this.actionUser = new User();
        this.tryNow = 0;
        this.numberInGame = new ArrayList<Integer>();
        this.historyTry = new ArrayList<ArrayList<Integer>>();
    }


    public String getName(){
        int j = this.usersTable.size();
        StringBuilder list = new StringBuilder();
        for(int i = 0; i<j; i++){
            list.append(this.usersTable.get(i).getLogin() + " ");
        }

        return list.toString();
    }

    public ArrayList<User> getUsersTable(){
        return this.usersTable;
    }
    public void setUsersTable(ArrayList<User> model){
        //this.usersTable.clear();
        this.usersTable = new ArrayList<User>(model);
    }
    public void setActionUser(User actionUser){
       // System.out.println(actionUser.toString());
        this.actionUser = actionUser;
       // System.out.println(this.actionUser.toString());
    }
    public User getActionUser(){
        return this.actionUser;
    }

    public void setNumberInGame(ArrayList<Integer> numberInGame){
        this.numberInGame = new ArrayList<Integer>(numberInGame);
    }
    public ArrayList<Integer> getNumberInGame(){
        return this.numberInGame;
    }

    public int getTryNow() {
        return this.tryNow;
    }
    public void setTryNow(int tryNow) {
        this.tryNow = tryNow;
    }
    public void plusTryNow(int countTry) {
        this.tryNow += countTry;
    }
    public double getAverageCountTry(User user){
        if(user.getCountGame()>0) {
            //return Math.round((double) (actionUser.getCountTry() / actionUser.getCountGame() * 1000)) / (double) 1000.0;
            return (double) user.getCountTry()/user.getCountGame();
           // return Math.round(((actionUser.getCountTry() / actionUser.getCountGame()) * 1000)) / (double) 1000.0;
        }else return 0;
    }
    public ArrayList<ArrayList<Integer>> getHistoryTry() {
        return this.historyTry;
    }
    public void setHistoryTry(ArrayList<ArrayList<Integer>> historyTry) {
        this.historyTry = historyTry;
    }

    public static String MD5hash(String password){
        try {
            Class.forName("org.apache.commons.codec.digest.DigestUtils");
            return DigestUtils.md5Hex(password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}