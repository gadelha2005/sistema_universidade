package com.gadelha.projetofinal.service.emprestimo.concrete;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.gadelha.projetofinal.config.RepositoryRegistry;
import com.gadelha.projetofinal.model.aluno.Aluno;
import com.gadelha.projetofinal.model.biblioteca.Livro;
import com.gadelha.projetofinal.model.emprestimo.Emprestimo;
import com.gadelha.projetofinal.repository.interfaces.IRepository;
import com.gadelha.projetofinal.repository.interfaces.IReadRepository;
import com.gadelha.projetofinal.repository.biblioteca.concrete.LivroRepository;
import com.gadelha.projetofinal.service.emprestimo.interfaces.IEmprestimoService;

public class EmprestimoService implements IEmprestimoService {

    private final IRepository<Emprestimo> emprestimoRepository;
    private final IReadRepository<Aluno> alunoRepository;
    private final IReadRepository<Livro> livroRepository;
    private static final int PRAZO_EMPRESTIMO_DIAS = 15;
    private static final int LIMITE_EMPRESTIMOS_ATIVOS = 3;

    public EmprestimoService() {
        this.emprestimoRepository = RepositoryRegistry.getEmprestimoRepository();
        this.alunoRepository = RepositoryRegistry.getAlunoRepository();
        this.livroRepository = RepositoryRegistry.getLivroRepository();
    }

    @Override
    public Emprestimo realizarEmprestimo(int alunoId, int livroId) {
        Aluno aluno = alunoRepository.buscarPorId(alunoId)
            .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado com ID: " + alunoId));
        
        Livro livro = livroRepository.buscarPorId(livroId)
            .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado com ID: " + livroId));
        
        validarEmprestimo(aluno, livro);
        
        LocalDate dataEmprestimo = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataEmprestimo.plusDays(PRAZO_EMPRESTIMO_DIAS);
        
        Emprestimo emprestimo = new Emprestimo(0, aluno, livro, dataEmprestimo, dataDevolucaoPrevista);
        
        emprestimoRepository.salvar(emprestimo);
        
        livro.setStatus("INDISPONIVEL");
        atualizarLivro(livro);
        
        return emprestimo;
    }

    @Override
    public void devolverLivro(int emprestimoId) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.buscarPorId(emprestimoId);
        if (emprestimoOptional.isEmpty()) {
            throw new IllegalArgumentException("Empréstimo não encontrado com ID: " + emprestimoId);
        }
        
        Emprestimo emprestimo = emprestimoOptional.get();
        
        if (!emprestimo.estaAtivo()) {
            throw new IllegalStateException("Este empréstimo já foi devolvido");
        }
        
        emprestimo.devolver();
        emprestimoRepository.atualizar(emprestimo);
        
        Livro livro = emprestimo.getLivro();
        livro.setStatus("DISPONIVEL");
        atualizarLivro(livro);
    }

    @Override
    public List<Emprestimo> listarEmprestimosAtivosPorAluno(int alunoId) {
        return emprestimoRepository.listarTodos().stream()
            .filter(emprestimo -> emprestimo.getAluno().getId() == alunoId && emprestimo.estaAtivo())
            .toList();
    }

    @Override
    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimoRepository.listarTodos().stream()
            .filter(Emprestimo::estaAtrasado)
            .toList();
    }

    @Override
    public List<Emprestimo> listarTodosEmprestimos() {
        return emprestimoRepository.listarTodos();
    }

    @Override
    public int contarEmprestimosAtivosPorAluno(int alunoId) {
        return listarEmprestimosAtivosPorAluno(alunoId).size();
    }

    private void atualizarLivro(Livro livro) {
        if (livroRepository instanceof LivroRepository) {
            LivroRepository repoEspecifico = (LivroRepository) livroRepository;
            repoEspecifico.atualizar(livro);
        }
    }

    private void validarEmprestimo(Aluno aluno, Livro livro) {

        System.out.println("DEBUG - Validando empréstimo:");
        System.out.println("DEBUG - Aluno ID: " + aluno.getId() + ", Ativo: " + aluno.estaAtivo());
        System.out.println("DEBUG - Livro ID: " + livro.getId() + ", Título: " + livro.getTitulo());
        System.out.println("DEBUG - Status do livro: " + livro.getStatus());
        System.out.println("DEBUG - Livro disponível: " + livro.estaDisponivel());
        
        if (!aluno.estaAtivo()) {
            throw new IllegalStateException("Aluno com matrícula trancada não pode realizar empréstimos");
        }
        
        
        if (!livro.estaDisponivel()) {
            throw new IllegalStateException("Livro não está disponível para empréstimo");
        }
        
        
        int emprestimosAtivos = contarEmprestimosAtivosPorAluno(aluno.getId());
        if (emprestimosAtivos >= LIMITE_EMPRESTIMOS_ATIVOS) {
            throw new IllegalStateException(
                "Aluno já possui " + emprestimosAtivos + " empréstimos ativos. Limite é " + LIMITE_EMPRESTIMOS_ATIVOS + ".");
        }
        
        boolean temAtrasos = listarEmprestimosAtivosPorAluno(aluno.getId()).stream()
            .anyMatch(Emprestimo::estaAtrasado);
            
        if (temAtrasos) {
            throw new IllegalStateException("Aluno possui empréstimos em atraso. Regularize antes de novos empréstimos.");
        }
        
        boolean livroJaEmprestado = emprestimoRepository.listarTodos().stream()
            .anyMatch(e -> e.getLivro().getId() == livro.getId() && e.estaAtivo());
            
        if (livroJaEmprestado) {
            throw new IllegalStateException("Livro já está emprestado para outro aluno");
        }
    }
}