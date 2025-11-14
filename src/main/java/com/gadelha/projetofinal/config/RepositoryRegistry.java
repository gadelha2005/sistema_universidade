package com.gadelha.projetofinal.config;

import com.gadelha.projetofinal.repository.aluno.concrete.AlunoRepository;
import com.gadelha.projetofinal.repository.disciplina.concrete.DisciplinaRepository;
import com.gadelha.projetofinal.repository.biblioteca.concrete.LivroRepository;
import com.gadelha.projetofinal.repository.matricula.concrete.MatriculaRepository;
import com.gadelha.projetofinal.repository.emprestimo.concrete.EmprestimoRepository;

public class RepositoryRegistry {
    
    private static final AlunoRepository alunoRepository = new AlunoRepository();
    private static final DisciplinaRepository disciplinaRepository = new DisciplinaRepository();
    private static final LivroRepository livroRepository = new LivroRepository();
    private static final MatriculaRepository matriculaRepository = new MatriculaRepository();
    private static final EmprestimoRepository emprestimoRepository = new EmprestimoRepository();
    
    private RepositoryRegistry() {}
    
    public static AlunoRepository getAlunoRepository() {
        return alunoRepository;
    }
    
    public static DisciplinaRepository getDisciplinaRepository() {
        return disciplinaRepository;
    }
    
    public static LivroRepository getLivroRepository() {
        return livroRepository;
    }
    
    public static MatriculaRepository getMatriculaRepository() {
        return matriculaRepository;
    }
    
    public static EmprestimoRepository getEmprestimoRepository() {
        return emprestimoRepository;
    }
}