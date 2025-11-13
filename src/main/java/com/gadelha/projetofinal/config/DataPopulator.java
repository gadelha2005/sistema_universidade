package com.gadelha.projetofinal.config;

import java.util.List;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.model.biblioteca.Livro;

public class DataPopulator {
    
    private DataPopulator() {
    }
    
    public static void popularDadosAlunos(List<Aluno> alunos) {
        try {
            
            var alunoRepository = RepositoryRegistry.getAlunoRepositoryDuranteInicializacao();
            if (alunoRepository != null) {
                alunoRepository.carregarDados(alunos);
            } else {
                System.err.println("Repositório de alunos não está disponível durante inicialização");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao popular dados de alunos: " + e.getMessage(), e);
        }
    }
    
    public static void popularDadosDisciplinas(List<Disciplina> disciplinas) {
        try {
            var disciplinaRepository = RepositoryRegistry.getDisciplinaRepositoryDuranteInicializacao();
            if (disciplinaRepository != null) {
                disciplinaRepository.carregarDados(disciplinas);
            } else {
                System.err.println("Repositório de disciplinas não está disponível durante inicialização");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao popular dados de disciplinas: " + e.getMessage(), e);
        }
    }
    
    public static void popularDadosLivros(List<Livro> livros) {
        try {
            var livroRepository = RepositoryRegistry.getLivroRepositoryDuranteInicializacao();
            if (livroRepository != null) {
                livroRepository.carregarDados(livros);
            } else {
                System.err.println("Repositório de livros não está disponível durante inicialização");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao popular dados de livros: " + e.getMessage(), e);
        }
    }
}