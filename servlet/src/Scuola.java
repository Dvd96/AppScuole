public class Scuola {
String codicescuola;
String regione;
String provincia;
String nomescuola;
String indirizzo;
String tipologia;
String sitoweb;
String lat;
String lon;
String comune;
String cap;
String telefono;
String pubblica;


public Scuola(String codicescuola, String regione, String provincia, String nomescuola, String indirizzo,
String tipologia, String sitoweb, String lat, String lon, String comune, String cap, String telefono,
String pubblica) {
super();
this.codicescuola = codicescuola;
this.regione = regione;
this.provincia = provincia;
this.nomescuola = nomescuola;
this.indirizzo = indirizzo;
this.tipologia = tipologia;
this.sitoweb = sitoweb;
this.lat = lat;
this.lon = lon;
this.comune = comune;
this.cap = cap;
this.telefono = telefono;
this.pubblica = pubblica;
}
@Override
public String toString() {
return "Scuola [codicescuola=" + codicescuola + ", regione=" + regione + ", provincia=" + provincia
+ ", nomescuola=" + nomescuola + ", indirizzo=" + indirizzo + ", tipologia=" + tipologia + ", sitoweb="
+ sitoweb + ", lat=" + lat + ", lon=" + lon + ", comune=" + comune + ", cap=" + cap + ", telefono="
+ telefono + ", pubblica=" + pubblica + "]";
}
public String getCodicescuola() {
return codicescuola;
}
public void setCodicescuola(String codicescuola) {
this.codicescuola = codicescuola;
}
public String getRegione() {
return regione;
}
public void setRegione(String regione) {
this.regione = regione;
}
public String getProvincia() {
return provincia;
}
public void setProvincia(String provincia) {
this.provincia = provincia;
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
public String getTipologia() {
return tipologia;
}
public void setTipologia(String tipologia) {
this.tipologia = tipologia;
}
public String getSitoweb() {
return sitoweb;
}
public void setSitoweb(String sitoweb) {
this.sitoweb = sitoweb;
}
public String getLat() {
return lat;
}
public void setLat(String lat) {
this.lat = lat;
}
public String getLon() {
return lon;
}
public void setLon(String lon) {
this.lon = lon;
}
public String getComune() {
return comune;
}
public void setComune(String comune) {
this.comune = comune;
}
public String getCap() {
return cap;
}
public void setCap(String cap) {
this.cap = cap;
}
public String getTelefono() {
return telefono;
}
public void setTelefono(String telefono) {
this.telefono = telefono;
}
public String getPubblica() {
return pubblica;
}
public void setPubblica(String pubblica) {
this.pubblica = pubblica;
}

}