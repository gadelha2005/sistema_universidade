package com.gadelha.projetofinal.controller.matricula.interfaces;

import java.util.List;

import com.gadelha.projetofinal.model.matricula.Matricula;

public interface IMatriculaController {
    Matricula matricularAluno(int alunoId, int disciplinaId);
    void cancelarMatricula(int matriculaId);
    List<Matricula> listarMatriculasAtivasPorAluno(int alunoId);
    List<Matricula> listarTodasMatriculas();
    int contarMatriculasAtivasPorAluno(int alunoId);
}