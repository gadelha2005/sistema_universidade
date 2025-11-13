package com.gadelha.projetofinal.gateway.interfaces;

import java.util.List;

public interface IJsonParser<T> {
    List<T> parseJsonParaLista(String json);
    T parseJsonParaObjeto(String json);
}
