package com.gadelha.projetofinal.controller.disciplina.interfaces;

public interface IDisciplinaController {
    void consultarDisciplinaPorId(int id);
    void consultarDisciplinasPorCurso(String curso);
    void listarTodasDisciplinas();
}