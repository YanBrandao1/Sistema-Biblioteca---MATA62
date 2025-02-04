

public class DevolverComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		UsuarioAbstrato usuario = repositorio.obterUsuarioPorCodigo(carregadorParametros.getParametroUm());
		Livro livro = repositorio.obterLivroPorCodigo(carregadorParametros.getParametroDois());
		
		String mensagemDeRetorno = usuario.devolver(livro);
		
		return mensagemDeRetorno;
	}

}
