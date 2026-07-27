package com.metalflow.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class RespostaJson {

    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(
            LocalDateTime.class,
                (JsonSerializer<LocalDateTime>) (src, TypeOfSrc, context)->
                    new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
    ).create();

    private RespostaJson(){}

    public static void enviar(HttpServletResponse res, int status, Object object)
        throws IOException{

        res.setStatus(status);
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");

        gson.toJson(object, res.getWriter());
    }

    public static void erro(HttpServletResponse res, int status, String mensagem)
        throws IOException{

        Map<String, String> resposta = new LinkedHashMap<>();
        resposta.put("mensagem", mensagem);

        enviar(res, status, resposta);
    }

    public static void badRequest(HttpServletResponse res, String mensagem)
        throws IOException{

        erro(res, HttpServletResponse.SC_BAD_REQUEST, mensagem);
    }

    public static void notFound(HttpServletResponse res, String mensagem)
        throws IOException{

        erro(res, HttpServletResponse.SC_NOT_FOUND, mensagem);
    }

    public static void conflict(HttpServletResponse res, String mensagem)
        throws IOException{

        erro(res, HttpServletResponse.SC_CONFLICT, mensagem);
    }

    public static void internalServerError(HttpServletResponse res, String mensagem)
        throws IOException{

        erro(res, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, mensagem);
    }
}
