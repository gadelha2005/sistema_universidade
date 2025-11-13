package com.gadelha.projetofinal.gateway.interfaces;

import java.util.List;

public interface IGateway<T> {
    List<T> carregarDadosDoServicoExterno();
}
