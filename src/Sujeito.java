import java.util.List;

public interface Sujeito
{
    public String registrarObservador(Observador observador);
    public void notificarObservadores();
}