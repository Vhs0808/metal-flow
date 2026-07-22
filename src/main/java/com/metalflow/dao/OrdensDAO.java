package com.metalflow.dao;

import com.metalflow.enums.StatusOP;
import com.metalflow.model.OrdensProducao;
import com.metalflow.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdensDAO {

    public List<OrdensProducao> buscarOrdens(/*ITENS PARA PAGINAÇÃO*/){
        List<OrdensProducao> listaOrdens = new ArrayList<>();

        /*

            LÓGICA DA PAGINAÇÃO

         */

        String sql = "SELECT * FROM ordens_producao";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    OrdensProducao ordem = new OrdensProducao();
                    ordem.setId(rs.getInt("id"));
                    ordem.setNumero_op(rs.getString("numero_op"));
                    ordem.setDescricao_produto(rs.getString("descricao_produto"));
                    ordem.setQuantidade_planejada(rs.getInt("quantidade_planejada"));
                    ordem.setSaldo_op(rs.getInt("saldo_op"));
                    ordem.setStatus_op(StatusOP.valueOf(rs.getString("status_op")));
                    ordem.setCriado_em(rs.getTimestamp("criado_em").toLocalDateTime());
                    listaOrdens.add(ordem);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar ordens" + e.getMessage());
        }

        return listaOrdens;
    }

    public void inserirOrdem(OrdensProducao ordem){
        String sql = "INSERT INTO ORDENS_PRODUCAO (numero_op, codigo_produto, descricao_produto, quantidade_planejada, saldo_op, criado_em) VALUES (?, ?, ?, ?, ?, ?)";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){

            stmt.setString(1, ordem.getNumero_op());
            stmt.setString(2, ordem.getCodigo_produto());
            stmt.setString(3, ordem.getDescricao_produto());
            stmt.setInt(4, ordem.getQuantidade_planejada());
            stmt.setInt(5, ordem.getQuantidade_planejada());
            stmt.setDate(6, Date.valueOf(ordem.getCriado_em().toLocalDate()));

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    ordem.setId(rs.getInt("id"));
                }
            }

            ordem.setSaldo_op(ordem.getSaldo_op());
            ordem.setStatus_op(StatusOP.ABERTO);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar uma nova Ordem de Produção" + e.getMessage());
        }

    }

}
