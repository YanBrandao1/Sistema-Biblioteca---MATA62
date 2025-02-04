
public class CarregadorParametros {

	private String parametroUm;
	private String parametroDois;
	
	public CarregadorParametros(String parametroUm) {
		this.parametroUm = parametroUm;
	}
	
	public CarregadorParametros(String parametroUm, String parametroDois) {
		this.parametroUm = parametroUm;
		this.parametroDois = parametroDois;
	}
	
	public String getParametroUm() {
		return this.parametroUm;
	}
	public String getParametroDois() {
		return this.parametroDois;
	}

	public void setParametroUm(String parametro) {
		this.parametroUm = parametro;
	}

	public void setParametroDois(String parametro) {
		this.parametroDois = parametro;
	}

}
