package it.uniroma2.mdg.dvd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scuola extends AppCompatActivity {

    public static String url;
    public static String url1;
    public static Context context;
    public static ScuolaDati scuolaselezionata;
    public static TinyDB tinyDB;
    public static TinyDB tinyDB2;
    public static TextView nomescuola;
    public static TextView indirizzoscuola;
    public static TextView telefonoscuola;
    public static TextView sitowescuola;
    public static TextView comunescuola;
    public static ExpandableListView listacorsi;
    public static ArrayList<CorsiScuola> corsiScuola;
    public static ExpandableList adapter;
    public static HashMap<String, List<String>> materie = new HashMap<>();
    public static ArrayList<String> list= new ArrayList<>();
    public static String ERRORELISTA1="CORSI E MATERIE NON DISPONIBILI PER QUESTA SCUOLA";
    public static ArrayList<String> ERROREMATERIE= new ArrayList<>();
    public static ArrayList<TinyDB> tyni = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scuola);
        ERROREMATERIE.add(ERRORELISTA1);
        ERROREMATERIE.add(ERRORELISTA1);
        boolean b = false;
        int k =0;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Bundle bundle=getIntent().getExtras();
        String codicescuola=bundle.getString("codicescuola");
        if(!(tinyDB2 == null)) {
            Log.d("dentro", "onCreate: ");
            for (int f = 0; f < tinyDB2.getInt("numero"); f++) {
                ScuolaDati c = tinyDB.getObject("scuolaselezionata" + f, ScuolaDati.class);
                if (c.codicescuola.equals(codicescuola)) {
                    b = true;
                    k = f;
                }
            }
        }
        if(b){
            scuolaselezionata = tinyDB.getObject("scuolaselezionata" +k,ScuolaDati.class);
            myHandler.sendEmptyMessageDelayed(Scuola.MSG_UPDATE,10);
        } else {
            url = Caratteristiche.baseurl + "/Progeto/Main?codicescuola=".concat(codicescuola);
            AsyncTask task = new Task().execute(url, "1");
        }
        url1= Caratteristiche.baseurl+"/Progeto/Materie?codicescuola=".concat(codicescuola);
        AsyncTask task1=new Task().execute(url1,"8");
        nomescuola= findViewById(R.id.nomescuola_scuola);
        indirizzoscuola= findViewById(R.id.descirzioneindirizzo_scuola);
        telefonoscuola= findViewById(R.id.dnumero_scuola);
        sitowescuola= findViewById(R.id.descrizionesito_scuola);
        comunescuola=findViewById(R.id.comunedescrizione_scuola);
        listacorsi=findViewById(R.id.listacorsi1_scuola);
        int i=0;
        context = this;
        Button preferiti = findViewById(R.id.button);
        preferiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean uguale=true;
                for(int i=0; i< Preferiti.list.size(); i++){
                    if(Preferiti.list.get(i).equals(scuolaselezionata.getNomescuola() + " (" + scuolaselezionata.getProvincia() + ")")){
                        uguale = false;
                    }
                    if(Preferiti.list.get(i).equals("Non hai aggiunto nessuna Preferenza")){
                        Preferiti.list.remove(i);
                        Preferiti.tinyDB1.putListString("preferiti",Preferiti.list);
                    }
                }
                if(uguale) {
                    Preferiti.list.add(scuolaselezionata.getNomescuola() + " (" + scuolaselezionata.getProvincia() + ")");
                    Preferiti.codici.add(scuolaselezionata.getCodicescuola());
                    Preferiti.tinyDB1.putListString("preferiti",Preferiti.list);
                    Preferiti.tinyDB1.putListString("codici",Preferiti.codici);
                    Toast.makeText(getApplicationContext(), "Aggiunti ai preferiti " + scuolaselezionata.getNomescuola(), Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Hai già aggiunto  " + scuolaselezionata.getNomescuola() + " ai tuoi preferiti!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public class MaterieCorsi{

    }


    public static final int MSG_UPDATE = 1;
    public static final int MSG_UPDATE1 = 2;
    public static Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_UPDATE){
                nomescuola.setText(scuolaselezionata.getNomescuola());
                if(scuolaselezionata.getIndirizzo()==null){
                    indirizzoscuola.setText("Indirizzo non disponibile");
                }else
                    indirizzoscuola.setText(scuolaselezionata.getIndirizzo());
                if(scuolaselezionata.getTelefono()==null){
                    telefonoscuola.setText("Telefono non disponibile");
                }else
                    telefonoscuola.setText(scuolaselezionata.getTelefono());

                if(scuolaselezionata.getSitoweb()==null){
                    sitowescuola.setText("Sito web non disponibile");
                }else
                    sitowescuola.setText(scuolaselezionata.getSitoweb());

                if(scuolaselezionata.getComune()==null){
                    comunescuola.setText("Comune non disponibile. ");
                }else
                    comunescuola.setText(scuolaselezionata.getComune());

                comunescuola.setText(comunescuola.getText()+"("+scuolaselezionata.getProvincia()+")");


            }

            if(msg.what==MSG_UPDATE1){
                //Log.d("LLL", ""+corsiScuola)
                materie.clear();
                int i=0;
                int g=0;
                if(corsiScuola!=null){
                    if(corsiScuola.size()!=0){
                        while (i<corsiScuola.size()) {
                            list.add(corsiScuola.get(i).indirizzomateria);
                            materie.put(corsiScuola.get(i).indirizzomateria,corsiScuola.get(i).materie);
                            i++;
                        }
                    }else{
                        list.clear();
                        materie.clear();
                        materie.put(ERRORELISTA1,ERROREMATERIE);
                        list.add(ERRORELISTA1);
                    }
                }else{
                    list.clear();
                    materie.clear();
                    materie.put(ERRORELISTA1,ERROREMATERIE);
                    list.add(ERRORELISTA1);
                }
                List<String> titoli = new ArrayList<String>(materie.keySet());
                adapter = new ExpandableList(context,titoli,materie);
                listacorsi.setAdapter(adapter);


                listacorsi.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
                       /* Toast.makeText(context,
                                list.get(groupPosition) + " List Expanded.",
                                Toast.LENGTH_SHORT).show();*/
                    }
                });

                listacorsi.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        /*Toast.makeText(context,
                                list.get(groupPosition) + " List Collapsed.",
                                Toast.LENGTH_SHORT).show();*/

                    }
                });

                listacorsi.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        /*Toast.makeText(
                                context,
                                list.get(groupPosition)
                                        + " -> "
                                        + materie.get(
                                        list.get(groupPosition)).get(
                                        childPosition), Toast.LENGTH_SHORT
                        ).show();*/
                        return false;
                    }
                });


            }
        }
    };
}
