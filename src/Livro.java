import java.util.ArrayList;
import java.util.List;

public class Livro implements Sujeito
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
}
