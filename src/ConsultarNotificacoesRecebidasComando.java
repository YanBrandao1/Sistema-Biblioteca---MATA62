
public class ConsultarNotificacoesRecebidasComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		UsuarioAbstrato usuario = repositorio.obterUsuarioPorCodigo(carregadorParametros.getParametroUm());

        Professor professor = (Professor) usuario;
        
		String mensagemDeRetorno = professor.obterQuantidadeDeNotificacoes();
		
		return mensagemDeRetorno;
	}

}