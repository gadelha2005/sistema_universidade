package com.gadelha.projetofinal.gateway.disciplina.concrete;

import java.util.ArrayList;
import java.util.List;

import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DisciplinaJsonParser implements IJsonParser<Disciplina>{

    private final Gson gson = new Gson();

    @Override
    public List<Disciplina> parseJsonParaLista(String json) {
        if(json == null || json.trim().isEmpty()){
            return new ArrayList<>();
        }

        try{
            return gson.fromJson(json, new TypeToken<List<Disciplina>>(){}.getType());
        }
        catch(Exception e){
            return new ArrayList<>();
        }
    }

    @Override
    public Disciplina parseJsonParaObjeto(String json) {
       if (json == null || json.trim().isEmpty()) {
            return null;
        }
        
        try {
            return gson.fromJson(json, Disciplina.class);
        } catch (Exception e) {
            return null;
        }
    }
    
}
