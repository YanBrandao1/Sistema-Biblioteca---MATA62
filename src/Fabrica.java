import java.util.HashMap;

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


    public static EmprestarComando criarInstanciaDeComandoEmprestar()
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

    public static RegistrarObservadorComando criarInstanciaDeComandoRegistrarObservador()
    {
        return new RegistrarObservadorComando();
    }

    public static ConsultarNotificacoesRecebidasComando criarInstanciaDeConsultarNotificacoesRecebidas()
    {
        return new ConsultarNotificacoesRecebidasComando();
    }

    public static Exemplar criarExemplar(String CodigoDoExemplar)
    {
        return new Exemplar(CodigoDoExemplar);
    }

    public static ConsultarInformacoesDeLivroComando criarInstanciaDeConsultarInformacoesDeLivroComando()
    {
        return new ConsultarInformacoesDeLivroComando();
    }

    public static ConsultarInformacoesDeUsuarioComando criarInstanciaDeConsultarInformacoesDeUsuarioComando()
    {
        return new ConsultarInformacoesDeUsuarioComando();
    }

    public static CarregadorParametros instanciarCarregadorDeParametros(String parametroUm)
    {
        return new CarregadorParametros(parametroUm);
    }

    public static CarregadorParametros instanciarCarregadorDeParametros(String parametroUm, String parametroDois)
    {
        return new CarregadorParametros(parametroUm, parametroDois);
    }

    public static HashMap instanciarHashMapStringComando()
    {
        return new HashMap<String,Comando>();
    }
}
