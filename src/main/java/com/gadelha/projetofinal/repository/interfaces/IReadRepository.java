package com.gadelha.projetofinal.repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface IReadRepository<T> {
    Optional<T> buscarPorId(int id);
    List<T> listarTodos();
}