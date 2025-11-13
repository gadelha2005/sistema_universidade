package com.gadelha.projetofinal.view.matricula.interfaces;

import java.util.List;

import com.gadelha.projetofinal.model.matricula.Matricula;

public interface IMatriculaView {
    void iniciar();
    void exibirDetalhesMatricula(Matricula matricula);
    void exibirListaMatriculas(List<Matricula> matriculas);
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagemErro);
}