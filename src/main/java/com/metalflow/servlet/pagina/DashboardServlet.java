package com.metalflow.servlet.pagina;

import com.metalflow.dao.DashboardDAO;
import com.metalflow.dao.OrdemDAO;
import com.metalflow.model.OrdemProducao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ordens")
public class DashboardServlet extends HttpServlet {

    private OrdemDAO ordemDAO = new OrdemDAO();
    private DashboardDAO dashboardDAO = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        List<OrdemProducao> ordens = ordemDAO.buscarOrdens();
        req.setAttribute("ordens", ordens);

        int abertas = dashboardDAO.contaAbertas();
        req.setAttribute("abertas", abertas);
        int emProducao = dashboardDAO.contaEmProducao();
        req.setAttribute("emProducao", emProducao);
        int concluidas = dashboardDAO.contaConcluidas();
        req.setAttribute("concluidas", concluidas);
        int saldoTotal = dashboardDAO.somaSaldoTotal();
        req.setAttribute("saldoTotal", saldoTotal);

        String sucessoParam = req.getParameter("sucesso");
        String erroParam = req.getParameter("erro");
        if ("1".equals(sucessoParam))req.setAttribute("sucesso", "Registro cadastrado com sucesso!");
        if ("1".equals(erroParam))req.setAttribute("erro", "Não foi possível cadastrar o registro!");



        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}