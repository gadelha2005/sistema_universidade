package com.gadelha.projetofinal.view.biblioteca.concrete;

import java.util.Scanner;
import java.util.List;

import com.gadelha.projetofinal.controller.biblioteca.concrete.LivroController;
import com.gadelha.projetofinal.controller.biblioteca.interfaces.ILivroController;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.view.biblioteca.interfaces.ILivroView;


public class LivroView implements ILivroView {
    
    private final Scanner sc;
    private final ILivroController iLivroController;
    
    public LivroView(Scanner scanner) {
        this.sc = scanner;
        this.iLivroController = new LivroController(this);
    }

    @Override
    public void iniciar() {
        exibirMenuLivros();
    }
    
    private void exibirMenuLivros() {
        int opcao;
        do {
            System.out.println("1. Consultar livro por ID");
            System.out.println("2. Listar todos os livros"); 
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            opcao = pegarOpcao();
            
            switch (opcao) {
                case 1:
                    iLivroController.consultarLivroPorId(pegarIdLivro());
                    break;
                case 2:
                    iLivroController.listarTodosLivros();
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

    @Override
    public void exibirDetalhesLivro(int id, String titulo, String autor, int ano, String situacao) {
        System.out.println("\nInformações do Livro");
        System.out.println("ID: " + id);
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Ano: " + ano);
        System.out.println("Status: " + situacao);
    }

    @Override
    public void exibirListaLivros(List<Livro> livros) {
        System.out.println("\nLista de Livros");
        System.out.println("Total de livros: " + livros.size());
        
        for (Livro livro : livros) {
            System.out.println("\nID: " + livro.getId() + 
                             " \nTítulo: " + livro.getTitulo() + 
                             " \nAutor: " + livro.getAutor() + 
                             " \nAno: " + livro.getAno() +
                             " \nStatus: " + livro.getStatus());
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