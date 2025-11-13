package com.gadelha.projetofinal.model.matricula;

import java.time.LocalDateTime;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.disciplina.Disciplina;

public class Matricula {

    private int id;
    private Aluno aluno;
    private Disciplina disciplina;
    private boolean ativa;
    private LocalDateTime dataMatricula;

    public Matricula(int id, Aluno aluno, Disciplina disciplina) {
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo.");
        }
        if (disciplina == null) {
            throw new IllegalArgumentException("A disciplina não pode ser nula.");
        }

        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.ativa = true;
        this.dataMatricula = LocalDateTime.now();
    }
    
    public int getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}
