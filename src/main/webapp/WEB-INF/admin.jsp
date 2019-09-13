<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: Змей
  Date: 03.09.2019
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" language="java" import="java.sql.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <META http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <section>
        <h2>Create new user</h2>
        <form method="post" action="/admin/create">
            <input name="name" placeholder="Name" >
            <input type="number" name="age" placeholder="Age">
            <input name="login" placeholder="Login">
            <input name="password" placeholder="password">
            <select size="2" name="role">
                <option selected value="user">user</option>
                <option value="admin">admin</option>
            </select>
            <button type="submit">Create</button>
        </form>
    </section>

    <section>
        <p><c:out value="${message}"/></p>
        <a href="/">На главную</a>
        <a href="/user">User page</a>
    </section>

    <section>
        <h2>Update user</h2>
        <form method="post" action="/admin/update">
            <input type="number" name="id" placeholder="ID">
            <input name="name" placeholder="New name">
            <input type="number" name="age" placeholder="New age">
            <input name="login" placeholder="New login">
            <input name="password" placeholder="New password">
            <select size="2" name="role">
                <option selected value="user">user</option>
                <option value="admin">admin</option>
            </select>
            <button type="submit">Update</button>
        </form>
    </section>

    <section>
        <h2>All users</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Login</th>
                <th>Password</th>
                <th>Role</th>
            </tr>
            <%--@elvariable id="users" type="java.util.List"--%>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>${user.login}</td>
                    <td>${user.password}</td>
                    <td>${user.role}</td>
                    <td>
                        <form method="post" action="/admin/delete">
                            <button type="submit" name="id" value="${user.id}" >Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>


</body>
</html>
