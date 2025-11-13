package com.gadelha.projetofinal.view.disciplina.interfaces;

import java.util.List;
import com.gadelha.projetofinal.model.disciplina.Disciplina;

public interface IDisciplinaView {
    void iniciar();
    void exibirDetalhesDisciplina(int id, String nome, String curso, int vagas);
    void exibirListaDisciplinas(List<Disciplina> disciplinas);
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagemErro);
}
