package com.gadelha.projetofinal.gateway.biblioteca.concrete;

import java.util.ArrayList;
import java.util.List;

import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LivroJsonParser implements IJsonParser<Livro>{

    private final Gson gson = new Gson();

    @Override
    public List<Livro> parseJsonParaLista(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        try {
            return gson.fromJson(json, new TypeToken<List<Livro>>(){}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public Livro parseJsonParaObjeto(String json) {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        
        try {
            return gson.fromJson(json, Livro.class);
        } catch (Exception e) {
            return null;
        }
    }

    
}
