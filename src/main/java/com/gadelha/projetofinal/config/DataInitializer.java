package com.gadelha.projetofinal.config;


public class DataInitializer {
    
    private DataInitializer() {
        
    }
    
    public static void inicializarRepositorios() {
        try {
            
            RepositoryRegistry.iniciarInicializacao();
            
            RepositoryRegistryConfig.configurarRepositorios();
            
            carregarEPopularDadosExternos();
            
            RepositoryRegistry.finalizarInicializacao();
            
        } 
        catch (Exception e) {
            RepositoryRegistry.finalizarInicializacao();
            throw new RuntimeException("Erro ao inicializar repositórios: " + e.getMessage(), e);
        }
    }
    
    private static void carregarEPopularDadosExternos() {
        
        carregarDadosAlunos();
        
        carregarDadosDisciplinas();
        
        carregarDadosLivros();
    }
    
    private static void carregarDadosAlunos() {
        try {
            var alunoGateway = GatewayLoader.carregarAlunoGateway();
            var alunos = DataLoader.carregarDados(alunoGateway);
            DataPopulator.popularDadosAlunos(alunos);
        } 
        catch (Exception e) {
            System.err.println("Não foi possível carregar dados de alunos: " + e.getMessage());
            
        }
    }
    
    private static void carregarDadosDisciplinas() {
        try {
            var disciplinaGateway = GatewayLoader.carregarDisciplinaGateway();
            var disciplinas = DataLoader.carregarDados(disciplinaGateway);
            DataPopulator.popularDadosDisciplinas(disciplinas);
        } 
        catch (Exception e) {
            System.err.println(" Não foi possível carregar dados de disciplinas: " + e.getMessage());
            
        }
    }
    
    private static void carregarDadosLivros() {
        try {
            var livroGateway = GatewayLoader.carregarLivroGateway();
            var livros = DataLoader.carregarDados(livroGateway);
            DataPopulator.popularDadosLivros(livros);
        } 
        catch (Exception e) {
            System.err.println("Não foi possível carregar dados de livros: " + e.getMessage());
            
        }
    }
}