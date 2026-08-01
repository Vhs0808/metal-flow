package com.metalflow.servlet.pagina;

import com.metalflow.dao.OrdemDAO;
import com.metalflow.dto.OrdemCadastroDTO;
import com.metalflow.enums.StatusOP;
import com.metalflow.model.OrdemProducao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ordem-formulario")
public class FormOrdem extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OrdemDAO ordemDAO = new OrdemDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");

        req.getRequestDispatcher("/WEB-INF/views/ordem-formulario.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");

        OrdemCadastroDTO ordemDTO = new OrdemCadastroDTO();

        ordemDTO.setNumeroOp(req.getParameter("numero_OP"));
        ordemDTO.setCodigoProduto(req.getParameter("codigo_produto"));
        ordemDTO.setDescricaoProduto(req.getParameter("descricao"));
        ordemDTO.setQuantidadePlanejada(Integer.parseInt(req.getParameter("quantidade")));

        String erro = null;

        if(ordemDAO.buscaOrdemPorNumeroOP(ordemDTO.getNumeroOp()) ==  true) {
            erro = "Número de OP duplicado";
            req.setAttribute("erro", erro);
            req.getRequestDispatcher("/WEB-INF/views/ordem-formulario.jsp").forward(req, res);
        }

        OrdemProducao ordem = new OrdemProducao();

        ordem.setNumeroOp(ordemDTO.getNumeroOp());
        ordem.setCodigoProduto(ordemDTO.getCodigoProduto());
        ordem.setDescricaoProduto(ordemDTO.getDescricaoProduto());
        ordem.setQuantidadePlanejada(ordemDTO.getQuantidadePlanejada());
        ordem.setSaldoOp(ordemDTO.getQuantidadePlanejada());

        ordemDAO.inserirOrdem(ordem);

        ordem.setStatusOp(StatusOP.ABERTA);

        req.setAttribute("erro", erro);
        res.sendRedirect(req.getContextPath() + "/ordens?sucesso=1");
    }
}
