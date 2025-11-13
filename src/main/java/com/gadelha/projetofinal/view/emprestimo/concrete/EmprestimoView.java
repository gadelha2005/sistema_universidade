package com.gadelha.projetofinal.view.emprestimo.concrete;

import java.util.List;
import java.util.Scanner;
import com.gadelha.projetofinal.controller.emprestimo.concrete.EmprestimoController;
import com.gadelha.projetofinal.controller.emprestimo.interfaces.IEmprestimoController;
import com.gadelha.projetofinal.model.emprestimo.Emprestimo;
import com.gadelha.projetofinal.view.emprestimo.interfaces.IEmprestimoView;

public class EmprestimoView implements IEmprestimoView {

    private final Scanner sc;
    private final IEmprestimoController iEmprestimoController;

    public EmprestimoView(Scanner sc){
        this.sc = sc;
        this.iEmprestimoController = new EmprestimoController(this);
    }

    @Override
    public void iniciar() {
        exibirMenuEmprestimos();
    }
    
    private void exibirMenuEmprestimos() {
        int opcao;
        do {
            System.out.println("1. Realizar empréstimo");
            System.out.println("2. Devolver livro");
            System.out.println("3. Consultar empréstimos ativos por aluno");
            System.out.println("4. Consultar empréstimos em atraso");
            System.out.println("5. Listar todos os empréstimos");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iEmprestimoController.realizarEmprestimo(pegarIdAluno(), pegarIdLivro());
                    break;
                case 2:
                    iEmprestimoController.devolverLivro(pegarIdEmprestimo());
                    break;
                case 3:
                    iEmprestimoController.consultarEmprestimosAtivosPorAluno(pegarIdAluno());
                    break;
                case 4:
                    iEmprestimoController.consultarEmprestimosAtrasados();
                    break;
                case 5:
                    iEmprestimoController.listarTodosEmprestimos();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    exibirErro("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private int pegarOpcao() {
        while(!sc.hasNextInt()) {
            System.out.print("Opção Inválida! Digite uma opção válida: ");
            sc.next();
        }
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }
    
    private int pegarIdAluno() {
        System.out.print("Digite o ID do aluno: ");
        while(!sc.hasNextInt()) {
            System.out.print("ID inválido! Digite um ID válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }
    
    private int pegarIdLivro() {
        System.out.print("Digite o ID do livro: ");
        while(!sc.hasNextInt()) {
            System.out.print("ID inválido! Digite um ID válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }
    
    private int pegarIdEmprestimo() {
        System.out.print("Digite o ID do empréstimo: ");
        while(!sc.hasNextInt()) {
            System.out.print("ID inválido! Digite um ID válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    @Override
    public void exibirDetalhesEmprestimo(Emprestimo emprestimo) {
        System.out.println("\nDetalhes do Empréstimo");
        System.out.println("ID do Empréstimo: " + emprestimo.getId());
        System.out.println("Aluno: " + emprestimo.getAluno().getNome() + " (ID: " + emprestimo.getAluno().getId() + ")");
        System.out.println("Livro: " + emprestimo.getLivro().getTitulo() + " (ID: " + emprestimo.getLivro().getId() + ")");
        System.out.println("Autor: " + emprestimo.getLivro().getAutor());
        System.out.println("Data do Empréstimo: " + emprestimo.getDataEmprestimo());
        System.out.println("Data de Devolução Prevista: " + emprestimo.getDataDevolucaoPrevista());
        System.out.println("Data de Devolução Real: " + 
            (emprestimo.getDataDevolucaoReal() != null ? emprestimo.getDataDevolucaoReal() : "Não devolvido"));
        System.out.println("Status: " + 
            (emprestimo.estaAtivo() ? 
                (emprestimo.estaAtrasado() ? "ATRASADO" : "ATIVO") : 
                "DEVOLVIDO"));
        System.out.println();
    }

    @Override
    public void exibirListaEmprestimos(List<Emprestimo> emprestimos) {
        System.out.println("\nLista de Empréstimos");
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo encontrado.");
            return;
        }

        System.out.println("Total de empréstimos: " + emprestimos.size());
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("\nID Empréstimo: " + emprestimo.getId());
            System.out.println("Aluno: " + emprestimo.getAluno().getNome() + " (ID: " + emprestimo.getAluno().getId() + ")");
            System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
            System.out.println("Data Empréstimo: " + emprestimo.getDataEmprestimo());
            System.out.println("Data Devolução Prevista: " + emprestimo.getDataDevolucaoPrevista());
            System.out.println("Status: " + 
                (emprestimo.estaAtivo() ? 
                    (emprestimo.estaAtrasado() ? "ATRASADO" : "ATIVO") : 
                    "DEVOLVIDO"));
            System.out.println("---");
        }
    }

    @Override
    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    @Override
    public void exibirErro(String mensagemErro) {
        System.out.println(mensagemErro);
    }
}