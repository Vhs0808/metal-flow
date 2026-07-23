package com.metalflow.dao;

import com.metalflow.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    apontamento.put("data_apontamento", rs.getTimestamp("data_apontamento").toLocalDateTime());

                    apontamentos.add(apontamento);
                }

            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar apontamentos por ordem " + ordemId, e);
        }

        return apontamentos;
    }
}
