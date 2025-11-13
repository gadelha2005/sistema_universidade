package com.gadelha.projetofinal.service.matricula.interfaces;

import java.util.List;

import com.gadelha.projetofinal.model.matricula.Matricula;

public interface IMatriculaService {
    Matricula matricularAluno(int alunoId , int disciplinaId);
    void cancelarMatricula(int matriculaId);
    List<Matricula> listarMatriculasAtivasPorAluno(int alunoId);
    int contarMatricularAtivasPorAluno(int alunoId);
    List<Matricula> listarTodasMatriculas();
}
