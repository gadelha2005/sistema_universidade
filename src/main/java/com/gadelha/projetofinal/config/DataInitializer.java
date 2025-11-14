package com.gadelha.projetofinal.config;

public class DataInitializer {
    
    private static volatile boolean inicializado = false;
    private static final Object lock = new Object();
    
    private DataInitializer() {}
    
    public static void inicializar() {
        if (!inicializado) {
            synchronized (lock) {
                if (!inicializado) {
                    carregarDadosExternos();
                    inicializado = true;
                }
            }
        }
    }
    
    private static void carregarDadosExternos() {
        carregarDadosAlunos();
        carregarDadosDisciplinas();
        carregarDadosLivros();
    }
    
    private static void carregarDadosAlunos() {
        try {
            var gateway = GatewayLoader.carregarAlunoGateway();
            var alunos = gateway.carregarDadosDoServicoExterno();
            RepositoryRegistry.getAlunoRepository().carregarDados(alunos);
        } catch (Exception e) {
            System.err.println("Erro ao carregar alunos: " + e.getMessage());
        }
    }
    
    private static void carregarDadosDisciplinas() {
        try {
            var gateway = GatewayLoader.carregarDisciplinaGateway();
            var disciplinas = gateway.carregarDadosDoServicoExterno();
            RepositoryRegistry.getDisciplinaRepository().carregarDados(disciplinas);
        } catch (Exception e) {
            System.err.println("Erro ao carregar disciplinas: " + e.getMessage());
        }
    }
    
    private static void carregarDadosLivros() {
        try {
            var gateway = GatewayLoader.carregarLivroGateway();
            var livros = gateway.carregarDadosDoServicoExterno();
            RepositoryRegistry.getLivroRepository().carregarDados(livros);
        } catch (Exception e) {
            System.err.println("Erro ao carregar livros: " + e.getMessage());
        }
    }
    
    public static boolean estaInicializado() {
        return inicializado;
    }
}