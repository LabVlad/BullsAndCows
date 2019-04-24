<%@ page import="java.util.ArrayList" %>
<%@ page import="app.entities.User" %>
<%@ page import="app.model.Model" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Таблица рекордов</title>
</head>
<body style="text-align: center">
<table border="1" width="70%" cellpadding="5">
    <tr>
        <th>Логин</th>
        <th>Общее число попыток</th>
        <th>Общее число игр</th>
        <th>Среднее число попыток в игре</th>
    </tr>
<%
    ArrayList<User> users = (ArrayList) request.getAttribute("listName");
    for (int i = 0; i  < users.size(); i++){
       out.println("<tr>");
       out.println("<td>" + users.get(i).getLogin() + "</td>");
       out.println("<td>" + users.get(i).getCountTry() + "</td>");
       out.println("<td>" + users.get(i).getCountGame() + "</td>");
       out.println("<td>" + String.format("%8.2f",  Model.getInstance().getAverageCountTry(users.get(i))) + "</td>");
        out.println("</tr>");
    }

%>
</table>

<p>
    <button onclick="location.href='/menu'"> Назад </button>
</p>
</body>
</html>
