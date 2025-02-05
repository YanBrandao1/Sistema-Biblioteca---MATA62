

public class Exemplar {

    private boolean disponivel;
    private String codigoExemplar;

    public Exemplar(String codigoExemplar)
    {
        this.codigoExemplar = codigoExemplar;
        this.disponivel = true;
    }

    public boolean isDisponivel() {
        return this.disponivel;
    }

    public void emprestar() {
        this.disponivel = false;
    }

    public String getCodigoExemplar()
    {
        return this.codigoExemplar;
    }

    public void indisponibilizarExemplar()
    {
        this.disponivel = false;
    }

    public void disponibilizarExemplar()
    {
        this.disponivel = true;
    }
}