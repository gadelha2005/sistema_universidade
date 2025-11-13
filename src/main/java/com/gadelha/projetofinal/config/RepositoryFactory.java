package com.gadelha.projetofinal.config;

import com.gadelha.projetofinal.repository.aluno.concrete.AlunoRepository;
import com.gadelha.projetofinal.repository.disciplina.concrete.DisciplinaRepository;
import com.gadelha.projetofinal.repository.biblioteca.concrete.LivroRepository;
import com.gadelha.projetofinal.repository.matricula.concrete.MatriculaRepository;
import com.gadelha.projetofinal.repository.emprestimo.concrete.EmprestimoRepository;

public class RepositoryFactory {

    private RepositoryFactory() {
    }
    
    public static AlunoRepository criarAlunoRepository() {
        return new AlunoRepository();
    }
    
    public static DisciplinaRepository criarDisciplinaRepository() {
        return new DisciplinaRepository();
    }
    
    public static LivroRepository criarLivroRepository() {
        return new LivroRepository();
    }
    
    public static MatriculaRepository criarMatriculaRepository() {
        return new MatriculaRepository();
    }
    
    public static EmprestimoRepository criarEmprestimoRepository() {
        return new EmprestimoRepository();
    }
}