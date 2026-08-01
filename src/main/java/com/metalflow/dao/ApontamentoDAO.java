package com.metalflow.dao;

import com.metalflow.dto.ApontamentoDTO;
import com.metalflow.enums.StatusOP;
import com.metalflow.exception.ConflictException;
import com.metalflow.exception.NotFoundException;
import com.metalflow.util.ConnectionFactory;

import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ApontamentoDAO {

    public List<Map<String, Object>> listarApontamentosPorOrdem(int ordemId){
        List<Map<String, Object>> apontamentos = new ArrayList<>();
        String sql = """
                SELECT s.codigo AS setor, a.quantidade_apontada, a.data_apontamento FROM apontamentos a
                INNER JOIN setores s ON s.id = a.setor_id
                WHERE a.ordem_id = ?
                ORDER BY a.data_apontamento ASC
                """;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
            ){
            stmt.setInt(1, ordemId);

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Map<String, Object> apontamento = new LinkedHashMap<>();

                    apontamento.put("setor", rs.getString("setor"));
                    apontamento.put("quantidade_apontada", rs.getInt("quantidade_apontada"));
                    LocalDateTime data = rs.getTimestamp("data_apontamento").toLocalDateTime();
                    apontamento.put("data_apontamento", data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")));
                    apontamentos.add(apontamento);
                }

            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar apontamentos por ordem " + ordemId, e);
        }

        return apontamentos;
    }

    public Map<String,Object> inserirApontamento(int ordemId, ApontamentoDTO apontamentoDTO){

        String sqlBuscarOrdem = """
                    SELECT id, codigo_produto, saldo_op
                    FROM ordens_producao
                    WHERE id = ?
                    FOR UPDATE
                """;

        String sqlBuscarSetor = """
                    SELECT id
                    FROM setores
                    WHERE codigo = ?
                    AND ativo = TRUE
                """;

        String sqlInserirApontamento = """
                    INSERT INTO apontamentos (quantidade_apontada, setor_id, ordem_id)
                    VALUES (?, ?, ?)
                """;

        String sqlAtualizarOrdemProducao = """
                    UPDATE ordens_producao
                    SET saldo_op = ?, status_op = ?
                    WHERE id = ?
                """;

        String sqlAtualizarEstoque = """
                    INSERT INTO estoques_setor (codigo_produto, quantidade,  setor_id, ordem_id)
                    VALUES (?,?,?,?)
                    ON DUPLICATE KEY UPDATE
                    quantidade = quantidade + VALUES(quantidade)
                """;

        try(Connection conn = ConnectionFactory.getConnection()) {

            conn.setAutoCommit(false);

            try {
                int saldoAtual;
                String codigoProduto;

                try (PreparedStatement stmt = conn.prepareStatement(sqlBuscarOrdem)) {
                    stmt.setInt(1, ordemId);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (!rs.next()) {
                            throw new NotFoundException("OP ou setor não encontrado");
                        }

                        codigoProduto = rs.getString("codigo_produto");
                        saldoAtual = rs.getInt("saldo_op");
                    }
                }

                int setorId;

                try (PreparedStatement stmt = conn.prepareStatement(sqlBuscarSetor)) {
                    stmt.setString(1, apontamentoDTO.getCodigoSetor());

                        try (ResultSet rs = stmt.executeQuery()) {
                            if (!rs.next()) {
                                throw new NotFoundException("OP ou setor não encontrado.");
                            }

                            setorId = rs.getInt("id");
                        }
                    }

                    int quantidadeApontada = apontamentoDTO.getQuantidadeApontada();

                    if (quantidadeApontada > saldoAtual) {
                        throw new ConflictException("Saldo insuficiente na OP");
                    }

                    int novoSaldo = saldoAtual - quantidadeApontada;

                    StatusOP novoStatus;

                    if (novoSaldo == 0) {
                        novoStatus = StatusOP.CONCLUIDA;
                    } else {
                        novoStatus = StatusOP.EM_PRODUCAO;
                    }

                    try (PreparedStatement stmt = conn.prepareStatement(sqlInserirApontamento)) {
                        stmt.setInt(1, quantidadeApontada);
                        stmt.setInt(2, setorId);
                        stmt.setInt(3, ordemId);

                        stmt.executeUpdate();
                    }

                    try (PreparedStatement stmt = conn.prepareStatement(sqlAtualizarOrdemProducao)) {
                        stmt.setInt(1, novoSaldo);
                        stmt.setString(2, novoStatus.name());
                        stmt.setInt(3, ordemId);

                        stmt.executeUpdate();
                    }

                    try (PreparedStatement stmt = conn.prepareStatement(sqlAtualizarEstoque)) {
                        stmt.setString(1, codigoProduto);
                        stmt.setInt(2, quantidadeApontada);
                        stmt.setInt(3, setorId);
                        stmt.setInt(4, ordemId);

                        stmt.executeUpdate();
                    }

                    conn.commit();

                    Map<String, Object> resposta = new LinkedHashMap<>();
                    resposta.put("mensagem", "Apontamento registrado com sucesso");
                    resposta.put("saldo_op", novoSaldo);
                    resposta.put("status_OP", novoStatus);

                    return resposta;

                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } catch (SQLException e) {
                throw new RuntimeException("Erro ao cadastrar Apontamento na ordem de produção " + ordemId, e);
            }
        }
    }

