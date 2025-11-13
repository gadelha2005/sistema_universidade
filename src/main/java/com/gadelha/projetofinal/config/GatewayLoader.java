package com.gadelha.projetofinal.config;

import java.util.ServiceLoader;
import com.gadelha.projetofinal.gateway.interfaces.IGateway;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.model.disciplina.Disciplina;

public class GatewayLoader {
    
    private GatewayLoader() {
    }
    
    @SuppressWarnings("unchecked")
    public static <T> IGateway<T> carregarGateway(String gateaway) {
        ServiceLoader<IGateway> loader = ServiceLoader.load(IGateway.class);
        return loader.stream()
            .filter(provider -> {
                String className = provider.get().getClass().getSimpleName();
                return className.contains(gateaway);
            })
            .findFirst()
            .map(provider -> (IGateway<T>) provider.get())
            .orElseThrow(() -> new RuntimeException(gateaway + " não encontrado"));
    }
    
    
    public static IGateway<Aluno> carregarAlunoGateway() {
        return carregarGateway("AlunoGateway");
    }
    
    public static IGateway<Disciplina> carregarDisciplinaGateway() {
        return carregarGateway("DisciplinaGateway");
    }
    
    public static IGateway<Livro> carregarLivroGateway() {
        return carregarGateway("LivroGateway");
    }
}