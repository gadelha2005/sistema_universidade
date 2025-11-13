package com.gadelha.projetofinal.service.emprestimo.interfaces;

import java.util.List;

import com.gadelha.projetofinal.model.emprestimo.Emprestimo;

public interface IEmprestimoService {
    Emprestimo realizarEmprestimo(int alunoId, int livroId);
    
    void devolverLivro(int emprestimoId);
    
    List<Emprestimo> listarEmprestimosAtivosPorAluno(int alunoId);
    
    List<Emprestimo> listarEmprestimosAtrasados();
    
    List<Emprestimo> listarTodosEmprestimos();
    
    int contarEmprestimosAtivosPorAluno(int alunoId);
}
