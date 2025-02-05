
public class ConsultarInformacoesDeLivroComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		Livro livro = repositorio.obterLivroPorCodigo(carregadorParametros.getParametroUm());
        
		String mensagemDeRetorno = livro.consultarInformacoesDoLivro(carregadorParametros.getParametroUm());
		
		return mensagemDeRetorno;
	}

}