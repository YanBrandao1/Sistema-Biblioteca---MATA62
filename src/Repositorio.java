
// Utilização do padrão Singleton para implementar a classe em que vão permanecer os dados de usuários e livros

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Repositorio
{
    private List<UsuarioAbstrato> usuarios = new ArrayList<UsuarioAbstrato>();
	private List<Livro> livros = new ArrayList<Livro>();
    private List<Reserva> reservas = new ArrayList<Reserva>();
    private List<Reserva> reservasInativas = new ArrayList<Reserva>();
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();
                                                
    private static Repositorio instancia;
    
    private Repositorio()
    {
        // Construtor vazio
    }

    public static Repositorio obterInstancia()
    {
        if (instancia == null)
            instancia = new Repositorio();
        
        return instancia;
    }

    public UsuarioAbstrato obterUsuarioPorCodigo(String codigoDoUsuario)
    {
        for (UsuarioAbstrato usuario : usuarios)
        {
            if (usuario.getCodigo().equals(codigoDoUsuario))
                return usuario;
        }
        return null;
    }

    public Livro obterLivroPorCodigo(String codigoDoLivro)
    {
        for (Livro livro : livros)
        {
            if (livro.getCodigo().equals(codigoDoLivro))
                return livro;
        }
        return null;
    }

    public List<Reserva> obterReservasDeUmLivro(String codigoDoLivro)
    {
        List<Reserva> reservasDoLivro = new ArrayList<>();

        for(Reserva reserva : reservas)
        {
            if (reserva.getCodigoDoLivro().equals(codigoDoLivro))
            {
                reservasDoLivro.add(reserva);
            }
        }
        return reservasDoLivro;
    }

    public List<Emprestimo> ObterListaDeEmprestimos()
    {
        return emprestimos;
    }

    public void removerReservaDaLista(String codigoDoUsuario, String codigoDoLivro)
    {
        Iterator<Reserva> iterator = reservas.iterator();
        
        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            
            if (reserva.getCodigoDoUsuario().equals(codigoDoUsuario) && 
                reserva.getCodigoDoLivro().equals(codigoDoLivro)) {
                
                reserva.cancelarReserva();
                iterator.remove();
                this.adicionarReservaInativa(reserva);
            }
        }
    }

    public void inativarEmprestimo(String codigoDoUsuario, String codigoDoLivro)
    {
        Iterator<Emprestimo> iterator = emprestimos.iterator();
        
        while (iterator.hasNext()) {
            Emprestimo emprestimo = iterator.next();
            
            if (emprestimo.getCodigoDoUsuario().equals(codigoDoUsuario) && 
                emprestimo.getCodigoDoLivro().equals(codigoDoLivro)) {
                
                if (emprestimo.getEmprestimoEmAberto()) {
                    emprestimo.cancelarEmprestimo();

                }
            }
        }
    }

    public void adicionarUsuarioNaBase(UsuarioAbstrato usuario)
    {
        usuarios.add(usuario);
    }

    public void adicionarLivroNaBase(Livro livro)
    {
        livros.add(livro);
    }

    public int obterQuantidadeDeReservasDeUmUsuario(UsuarioAbstrato usuario)
    {
        int quantidadeDeReservas = 0;
        for (Reserva reserva : reservas)
        {
            if (reserva.getCodigoDoUsuario().equals(usuario.getCodigo()))
            {
                quantidadeDeReservas++;
            }
        }
        return quantidadeDeReservas;
    }

    public int obterQuantidadeDeReservasDeUmLivro(Livro livro)
    {
        int quantidadeDeReservas = 0;
        for (Reserva reserva : reservas)
        {
            if (reserva.getCodigoDoLivro().equals(livro.getCodigo()))
            {
                quantidadeDeReservas++;
            }
        }
        return quantidadeDeReservas;
    }

    public void adicionarReserva(Reserva reserva)
    {
        reservas.add(reserva);
        this.verificarQuantidadeDeReservasDeUmLivro(reserva);
    }

    public void verificarQuantidadeDeReservasDeUmLivro(Reserva reserva)
    {
        String codigoDoLivroReservado = reserva.getCodigoDoLivro();
        Livro livroReservado = obterLivroPorCodigo(codigoDoLivroReservado);
        int quantidadeDeReservarDoLivro = this.obterQuantidadeDeReservasDeUmLivro(livroReservado);

        if (quantidadeDeReservarDoLivro > 2)
        {
            livroReservado.notificarObservadores();
        }
    }

    public UsuarioAbstrato obterUsuarioQuePegouLivroEmprestado(String codigoDoLivro)
    {
        for(Emprestimo emprestimo : emprestimos)
        {
            if (emprestimo.getCodigoDoLivro().equals(codigoDoLivro))
            {
                UsuarioAbstrato usuarioQuePegouEmprestado = this.obterUsuarioPorCodigo(emprestimo.getCodigoDoUsuario());
                return usuarioQuePegouEmprestado;
            }
        }
        return null;
    }

    public Emprestimo obterEmprestimo(UsuarioAbstrato usuario, Livro livro)
    {
        for(Emprestimo emprestimo : emprestimos)
        {
            if(emprestimo.getCodigoDoUsuario().equals(usuario.getCodigo()) && emprestimo.getCodigoDoLivro().equals(livro.getCodigo()))
                return emprestimo;
        }
        return null;
    }

    public Reserva obterReservaDeUmUsuarioParaUmLivro(UsuarioAbstrato usuario, Livro livro)
    {
        for(Reserva reserva : reservas)
        {
            if(reserva.getCodigoDoUsuario().equals(usuario.getCodigo()))
                return reserva;
        }
        return null;
    }

    public void adicionarReservaInativa(Reserva reservaInativa)
    {
        reservasInativas.add(reservaInativa);
    }

    public List<Emprestimo> ObterListaDeEmprestimosDeUmUsuario(String codigoDoUsuario)
    {
        List<Emprestimo> emprestimosDoUsuario = new ArrayList<Emprestimo>();
        for (Emprestimo emprestimo : emprestimos)
        {
            if (emprestimo.getCodigoDoUsuario().equals(codigoDoUsuario))
            {
             emprestimosDoUsuario.add(emprestimo);
            }
        }
        return emprestimosDoUsuario;
    }

    public List<Reserva> obterOTotalDeReservasDeUmUsuario(UsuarioAbstrato usuario)
    {
        List<Reserva> totalDeReservas =  new ArrayList<Reserva>(); // soma entre reservas ativas e inativas

        for(Reserva reserva : reservas)
        {
            if(reserva.getCodigoDoUsuario().equals(usuario.getCodigo()))
                totalDeReservas.add(reserva);
        }
        
        for(Reserva reserva : reservasInativas)
        {
            if(reserva.getCodigoDoUsuario().equals(usuario.getCodigo()))
                totalDeReservas.add(reserva);
        }

        return totalDeReservas;
    }
}
