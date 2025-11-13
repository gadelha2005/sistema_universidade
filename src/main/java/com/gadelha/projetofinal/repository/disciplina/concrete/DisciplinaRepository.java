// DisciplinaRepository.java
package com.gadelha.projetofinal.repository.disciplina.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;

public class DisciplinaRepository implements IReadRepository<Disciplina> {
    private List<Disciplina> disciplinas;

    public DisciplinaRepository() {
        this.disciplinas = new ArrayList<>();
    }
    
   
    public void carregarDados(List<Disciplina> dadosDisciplinas) {
        this.disciplinas = new ArrayList<>(dadosDisciplinas);
    }

    @Override
    public Optional<Disciplina> buscarPorId(int id) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getId() == id)
                .findFirst();
    }

    @Override
    public List<Disciplina> listarTodos() {
        return new ArrayList<>(disciplinas);
    }

    
    public List<Disciplina> buscarPorCurso(String curso) {
        return disciplinas.stream()
                .filter(disciplina -> disciplina.getCurso().equalsIgnoreCase(curso))
                .toList();
    }

    
    public void atualizar(Disciplina disciplina) {
        for (int i = 0; i < disciplinas.size(); i++) {
            if (disciplinas.get(i).getId() == disciplina.getId()) {
                disciplinas.set(i, disciplina);
                break;
            }
        }
    }
}