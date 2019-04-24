
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body style="text-align: center">
    <form method="post">
        <p>Введите логин и пароль</p>
        <%
            if(response.getStatus()==40) {
                out.println("<p>" + "Такой пользователь уже существует" + "</p>");
            }

        %>
        <p> <input type="text" name="login" placeholder="Логин"></p>
        <p> <input type="password" name="password" placeholder="Пароль"></p>
        <p>
            <input type="submit" value="Подтвердить">
            <input type="reset" value="Очистить">
        </p>
    </form>
<p>
    <button onclick="location.href='/'"> Назад </button>
</p>
</body>
</html>
