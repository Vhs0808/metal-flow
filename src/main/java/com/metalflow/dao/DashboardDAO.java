package com.metalflow.dao;

import com.metalflow.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardDAO {

    public int contaAbertas() {
        String sql = "SELECT COUNT(*) FROM ordens_producao WHERE status_op = 'ABERTA'";
        int qtdContaAberta = 0;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            if (rs.next()) {
                qtdContaAberta = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar Ordens de Produção abertas" + e);
        }

        return qtdContaAberta;
    }

    public int contaEmProducao() {
        String sql = "SELECT COUNT(*) FROM ordens_producao WHERE status_op = 'EM_PRODUCAO'";
        int qtdContaEmProducao = 0;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            if (rs.next()) {
                qtdContaEmProducao = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar Ordens de Produção em produção" + e);
        }

        return qtdContaEmProducao;
    }

    public int contaConcluidas() {
        String sql = "SELECT COUNT(*) FROM ordens_producao WHERE status_op = 'CONCLUIDA'";
        int qtdContaConcluida = 0;

        try (
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                qtdContaConcluida = rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar Ordens de Produção concluídas" + e);
        }
        return qtdContaConcluida;
    }

    public int somaSaldoTotal(){
        String sql = "SELECT SUM(saldo_op) FROM ordens_producao";
        int saldoTotal = 0;

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){
            if(rs.next()){
                saldoTotal = rs.getInt(1);
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao somar o saldo total das Ordens de Produção" + e);
        }

        return  saldoTotal;
    }
}