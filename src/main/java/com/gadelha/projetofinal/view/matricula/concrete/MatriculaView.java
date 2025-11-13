package com.gadelha.projetofinal.view.matricula.concrete;

import java.util.List;
import java.util.Scanner;

import com.gadelha.projetofinal.controller.matricula.concrete.MatriculaController;
import com.gadelha.projetofinal.controller.matricula.interfaces.IMatriculaController;
import com.gadelha.projetofinal.model.matricula.Matricula;
import com.gadelha.projetofinal.view.matricula.interfaces.IMatriculaView;

public class MatriculaView implements IMatriculaView {

    private final Scanner sc;
    private final IMatriculaController iMatriculaController;

    public MatriculaView(Scanner sc){
        this.sc = sc;
        this.iMatriculaController = new MatriculaController(this);
    }

    @Override
    public void iniciar() {
        exibirMenuMatriculas();
    }
    
    private void exibirMenuMatriculas() {
        int opcao;
        do {
            System.out.println("1. Matricular aluno em disciplina");
            System.out.println("2. Cancelar matrícula");
            System.out.println("3. Listar matrículas ativas por aluno");
            System.out.println("4. Listar todas as matrículas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iMatriculaController.matricularAluno(pegarIdAluno(), pegarIdDisciplina());
                    break;
                case 2:
                    iMatriculaController.cancelarMatricula(pegarIdMatricula());
                    break;
                case 3:
                    iMatriculaController.listarMatriculasAtivasPorAluno(pegarIdAluno());
                    break;
                case 4:
                    iMatriculaController.listarTodasMatriculas();
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
    
    private int pegarIdDisciplina() {
        System.out.print("Digite o ID da disciplina: ");
        while(!sc.hasNextInt()) {
            System.out.print("ID inválido! Digite um ID válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }
    
    private int pegarIdMatricula() {
        System.out.print("Digite o ID da matrícula: ");
        while(!sc.hasNextInt()) {
            System.out.print("ID inválido! Digite um ID válido: ");
            sc.next();
        }
        int id = sc.nextInt();
        sc.nextLine();
        return id;
    }

    @Override
    public void exibirDetalhesMatricula(Matricula matricula) {
        System.out.println("\nInformações da Matrícula");
        System.out.println("ID da Matrícula: " + matricula.getId());
        System.out.println("Aluno: " + matricula.getAluno().getNome() + " (ID: " + matricula.getAluno().getId() + ")");
        System.out.println("Disciplina: " + matricula.getDisciplina().getNome() + " (ID: " + matricula.getDisciplina().getId() + ")");
        System.out.println("Curso: " + matricula.getDisciplina().getCurso());
        System.out.println("Vagas restantes: " + matricula.getDisciplina().getVagas());
        System.out.println("Status: " + (matricula.isAtiva() ? "ATIVA" : "CANCELADA"));
        System.out.println("Data da matrícula: " + matricula.getDataMatricula());

        System.out.println();
    }

    @Override
    public void exibirListaMatriculas(List<Matricula> matriculas) {
        System.out.println("\nLista de Matrículas");
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matrícula encontrada.");
            return;
        }

        System.out.println("Total de matrículas: " + matriculas.size());
        
        for (Matricula matricula : matriculas) {
            System.out.println("\nID Matrícula: " + matricula.getId());
            System.out.println("Aluno: " + matricula.getAluno().getNome() + " (ID: " + matricula.getAluno().getId() + ")");
            System.out.println("Disciplina: " + matricula.getDisciplina().getNome());
            System.out.println("Curso: " + matricula.getDisciplina().getCurso());
            System.out.println("Status: " + (matricula.isAtiva() ? "ATIVA" : "CANCELADA"));
            System.out.println("Data: " + matricula.getDataMatricula());
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
