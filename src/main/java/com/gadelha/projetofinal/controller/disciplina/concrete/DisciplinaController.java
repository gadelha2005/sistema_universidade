package com.gadelha.projetofinal.controller.disciplina.concrete;

import java.util.List;
import java.util.ServiceLoader;


import com.gadelha.projetofinal.controller.disciplina.interfaces.IDisciplinaController;
import com.gadelha.projetofinal.model.disciplina.Disciplina;
import com.gadelha.projetofinal.service.disciplina.interfaces.IDisciplinaService;
import com.gadelha.projetofinal.view.disciplina.interfaces.IDisciplinaView;

public class DisciplinaController implements IDisciplinaController {
    
    private final IDisciplinaView iDisciplinaView;
    private final IDisciplinaService iDisciplinaService;

    public DisciplinaController(IDisciplinaView iDisciplinaView) {
        this.iDisciplinaView = iDisciplinaView;
        this.iDisciplinaService = carregarService();
    }

    @Override
    public void consultarDisciplinaPorId(int id) {
        try {
            var disciplinaOptional = iDisciplinaService.consultarDisciplinaPorId(id);
            if (disciplinaOptional.isPresent()) {
                var disciplina = disciplinaOptional.get();
                iDisciplinaView.exibirDetalhesDisciplina(
                    disciplina.getId(), 
                    disciplina.getNome(), 
                    disciplina.getCurso(), 
                    disciplina.getVagas()
                );
            } else {
                iDisciplinaView.exibirErro("Disciplina com ID " + id + " não encontrada");
            }
        } catch (Exception e) {
            iDisciplinaView.exibirErro("Erro ao consultar disciplina: " + e.getMessage());
        }
    }

    @Override
    public void consultarDisciplinasPorCurso(String curso) {
        try {
            var disciplinasOptional = iDisciplinaService.consultarDisciplinasPorCurso(curso);

            if (disciplinasOptional.isPresent()) {
                var disciplinas = disciplinasOptional.get();

                if (disciplinas.isEmpty()) {
                    iDisciplinaView.exibirErro("Nenhuma disciplina encontrada para o curso: " + curso);
                } else {
                    iDisciplinaView.exibirListaDisciplinas(disciplinas);
                }
            } else {
                iDisciplinaView.exibirErro("Nenhuma disciplina encontrada para o curso: " + curso);
            }
        } catch (Exception e) {
            iDisciplinaView.exibirErro("Erro ao consultar disciplinas do curso " + curso + ": " + e.getMessage());
        }
    }

    @Override
    public void listarTodasDisciplinas() {
        try {
            List<Disciplina> disciplinas = iDisciplinaService.listarTodasDisciplinas();
            if (disciplinas.isEmpty()) {
                iDisciplinaView.exibirErro("Nenhuma disciplina encontrada");
            } else {
                iDisciplinaView.exibirListaDisciplinas(disciplinas);
            }
        } catch (Exception e) {
            iDisciplinaView.exibirErro("Erro ao listar disciplinas: " + e.getMessage());
        }
    }

    private IDisciplinaService carregarService() {
        ServiceLoader<IDisciplinaService> loader = ServiceLoader.load(IDisciplinaService.class);
        return loader.findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma implementação de IDisciplinaService encontrada"));
    }
}