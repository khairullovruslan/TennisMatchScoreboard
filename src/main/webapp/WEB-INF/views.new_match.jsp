<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание нового матча</title>
    <link rel="stylesheet" type="text/css" href="static/new-match.css">
</head>
<body>
<div class="text-field">
    <form id="new-match" action="new-match" method="post">
        <label class="text-field__label" for="name">Игрок 1:</label>
        <input class="text-field__input" type="text" id="name" name="name" required>
        <br>
        <label class="text-field__label" for="name2">Игрок 2:</label>
        <input class="text-field__input" type="text" id="name2" name="name2" required>
        <br>
        <button type="submit">Играть</button>
    </form>
</div>
</body>
</html>
