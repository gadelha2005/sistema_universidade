package com.gadelha.projetofinal.gateway.biblioteca.concrete;

import java.util.List;
import java.util.ServiceLoader;
import com.gadelha.projetofinal.gateway.interfaces.IApiConsumer;
import com.gadelha.projetofinal.gateway.interfaces.IGateway;
import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.biblioteca.Livro;

public class LivroGateway implements IGateway<Livro> {

    private final IApiConsumer iApiConsumer;
    private final IJsonParser<Livro> iJsonParser;

    public LivroGateway() {
        this.iApiConsumer = carregarApiConsumer();
        this.iJsonParser = carregarJsonParser();
    }

    @Override
    public List<Livro> carregarDadosDoServicoExterno() {
        String json = iApiConsumer.buscarDados();
        return iJsonParser.parseJsonParaLista(json);
    }

    private IApiConsumer carregarApiConsumer() {
        ServiceLoader<IApiConsumer> loader = ServiceLoader.load(IApiConsumer.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IApiConsumer consumer = provider.get();
                    return consumer.getClass().getSimpleName().contains("LivroApiConsumer");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .orElseThrow(() -> new RuntimeException("Nenhum LivroApiConsumer encontrado"));
    }

    private IJsonParser<Livro> carregarJsonParser() {
        ServiceLoader<IJsonParser> loader = ServiceLoader.load(IJsonParser.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IJsonParser<?> parser = provider.get();
                    return parser.getClass().getSimpleName().contains("LivroJsonParser");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .map(parser -> (IJsonParser<Livro>) parser)
            .orElseThrow(() -> new RuntimeException("Nenhum LivroJsonParser encontrado"));
    }
}
