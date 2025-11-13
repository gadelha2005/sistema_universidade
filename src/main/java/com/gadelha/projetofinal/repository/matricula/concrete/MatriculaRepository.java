package com.gadelha.projetofinal.repository.matricula.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.matricula.Matricula;
import com.gadelha.projetofinal.repository.interfaces.IRepository;

public class MatriculaRepository implements IRepository<Matricula> {
    private List<Matricula> matriculas = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public Optional<Matricula> buscarPorId(int id) {
        return matriculas.stream()
            .filter(matricula -> matricula.getId() == id)
            .findFirst();
    }

    @Override
    public List<Matricula> listarTodos() {
        return new ArrayList<>(matriculas);
    }

    @Override
    public void salvar(Matricula matricula) {
        if(matricula == null){
            throw new IllegalArgumentException("A matrícula não pode ser nula!");
        }
        
        if (matricula.getId() == 0) {
            matricula.setId(proximoId++);
        }
        matriculas.add(matricula);
    }

    @Override
    public void atualizar(Matricula matricula) {
        for (int i = 0; i < matriculas.size(); i++) {
            if (matriculas.get(i).getId() == matricula.getId()) {
                matriculas.set(i, matricula);
                break;
            }
        }
    }

    @Override
    public void remover(int id) {
        matriculas.removeIf(matricula -> matricula.getId() == id);
    }

    public List<Matricula> buscarMatriculasAtivasPorAluno(int alunoId) {
        return matriculas.stream()
            .filter(matricula -> matricula.getAluno().getId() == alunoId && matricula.isAtiva())
            .toList();
    }
}