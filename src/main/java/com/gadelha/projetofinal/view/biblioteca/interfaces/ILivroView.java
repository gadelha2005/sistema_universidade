package com.gadelha.projetofinal.view.biblioteca.interfaces;

import java.util.List;

import com.gadelha.projetofinal.model.biblioteca.Livro;

public interface ILivroView {
    void iniciar();
    void exibirDetalhesLivro(int id, String titulo, String autor, int ano, String situacao);
    void exibirListaLivros(List<Livro> livros);
    void exibirMensagem(String mensagem);
    void exibirErro(String mensagemErro);
}