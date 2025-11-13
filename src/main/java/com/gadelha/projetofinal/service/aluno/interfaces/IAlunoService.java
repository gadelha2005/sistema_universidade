package com.gadelha.projetofinal.service.aluno.interfaces;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.aluno.Aluno;

public interface IAlunoService {
    Optional<Aluno> consultarAlunoPorId(int id);
    List<Aluno> listarTodosAlunos();
    
}