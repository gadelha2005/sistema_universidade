package com.gadelha.projetofinal.controller.emprestimo.interfaces;

public interface IEmprestimoController {
    
    void realizarEmprestimo(int alunoId, int livroId);
    
    void devolverLivro(int emprestimoId);
    
    void consultarEmprestimosAtivosPorAluno(int alunoId);
    
    void consultarEmprestimosAtrasados();
    
    void listarTodosEmprestimos();
}
