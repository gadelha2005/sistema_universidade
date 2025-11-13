package com.gadelha.projetofinal.view.disciplina.concrete;

import java.util.Scanner;
import java.util.List;
import com.gadelha.projetofinal.controller.disciplina.concrete.DisciplinaController;
import com.gadelha.projetofinal.controller.disciplina.interfaces.IDisciplinaController;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.view.disciplina.interfaces.IDisciplinaView;

public class DisciplinaView implements IDisciplinaView {
    
    private final Scanner sc;
    private final IDisciplinaController iDisciplinaController;
    
    public DisciplinaView(Scanner scanner) {
        this.sc = scanner;
        this.iDisciplinaController = new DisciplinaController(this);
    }

    @Override
    public void iniciar() {
        exibirMenuDisciplinas();
    }
    
    private void exibirMenuDisciplinas() {
        int opcao;
        do {
            System.out.println("1. Consultar disciplina por ID");
            System.out.println("2. Consultar disciplinas por curso");
            System.out.println("3. Listar todas as disciplinas"); 
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iDisciplinaController.consultarDisciplinaPorId(pegarIdDisciplina());
                    break;
                case 2:
                    iDisciplinaController.consultarDisciplinasPorCurso(pegarNomeCurso());
                    break;
                case 3:
                    iDisciplinaController.listarTodasDisciplinas();
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

    private String pegarNomeCurso() {
        System.out.print("Digite o nome do curso: ");
        return sc.nextLine().trim();
    }

    @Override
    public void exibirDetalhesDisciplina(int id, String nome, String curso, int vagas) {
        System.out.println("\n=Informações da Disciplina");
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Curso: " + curso);
        System.out.println("Vagas disponíveis: " + vagas);
        System.out.println("Status: " + (vagas > 0 ? "Com vagas" : "Lotada"));
    }

    @Override
    public void exibirListaDisciplinas(List<Disciplina> disciplinas) {
        System.out.println("\nLista de Disciplinas");
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina encontrada.");
            return;
        }

        System.out.println("Total de disciplinas: " + disciplinas.size());
        
        for (Disciplina disciplina : disciplinas) {
            System.out.println("\nID: " + disciplina.getId() + 
                             "\nNome: " + disciplina.getNome() + 
                             "\nCurso: " + disciplina.getCurso() + 
                             "\nVagas: " + disciplina.getVagas() +
                             " | " + (disciplina.temVagasDisponiveis() ? "Com vagas" : "Lotada"));
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
