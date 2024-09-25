package org.tomato.tennismatchscoreboardweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.tomato.tennismatchscoreboardweb.models.Match;
import org.tomato.tennismatchscoreboardweb.services.MatchesService;
import org.tomato.tennismatchscoreboardweb.util.RequestParametersValidator;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final MatchesService matchesService = MatchesService.getInstance();
    private final RequestParametersValidator validator = RequestParametersValidator.getInstance();
    private final static int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String nameParam = req.getParameter("filter_by_player_name");
        int page = 1;

        if (pageParam != null) {
            if (validator.canConvertToInteger(pageParam)) {
                page = Integer.parseInt(pageParam);
            } else {
                resp.sendError(400, "Некорректные параметры запроса");
            }
        }

        List<Match> matchList;
        if (nameParam == null) {
            matchList = matchesService.findAll(page, PAGE_SIZE);
        } else {
            matchList = matchesService.findAllWithName(page, PAGE_SIZE, nameParam);
        }

        req.setAttribute("matches", matchList);
        req.getRequestDispatcher("WEB-INF/all-matches.jsp").forward(req, resp);

    }
}
