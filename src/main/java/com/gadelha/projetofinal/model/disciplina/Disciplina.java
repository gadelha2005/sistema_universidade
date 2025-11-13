package com.gadelha.projetofinal.model.disciplina;

public class Disciplina {

    private int id;
    private String curso;
    private String nome;
    private int vagas;

    public Disciplina(int id, String curso, String nome, int vagas) {
        this.id = id;
        this.curso = curso;
        this.nome = nome;
        this.vagas = vagas;
    }

    
    public boolean temVagasDisponiveis() {
        return vagas > 0;
    }

    public boolean podeMatricularAlunos() {
        return temVagasDisponiveis();
    }

    public void decrementarVaga() {
        if (vagas > 0) {
            vagas--;
        } else {
            throw new IllegalStateException("Não há vagas disponíveis na disciplina");
        }
    }

    public void incrementarVaga() {
        vagas++;
    }

    public void validar() {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da disciplina é obrigatório");
        }
        if (curso == null || curso.trim().isEmpty()) {
            throw new IllegalArgumentException("Curso da disciplina é obrigatório");
        }
        if (vagas < 0) {
            throw new IllegalArgumentException("Número de vagas não pode ser negativo");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }


    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", curso=" + curso + ", nome=" + nome + ", vagas=" + vagas + "]";
    }

    
}