package com.metalflow.dao;

import com.metalflow.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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

    public List<Map<String, Object>> listaSetores(){
        String sql="SELECT codigo FROM SETORES";

        List<Map<String, Object>> setores = new ArrayList<>();

        try(
                Connection conn = ConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
                ){

            while(rs.next()){
                Map<String, Object> setor = new LinkedHashMap<>();
                setor.put("codigo", rs.getString("codigo"));
                setores.add(setor);
            }

        }catch (SQLException e){
            throw new RuntimeException("Erro ao buscar Setores", e);
        }

        return setores;
    }
}
