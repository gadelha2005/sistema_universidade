package com.gadelha.projetofinal.gateway.disciplina.concrete;

import java.util.List;
import java.util.ServiceLoader;
import com.gadelha.projetofinal.gateway.interfaces.IApiConsumer;
import com.gadelha.projetofinal.gateway.interfaces.IGateway;
import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.disciplina.Disciplina;

public class DisciplinaGateway implements IGateway<Disciplina> {

    private final IApiConsumer iApiConsumer;
    private final IJsonParser<Disciplina> iJsonParser;

    public DisciplinaGateway() {
        this.iApiConsumer = carregarApiConsumer();
        this.iJsonParser = carregarJsonParser();
    }

    @Override
    public List<Disciplina> carregarDadosDoServicoExterno() {
        String json = iApiConsumer.buscarDados();
        return iJsonParser.parseJsonParaLista(json);
    }

    private IApiConsumer carregarApiConsumer() {
        ServiceLoader<IApiConsumer> loader = ServiceLoader.load(IApiConsumer.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IApiConsumer consumer = provider.get();
                    return consumer.getClass().getSimpleName().contains("DisciplinaApiConsumer");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .orElseThrow(() -> new RuntimeException("Nenhum DisciplinaApiConsumer encontrado"));
    }

    private IJsonParser<Disciplina> carregarJsonParser() {
        ServiceLoader<IJsonParser> loader = ServiceLoader.load(IJsonParser.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IJsonParser<?> parser = provider.get();
                    return parser.getClass().getSimpleName().contains("DisciplinaJsonParser");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .map(parser -> (IJsonParser<Disciplina>) parser)
            .orElseThrow(() -> new RuntimeException("Nenhum DisciplinaJsonParser encontrado"));
    }
}
