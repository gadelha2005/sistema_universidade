package com.gadelha.projetofinal.controller.emprestimo.concrete;

import java.util.ServiceLoader;

import com.gadelha.projetofinal.controller.emprestimo.interfaces.IEmprestimoController;
import com.gadelha.projetofinal.service.emprestimo.interfaces.IEmprestimoService;
import com.gadelha.projetofinal.view.emprestimo.interfaces.IEmprestimoView;

public class EmprestimoController implements IEmprestimoController {
    
    private final IEmprestimoView iEmprestimoView;
    private final IEmprestimoService iEmprestimoService;

    public EmprestimoController(IEmprestimoView iEmprestimoView) {
        this.iEmprestimoView = iEmprestimoView;
        this.iEmprestimoService = carregarService();
    }

    @Override
    public void realizarEmprestimo(int alunoId, int livroId) {
        try {
            var emprestimo = iEmprestimoService.realizarEmprestimo(alunoId, livroId);
            iEmprestimoView.exibirDetalhesEmprestimo(emprestimo);
        } catch (Exception e) {
            iEmprestimoView.exibirErro("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    @Override
    public void devolverLivro(int emprestimoId) {
        try {
            iEmprestimoService.devolverLivro(emprestimoId);
            iEmprestimoView.exibirMensagem("Devolução realizada com sucesso! Empréstimo ID: " + emprestimoId);
        } catch (Exception e) {
            iEmprestimoView.exibirErro("Erro ao devolver livro: " + e.getMessage());
        }
    }

    @Override
    public void consultarEmprestimosAtivosPorAluno(int alunoId) {
        try {
            var emprestimos = iEmprestimoService.listarEmprestimosAtivosPorAluno(alunoId);
            if (emprestimos.isEmpty()) {
                iEmprestimoView.exibirMensagem("Nenhum empréstimo ativo encontrado para o aluno ID: " + alunoId);
            } else {
                iEmprestimoView.exibirListaEmprestimos(emprestimos);
            }
        } catch (Exception e) {
            iEmprestimoView.exibirErro("Erro ao consultar empréstimos ativos: " + e.getMessage());
        }
    }

    @Override
    public void consultarEmprestimosAtrasados() {
        try {
            var emprestimosAtrasados = iEmprestimoService.listarEmprestimosAtrasados();
            if (emprestimosAtrasados.isEmpty()) {
                iEmprestimoView.exibirMensagem("Nenhum empréstimo em atraso encontrado");
            } else {
                iEmprestimoView.exibirListaEmprestimos(emprestimosAtrasados);
            }
        } catch (Exception e) {
            iEmprestimoView.exibirErro("Erro ao consultar empréstimos em atraso: " + e.getMessage());
        }
    }

    @Override
    public void listarTodosEmprestimos() {
        try {
            var todosEmprestimos = iEmprestimoService.listarTodosEmprestimos();
            if (todosEmprestimos.isEmpty()) {
                iEmprestimoView.exibirMensagem("Nenhum empréstimo registrado");
            } else {
                iEmprestimoView.exibirListaEmprestimos(todosEmprestimos);
            }
        } catch (Exception e) {
            iEmprestimoView.exibirErro("Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    private IEmprestimoService carregarService() {
        ServiceLoader<IEmprestimoService> loader = ServiceLoader.load(IEmprestimoService.class);
        return loader.findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma implementação de IEmprestimoService encontrada"));
    }
}