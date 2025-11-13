package com.gadelha.projetofinal.view.emprestimo.interfaces;

import java.util.List;
import com.gadelha.projetofinal.model.emprestimo.Emprestimo;

public interface IEmprestimoView {
    
    void iniciar();
    
    void exibirDetalhesEmprestimo(Emprestimo emprestimo);
    
    void exibirListaEmprestimos(List<Emprestimo> emprestimos);
    
    void exibirMensagem(String mensagem);
    
    void exibirErro(String mensagemErro);
}