package com.metalflow.dao;

import com.metalflow.model.EstoquesSetor;
import com.metalflow.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EstoqueDAO {

    public List<Map<String, Object>> listarEstoques(String numero_op, String codigo_setor){
        List <Map<String, Object>> estoques = new ArrayList<>();

        StringBuilder sql = new  StringBuilder("""
                SELECT op.numero_op AS numero_OP, op.codigo_produto, s.codigo AS setor, es.quantidade
                FROM estoques_setor es
                INNER JOIN ordens_produtos op ON op.id = es.ordem_id
                INNER JOIN setores s ON s.id = es.setor_id
                WHERE 1 = 1
                """);

        List<Object> params = new ArrayList<>();

        if(numero_op != null && !numero_op.isBlank()){
            sql.append(" AND op.numero_op = ?");
            params.add(numero_op);
        }

        if(codigo_setor != null && !codigo_setor.isBlank()){
            sql.append(" AND s.codigo_setor = ?");
            params.add(codigo_setor);
        }

        sql.append(" ORDER BY op.numero_op ASC, s.codigo ASC");

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql.toString());
                ){

            for(int i = 0; i < params.size(); i++){
                stmt.setObject(i + 1, params.get(i));
            }

            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Map<String, Object> estoque = new LinkedHashMap<>();

                    estoque.put("numero_OP", rs.getString("numero_OP"));
                    estoque.put("codigo_produto", rs.getString("codigo_produto"));
                    estoque.put("setor", rs.getString("setor"));
                    estoque.put("quantidade", rs.getInt("quantidade"));

                    estoques.add(estoque);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao listar estoques ", e);
        }

        return estoques;
    }

}
