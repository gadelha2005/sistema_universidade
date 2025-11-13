package com.gadelha.projetofinal.gateway.aluno.concrete;

import java.util.List;
import java.util.ServiceLoader;
import com.gadelha.projetofinal.gateway.interfaces.IApiConsumer;
import com.gadelha.projetofinal.gateway.interfaces.IGateway;
import com.gadelha.projetofinal.gateway.interfaces.IJsonParser;
import com.gadelha.projetofinal.model.aluno.Aluno;

public class AlunoGateway implements IGateway<Aluno> {

    private final IApiConsumer iApiConsumer;
    private final IJsonParser<Aluno> iJsonParser;

    public AlunoGateway() {
        this.iApiConsumer = carregarApiConsumer();
        this.iJsonParser = carregarJsonParser();
    }

    @Override
    public List<Aluno> carregarDadosDoServicoExterno() {
        String json = iApiConsumer.buscarDados();
        return iJsonParser.parseJsonParaLista(json);
    }

    private IApiConsumer carregarApiConsumer() {
        ServiceLoader<IApiConsumer> loader = ServiceLoader.load(IApiConsumer.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IApiConsumer consumer = provider.get();
                    return consumer.getClass().getSimpleName().contains("AlunoApiConsumer");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .orElseThrow(() -> new RuntimeException("Nenhum AlunoApiConsumer encontrado"));
    }

    private IJsonParser<Aluno> carregarJsonParser() {
        ServiceLoader<IJsonParser> loader = ServiceLoader.load(IJsonParser.class);
        return loader.stream()
            .filter(provider -> {
                try {
                    IJsonParser<?> parser = provider.get();
                    return parser.getClass().getSimpleName().contains("AlunoJsonParser");
                } catch (Exception e) {
                    return false;
                }
            })
            .findFirst()
            .map(ServiceLoader.Provider::get)
            .map(parser -> (IJsonParser<Aluno>) parser)
            .orElseThrow(() -> new RuntimeException("Nenhum AlunoJsonParser encontrado"));
    }
}
