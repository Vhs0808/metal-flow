package com.metalflow.servlet.pagina;

import com.metalflow.dao.ApontamentoDAO;
import com.metalflow.dao.OrdemDAO;
import com.metalflow.dto.ApontamentoDTO;
import com.metalflow.exception.ConflictException;
import com.metalflow.exception.NotFoundException;
import com.metalflow.model.OrdemProducao;
import com.metalflow.util.RespostaJson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/detalhe-ordem")
public class DetalheOrdem extends HttpServlet {

    private static final long serialVersionUID = 1L;
    OrdemDAO ordemDAO = new OrdemDAO();
    ApontamentoDAO apontamentoDAO = new ApontamentoDAO();

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");

        String idParam = req.getParameter("id");

        if(idParam == null || idParam.isBlank()){
            res.sendRedirect(req.getContextPath()+ "/ordens");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(idParam);
        }catch (NumberFormatException e){
            res.sendRedirect(req.getContextPath() + "/ordens");
            return;
        }

        OrdemProducao ordem  = ordemDAO.buscarOrdemPorId(id);

        if(ordem == null){
            res.sendRedirect(req.getContextPath() + "/ordens");
            return;
        }

        List<Map<String, Object>> apontamentos = apontamentoDAO.listarApontamentosPorOrdem(ordem.getId());

        req.setAttribute("ordem", ordem);
        req.setAttribute("apontamentos", apontamentos);

        req.getRequestDispatcher("/WEB-INF/views/detalhe-ordem.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res )
        throws ServletException, IOException{

        req.setCharacterEncoding("UTF-8");

        String setor = req.getParameter("setor");
        String quantidadeParam = req.getParameter("quantidade");
        int idOrdem = Integer.parseInt(req.getParameter("ordem_id"));

        int quantidade;

        String erro = null;

        try {
            quantidade = Integer.parseInt(quantidadeParam);
        }catch (NumberFormatException e){
            erro = "Parâmetros inválidos";
            req.setAttribute("erro", erro);
            res.sendRedirect(req.getContextPath() + "/detalhe-ordem?id=" + idOrdem);
            return;
        }

        ApontamentoDTO apontamentoDTO = new ApontamentoDTO();
        apontamentoDTO.setCodigoSetor(setor);
        apontamentoDTO.setQuantidadeApontada(quantidade);

        try {
            Map<String, Object> resposta = apontamentoDAO.inserirApontamento(idOrdem, apontamentoDTO);
            res.sendRedirect(req.getContextPath() + "/ordens?sucesso=1");

        } catch (ConflictException e) {
            res.sendRedirect(req.getContextPath() + "/ordens?erro=1");

        } catch (NotFoundException e) {
            res.sendRedirect(req.getContextPath() + "/ordens?erro=1");
        }

    }
}
