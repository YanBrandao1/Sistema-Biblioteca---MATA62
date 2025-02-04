
public class Fabrica {
    
    public static UsuarioAbstrato criarUsuarioAlunoGraduacao(String codigoDoUsuario, String nomeDoUsuario)
    {
        return new AlunoGraduacao(codigoDoUsuario,nomeDoUsuario);
    }


    public static UsuarioAbstrato criarUsuarioAlunoPosGraduacao(String codigoDoUsuario, String nomeDoUsuario)
    {
        return new AlunoPosGraduacao(codigoDoUsuario,nomeDoUsuario);
    }


    public static UsuarioAbstrato criarUsuarioProfessor(String codigoDoUsuario, String nomeDoUsuario)
    {
        return new Professor(codigoDoUsuario,nomeDoUsuario);
    }


    public static Reserva criarReserva(UsuarioAbstrato usuario, Livro livro)
    {
        return new Reserva(usuario.getCodigo(), livro.getCodigo());
    }


    public static EmprestarComando criarInstanciaDeComandoExecutar()
    {
        return new EmprestarComando();
    }


    public static DevolverComando criarInstanciaDeComandoDevolver()
    {
        return new DevolverComando();
    }


    public static ReservarComando criarInstanciaDeComandoReservar()
    {
        return new ReservarComando();
    }

    public static Exemplar criarExemplar(String CodigoDoExemplar)
    {
        return new Exemplar(CodigoDoExemplar);
    }
}
