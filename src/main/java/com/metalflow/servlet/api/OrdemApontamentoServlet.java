package com.metalflow.servlet.api;

import com.metalflow.dao.ApontamentoDAO;
import com.metalflow.dao.OrdemDAO;
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

@WebServlet("/api/ordens/*")
public class OrdemApontamentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    OrdemDAO ordemDAO = new OrdemDAO();
    ApontamentoDAO apontamentoDAO = new ApontamentoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {

        req.setCharacterEncoding("UTF-8");

        String path = req.getPathInfo();

        if(path == null || path.isBlank()){
            RespostaJson.notFound(res, "Ordem de Produção não encontrada");
            return;
        }

        path = path.substring(path.indexOf("/")+1).trim();


        int id;

        try {
            id = Integer.parseInt(path);
        } catch (NumberFormatException e) {
            RespostaJson.badRequest(res, "ID inválido");
            return;
        }

        OrdemProducao ordem  = ordemDAO.buscarOrdemPorId(id);

        if(ordem == null){
            RespostaJson.notFound(res, "Ordem de Produção não encontrada");
            return;
        }

        Map<String, Object> ordemProducao = new LinkedHashMap<>();
        ordemProducao.put("id",ordem.getId());
        ordemProducao.put("numero_OP",ordem.getNumeroOp());
        ordemProducao.put("codigo_produto",ordem.getCodigoProduto());
        ordemProducao.put("descricao_produto",ordem.getDescricaoProduto());
        ordemProducao.put("quantidade_planejada",ordem.getQuantidadePlanejada());
        ordemProducao.put("saldo_OP",ordem.getSaldoOp());
        ordemProducao.put("status_OP",ordem.getStatusOp());

        List<Map<String, Object>> apontamentos = apontamentoDAO.listarApontamentosPorOrdem(ordem.getId());

        Map<Object, Object> ordemApontamentos = new LinkedHashMap<>();
        ordemApontamentos.put("ordem", ordemProducao);
        ordemApontamentos.put("apontamentos", apontamentos);

        RespostaJson.enviar(res, HttpServletResponse.SC_OK, ordemApontamentos);
    }

}
