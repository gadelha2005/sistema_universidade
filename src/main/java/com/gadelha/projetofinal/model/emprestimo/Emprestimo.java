package com.gadelha.projetofinal.model.emprestimo;

import java.time.LocalDate;

import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.biblioteca.Livro;

public class Emprestimo {
    
    private int id;
    private Aluno aluno;
    private Livro livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

     public Emprestimo(int id, Aluno aluno, Livro livro, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo.");
        }
        if (livro == null) {
            throw new IllegalArgumentException("O livro não pode ser nulo.");
        }
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("A data de empréstimo não pode ser nula.");
        }
        if (dataDevolucaoPrevista == null) {
            throw new IllegalArgumentException("A data de devolução prevista não pode ser nula.");
        }
        if (dataDevolucaoPrevista.isBefore(dataEmprestimo)) {
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de empréstimo.");
        }

        this.id = id;
        this.aluno = aluno;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;
    }

    public boolean estaAtivo() {
        return dataDevolucaoReal == null;
    }
    
    public boolean estaAtrasado() {
        return estaAtivo() && LocalDate.now().isAfter(dataDevolucaoPrevista);
    }
    
    public boolean foiDevolvido() {
        return dataDevolucaoReal != null;
    }
    
    public long calcularDiasAtraso() {
        if (!estaAtrasado()) {
            return 0;
        }
        return java.time.temporal.ChronoUnit.DAYS.between(dataDevolucaoPrevista, LocalDate.now());
    }
    
    public void devolver() {
        if (foiDevolvido()) {
            throw new IllegalStateException("Este empréstimo já foi devolvido.");
        }
        this.dataDevolucaoReal = LocalDate.now();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }
}
