public class ScuolaLista {

String codicescuola;
String nomescuola;
String provincia;
public ScuolaLista(String codicescuola, String nomescuola,String provincia) {
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

}