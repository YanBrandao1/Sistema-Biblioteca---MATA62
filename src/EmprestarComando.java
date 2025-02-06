
public class EmprestarComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		UsuarioAbstrato usuario = repositorio.obterUsuarioPorCodigo(carregadorParametros.getParametroUm());
		
		Livro livro = repositorio.obterLivroPorCodigo(carregadorParametros.getParametroDois());
		
		String mensagemDeRetorno = usuario.emprestar(livro);
		
		return mensagemDeRetorno;
	}

}
