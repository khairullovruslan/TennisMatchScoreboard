package org.tomato.tennismatchscoreboardweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tomato.tennismatchscoreboardweb.models.MatchScore;
import org.tomato.tennismatchscoreboardweb.services.CalculateTheMatchScoreService;
import org.tomato.tennismatchscoreboardweb.services.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final CalculateTheMatchScoreService calculateTheMatchScoreService =  CalculateTheMatchScoreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("uuid");
        if (param == null){
            resp.sendError(400, "Нет нужных параметров");
            return;
        }
        try {
            UUID uuid = UUID.fromString(param);
            MatchScore matchScore = MatchScore.getMatch(uuid);
            if (matchScore == null){
                resp.sendError(404, "Матч не был найден");
                return;
            }
            req.setAttribute("matchId", String.valueOf(uuid));
            req.setAttribute("player1", matchScore.getMatch().getPlayer1().getName());
            req.setAttribute("player2", matchScore.getMatch().getPlayer2().getName());
            req.setAttribute("score1", matchScore.getScore1());
            req.setAttribute("score2", matchScore.getScore2());

            req.getRequestDispatcher("WEB-INF/match-score.jsp").forward(req, resp);

        } catch (IllegalArgumentException e) {
            resp.sendError(400, "Некорректный формат uuid");
        }
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("uuid");
        String winner = req.getParameter("winner");
        if (param == null || winner == null){
            resp.sendError(400, "Отсуствуют необходимые параметры");
            return;
        }
        try {
            UUID uuid = UUID.fromString(param);
            MatchScore matchScore = MatchScore.getMatch(uuid);
            boolean isEnd = calculateTheMatchScoreService.calculate(matchScore, winner);
            if (isEnd){
                req.setAttribute("winner", matchScore.getMatch().getWinner().getName());
                req.setAttribute("count1", matchScore.getScore1().getCountSets());
                req.setAttribute("count2", matchScore.getScore2().getCountSets());
                MatchScore.removeMatch(uuid);
                req.getRequestDispatcher("WEB-INF/match-end.jsp").forward(req, resp);
            }
            else{
                resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
            }

        }
        catch (IllegalArgumentException e){
            resp.sendError(400, "Некорректный формат uuid");
        }

    }
}
