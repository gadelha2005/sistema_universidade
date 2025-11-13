package com.gadelha.projetofinal.gateway.aluno.concrete;

import java.util.List;
import java.util.ArrayList;
import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AlunoJsonParser implements IJsonParser<Aluno> {
    private final Gson gson = new Gson();
    
    @Override
    public List<Aluno> parseJsonParaLista(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }
         
        try {
            return gson.fromJson(json, new TypeToken<List<Aluno>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Aluno parseJsonParaObjeto(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        
        try {
            return gson.fromJson(json, Aluno.class);
        } catch (Exception e) {
            return null;
        }
    }
}