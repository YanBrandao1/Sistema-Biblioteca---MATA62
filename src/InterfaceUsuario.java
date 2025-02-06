import java.util.HashMap;
import java.util.Scanner;

public class InterfaceUsuario {

	private HashMap<String,Comando> comandos = Fabrica.instanciarHashMapStringComando();
	
	private void inicializarComandos() {
		comandos.put("emp", Fabrica.criarInstanciaDeComandoEmprestar());
		comandos.put("dev", Fabrica.criarInstanciaDeComandoDevolver());
		comandos.put("res", Fabrica.criarInstanciaDeComandoReservar());
		comandos.put("obs", Fabrica.criarInstanciaDeComandoRegistrarObservador());
		comandos.put("ntf", Fabrica.criarInstanciaDeConsultarNotificacoesRecebidas());
		comandos.put("liv", Fabrica.criarInstanciaDeConsultarInformacoesDeLivroComando());
		comandos.put("usu", Fabrica.criarInstanciaDeConsultarInformacoesDeUsuarioComando());
	}
	
	public String executarComando(String strComando, CarregadorParametros parametros) {
		Comando comando = comandos.get(strComando);
		String retorno = comando.executar(parametros);
		return retorno;
	}
	
	public void exibirListaDeComandos()
	{
		System.out.println("Lista de comandos: ");
		System.out.println();
		System.out.println("1) Para realizar um empréstimo, digite o comando 'emp', seguido do código do usuário e código do livro.");
		System.out.println("2) Para realizar uma devolução, digite o comando 'dev', seguido do código do usuário e código do livro.");
		System.out.println("3) Para realizar uma reserva, digite o comando 'res', seguido do código do usuário e código do livro.");
		System.out.println("4) Para cadastrar um professor como observador, digite o comando 'obs', seguido do código do usuário (professor).");
		System.out.println("5) Para consultar a quantidade de notificações recebidas por um professor, digite o comando 'ntf', seguido do código do usuário (professor).");
		System.out.println("6) Para consultar as informações de um livro, digite o comando 'liv', seguido do código do livro.");
		System.out.println("7) Para consultar as informações de um usuário, digite o comando 'usu', seguido do código do usuário.");
		System.out.println("8) Para ver novamente a lista de comandos, caso esqueça, digite o comando 'lis'.");
		System.out.println("9) Para sair, digite 'ext'");
		System.out.println();
	}

	public void finalizarPrograma()
	{
		System.out.println("Programa finalizado com sucesso. Volte sempre !");
		System.out.println();
	}

	// INTERFACE COM O USUÁRIO

	public void main()
	{

		Repositorio repositorio = Repositorio.obterInstancia();

		// INSTANCIAÇÃO DOS USUÁRIOS
		UsuarioAbstrato usuario1 = Fabrica.criarUsuarioAlunoGraduacao("123", "João da Silva");
		UsuarioAbstrato usuario2 = Fabrica.criarUsuarioAlunoPosGraduacao("456", "Luiz Fernando Rodrigues");
		UsuarioAbstrato usuario3 = Fabrica.criarUsuarioAlunoGraduacao("789", "Pedro Paulo");
		UsuarioAbstrato usuario4 = Fabrica.criarUsuarioProfessor("100", "Carlos Lucena");

		// ADIÇÃO DOS USUÁRIOS NA BASE DE DADOS
		repositorio.adicionarUsuarioNaBase(usuario1);
		repositorio.adicionarUsuarioNaBase(usuario2);
		repositorio.adicionarUsuarioNaBase(usuario3);
		repositorio.adicionarUsuarioNaBase(usuario4);

        Livro livro1 = new Livro("100","Engenharia de Software","Addison Wesley","Ian Sommervile","6a","2000");
		Livro livro2 = new Livro("101","UML - Guia do Usuário","Campus","Grady Booch, James Rumbaugh, Ivar Jacobson","7a","2000");
		Livro livro3 = new Livro("200","Code Complete","Microsoft Press","Steve McConell","2a","2014");
		Livro livro4 = new Livro("201","Agile Software Development Priciples, Patterns and Practices","Prentice Hall","Robert Martin","1a","2002");
		Livro livro5 = new Livro("300","Refactoring: Improving the Design of Existing Code","Addison Wesley","Martin Folwer","1a","1999");
		Livro livro6 = new Livro("301","Software Metrics: A rigorous and Practical Approach","CRC Press","Norman Fenton, James Bieman","3a","2014");
		Livro livro7 = new Livro("400","Design Patterns: Element of Reusable Object-Oriented Software","Addison Wesley Professional","Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides","1a","1994");
		Livro livro8 = new Livro("401","UML Distilled: a Brief Guide to the Standard Object Modeling Language","Addison Wesley Professional","Martin Folwer","3a","2003");
		
		// ADIÇÃO DOS LIVROS NA BASE DE DADOS
		repositorio.adicionarLivroNaBase(livro1);
		repositorio.adicionarLivroNaBase(livro2);
		repositorio.adicionarLivroNaBase(livro3);
		repositorio.adicionarLivroNaBase(livro4);
		repositorio.adicionarLivroNaBase(livro5);
		repositorio.adicionarLivroNaBase(livro6);
		repositorio.adicionarLivroNaBase(livro7);
		repositorio.adicionarLivroNaBase(livro8);
		
		// INSTACIAÇÃO DOS EXEMPLARES
        Exemplar exemplar1 = Fabrica.criarExemplar("01");
		Exemplar exemplar2 = Fabrica.criarExemplar("02");
		Exemplar exemplar3 = Fabrica.criarExemplar("03");
		Exemplar exemplar4 = Fabrica.criarExemplar("04");
		Exemplar exemplar5 = Fabrica.criarExemplar("05");
		Exemplar exemplar6 = Fabrica.criarExemplar("06");
		Exemplar exemplar7 = Fabrica.criarExemplar("07");
		Exemplar exemplar8 = Fabrica.criarExemplar("08");
		Exemplar exemplar9 = Fabrica.criarExemplar("09");


		// ASSOCIAÇÃO DOS EXEMPLARES AOS SEUS RESPECTIVOS LIVROS
        livro1.adicionarExemplar(exemplar1);
		livro1.adicionarExemplar(exemplar2);
		livro2.adicionarExemplar(exemplar3);
		livro3.adicionarExemplar(exemplar4);
		livro4.adicionarExemplar(exemplar5);
		livro5.adicionarExemplar(exemplar6);
		livro5.adicionarExemplar(exemplar7);
		livro7.adicionarExemplar(exemplar8);
		livro7.adicionarExemplar(exemplar9);

		

		this.inicializarComandos();
		



		System.out.println();
		System.out.println("---  SISTEMA BIBLIOTECA  ---");
		System.out.println();
		System.out.println("Seja bem-vindo(a)! ");
		System.out.println();
		System.out.println("Lista de comandos: ");
		System.out.println();
		System.out.println("1) Para realizar um empréstimo, digite o comando 'emp', seguido do código do usuário e código do livro.");
		System.out.println("2) Para realizar uma devolução, digite o comando 'dev', seguido do código do usuário e código do livro.");
		System.out.println("3) Para realizar uma reserva, digite o comando 'res', seguido do código do usuário e código do livro.");
		System.out.println("4) Para cadastrar um professor como observador, digite o comando 'obs', seguido do código do usuário (professor).");
		System.out.println("5) Para consultar a quantidade de notificações recebidas por um professor, digite o comando 'ntf', seguido do código do usuário (professor).");
		System.out.println("6) Para consultar as informações de um livro, digite o comando 'liv', seguido do código do livro.");
		System.out.println("7) Para consultar as informações de um usuário, digite o comando 'usu', seguido do código do usuário.");
		System.out.println("8) Para ver novamente a lista de comandos, caso esqueça, digite o comando 'lis'.");
		System.out.println("9) Para sair, digite 'ext'");
		System.out.println();

		
		while(true)
		{
			Scanner entrada = new Scanner(System.in);
			
			System.out.print("> "); 
            String linha = entrada.nextLine();
            String[] partes = linha.split(" ");
			System.out.println();

            String comandoDeEntrada = partes[0];
            String parametroUm = partes.length > 1 ? partes[1] : null;
            String parametroDois = partes.length > 2 ? partes[2] : null;
			
			if (comandoDeEntrada.equals("ext"))
			{
				this.finalizarPrograma();
				break;
			}
			else if(comandoDeEntrada.equals("lis"))
			{
				this.exibirListaDeComandos();
				continue;
			}

			CarregadorParametros parametros = Fabrica.instanciarCarregadorDeParametros(parametroUm,parametroDois);
			
			String comando1 = this.executarComando(comandoDeEntrada, parametros);
			System.out.println(comando1);
		}
	}
}
