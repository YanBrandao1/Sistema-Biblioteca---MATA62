
public class ConsultarInformacoesDeUsuarioComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		UsuarioAbstrato usuario = repositorio.obterUsuarioPorCodigo(carregadorParametros.getParametroUm());
        
		String mensagemDeRetorno = usuario.consultarInformacoesDoUsuario(carregadorParametros.getParametroUm());
		
		return mensagemDeRetorno;
	}

}