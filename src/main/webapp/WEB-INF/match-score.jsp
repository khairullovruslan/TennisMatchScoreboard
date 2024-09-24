<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Счет матча</title>
</head>
<body>
<table>
    <thead>
        <tr>
            <th>Players</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>

        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${player1}</td>
            <td>${score1.getCountSets()}</td>
            <td>${score1.getCountGames()}</td>
            <td>${score1.getPoints()}</td>
        </tr>
        <tr>
            <td>${player2}</td>
            <td>${score2.getCountSets()}</td>
            <td>${score2.getCountGames()}</td>
            <td>${score2.getPoints()}</td>
        </tr>
    </tbody>


</table>
<form method="post" action="match-score?uuid=${matchId}">
    <button type="submit" name="winner" value="player1">Игрок 1 выиграл текущее очко</button>
    <button type="submit" name="winner" value="player2">Игрок 2 выиграл текущее очко</button>
</form>
</body>
</html>