package com.gadelha.projetofinal.gateway.disciplina.concrete;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.gadelha.projetofinal.gateway.interfaces.IApiConsumer;


public class DisciplinaApiConsumer implements IApiConsumer{

    private final HttpClient clienteHttp;
    private final String url;

    public DisciplinaApiConsumer() {
        this.clienteHttp = HttpClient.newHttpClient();
        this.url = "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";
    }

    @Override
    public String buscarDados() {
        try{
            HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> resposta = clienteHttp.send(requisicao,  HttpResponse.BodyHandlers.ofString());

            if(resposta.statusCode() == 200){
                return resposta.body();
            }
            else{
                throw new RuntimeException("Erro HTTP: " + resposta.statusCode());
            }
        }
        catch(Exception e){
            throw new RuntimeException("Serviço de disciplinas indiponível" , e);
        }
    }
    
}
