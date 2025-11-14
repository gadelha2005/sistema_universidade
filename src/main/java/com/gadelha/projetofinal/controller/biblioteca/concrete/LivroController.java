package com.gadelha.projetofinal.controller.biblioteca.concrete;

import java.util.List;
import java.util.ServiceLoader;


import com.gadelha.projetofinal.controller.biblioteca.interfaces.ILivroController;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.service.biblioteca.interfaces.ILivroService;
import com.gadelha.projetofinal.view.biblioteca.interfaces.ILivroView;

public class LivroController implements ILivroController {
    
    private final ILivroView iLivroView;
    private final ILivroService iLivroService;

    public LivroController(ILivroView iLivroView) {
        this.iLivroView = iLivroView;
        this.iLivroService = carregarService();
    }

    @Override
    public void consultarLivroPorId(int id) {
        try {
            var livroOptional = iLivroService.consultarLivroPorId(id);
            if (livroOptional.isPresent()) {
                var livro = livroOptional.get();
                iLivroView.exibirDetalhesLivro(
                    livro.getId(), 
                    livro.getTitulo(), 
                    livro.getAutor(), 
                    livro.getAno(),
                    livro.getStatus()
                );
            } else {
                iLivroView.exibirErro("Livro com ID " + id + " não encontrado");
            }
        } catch (Exception e) {
            iLivroView.exibirErro("Erro ao consultar livro: " + e.getMessage());
        }
    }

    @Override
    public void listarTodosLivros() {
        try {
            List<Livro> livros = iLivroService.listarTodosLivros();
            if (livros.isEmpty()) {
                iLivroView.exibirErro("Nenhum livro encontrado");
            } else {
                iLivroView.exibirListaLivros(livros);
            }
        } catch (Exception e) {
            iLivroView.exibirErro("Erro ao listar livros: " + e.getMessage());
        }
    }

    private ILivroService carregarService() {
        ServiceLoader<ILivroService> loader = ServiceLoader.load(ILivroService.class);
        return loader.findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma implementação de ILivroService encontrada"));
    }
}