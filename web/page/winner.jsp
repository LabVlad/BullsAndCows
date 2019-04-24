<%@ page import="app.model.Model" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вы угадали!</title>
</head>
<body style="text-align: center">
<p>Вы угадали! Поздравляю!</p>
<%

    Model model = Model.getInstance();
    out.println("<p>"+ request.getAttribute("input").toString().replace(",","").substring(1,8) + "</p>");
    out.println("Количество попыток: " + model.getTryNow()+";");
    out.println("Общее число попыток за все игры: " + model.getActionUser().getCountTry()+";");
    out.println("Общее число игр: " + model.getActionUser().getCountGame()+";");
    out.println("Среднее число попыток за игру: " + String.format("%8.2f",  model.getAverageCountTry(model.getActionUser())));
    model.setTryNow(0);
    model.setHistoryTry(new ArrayList<ArrayList<Integer>>());
%>
<p><button onclick="location.href='/menu'"> Назад </button> </p>
</body>
</html>
