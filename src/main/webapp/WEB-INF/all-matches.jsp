<%@ page import="org.tomato.tennismatchscoreboardweb.models.Match" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>
    <title>TENNIS</title>
</head>
<body>
<div class="main_container">
    <div class="header">
        <h1>СЫГРАННЫЕ МАТЧИ</h1>
    </div>

    <div class="matches_table">


        <table>
            <tr>
                <th>Игрок #1</th>
                <th>Игрок #2</th>

            </tr>
            <% List<Match> matches = (List<Match>) request.getAttribute("matches"); %>

            <% for (Match match : matches) { %>
            <tr>
                <td><%= match.getPlayer1().getName() %>
                    <%= match.getWinner().getName().equals(match.getPlayer1().getName()) ? "✔" : "" %></td>
                <td><%= match.getPlayer2().getName() %>
                    <%= match.getWinner().getName().equals(match.getPlayer2().getName()) ? "✔" : "" %></td>
            </tr>
            <% } %>
        </table>
    </div>


</div>
</body>
</html>