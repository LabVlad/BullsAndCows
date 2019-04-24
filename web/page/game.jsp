<%@ page import="java.util.ArrayList" %>
<%@ page import="app.model.Model" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game</title>
</head>
<body style="text-align: center">
<p> Игра "Бык-корова"</p>
<form method="post">
    <p><input type="number" name="usernumber" min="0" value="0" max="9" >
        <input type="number" name="usernumber2" min="0" value="0" max="9">
        <input type="number" name="usernumber3" min="0" value="0" max="9">
        <input type="number" name="usernumber4" min="0" value="0" max="9"></p>
<p><input type="submit" value="Подтвердить"> </p>
</form>
<div>
    <%
        for (ArrayList<Integer> n: Model.getInstance().getHistoryTry()) {
            out.println("<p>"+ n.toString().replace(",","").substring(1,8) + " Быков: "+ n.get(4)+
                    " Коров: " + n.get(5)  +"</p>");
        }
    %>
</div>
<p>
    <button onclick="location.href='/menu'"> Назад </button>
</p>
</body>
</html>
