package com.metalflow.servlet.api;

import com.google.gson.Gson;
import com.metalflow.dao.ApontamentoDAO;
import com.metalflow.dto.ApontamentoDTO;
import com.metalflow.exception.ConflictException;
import com.metalflow.exception.NotFoundException;
import com.metalflow.util.RespostaJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/api/ordens/apontamentos")
public class ApontamentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    ApontamentoDAO apontamentoDAO = new ApontamentoDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        int idOrdem;

        try {
            idOrdem = Integer.parseInt(req.getParameter("id").trim());
        } catch (NumberFormatException e) {
            RespostaJson.badRequest(res, "ID inválido");
            return;
        }

        Gson gson = new Gson();

        ApontamentoDTO apontamentoDTO = gson.fromJson(req.getReader(), ApontamentoDTO.class);

        if (apontamentoDTO == null || apontamentoDTO.getQuantidadeApontada() <= 0) {
            RespostaJson.badRequest(res, "Quantidade inválida ou setor não informado.");
            return;
        }

        try {
            Map<String, Object> resposta = apontamentoDAO.inserirApontamento(idOrdem, apontamentoDTO);
            RespostaJson.enviar(res, HttpServletResponse.SC_CREATED, resposta);

        } catch (ConflictException e) {
            RespostaJson.conflict(res, e.getMessage());

        } catch (NotFoundException e) {
            RespostaJson.notFound(res, e.getMessage());

        }

    }
}
