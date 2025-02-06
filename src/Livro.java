import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Livro implements Sujeito, RegraConsultaDeInformacoesDeUmLivroAbstrato
{
    private String codigo;
    private String titulo;
    private String editora;
    private String autores;
    private String edicao;
    private String anoPublicacao;
    private List<Exemplar> exemplares = new ArrayList<Exemplar>();
    private int quantidadeDeExemplares;
    private List<Observador> listaDeObservadores = new ArrayList<Observador>();
   

    public Livro(String codigo, String titulo, String editora, String autores, String edicao, String anoPublicacao)
    {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.autores = autores;
        this.edicao = edicao;
        this.anoPublicacao = anoPublicacao;
        quantidadeDeExemplares = 0;
    }

    @Override
    public String registrarObservador(Observador observador)
    {
        this.listaDeObservadores.add(observador);
        Professor observadorCasting = (Professor) observador;
        String mensagemDeRetorno = String.format("\nObservador %s registrado para o livro %s.\n", observadorCasting.getNome(), this.getTitulo());
        return mensagemDeRetorno; 
    }

    @Override
    public void notificarObservadores()
    {
        if(this.listaDeObservadores != null)
            this.listaDeObservadores.forEach(observador -> observador.notificar(this));
    }

    public String getCodigo()
    {
        return codigo;
    }

    public boolean temExemplarDisponivel()
    {
        for (Exemplar exemplar : exemplares)
        {
            if (exemplar.isDisponivel())
                return true;
        }
        return false;
    }

    public int quantidadeDeExemplares()
    {
        for (Exemplar exemplar : exemplares)
        {
            quantidadeDeExemplares++;
        }
        return quantidadeDeExemplares;
    }
   
    public Exemplar obterExemplarDisponivel()
    {
        for (Exemplar exemplar : exemplares)
        {
            if (exemplar.isDisponivel())
                return exemplar;
        }
        return null;
    }

    public String getTitulo()
    {
        return this.titulo;
    }

    public void adicionarExemplar(Exemplar exemplar)
    {
        exemplares.add(exemplar);
    }

    public Exemplar obterExemplarIndisponivel()
    {
        for (Exemplar exemplar : exemplares)
        {
            if (!exemplar.isDisponivel())
                return exemplar;
        }
        return null;
    }

    public List<Exemplar> obterListaDeExemplares()
    {
        return this.exemplares;
    }

    @Override
    public String consultarInformacoesDoLivro(String codigoDoLivro)
    {
        Repositorio repositorio = Repositorio.obterInstancia();
        String mensagemDeRetorno = "* Informações sobre o livro: \n";
        mensagemDeRetorno += "- Título: ";
        String nomeDoLivro = this.getTitulo();
        mensagemDeRetorno += nomeDoLivro + "\n";
        int quantidadeDeReservasDoLivro = repositorio.obterQuantidadeDeReservasDeUmLivro(this);
        mensagemDeRetorno += "- Quantidade de reservas: " + quantidadeDeReservasDoLivro + "\n";
        List<Reserva> reservasDoLivro = repositorio.obterReservasDeUmLivro(codigoDoLivro);

        if (quantidadeDeReservasDoLivro != 0)
        {
            mensagemDeRetorno += "- Usuários que realizaram as reservas: \n";
            for(Reserva reserva : reservasDoLivro)
            {
                String codigoDoUsuarioQueReservou = reserva.getCodigoDoUsuario();
                UsuarioAbstrato usuarioQueReservou = repositorio.obterUsuarioPorCodigo(codigoDoUsuarioQueReservou);
                String nomeDoUsuarioQueReservou = usuarioQueReservou.getNome();
                mensagemDeRetorno += "      - "+ nomeDoUsuarioQueReservou + "\n";
            }
        }

        List<Exemplar> listaDeExemplares = this.obterListaDeExemplares();

        if (listaDeExemplares != null)
        {
            mensagemDeRetorno += "- Lista de Exemplares: \n";
            for (Exemplar exemplar : listaDeExemplares)
            {
                String codigoDoExemplar = exemplar.getCodigoExemplar();
                boolean disponibilidadeDoExemplar = exemplar.isDisponivel();
                String statusDoExemplar;
                if (disponibilidadeDoExemplar)
                    statusDoExemplar = "Disponível";
                else
                    statusDoExemplar = "Indisponível";
                mensagemDeRetorno += "  - Código do exemplar: " + codigoDoExemplar +" ; Status do Exemplar: " + statusDoExemplar + "\n";

                if(!disponibilidadeDoExemplar)
                {
                    UsuarioAbstrato usuarioQuePegouOLivroEmprestado = repositorio.obterUsuarioQuePegouLivroEmprestado(codigoDoLivro);
                    String nomeDoUsuario = usuarioQuePegouOLivroEmprestado.getNome();
                    Emprestimo emprestimo = repositorio.obterEmprestimo(usuarioQuePegouOLivroEmprestado, this);
                    LocalDate dataDaRealizacaoDoEmprestimo = emprestimo.getDataInicio().toLocalDate();
                    int tempoLimiteDeEmprestimo = usuarioQuePegouOLivroEmprestado.getTempoLimiteDeEmprestimo();
                    LocalDate tempoPrevistoParaDevolucaoDoExemplar = dataDaRealizacaoDoEmprestimo.plusDays(tempoLimiteDeEmprestimo);
                    mensagemDeRetorno += "      - Usuário que realizou o empréstimo: " + nomeDoUsuario + ".\n"
                     + "      - Data de realização do empréstimo: " +  dataDaRealizacaoDoEmprestimo + ".\n"
                     + "      - O prazo previsto para devolução é: " + tempoPrevistoParaDevolucaoDoExemplar + ".\n";
                }
            }
        }
        return mensagemDeRetorno;

    }
}
