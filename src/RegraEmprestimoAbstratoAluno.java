

public interface RegraEmprestimoAbstratoAluno
{
    public boolean podeEmprestar(UsuarioAbstrato usuario, Livro livro);
    public boolean podeDevolver(UsuarioAbstrato usuario, Livro livro);
    public boolean podeReservar(UsuarioAbstrato usuario, Livro livro);
}
