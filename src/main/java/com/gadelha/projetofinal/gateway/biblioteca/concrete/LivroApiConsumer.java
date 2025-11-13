package com.gadelha.projetofinal.gateway.biblioteca.concrete;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.gadelha.projetofinal.gateway.interfaces.IApiConsumer;

public class LivroApiConsumer implements IApiConsumer{

    private HttpClient clienteHttp;
    private String url;

    public LivroApiConsumer(){
        this.clienteHttp = HttpClient.newHttpClient();
        this.url = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";
    }

    @Override
    public String buscarDados() {
        try {
            HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

            HttpResponse<String> resposta = clienteHttp.send(requisicao, HttpResponse.BodyHandlers.ofString());
            
            if (resposta.statusCode() == 200) {
                return resposta.body();
            } else {
                throw new RuntimeException("Erro HTTP: " + resposta.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Serviço de livros indisponível", e);
        }
    }
    
}
