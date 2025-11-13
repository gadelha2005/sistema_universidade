package com.gadelha.projetofinal.service.aluno.concrete;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.config.RepositoryRegistry;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;
import com.gadelha.projetofinal.service.aluno.interfaces.IAlunoService;

public class AlunoService implements IAlunoService {

    private final IReadRepository<Aluno> alunoRepository;

    public AlunoService(){
        this.alunoRepository = RepositoryRegistry.getAlunoRepository();
    }
   
    @Override
    public Optional<Aluno> consultarAlunoPorId(int id) {
        try {
            return alunoRepository.buscarPorId(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Aluno> listarTodosAlunos() {
        try {
            return alunoRepository.listarTodos();
        } catch (Exception e) {
            return List.of();
        }
    }
}