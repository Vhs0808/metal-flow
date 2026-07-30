package com.metalflow.servlet;

import com.google.gson.Gson;
import com.metalflow.dao.OrdemDAO;
import com.metalflow.dto.OrdemCadastroDTO;
import com.metalflow.enums.StatusOP;
import com.metalflow.model.OrdemProducao;
import com.metalflow.util.RespostaJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/ordens")
public class OrdemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    OrdemDAO ordemDAO = new OrdemDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        req.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();

       OrdemCadastroDTO ordemDTO = gson.fromJson(req.getReader(), OrdemCadastroDTO.class);

       if(ordemDTO.getNumeroOp() == null || ordemDTO.getNumeroOp().isBlank()) {
           RespostaJson.badRequest(res, "Campos obrigatórios ausentes ou inválidos.");
           return;
       }

       if(ordemDTO.getCodigoProduto() == null || ordemDTO.getCodigoProduto().isBlank()) {
           RespostaJson.badRequest(res, "Campos obrigatórios ausentes ou inválidos.");
           return;
       }

        if(ordemDTO.getDescricaoProduto() == null || ordemDTO.getDescricaoProduto().isBlank()) {
            RespostaJson.badRequest(res, "Campos obrigatórios ausentes ou inválidos.");
            return;
        }

        if(ordemDTO.getQuantidadePlanejada() <= 0) {
            RespostaJson.badRequest(res, "Campos obrigatórios ausentes ou inválidos.");
            return;
        }

        if(ordemDAO.buscaOrdemPorNumeroOP(ordemDTO.getNumeroOp()) == true){
            RespostaJson.conflict(res, "Número de OP duplicado");
        }

        OrdemProducao ordem = new OrdemProducao();

        ordem.setNumeroOp(ordemDTO.getNumeroOp());
        ordem.setCodigoProduto(ordemDTO.getCodigoProduto());
        ordem.setDescricaoProduto(ordemDTO.getDescricaoProduto());
        ordem.setQuantidadePlanejada(ordemDTO.getQuantidadePlanejada());
        ordem.setSaldoOp(ordemDTO.getQuantidadePlanejada());

        ordemDAO.inserirOrdem(ordem);

        ordem.setStatusOp(StatusOP.ABERTA);

        Map<String, Object> resposta = new LinkedHashMap<>();

        resposta.put("id", ordem.getId());
        resposta.put("numero_OP", ordem.getNumeroOp());
        resposta.put("saldo_OP", ordem.getSaldoOp());
        resposta.put("status_OP", ordem.getStatusOp());

        RespostaJson.enviar(res, HttpServletResponse.SC_CREATED, resposta);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException{

        req.setCharacterEncoding("UTF-8");

        List<OrdemProducao> ordens = ordemDAO.buscarOrdens();

        Map<String, Object> resposta = new LinkedHashMap<>();
        resposta.put("conteudo", ordens);

        RespostaJson.enviar(res, HttpServletResponse.SC_OK, resposta);
    }
}
