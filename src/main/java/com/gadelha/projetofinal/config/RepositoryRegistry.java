package com.gadelha.projetofinal.config;

import com.gadelha.projetofinal.model.matricula.Matricula;
import com.gadelha.projetofinal.model.emprestimo.Emprestimo;
import com.gadelha.projetofinal.repository.aluno.concrete.AlunoRepository;
import com.gadelha.projetofinal.repository.disciplina.concrete.DisciplinaRepository;
import com.gadelha.projetofinal.repository.biblioteca.concrete.LivroRepository;
import com.gadelha.projetofinal.repository.interfaces.IRepository;

public class RepositoryRegistry {
    private static AlunoRepository alunoRepository;
    private static DisciplinaRepository disciplinaRepository;
    private static LivroRepository livroRepository;
    private static IRepository<Matricula> matriculaRepository;
    private static IRepository<Emprestimo> emprestimoRepository;
    
    private static volatile boolean emInicializacao = false;

    public static AlunoRepository getAlunoRepository() {
        verificarInicializacao();
        return alunoRepository;
    }

    public static DisciplinaRepository getDisciplinaRepository() {
        verificarInicializacao();
        return disciplinaRepository;
    }

    public static LivroRepository getLivroRepository() {
        verificarInicializacao();
        return livroRepository;
    }

    public static IRepository<Matricula> getMatriculaRepository() {
        verificarInicializacao();
        return matriculaRepository;
    }

    public static IRepository<Emprestimo> getEmprestimoRepository() {
        verificarInicializacao();
        return emprestimoRepository;
    }

    private static void verificarInicializacao() {
        if (emInicializacao) {
            return;
        }
        
        if (!RepositoryInitializer.estaInicializado()) {
            throw new IllegalStateException("Repositórios não inicializados. Chame RepositoryInitializer.inicializar() primeiro.");
        }
    }

    
    static AlunoRepository getAlunoRepositoryDuranteInicializacao() {
        return alunoRepository;
    }

    static DisciplinaRepository getDisciplinaRepositoryDuranteInicializacao() {
        return disciplinaRepository;
    }

    static LivroRepository getLivroRepositoryDuranteInicializacao() {
        return livroRepository;
    }

    static void setAlunoRepository(AlunoRepository repository) {
        alunoRepository = repository;
    }

    static void setDisciplinaRepository(DisciplinaRepository repository) {
        disciplinaRepository = repository;
    }

    static void setLivroRepository(LivroRepository repository) {
        livroRepository = repository;
    }

    static void setMatriculaRepository(IRepository<Matricula> repository) {
        matriculaRepository = repository;
    }

    static void setEmprestimoRepository(IRepository<Emprestimo> repository) {
        emprestimoRepository = repository;
    }
    
    
    static void iniciarInicializacao() {
        emInicializacao = true;
    }
    
    static void finalizarInicializacao() {
        emInicializacao = false;
    }
}