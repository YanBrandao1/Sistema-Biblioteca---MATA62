public class RegistrarObservadorComando implements Comando
{
	
	public String executar(CarregadorParametros carregadorParametros) {
		Repositorio repositorio = Repositorio.obterInstancia();
		
		UsuarioAbstrato usuario = repositorio.obterUsuarioPorCodigo(carregadorParametros.getParametroUm());
		Livro livro = repositorio.obterLivroPorCodigo(carregadorParametros.getParametroDois());
        
		
        Professor professorCasting = (Professor) usuario;
        String mensagemDeRetorno = livro.registrarObservador(professorCasting);
		
		return mensagemDeRetorno;
	}

}