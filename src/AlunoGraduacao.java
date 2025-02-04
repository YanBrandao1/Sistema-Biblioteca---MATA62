import java.time.LocalDateTime;

public class AlunoGraduacao extends UsuarioAbstrato
{
    protected int quantidadeLimiteDeEmprestimos;
    protected int quantidadeDeLivrosEmEmprestimo;

    
    public AlunoGraduacao(String codigoDoUsuario, String nome)
    {
        super.codigoDoUsuario = codigoDoUsuario;
        super.nome = nome;
        super.tempoLimiteDeEmprestimo = 4;
        quantidadeLimiteDeEmprestimos = 2;
    }


    @Override
    public String emprestar(Livro livro)
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoAluno verificadorDeDisponibilidade = new RegraEmprestimoAlunoGraduacao();
        
        boolean disponivel = verificadorDeDisponibilidade.podeEmprestar(this, livro);
        
        if (disponivel)
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            Emprestimo emprestimo = new Emprestimo(this.getCodigo(), livro.getCodigo(), LocalDateTime.now());
            repositorio.ObterListaDeEmprestimos().add(emprestimo);
            super.livrosEmEmprestimo.add(livro);
            repositorio.removerReservaDaLista(super.codigoDoUsuario, livro.getCodigo());
            livro.obterExemplarDisponivel().indisponibilizarExemplar();
            mensagemDeRetorno = String.format("\nEmpréstimo do livro %s realizado para o usuário %s.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else
        {
            mensagemDeRetorno = String.format("\nNão foi possível realizar o empréstimo do livro %s para o usuário %s.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }

    @Override
    public String devolver(Livro livro)
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoAluno verificadorDeDisponibilidade = new RegraEmprestimoAlunoGraduacao();
        boolean disponivel = verificadorDeDisponibilidade.podeDevolver(this, livro);
        
        if (disponivel) 
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            repositorio.removerEmprestimoDaLista(this.getCodigo(), livro.getCodigo());
            super.livrosEmEmprestimo.remove(livro);
            livro.obterExemplarIndisponivel().disponibilizarExemplar();
            mensagemDeRetorno = String.format("\nDevolução do livro %s realizada com sucesso para o usuário %s.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else
        {
            mensagemDeRetorno = String.format("\nNão foi possível realizar a devolução do livro %s para o usuário %s, pois não há empréstimos em aberto.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }

    @Override
    public String reservar(Livro livro)
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoAluno verificadorDeDisponibilidade = new RegraEmprestimoAlunoGraduacao();
        
        boolean disponivel = verificadorDeDisponibilidade.podeReservar(this, livro);

        if (disponivel)
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            Reserva reserva = Fabrica.criarReserva(this, livro);
            repositorio.adicionarReserva(reserva);
            mensagemDeRetorno = String.format("\nReserva do livro %s realizada com sucesso para o usuário %s.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else{
            mensagemDeRetorno = String.format("\nNão foi possível realizar a reserva do livro %s para o usuário %s, pois o usuário já excedeu o limite máximo de reservas.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }

    public String getCodigo()
    {
        return super.codigoDoUsuario;
    }

    public boolean abaixoLimiteDeEmprestimos()
    {
        for (Livro livro : livrosEmEmprestimo)
        {
            quantidadeDeLivrosEmEmprestimo ++;
        }
        if (quantidadeDeLivrosEmEmprestimo == quantidadeLimiteDeEmprestimos)
            return false;

        return true;
    }
   
}
