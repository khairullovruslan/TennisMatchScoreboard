package org.tomato.tennismatchscoreboardweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tomato.tennismatchscoreboardweb.services.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private  final PlayerService playerService = PlayerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views.new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name1 = req.getParameter("name");
        String name2 = req.getParameter("name2");
        if (name1 == null || name2 == null){
            resp.sendError(400, "Не все поля заполнены");
            return;
        }
        if (name1.equals(name2)){
            resp.sendError(400, "Имена игроков одинаковые!");
        }
        UUID uuid = playerService.generateMatch(name1, name2);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
