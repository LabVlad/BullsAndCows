<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Авторизация</title>
</head>
<body style="text-align: center">
<p>Вход в систему</p>
<%
  // String test = (String) request.getAttribute("listName");
  if(response.getStatus()==404) {
    out.println("<p>" + "Имя пользователя или пароль неверны" + "</p>");
  }

%>
<p>
  <form method="post" action="">
  <p>Введите логин и пароль</p>
  <p>
    <input type="text" name="login" placeholder="Логин">
  </p>
  <p>
    <input type="password" name="password" placeholder="Пароль">
  </p>
  <p></p>
  <p>
    <input class="button" type="submit" value="Войти">
  </p>
</form>
</p>
<p><button onclick="location.href='/siginup'"> Регистрация </button> </p>
</body>
</html>
