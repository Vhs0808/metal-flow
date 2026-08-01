package com.metalflow.servlet.api;

import com.metalflow.dao.EstoqueDAO;
import com.metalflow.dao.OrdemDAO;
import com.metalflow.dao.SetorDAO;
import com.metalflow.util.RespostaJson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/api/estoques")
public class EstoqueServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    EstoqueDAO estoqueDAO = new EstoqueDAO();
    SetorDAO setorDAO = new SetorDAO();
    OrdemDAO  ordemDAO = new OrdemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException{

        req.setCharacterEncoding("UTF-8");
        String numeroOp = req.getParameter("numero_OP");
        String codigoSetor = req.getParameter("codigo_setor");

        if (numeroOp != null && numeroOp.isBlank()) numeroOp = null;
        if (codigoSetor != null && codigoSetor.isBlank()) codigoSetor = null;
        if (codigoSetor != null) codigoSetor = codigoSetor.toUpperCase();

        if(numeroOp != null) {
            if(ordemDAO.buscaOrdemPorNumeroOP(numeroOp) == false) {
                RespostaJson.badRequest(res, "Parâmetros inválidos.");
                return;
            }
        }

        if(codigoSetor != null) {
            if(setorDAO.buscarSetor(codigoSetor) == false) {
                RespostaJson.badRequest(res, "Parâmetros inválidos.");
                return;
            }
        }

        List<Map<String, Object>> resposta = estoqueDAO.listarEstoques( numeroOp, codigoSetor);

        RespostaJson.enviar(res, HttpServletResponse.SC_OK, resposta);

    }
}
