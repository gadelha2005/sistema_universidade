package com.gadelha.projetofinal.repository.aluno.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;

public class AlunoRepository implements IReadRepository<Aluno> {
    private List<Aluno> alunos;

    public AlunoRepository() {
        this.alunos = new ArrayList<>();
    }
    
    public void carregarDados(List<Aluno> dadosAlunos) {
        this.alunos = new ArrayList<>(dadosAlunos);
    }

    @Override
    public Optional<Aluno> buscarPorId(int id) {
        return alunos.stream()
                .filter(aluno -> aluno.getId() == id)
                .findFirst();
    }

    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    
    public List<Aluno> buscarPorCurso(String curso) {
        return alunos.stream()
                .filter(aluno -> aluno.getCurso().equalsIgnoreCase(curso))
                .toList();
    }
}