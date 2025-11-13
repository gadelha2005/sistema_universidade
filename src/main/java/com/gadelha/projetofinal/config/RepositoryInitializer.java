package com.gadelha.projetofinal.config;

public class RepositoryInitializer {
    private static volatile boolean inicializado = false;
    private static final Object lock = new Object();

    public static void garantirInicializado() {
        if (!inicializado) {
            synchronized (lock) {
                if (!inicializado) {
                    DataInitializer.inicializarRepositorios();
                    inicializado = true;
                }
            }
        }
    }

    public static boolean estaInicializado() {
        return inicializado;
    }

    public static void reinicializar() {
        synchronized (lock) {
            inicializado = false;
        }
    }
}