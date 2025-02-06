
import java.time.LocalDateTime;

public class Reserva
{

    private String codigoDoUsuario;
    private String codigoDoLivro;
    private LocalDateTime dataDaReserva;
    private boolean reservaIsAtiva;

    public Reserva(String codigoDoUsuario, String codigoDoLivro)
    {
        this.codigoDoUsuario = codigoDoUsuario;
        this.codigoDoLivro = codigoDoLivro;
        this.dataDaReserva = LocalDateTime.now();
        this.reservaIsAtiva = true;
    }

    public String getCodigoDoLivro()
    {
        return this.codigoDoLivro;
    }

    public String getCodigoDoUsuario()
    {
        return this.codigoDoUsuario;
    }

    public void cancelarReserva()
    {
        this.reservaIsAtiva = false;
    }

    public LocalDateTime getDataDaReserva()
    {
        return this.dataDaReserva;
    }
}
