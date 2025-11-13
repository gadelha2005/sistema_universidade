package com.gadelha.projetofinal.repository.emprestimo.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.emprestimo.Emprestimo;
import com.gadelha.projetofinal.repository.interfaces.IRepository;

public class EmprestimoRepository implements IRepository<Emprestimo> {
    private List<Emprestimo> emprestimos = new ArrayList<>();
    private int proximoId = 1;

    @Override
    public Optional<Emprestimo> buscarPorId(int id) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getId() == id)
                .findFirst();
    }

    @Override
    public List<Emprestimo> listarTodos() {
        return new ArrayList<>(emprestimos);
    }

    @Override
    public void salvar(Emprestimo emprestimo) {
        if (emprestimo.getId() == 0) {
            emprestimo.setId(proximoId++);
        }
        emprestimos.add(emprestimo);
    }

    @Override
    public void atualizar(Emprestimo emprestimo) {
        for (int i = 0; i < emprestimos.size(); i++) {
            if (emprestimos.get(i).getId() == emprestimo.getId()) {
                emprestimos.set(i, emprestimo);
                break;
            }
        }
    }

    @Override
    public void remover(int id) {
        emprestimos.removeIf(emprestimo -> emprestimo.getId() == id);
    }

    public List<Emprestimo> buscarEmprestimosAtivosPorAluno(int alunoId) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getAluno().getId() == alunoId && emprestimo.estaAtivo())
                .toList();
    }

    public Optional<Emprestimo> buscarEmprestimoAtivoPorLivro(int livroId) {
        return emprestimos.stream()
                .filter(emprestimo -> emprestimo.getLivro().getId() == livroId && emprestimo.estaAtivo())
                .findFirst();
    }

    public List<Emprestimo> buscarEmprestimosAtrasados() {
        return emprestimos.stream()
                .filter(Emprestimo::estaAtrasado)
                .toList();
    }
}