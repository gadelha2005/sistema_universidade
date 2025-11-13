package com.gadelha.projetofinal.controller.aluno.concrete;

import java.util.List;
import java.util.ServiceLoader;

import com.gadelha.projetofinal.config.RepositoryInitializer;
import com.gadelha.projetofinal.controller.aluno.interfaces.IAlunoController;
import com.gadelha.projetofinal.service.aluno.interfaces.IAlunoService;
import com.gadelha.projetofinal.view.aluno.interfaces.IAlunoView;
import com.gadelha.projetofinal.model.aluno.Aluno;

public class AlunoController implements IAlunoController {
    
    private final IAlunoView iAlunoView;
    private final IAlunoService iAlunoService;

    public AlunoController(IAlunoView visao) {
        this.iAlunoView = visao;
        RepositoryInitializer.garantirInicializado();
        this.iAlunoService = carregarService();
    }
 
    @Override
    public void consultarAlunoPorId(int id) {
        try {
            var alunoOptional = iAlunoService.consultarAlunoPorId(id);
            if (alunoOptional.isPresent()) {
                var aluno = alunoOptional.get();
                iAlunoView.exibirDetalhesAluno(aluno.getId(), aluno.getNome(), aluno.getCurso(), aluno.getModalidade(), aluno.getStatus());
            } else {
                iAlunoView.exibirErro("Aluno com ID " + id + " não encontrado");
            }
        } catch (Exception e) {
            iAlunoView.exibirErro("Erro ao consultar aluno: " + e.getMessage());
        }
    }

    @Override
    public void listarTodosAlunos() {
        try {
            List<Aluno> alunos = iAlunoService.listarTodosAlunos();
            if (alunos.isEmpty()) {
                iAlunoView.exibirErro("Nenhum aluno encontrado");
            } else {
                iAlunoView.exibirListaAlunos(alunos);
            }
        } catch (Exception e) {
            iAlunoView.exibirErro("Erro ao listar alunos: " + e.getMessage());
        }
    }

    private IAlunoService carregarService() {
        ServiceLoader<IAlunoService> loader = ServiceLoader.load(IAlunoService.class);
        return loader.findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhuma implementação de IAlunoService encontrada"));
    }
}