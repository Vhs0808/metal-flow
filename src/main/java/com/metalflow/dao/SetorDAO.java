package com.metalflow.dao;

import com.metalflow.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetorDAO {

    public boolean buscarSetor(String codigo){

        String sql = "SELECT codigo FROM SETORES WHERE codigo = ?";

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ){
            stmt.setString(1, codigo);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return true;
                }
            }
        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar setor ", e);
        }
        return false;
    }
}
