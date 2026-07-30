package com.metalflow.dao;

import com.metalflow.enums.StatusOP;
import com.metalflow.model.OrdemProducao;
import com.metalflow.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdemDAO {

    public List<OrdemProducao> buscarOrdens(/*ITENS PARA FILTRAGEM*/){
        List<OrdemProducao> listaOrdens = new ArrayList<>();


        /*
            LÓGICA DA FILTRAGEM
        */

        String sql = "SELECT * FROM ordens_producao";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    OrdemProducao ordem = new OrdemProducao();
                    ordem.setId(rs.getInt("id"));
                    ordem.setNumeroOp(rs.getString("numero_op"));
                    ordem.setCodigoProduto(rs.getString("codigo_produto"));
                    ordem.setDescricaoProduto(rs.getString("descricao_produto"));
                    ordem.setQuantidadePlanejada(rs.getInt("quantidade_planejada"));
                    ordem.setSaldoOp(rs.getInt("saldo_op"));
                    ordem.setStatusOp(StatusOP.valueOf(rs.getString("status_op")));
                    listaOrdens.add(ordem);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Erro ao buscar ordens" + e);
        }

        return listaOrdens;
    }

    public void inserirOrdem(OrdemProducao ordem){
        String sql = "INSERT INTO ORDENS_PRODUCAO (numero_op, codigo_produto, descricao_produto, quantidade_planejada, saldo_op) VALUES (?, ?, ?, ?, ?)";


        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ){

            stmt.setString(1, ordem.getNumeroOp());
            stmt.setString(2, ordem.getCodigoProduto());
            stmt.setString(3, ordem.getDescricaoProduto());
            stmt.setInt(4, ordem.getQuantidadePlanejada());
            stmt.setInt(5, ordem.getSaldoOp());

            stmt.executeUpdate();

            try(ResultSet rs = stmt.getGeneratedKeys()){
                if(rs.next()){
                    ordem.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar uma nova Ordem de Produção" + e);
        }

    }

    public OrdemProducao buscarOrdemPorId(int id){
        String sql = "SELECT * FROM ORDENS_PRODUCAO WHERE id = ?";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    OrdemProducao ordem = new OrdemProducao();
                    ordem.setId(rs.getInt("id"));
                    ordem.setNumeroOp(rs.getString("numero_op"));
                    ordem.setCodigoProduto(rs.getString("codigo_produto"));
                    ordem.setDescricaoProduto(rs.getString("descricao_produto"));
                    ordem.setQuantidadePlanejada(rs.getInt("quantidade_planejada"));
                    ordem.setSaldoOp(rs.getInt("saldo_op"));
                    ordem.setStatusOp(StatusOP.valueOf(rs.getString("status_op")));
                    return ordem;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar ordem por Id" + e);
        }
        return null;
    }

    public boolean buscaOrdemPorNumeroOP(String numeroOp){
        String sql = "SELECT numero_op FROM ORDENS_PRODUCAO WHERE numero_op = ?";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){

            stmt.setString(1, numeroOp);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordem por número de OP" + e);
        }
        return false;
    }

}
