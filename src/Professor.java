import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class Professor extends UsuarioAbstrato implements Observador, RegraConsultaDeInformacoesDeUmUsuarioAbstrato
{
    int contadorDeNotificacoesRecebidas;

    public Professor(String codigoDoUsuario, String nome)
    {
        super.codigoDoUsuario = codigoDoUsuario;
        super.nome = nome;
        super.tempoLimiteDeEmprestimo = 8;
        this.contadorDeNotificacoesRecebidas = 0;
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
            mensagemDeRetorno = String.format("\nEmpréstimo do livro %s realizado para o usuário %s.\n", livro.getTitulo() ,super.getNome());
            return mensagemDeRetorno;
        }
        else
        {
            mensagemDeRetorno = String.format("\nNão foi possível realizar o empréstimo do livro %s para o usuário %s.\n", livro.getTitulo() ,super.getNome());
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
            repositorio.inativarEmprestimo(this.getCodigo(), livro.getCodigo());
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
        RegraEmprestimoAbstratoProfessor verificadorDeDisponibilidade = new RegraEmprestimoProfessor();
        
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

    public void notificar(Livro livro)
    {
        System.out.println(String.format("\n[NOTIFICAÇÃO]: Atenção, Professor %s, o livro %s possui mais de duas reservas \n", super.getNome(), livro.getTitulo()));
        this.contadorDeNotificacoesRecebidas++;
    }

    public String obterQuantidadeDeNotificacoes()
    {
        String mensagemDeRetorno = String.format("\nA quantidade de notificações recebidas pelo professor %s é %d\n", super.getNome(), this.getContadorDeNotificacoesRecebidas());
        return mensagemDeRetorno;
    }
    
    public int getContadorDeNotificacoesRecebidas()
    {
        return this.contadorDeNotificacoesRecebidas;
    }

    @Override
    public String consultarInformacoesDoUsuario(String codigoDoUsuario)
    {
        String mensagemDeRetorno = "";
        int contador = 0;
        Repositorio repositorio = Repositorio.obterInstancia();
        List<Emprestimo> emprestimosDoUsuario = repositorio.ObterListaDeEmprestimosDeUmUsuario(codigoDoUsuario);
        if (emprestimosDoUsuario != null)
        {
            mensagemDeRetorno += "* Histórico de empréstimos do usuário: \n";
            for(Emprestimo emprestimo : emprestimosDoUsuario)
            {
                if(contador > 0)
                    mensagemDeRetorno += "\n";
                Livro livro = repositorio.obterLivroPorCodigo(emprestimo.getCodigoDoLivro());
                String nomeDoLivro = livro.getTitulo();
                mensagemDeRetorno += "- Nome Do Livro: " + nomeDoLivro + "\n";
                LocalDate dataDeEmprestimo = emprestimo.getDataInicio().toLocalDate();
                mensagemDeRetorno += "- Data de realização do empréstimo: " + dataDeEmprestimo + "\n";
                String statusDoEmprestimo;

                if(emprestimo.getEmprestimoEmAberto())
                {
                    statusDoEmprestimo = "Em curso";
                    mensagemDeRetorno += "- Status do Empréstimo: " + statusDoEmprestimo + "\n";
                    LocalDate dataPrevistaDeDevolucao = dataDeEmprestimo.plusDays(super.getTempoLimiteDeEmprestimo());
                    mensagemDeRetorno += "- Data prevista para devolução: " + dataPrevistaDeDevolucao + "\n";
                }
                else
                {
                    statusDoEmprestimo = "Finalizado";
                    mensagemDeRetorno += "- Status do Empréstimo: " + statusDoEmprestimo + "\n";
                    LocalDate dataDeDevolucao = emprestimo.getDataFim().toLocalDate();
                    mensagemDeRetorno += "- Data de devolução: " + dataDeDevolucao + "\n";
                }
                contador++;
            }
        }
        List<Reserva> totalDeReservas = repositorio.obterOTotalDeReservasDeUmUsuario(this);
        mensagemDeRetorno += "\n";
        if(totalDeReservas != null)
        {
            mensagemDeRetorno += "* Histórico de Reservas do usuário:\n";
            for(Reserva reserva : totalDeReservas)
            {
                Livro livroReservado = repositorio.obterLivroPorCodigo(reserva.getCodigoDoLivro());
                String nomeDoLivroReservado = livroReservado.getTitulo();
                LocalDate dataDaReserva = reserva.getDataDaReserva().toLocalDate();
                mensagemDeRetorno += "- Título: " + nomeDoLivroReservado + " ; " + "Data Da Reserva: " + dataDaReserva + "\n"; 
            }
        }
        return mensagemDeRetorno;
    }
}
