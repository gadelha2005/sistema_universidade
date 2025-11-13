// LivroService.java (refatorada)
package com.gadelha.projetofinal.service.biblioteca.concrete;

import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.config.RepositoryRegistry;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;
import com.gadelha.projetofinal.service.biblioteca.interfaces.ILivroService;

public class LivroService implements ILivroService {
    
    private final IReadRepository<Livro> livroRepository;
    
    public LivroService() {
        this.livroRepository = RepositoryRegistry.getLivroRepository();
    }
    
    @Override
    public Optional<Livro> consultarLivroPorId(int id) {
        try {
            return livroRepository.buscarPorId(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Livro> listarTodosLivros() {
        try {
            return livroRepository.listarTodos();
        } catch (Exception e) {
            return List.of();
        }
    }

}