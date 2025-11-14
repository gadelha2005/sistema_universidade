package com.gadelha.projetofinal.controller.matricula.concrete;

import java.util.List;
import java.util.ServiceLoader;
import com.gadelha.projetofinal.controller.matricula.interfaces.IMatriculaController;
import com.gadelha.projetofinal.model.matricula.Matricula;
import com.gadelha.projetofinal.service.matricula.interfaces.IMatriculaService;
import com.gadelha.projetofinal.view.matricula.interfaces.IMatriculaView;

public class MatriculaController implements IMatriculaController{

    private final IMatriculaService iMatriculaSimuladaService;
    private final IMatriculaView iMatriculaSimuladaView;

    public MatriculaController(IMatriculaView iMatriculaSimuladaView){
        this.iMatriculaSimuladaView = iMatriculaSimuladaView;
        this.iMatriculaSimuladaService = carregarServico();
    }

    @Override
    public Matricula matricularAluno(int alunoId, int disciplinaId) {
        try {
            Matricula matricula = iMatriculaSimuladaService.matricularAluno(alunoId, disciplinaId);
            iMatriculaSimuladaView.exibirMensagem("Matrícula realizada com sucesso!");
            iMatriculaSimuladaView.exibirDetalhesMatricula(matricula);
            return matricula;
        } catch (Exception e) {
            iMatriculaSimuladaView.exibirErro("Erro ao matricular aluno: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public void cancelarMatricula(int matriculaId) {
        try {
            iMatriculaSimuladaService.cancelarMatricula(matriculaId);
            iMatriculaSimuladaView.exibirMensagem("Matrícula cancelada com sucesso!");
        } catch (Exception e) {
            iMatriculaSimuladaView.exibirErro("Erro ao cancelar matrícula: " + e.getMessage());
        }
    }
    
    @Override
    public List<Matricula> listarMatriculasAtivasPorAluno(int alunoId) {
        try {
            List<Matricula> matriculas = iMatriculaSimuladaService.listarMatriculasAtivasPorAluno(alunoId);
            iMatriculaSimuladaView.exibirListaMatriculas(matriculas);
            return matriculas;
        } catch (Exception e) {
            iMatriculaSimuladaView.exibirErro("Erro ao listar matrículas: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public List<Matricula> listarTodasMatriculas() {
        try {
            List<Matricula> matriculas = iMatriculaSimuladaService.listarTodasMatriculas();
            iMatriculaSimuladaView.exibirListaMatriculas(matriculas);
            return matriculas;
        } catch (Exception e) {
            iMatriculaSimuladaView.exibirErro("Erro ao listar matrículas: " + e.getMessage());
            return List.of();
        }
    }
    
    @Override
    public int contarMatriculasAtivasPorAluno(int alunoId) {
        try {
            return iMatriculaSimuladaService.contarMatricularAtivasPorAluno(alunoId);
        } catch (Exception e) {
            iMatriculaSimuladaView.exibirErro("Erro ao contar matrículas: " + e.getMessage());
            return 0;
        }
    }

    private IMatriculaService carregarServico() {
        ServiceLoader<IMatriculaService> loader = ServiceLoader.load(IMatriculaService.class);
        return loader.findFirst()
            .orElseThrow(() -> new RuntimeException("Serviço de matrícula simulada não encontrado"));
    }
}