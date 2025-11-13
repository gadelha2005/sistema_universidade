package com.gadelha.projetofinal.view.aluno.concrete;

import java.util.Scanner;
import java.util.List;
import com.gadelha.projetofinal.controller.aluno.concrete.AlunoController;
import com.gadelha.projetofinal.controller.aluno.interfaces.IAlunoController;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.view.aluno.interfaces.IAlunoView;

public class AlunoView implements IAlunoView {
    
    private final Scanner sc;
    private final IAlunoController iAlunoController;
    
    public AlunoView(Scanner scanner) {
        this.sc = scanner;
        this.iAlunoController = new AlunoController(this);
    }

    @Override
    public void iniciar() {
        exibirMenuPrincipal();
    }
    
    private void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("1. Consultar aluno por ID");
            System.out.println("2. Listar todos os alunos"); 
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iAlunoController.consultarAlunoPorId(pegarIdAluno());
                    break;
                case 2:
                    iAlunoController.listarTodosAlunos();
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

    @Override
    public void exibirDetalhesAluno(int id, String nome, String curso, String modalidade, String status) {
        System.out.println("\nInformações do Aluno: ");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Curso: " + curso);
        System.out.println("Modalidade: " + modalidade);
        System.out.println("Status: " + status);

        System.out.println("");
    }

    @Override
    public void exibirListaAlunos(List<Aluno> alunos) {
        System.out.println("\nLista de Alunos");
        System.out.println("Total de alunos: " + alunos.size());
        
        for (Aluno aluno : alunos) {
            System.out.println("\nID: " + aluno.getId() + 
                             " \nNome: " + aluno.getNome() + 
                             " \nCurso: " + aluno.getCurso() + 
                             " \nModalidade: " + aluno.getModalidade() + 
                             " \nStatus: " + aluno.getStatus());
        }
        System.out.println("");
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