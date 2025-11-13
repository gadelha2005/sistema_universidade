package com.gadelha.projetofinal.config;

import java.util.List;
import com.gadelha.projetofinal.gateway.interfaces.IGateway;

public class DataLoader {
    
    private DataLoader() {
    }
    
    public static <T> List<T> carregarDados(IGateway<T> gateway) {
        if (gateway == null) {
            throw new IllegalArgumentException("Gateway não pode ser nulo");
        }
        
        try {
            return gateway.carregarDadosDoServicoExterno();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar dados do gateway: " + e.getMessage(), e);
        }
    }
}