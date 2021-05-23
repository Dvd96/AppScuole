package it.uniroma2.mdg.dvd;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task extends android.os.AsyncTask <String,Integer,String> {
    String a="";
    ArrayList<ListaScuola> listaScuola;
    String var="";
    ScuolaDati scuolaDati= new ScuolaDati();
    ArrayList<String> appoggio= new ArrayList<>();
    ArrayList<String> appoggio1= new ArrayList<>();
    ArrayList<String> appoggio2= new ArrayList<>();
    ArrayList<String> appoggio3= new ArrayList<>();
    ArrayList<String> appoggio4= new ArrayList<>();
    ArrayList<CorsiScuola> corsiscuola;
    ArrayList<PosizioneMarker> posizioneMarkers;


    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            URLConnection urlConnection= url.openConnection();
            BufferedReader br= new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            a=br.readLine();
            br.close();
            Gson gson=new Gson();
            var=strings[1];
            switch (strings[1]){
                case "0":
                    listaScuola = gson.fromJson(a, new TypeToken<List<ListaScuola>>(){}.getType());
                    break;
                case "1":
                    scuolaDati=gson.fromJson(a,ScuolaDati.class);
                    break;
                case "2":
                    appoggio=gson.fromJson(a,new TypeToken<List<String>>(){}.getType());
                    break;
                case "3":
                    appoggio1=gson.fromJson(a,new TypeToken<List<String>>(){}.getType());
                    break;
                case "4":
                    appoggio2=gson.fromJson(a,new TypeToken<List<String>>(){}.getType());
                    break;
                case "5":
                    appoggio3=gson.fromJson(a,new TypeToken<List<String>>(){}.getType());
                    break;
                case "6":
                    appoggio4=gson.fromJson(a,new TypeToken<List<String>>(){}.getType());
                    break;
                case"7":
                    if(a!=null){
                        listaScuola = gson.fromJson(a, new TypeToken<List<ListaScuola>>(){}.getType());
                    }
                    break;
                case "8":
                    if(!a.equals("a")){
                        corsiscuola=gson.fromJson(a, new TypeToken<List<CorsiScuola>>(){}.getType());
                    } else{
                        corsiscuola=null;
                    }
                    break;
                case "9":
                    if(a!=null){
                        posizioneMarkers = gson.fromJson(a,new TypeToken<List<PosizioneMarker>>(){}.getType());
                    }
                    break;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        switch (var){
            case "0":
                Nome.listascuole=listaScuola;
                Nome.myHandler.sendEmptyMessageDelayed(Nome.MSG_UPDATE,10);
                break;
            case "1":
                Scuola.scuolaselezionata=scuolaDati;
                if(Scuola.tinyDB2.getInt("numero") == 0){
                    Scuola.tinyDB.putObject("scuolaselezionata" + 0,scuolaDati);
                } else {
                    Scuola.tinyDB.putObject("scuolaselezionata" + Scuola.tinyDB2.getInt("numero"),scuolaDati);
                }
                int cont = Scuola.tinyDB2.getInt("numero") + 1;
                Scuola.tinyDB2.putInt("numero", cont);
                Scuola.myHandler.sendEmptyMessageDelayed(Scuola.MSG_UPDATE,10);
                break;
            case "2":
                Caratteristiche.appoggio=appoggio;
                Caratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE1,10);
                break;
            case "3":
                Caratteristiche.appoggio1=appoggio1;
                Caratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE2,10);
                break;
            case "4":
                Caratteristiche.appoggio2=appoggio2;
                Caratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE3,10);
                break;
            case "5":
                Caratteristiche.appoggio3=appoggio3;
                Caratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE4,10);
                break;
            case "6":
                Caratteristiche.appoggio4=appoggio4;
                Caratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE5,10);
                break;
            case"7":
                ListaCaratteristiche.listascuole=listaScuola;
                ListaCaratteristiche.myHandler.sendEmptyMessageDelayed(Caratteristiche.MSG_UPDATE1,10);
                break;
            case "8":
                Scuola.corsiScuola=corsiscuola;
                Scuola.myHandler.sendEmptyMessageDelayed(Scuola.MSG_UPDATE1,10);
                break;
            case "9":
                Indirizzo.posizioneMarkers = posizioneMarkers;
                Indirizzo.Myhandler.sendEmptyMessageDelayed(Indirizzo.MSG_UPDATE,10);
        }
    }
}
