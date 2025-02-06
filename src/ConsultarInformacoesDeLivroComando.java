
public class ConsultarInformacoesDeLivroComando implements Comando {
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		Livro livro = repositorio.obterLivroPorCodigo(carregadorParametros.getParametroDois());
        
		String mensagemDeRetorno = livro.consultarInformacoesDoLivro(carregadorParametros.getParametroDois());
		
		return mensagemDeRetorno;
	}

}