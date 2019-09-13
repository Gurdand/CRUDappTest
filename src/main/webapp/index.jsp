<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 02.09.2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>User Manager</title>
  </head>
  <body>
    <header>
      <h1>User Manager</h1>
      <form method="post" action="/login">
        <input name="login" placeholder="Login">
        <input type="password" name="password" placeholder="Password">
        <input type="submit" value="Войти">
      </form>
      <p>${message}</p>
    </header>
  </body>
</html>
