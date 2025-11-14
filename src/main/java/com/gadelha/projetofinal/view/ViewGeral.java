package com.gadelha.projetofinal.view;

import java.util.Scanner;

import com.gadelha.projetofinal.config.DataInitializer;
import com.gadelha.projetofinal.view.aluno.concrete.AlunoView;
import com.gadelha.projetofinal.view.aluno.interfaces.IAlunoView;
import com.gadelha.projetofinal.view.biblioteca.concrete.LivroView;
import com.gadelha.projetofinal.view.biblioteca.interfaces.ILivroView;
import com.gadelha.projetofinal.view.disciplina.concrete.DisciplinaView;
import com.gadelha.projetofinal.view.disciplina.interfaces.IDisciplinaView;
import com.gadelha.projetofinal.view.matricula.concrete.MatriculaView;
import com.gadelha.projetofinal.view.matricula.interfaces.IMatriculaView;
import com.gadelha.projetofinal.view.emprestimo.concrete.EmprestimoView;
import com.gadelha.projetofinal.view.emprestimo.interfaces.IEmprestimoView;

public class ViewGeral {
    private final Scanner sc;
    private boolean executando = true;
    
    public ViewGeral() {
        this.sc = new Scanner(System.in);
        DataInitializer.inicializar();
    }
    
    public void iniciar() {
        System.out.println("\nSistema Acadêmico e Biblioteca");
        exibirMenuPrincipal();
    }
    
    private void exibirMenuPrincipal() {
        while (executando) {
            System.out.println("\nMenu Principal");
            System.out.println("1. Gerenciar Alunos");
            System.out.println("2. Gerenciar Disciplinas");
            System.out.println("3. Gerenciar Livros");
            System.out.println("4. Gerenciar Matrículas");
            System.out.println("5. Gerenciar Empréstimos");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha uma opção: ");
            
            int opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iniciarModuloAlunos();
                    break;
                case 2:
                    iniciarModuloDisciplinas();
                    break;
                case 3:
                    iniciarModuloLivros();
                    break;
                case 4:
                    iniciarModuloMatriculas();
                    break;
                case 5:
                    iniciarModuloEmprestimos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        fecharCaptura();
    }
    
    private void iniciarModuloAlunos() {
        System.out.println("\nMódulo de Alunos");
        IAlunoView alunoView = new AlunoView(sc);
        alunoView.iniciar();
    }
    
    private void iniciarModuloDisciplinas() {
        System.out.println("\nMódulo de Disciplinas");
        IDisciplinaView disciplinaView = new DisciplinaView(sc);
        disciplinaView.iniciar();
    }
    
    private void iniciarModuloLivros() {
        System.out.println("\nMódulo de Livros");
        ILivroView livroView = new LivroView(sc);
        livroView.iniciar();
    }
    
    private void iniciarModuloMatriculas() {
        System.out.println("\nMódulo de Matrículas");
        IMatriculaView matriculaView = new MatriculaView(sc);
        matriculaView.iniciar();
    }
    
    private void iniciarModuloEmprestimos() {
        System.out.println("\nMódulo de Empréstimos");
        IEmprestimoView emprestimoView = new EmprestimoView(sc);
        emprestimoView.iniciar();
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
    
    public void fecharCaptura() {
        if (sc != null) {
            sc.close();
        }
    }
}