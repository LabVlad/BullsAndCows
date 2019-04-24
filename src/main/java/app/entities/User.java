package app.entities;


public class User {
    private String login;
    private int countTry;
    private int  countGame;

    public User(){
    }

    public User (String login){
        this.login = login;
        this.countTry = 0;
        this.countGame = 0;
    }

    //test const
    public User (String login, /* String password,*/ int countTry, int countGame){
        this.login = login;
        this.countTry = countTry;
        this.countGame = countGame;
    }

    public String getLogin() {
        return this.login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public int getCountTry() {
        return this.countTry;
    }
    public void setCountTry(int countTry) {
        this.countTry = countTry;
    }
    public void plusTry(int countTry){
        this.countTry += countTry;
    }

    public int getCountGame() {
        return this.countGame;
    }
    public void setCountGame(int countGame) {
        this.countGame = countGame;
    }
    public void plusOneGame(){
        this.countGame += 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + login + '\'' +
                ", countTry=" + countTry +
                ", countGame=" + countGame +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (this.countTry != user.countTry) return false;
        if (this.countGame != user.countGame) return false;
        return this.login != null ? this.login.equals(user.login) : user.login == null;
    }

    @Override
    public int hashCode() {
        int result = this.login != null ? this.login.hashCode() : 0;
        result = 31 * result + this.countTry;
        result = 31 * result + this.countGame;
        return result;
    }
}
