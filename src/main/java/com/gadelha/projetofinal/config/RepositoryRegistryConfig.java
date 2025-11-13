package com.gadelha.projetofinal.config;

public class RepositoryRegistryConfig {
    
    private RepositoryRegistryConfig() {
    }
    
    public static void configurarRepositorios() {
        try {
            RepositoryRegistry.setAlunoRepository(RepositoryFactory.criarAlunoRepository());
            RepositoryRegistry.setDisciplinaRepository(RepositoryFactory.criarDisciplinaRepository());
            RepositoryRegistry.setLivroRepository(RepositoryFactory.criarLivroRepository());
            RepositoryRegistry.setMatriculaRepository(RepositoryFactory.criarMatriculaRepository());
            RepositoryRegistry.setEmprestimoRepository(RepositoryFactory.criarEmprestimoRepository());
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar repositórios no registry: " + e.getMessage(), e);
        }
    }
}