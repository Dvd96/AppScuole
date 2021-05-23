package it.uniroma2.mdg.dvd;

public class ListaScuola {
    String codicescuola;
    String nomescuola;
    String provincia;

    public ListaScuola(String codicescuola, String nomescuola,String citta,String provincia) {
        super();
        this.codicescuola = codicescuola;
        this.nomescuola = nomescuola;
        this.provincia=provincia;
    }
    public String getCodicescuola() {
        return codicescuola;
    }
    public void setCodicescuola(String codicescuola) {
        this.codicescuola = codicescuola;
    }
    public String getNomescuola() {
        return nomescuola;
    }
    public void setNomescuola(String nomescuola) {
        this.nomescuola = nomescuola;
    }
    public String getProvincia() {
        return provincia;
    }
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "ListaScuola{" +
                "codicescuola='" + codicescuola + '\'' +
                ", nomescuola='" + nomescuola + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
