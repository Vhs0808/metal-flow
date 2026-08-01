package com.metalflow.servlet.api;

import com.metalflow.dao.DashboardDAO;
import com.metalflow.util.RespostaJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/api/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DashboardDAO dashboardDAO = new DashboardDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
        Map<String, Object> resposta = new LinkedHashMap<>();

        try {
            resposta.put("abertas", dashboardDAO.contaAbertas());
            resposta.put("em_produção", dashboardDAO.contaEmProducao());
            resposta.put("concluidas", dashboardDAO.contaConcluidas());
            resposta.put("saldo_total", dashboardDAO.somaSaldoTotal());
        }catch (Exception e){
            RespostaJson.internalServerError(res, "Erro inesperado na consulta");
            return;
        }

        RespostaJson.enviar(res, HttpServletResponse.SC_OK, resposta);
    }
}
