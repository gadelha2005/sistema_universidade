package com.gadelha.projetofinal.service.biblioteca.interfaces;

import java.util.List;
import java.util.Optional;

import com.gadelha.projetofinal.model.biblioteca.Livro;

public interface ILivroService {
    Optional<Livro> consultarLivroPorId(int id);
    List<Livro> listarTodosLivros();
}