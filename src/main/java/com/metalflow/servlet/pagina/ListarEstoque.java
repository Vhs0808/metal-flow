package com.metalflow.servlet.pagina;

import com.metalflow.dao.EstoqueDAO;
import com.metalflow.dao.OrdemDAO;
import com.metalflow.dao.SetorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/estoque")
public class ListarEstoque extends HttpServlet {

    private static final long serialVersionUID = 1L;
    EstoqueDAO estoqueDAO = new EstoqueDAO();
    SetorDAO setorDAO = new SetorDAO();
    OrdemDAO  ordemDAO = new OrdemDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");
        String numeroOp = req.getParameter("numero_OP");
        String codigoSetor = req.getParameter("codigo_setor");

        if (numeroOp != null && numeroOp.isBlank()) numeroOp = null;
        if (codigoSetor != null && codigoSetor.isBlank()) codigoSetor = null;
        if (codigoSetor != null) codigoSetor = codigoSetor.toUpperCase();

        String erro = null;

        if (numeroOp != null && !ordemDAO.buscaOrdemPorNumeroOP(numeroOp)) {
            erro = "Parâmetros inválidos.";
            numeroOp = null;
        }

        if (codigoSetor != null && !setorDAO.buscarSetor(codigoSetor)) {
            erro = "Parâmetros inválidos.";
            codigoSetor = null;
        }


        List<Map<String, Object>> estoques = estoqueDAO.listarEstoques( numeroOp, codigoSetor);
        List<Map<String, Object>> setores = setorDAO.listaSetores();

        req.setAttribute("estoques", estoques);
        req.setAttribute("setores", setores);
        req.setAttribute("erro", erro);
        req.setAttribute("numeroOpFiltro", numeroOp);
        req.setAttribute("codigoSetorFiltro", codigoSetor);

        req.getRequestDispatcher("/WEB-INF/views/estoque-consulta.jsp").forward(req, res);
    }
}
