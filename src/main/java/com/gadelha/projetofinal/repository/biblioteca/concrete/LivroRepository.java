// LivroRepository.java
package com.gadelha.projetofinal.repository.biblioteca.concrete;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;

public class LivroRepository implements IReadRepository<Livro> {
    private List<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    public void carregarDados(List<Livro> dadosLivros) {
        this.livros = new ArrayList<>(dadosLivros);
    }

    @Override
    public Optional<Livro> buscarPorId(int id) {
        return livros.stream()
                .filter(livro -> livro.getId() == id)
                .findFirst();
    }

    @Override
    public List<Livro> listarTodos() {
        return new ArrayList<>(livros);
    }

    
    public List<Livro> buscarPorTitulo(String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    public List<Livro> buscarPorAutor(String autor) {
        return livros.stream()
                .filter(livro -> livro.getAutor().toLowerCase().contains(autor.toLowerCase()))
                .toList();
    }

    public void atualizar(Livro livro) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == livro.getId()) {
                livros.set(i, livro);
                break;
            }
        }
    }
}