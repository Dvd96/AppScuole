package it.uniroma2.mdg.dvd;

public class PosizioneMarker {
    String codicescuola;
    String nomescuola;
    String indirizzo;
    String longitudine;
    String latitudine;

    @Override
    public String toString() {
        return "PosizioneMarker{" +
                "codicescuola='" + codicescuola + '\'' +
                ", nomescuola='" + nomescuola + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", longitudine='" + longitudine + '\'' +
                ", latitudine='" + latitudine + '\'' +
                '}';
    }

    public PosizioneMarker(String codicescuola, String nomescuola, String indirizzo, String longitudine, String latitudine) {
        this.codicescuola = codicescuola;
        this.nomescuola = nomescuola;
        this.indirizzo = indirizzo;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
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

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(String latitudine) {
        this.latitudine = latitudine;
    }
}
