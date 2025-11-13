package com.gadelha.projetofinal.service.matricula.concrete;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.config.RepositoryRegistry;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.model.matricula.Matricula;
import com.gadelha.projetofinal.repository.interfaces.IRepository;
import com.gadelha.projetofinal.repository.disciplina.concrete.DisciplinaRepository;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;
import com.gadelha.projetofinal.service.matricula.interfaces.IMatriculaService;

public class MatriculaService implements IMatriculaService {

    private final IRepository<Matricula> matriculaRepository;
    private final IReadRepository<Aluno> alunoRepository;
    private final IReadRepository<Disciplina> disciplinaRepository;

    public MatriculaService() {
        this.matriculaRepository = RepositoryRegistry.getMatriculaRepository(); 
        this.alunoRepository = RepositoryRegistry.getAlunoRepository();
        this.disciplinaRepository = RepositoryRegistry.getDisciplinaRepository();
    }

    @Override
    public Matricula matricularAluno(int alunoId, int disciplinaId) {
        Aluno aluno = alunoRepository.buscarPorId(alunoId)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
        
        Disciplina disciplina = disciplinaRepository.buscarPorId(disciplinaId)
            .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada com ID: " + disciplinaId));
        
        validarMatricula(aluno, disciplina);
        
        Matricula matricula = new Matricula(0, aluno, disciplina); 
        
        matriculaRepository.salvar(matricula);
        
        disciplina.decrementarVaga();
        
        atualizarDisciplina(disciplina);
        
        return matricula;
    }

    @Override
    public void cancelarMatricula(int matriculaId) {
        Optional<Matricula> matriculaOptional = matriculaRepository.buscarPorId(matriculaId);
        if (matriculaOptional.isEmpty()) {
            throw new IllegalArgumentException("Matrícula não encontrada com ID: " + matriculaId);
        }
        
        Matricula matricula = matriculaOptional.get();
        Disciplina disciplina = matricula.getDisciplina();
        
        disciplina.incrementarVaga();
        atualizarDisciplina(disciplina);
        
        matriculaRepository.remover(matriculaId);
    }

    @Override
    public List<Matricula> listarMatriculasAtivasPorAluno(int alunoId) {
        return matriculaRepository.listarTodos().stream()
            .filter(matricula -> matricula.getAluno().getId() == alunoId && matricula.isAtiva())
            .toList();
    }

    @Override
    public int contarMatricularAtivasPorAluno(int alunoId) {
        return listarMatriculasAtivasPorAluno(alunoId).size();
    }

    @Override
    public List<Matricula> listarTodasMatriculas() {
        return matriculaRepository.listarTodos();
    }
    
    private void atualizarDisciplina(Disciplina disciplina) {
        
        if (disciplinaRepository instanceof DisciplinaRepository) {
            
            DisciplinaRepository repoEspecifico = (DisciplinaRepository) disciplinaRepository;
            repoEspecifico.atualizar(disciplina);
        }
    }
    
    private void validarMatricula(Aluno aluno, Disciplina disciplina) {
        if (!aluno.estaAtivo()) {
            throw new IllegalStateException("Aluno com matrícula trancada não pode se matricular");
        }
        
        if (!disciplina.temVagasDisponiveis()) {
            throw new IllegalStateException("Disciplina não possui vagas disponíveis");
        }
        
        if (!aluno.getCurso().equalsIgnoreCase(disciplina.getCurso())) {
            throw new IllegalStateException(
                "Disciplina pertence ao curso '" + disciplina.getCurso() + 
                "' e aluno está no curso '" + aluno.getCurso() + "'");
        }
        
        int matriculasAtivas = contarMatricularAtivasPorAluno(aluno.getId());
        if (matriculasAtivas >= 5) {
            throw new IllegalStateException(
                "Aluno já possui " + matriculasAtivas + " matrículas ativas. Limite é 5.");
        }
        
        boolean jaMatriculado = listarMatriculasAtivasPorAluno(aluno.getId()).stream()
            .anyMatch(m -> m.getDisciplina().getId() == disciplina.getId());
            
        if (jaMatriculado) {
            throw new IllegalStateException("Aluno já está matriculado nesta disciplina");
        }
    }
}