import java.time.LocalDateTime;


public class Professor extends UsuarioAbstrato
{

    public Professor(String codigoDoUsuario, String nome)
    {
        super.codigoDoUsuario = codigoDoUsuario;
        super.nome = nome;
        super.tempoLimiteDeEmprestimo = 8;
    }

    @Override
    public String emprestar(Livro livro) 
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoProfessor verificadorDeDisponibilidade = new RegraEmprestimoProfessor();
        boolean disponivel = verificadorDeDisponibilidade.podeEmprestar(this, livro);
        
        if (disponivel)
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            Emprestimo emprestimo = new Emprestimo(this.getCodigo(), livro.getCodigo(), LocalDateTime.now());
            repositorio.ObterListaDeEmprestimos().add(emprestimo);
            super.livrosEmEmprestimo.add(livro);
            repositorio.removerReservaDaLista(super.codigoDoUsuario, livro.getCodigo());
            livro.obterExemplarDisponivel().indisponibilizarExemplar();
            mensagemDeRetorno = String.format("Empréstimo do livro %s realizado para o usuário %s", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else
        {
            mensagemDeRetorno = String.format("Não foi possível realizar o empréstimo do livro %s para o usuário %s", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }
    
    public String devolver(Livro livro)
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoProfessor verificadorDeDisponibilidade = new RegraEmprestimoProfessor();
        boolean disponivel = verificadorDeDisponibilidade.podeDevolver(this, livro);
        
        if (disponivel) 
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            repositorio.removerEmprestimoDaLista(this.getCodigo(), livro.getCodigo());
            super.livrosEmEmprestimo.remove(livro);
            livro.obterExemplarIndisponivel().disponibilizarExemplar();
            mensagemDeRetorno = String.format("Devolução do livro %s realizada com sucesso para o usuário %s", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else
        {
            mensagemDeRetorno = String.format("Não foi possível realizar a devolução do livro %s para o usuário %s, pois não há empréstimos em aberto", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }

    @Override
    public String reservar(Livro livro)
    {
        String mensagemDeRetorno;
        RegraEmprestimoAbstratoProfessor verificadorDeDisponibilidade = new RegraEmprestimoProfessor();
        
        boolean disponivel = verificadorDeDisponibilidade.podeReservar(this, livro);

        if (disponivel)
        {
            Repositorio repositorio = Repositorio.obterInstancia();
            Reserva reserva = Fabrica.criarReserva(this, livro);
            repositorio.adicionarReserva(reserva);
            mensagemDeRetorno = String.format("Reserva do livro %s realizada com sucesso para o usuário %s", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else{
            mensagemDeRetorno = String.format("Não foi possível realizar a reserva do livro %s para o usuário %s, pois o usuário já excedeu o limite máximo de reservas", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
    }
}
