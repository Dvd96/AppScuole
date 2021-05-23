package it.uniroma2.mdg.dvd;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * Created by Belal on 1/23/2018.
 */

public class Caratteristiche extends Fragment {


    Spinner regione_sel;
    Spinner provincia_sel;
    Spinner comune_sel;
    Spinner tipologia_sel;
    Spinner materia_sel;

    public static String baseurl="http://progettojava.servemp3.com:4040";

    public static ArrayAdapter<String> spinneradapterreg;
    public static ArrayAdapter<String> spinneradapterprov;
    public static ArrayAdapter<String> spinneradaptercom;
    public static ArrayAdapter<String> spinneradaptertip;
    public static ArrayAdapter<String> spinneradaptermat;

    //String regioneselezionata="ABRUZZO";
    //String provinciaselezionata="CHIETI";
    String regioneselezionata=null;
    String provinciaselezionata=null;
    String comuneselezionato=null;
    String tipologiaselezionata=null;
    String materiaselezionata=null;
    public static boolean a=false;

    public static ArrayList<String> appoggio=new ArrayList<>();
    public static ArrayList<String> appoggio1=new ArrayList<>();
    public static ArrayList<String> appoggio2=new ArrayList<>();
    public static ArrayList<String> appoggio3=new ArrayList<>();
    public static ArrayList<String> appoggio4=new ArrayList<>();



    public static Button ricerca;

    boolean b=true;




    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_caratteristiche, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regione_sel=getView().findViewById(R.id.regione_cara);
        provincia_sel= getView().findViewById(R.id.provincia_cara);
        comune_sel=getView().findViewById(R.id.comune_cara);
        tipologia_sel=getView().findViewById(R.id.tipologia_cara);
        materia_sel=getView().findViewById(R.id.materia_cara);
        ricerca=getView().findViewById(R.id.bottonericerca_cara);



        spinneradapterreg=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,appoggio);
        spinneradapterprov=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,appoggio1);
        spinneradaptercom=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,appoggio2);
        spinneradaptertip=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,appoggio3);
        spinneradaptermat=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,appoggio4);

        final Toast toast=Toast.makeText(getContext(),"Selezionare regione e provincia",Toast.LENGTH_SHORT);


        regione_sel.setAdapter(spinneradapterreg);
        provincia_sel.setAdapter(spinneradapterprov);
        comune_sel.setAdapter(spinneradaptercom);
        tipologia_sel.setAdapter(spinneradaptertip);
        materia_sel.setAdapter(spinneradaptermat);

        String url;
        url= baseurl+"/Progeto/Caratteristiche?regioni=c";
        AsyncTask task=new Task().execute(url,"2");
        spinneradapterreg.notifyDataSetChanged();


        regione_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    regioneselezionata=adapterView.getItemAtPosition(i).toString();
                    String url;
                    url= baseurl+"/Progeto/Caratteristiche?provincie=c&regione=".concat(adapterView.getItemAtPosition(i).toString());
                    spinneradapterprov.clear();
                    spinneradapterprov.notifyDataSetChanged();
                    AsyncTask task1=new Task().execute(url,"3");
                    spinneradapterprov.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                regioneselezionata=adapterView.getItemAtPosition(0).toString();
                String url;
                url= baseurl+"/Progeto/Caratteristiche?provincie=c&regione=".concat(regioneselezionata);
                spinneradapterprov.clear();
                spinneradapterprov.notifyDataSetChanged();
                AsyncTask task1=new Task().execute(url,"3");
                spinneradapterprov.notifyDataSetChanged();
            }
        });

        provincia_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinneradaptercom.clear();
                spinneradaptercom.notifyDataSetChanged();
                spinneradaptertip.clear();
                spinneradaptertip.notifyDataSetChanged();
                spinneradaptermat.clear();
                spinneradaptermat.notifyDataSetChanged();
                if(i!=0) {
                    provinciaselezionata = adapterView.getItemAtPosition(i).toString();

                    String url;
                    url = baseurl+"/Progeto/Caratteristiche?comuni=c&provincia=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task2 = new Task().execute(url, "4");
                    spinneradaptercom.notifyDataSetChanged();

                    url=baseurl+"/Progeto/Caratteristiche?tipologie=c&provincia=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task3= new Task().execute(url,"5");
                    spinneradaptertip.notifyDataSetChanged();

                    url=baseurl+"/Progeto/Caratteristiche?materieprovincia=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task4=new Task().execute(url,"6");
                    spinneradaptermat.notifyDataSetChanged();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                provinciaselezionata=null;
                comuneselezionato=null;
                tipologiaselezionata = null;
                materiaselezionata=null;

            }
        });


        comune_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //spinneradaptercom.clear();
                //spinneradaptercom.notifyDataSetChanged();
                if(i!=0){
                    comuneselezionato=adapterView.getItemAtPosition(i).toString();
                    String url;
                    url=baseurl+"/Progeto/Caratteristiche?materiecomune=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task4=new Task().execute(url,"6");
                    spinneradaptermat.notifyDataSetChanged();

                    url=baseurl+"/Progeto/Caratteristiche?tipologiecomune=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task3=new Task().execute(url,"5");
                    spinneradaptertip.notifyDataSetChanged();
                    b=false;

                }else{
                    comuneselezionato=null;
                    String url;
                    spinneradaptertip.clear();
                    spinneradaptertip.notifyDataSetChanged();
                    spinneradaptermat.clear();
                    spinneradaptermat.notifyDataSetChanged();
                    if(provinciaselezionata!=null) {
                        url = baseurl + "/Progeto/Caratteristiche?tipologie=c&provincia=".concat(provinciaselezionata);
                        AsyncTask task3 = new Task().execute(url, "5");
                        spinneradaptertip.notifyDataSetChanged();

                        url = baseurl + "/Progeto/Caratteristiche?materieprovincia=".concat(provinciaselezionata);
                        AsyncTask task4 = new Task().execute(url, "6");
                        spinneradaptermat.notifyDataSetChanged();
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        tipologia_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //spinneradaptertip.clear();
                //spinneradaptertip.notifyDataSetChanged();
                if(i!=0){
                    tipologiaselezionata = adapterView.getItemAtPosition(i).toString();
                    if(materiaselezionata==null) {
                        String url;
                        url = baseurl + "/Progeto/Caratteristiche?materietipologia=".concat(adapterView.getItemAtPosition(i).toString());
                        AsyncTask task4 = new Task().execute(url, "6");
                        spinneradaptermat.notifyDataSetChanged();
                    }
                }else{
                    tipologiaselezionata = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        materia_sel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //spinneradaptermat.clear();
                //spinneradaptermat.notifyDataSetChanged();
                if(i!=0){
                    materiaselezionata = adapterView.getItemAtPosition(i).toString();
                    String url;
                    url = baseurl + "/Progeto/Caratteristiche?tipologiamateria=".concat(adapterView.getItemAtPosition(i).toString());
                    AsyncTask task3 = new Task().execute(url, "5");
                    spinneradaptertip.notifyDataSetChanged();
                }else{
                    materiaselezionata=null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Intent intent=new Intent(getContext(),ListaCaratteristiche.class);

        ricerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regioneselezionata!=null && provinciaselezionata!=null){
                    //intent.putExtra("regioneselezionata",regioneselezionata);
                    //intent.putExtra("provinciaselezionata",provinciaselezionata);
                    //intent.putExtra("comuneselezionato",comuneselezionato);
                    //intent.putExtra("tipologiaselezionata",tipologiaselezionata);
                    //intent.putExtra("materiaselezionata",materiaselezionata);
                    if(comuneselezionato!=null){
                        if(tipologiaselezionata!=null){
                            if(materiaselezionata!=null){
                                //tutto selezionato
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?h=c&tipologia=".concat(tipologiaselezionata)+"&disciplina="
                                        .concat(materiaselezionata)+"&comune=".concat(comuneselezionato);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }else{
                                //comune+tipo
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?f=c&tipologia=".concat(tipologiaselezionata)+"&comune=".concat(comuneselezionato);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }
                        }else{
                            if (materiaselezionata!=null){
                                //comune+materia
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?e=c&disciplina=".concat(materiaselezionata)+"&comune=".concat(comuneselezionato);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }else{
                                //comune
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?b=c&comune=".concat(comuneselezionato);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }
                        }
                    }else{
                        if(tipologiaselezionata!=null){
                            if(materiaselezionata!=null){
                                //tipo+materia
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?g=c&disciplina=".concat(materiaselezionata)+"&tipologia=".concat(tipologiaselezionata);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }else{
                                //tipo
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?d=c&tipologia=".concat(tipologiaselezionata)+"&provincia=".concat(provinciaselezionata);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }
                        }else{
                            if(materiaselezionata!=null){
                                //materia
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?c=c&disciplina=".concat(materiaselezionata)+"&provincia=".concat(provinciaselezionata);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }else{
                                //nessuna
                                String url;
                                url = baseurl+"/Progeto/Caratteristiche?a=c&provincia=".concat(provinciaselezionata);
                                AsyncTask task5 = new Task().execute(url, "7");
                            }
                        }
                    }

                    Log.d("LLL", ""+regioneselezionata+"-"+provinciaselezionata+"-"+comuneselezionato+"-"+materiaselezionata+"-"+tipologiaselezionata);
                   startActivity(intent);
                }else
                    toast.show();
            }
        });
    }


    public static final int MSG_UPDATE1 = 1;
    public static final int MSG_UPDATE2= 2;
    public static final int MSG_UPDATE3= 3;
    public static final int MSG_UPDATE4= 4;
    public static final int MSG_UPDATE5= 5;

    public static String msgsel="Non selezionato";

    public static Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_UPDATE1){
                spinneradapterreg.addAll(appoggio);
                spinneradapterreg.notifyDataSetChanged();
            }
            if(msg.what==MSG_UPDATE2){
                spinneradapterprov.add(msgsel);
                spinneradapterprov.addAll(appoggio1);
                spinneradapterprov.notifyDataSetChanged();
            }
            if(msg.what==MSG_UPDATE3){
                spinneradaptercom.add(msgsel);
                spinneradaptercom.addAll(appoggio2);
                spinneradaptercom.notifyDataSetChanged();
            }
            if(msg.what==MSG_UPDATE4){
                spinneradaptertip.clear();
                spinneradaptertip.notifyDataSetChanged();
                spinneradaptertip.add(msgsel);
                if(appoggio3!=null){
                    spinneradaptertip.addAll(appoggio3);
                }
                spinneradaptertip.notifyDataSetChanged();
            }
            if(msg.what==MSG_UPDATE5){
                spinneradaptermat.clear();
                spinneradaptermat.notifyDataSetChanged();
                spinneradaptermat.add(msgsel);
                if(appoggio4!=null) {
                    spinneradaptermat.addAll(appoggio4);
                }
                spinneradaptermat.notifyDataSetChanged();
            }
        }
    };
}