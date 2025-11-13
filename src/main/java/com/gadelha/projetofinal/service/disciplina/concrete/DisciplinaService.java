package com.gadelha.projetofinal.service.disciplina.concrete;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.config.RepositoryRegistry;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.repository.disciplina.concrete.DisciplinaRepository;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;
import com.gadelha.projetofinal.service.disciplina.interfaces.IDisciplinaService;

public class DisciplinaService implements IDisciplinaService {

    private final IReadRepository<Disciplina> disciplinaRepository;

    public DisciplinaService(){
        this.disciplinaRepository = RepositoryRegistry.getDisciplinaRepository();
    }

    @Override
    public Optional<Disciplina> consultarDisciplinaPorId(int id) {
        try{
            return disciplinaRepository.buscarPorId(id);
        }
        catch(Exception e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Disciplina>> consultarDisciplinasPorCurso(String curso) {
        try {
            if (disciplinaRepository instanceof DisciplinaRepository) {
                DisciplinaRepository repoEspecifico = (DisciplinaRepository) disciplinaRepository;
                List<Disciplina> disciplinasDoCurso = repoEspecifico.buscarPorCurso(curso);
                return disciplinasDoCurso.isEmpty() 
                    ? Optional.empty() 
                    : Optional.of(disciplinasDoCurso);
            } else {
                
                List<Disciplina> todas = disciplinaRepository.listarTodos();
                List<Disciplina> disciplinasDoCurso = todas.stream()
                    .filter(disciplina -> disciplina.getCurso().equalsIgnoreCase(curso))
                    .toList();
                return disciplinasDoCurso.isEmpty() 
                    ? Optional.empty() 
                    : Optional.of(disciplinasDoCurso);
            }
        } 
        catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Disciplina> listarTodasDisciplinas() {
        try{
            return disciplinaRepository.listarTodos();
        }
        catch(Exception e){
            return List.of();
        }
    }
}