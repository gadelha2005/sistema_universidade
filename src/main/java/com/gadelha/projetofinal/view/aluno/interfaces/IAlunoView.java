package com.gadelha.projetofinal.view.aluno.interfaces;

import java.util.List;
import com.gadelha.projetofinal.model.aluno.Aluno;

public interface IAlunoView {
    void iniciar();
    void exibirDetalhesAluno(int id, String nome, String curso, String modalidade, String status);
    void exibirListaAlunos(List<Aluno> alunos); 
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagemErro);
    
}