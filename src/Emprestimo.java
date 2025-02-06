
import java.time.LocalDateTime;

public class Emprestimo
{
    private String codigoDoUsuario;
    private String codigoDoLivro;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private boolean emprestimoEmAberto;

    public Emprestimo(String codigoDoUsuario, String codigoDoLivro, LocalDateTime dataInicio)
    {
        this.codigoDoUsuario = codigoDoUsuario;
        this.codigoDoLivro = codigoDoLivro;
        this.dataInicio = dataInicio;
        this.dataFim = null;
        this.emprestimoEmAberto = true;
    }

    public void devolver(LocalDateTime dataFim)
    {
        this.dataFim = dataFim;
        this.emprestimoEmAberto = false;
    }

    public String getCodigoDoUsuario()
    {
        return this.codigoDoUsuario;
    }

    public boolean getEmprestimoEmAberto()
    {
        return this.emprestimoEmAberto;
    }

    public LocalDateTime getDataInicio()
    {
        return this.dataInicio;
    }

    public LocalDateTime getDataFim()
    {
        return this.dataFim;
    }

    public String getCodigoDoLivro()
    {
        return this.codigoDoLivro;
    }
    
    public void setDataFim()
    {
        this.dataFim = LocalDateTime.now();
    }

    public void cancelarEmprestimo()
    {
        this.emprestimoEmAberto = false;
        this.setDataFim();
    }
}
