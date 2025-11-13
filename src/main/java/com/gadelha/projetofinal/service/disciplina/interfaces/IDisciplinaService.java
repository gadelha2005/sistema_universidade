package com.gadelha.projetofinal.service.disciplina.interfaces;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.disciplina.Disciplina;

public interface IDisciplinaService {
    Optional<Disciplina> consultarDisciplinaPorId(int id);
    Optional<List<Disciplina>> consultarDisciplinasPorCurso(String curso);
    List<Disciplina> listarTodasDisciplinas();
}