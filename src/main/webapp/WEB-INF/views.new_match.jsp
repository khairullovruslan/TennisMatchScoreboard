<%--
  Created by IntelliJ IDEA.
  User: ruslankhairullov
  Date: 23.09.2024
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Напишите имена игроков</h1>
    <form id="new-match" action="new-match" method="post">
        <label for="name">Игрок 1:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="name2">Игрок 2:</label>
        <input type="text" id="name2" name="name2" required>
        <br>
        <button type="submit">Играть</button>
    </form>

</body>
</html>
