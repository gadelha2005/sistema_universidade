package com.gadelha.projetofinal.repository.interfaces;

public interface IRepository<T> extends IReadRepository<T> {
    void salvar(T entity);
    void atualizar(T entity);
    void remover(int id);
}