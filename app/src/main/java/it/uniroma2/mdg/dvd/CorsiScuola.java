package it.uniroma2.mdg.dvd;
import java.util.ArrayList;
import java.util.Arrays;

public class CorsiScuola {
    String indirizzomateria;
    ArrayList<String> materie;
    public String getIndirizzomateria() {
        return indirizzomateria;
    }
    public void setIndirizzomateria(String indirizzomateria) {
        this.indirizzomateria = indirizzomateria;
    }
    public ArrayList<String> getMaterie() {
        return materie;
    }
    public void setMaterie(ArrayList<String> materie) {
        this.materie = materie;
    }
    @Override
    public String toString() {
        return "Indirizzo materia = " + indirizzomateria + "     " +  materie +  " ";
    }
}
