
// Utilização do padrão Strategy para implementar a lógica dos usuários

import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioAbstrato
{
    protected String codigoDoUsuario;
    protected String nome;
    protected int tempoLimiteDeEmprestimo;
    protected List<Livro> livrosEmEmprestimo = new ArrayList<Livro>();

    public abstract String emprestar(Livro livro);
    public abstract String devolver(Livro livro);
    public abstract String reservar(Livro livro);

    public String getCodigo()
    {
        return this.codigoDoUsuario;
    }

    public int getTempoLimiteDeEmprestimo()
    {
        return this.tempoLimiteDeEmprestimo;
    }

    public List<Livro> getLivrosEmEmprestimo()
    {
        return livrosEmEmprestimo;
    }

    public String getNome()
    {
        return this.nome;
    }

}