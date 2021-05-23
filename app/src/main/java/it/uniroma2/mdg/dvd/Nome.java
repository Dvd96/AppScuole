package it.uniroma2.mdg.dvd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;



public class Nome extends Fragment {
    @Override    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nome, null);
    }

    public static String url;
    public static ArrayList<String> list= new ArrayList<>();
    public static ArrayList<ListaScuola> listascuole=new ArrayList<>();
    public static String ERRORELISTA="INSERISCI ALMENO 5 CARATTERI PER AVERE I SUGGERIMENTI";
    public static String ERRORELISTA1="LA SCUOLA RICHIESTA NON È STATA TROVATA";
    public static MyRecicleAdapter myRecicleAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final RecyclerView recyclerView=getView().findViewById(R.id.lv_nome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecicleAdapter = new MyRecicleAdapter(getContext(),list,2);
        recyclerView.setAdapter(myRecicleAdapter);
        list.clear();
        myRecicleAdapter.notifyDataSetChanged();

        final EditText editText=getView().findViewById(R.id.scuola_edit);
        list.clear();
        myRecicleAdapter.notifyDataSetChanged();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                final Intent intent=new Intent(getContext(),Scuola.class);
                if (editable.length() > 4) {
                    list.clear();
                    //List.add(editable.toString());
                    //url= "http://192.168.1.76:8080/progettoScuole/Lista?nome=".concat(editable.toString());
                    url= "http://progettojava.servemp3.com:4040/Progeto/Lista?nome=".concat(editable.toString());
                    AsyncTask task=new Task().execute(url,"0");
                    myRecicleAdapter.notifyDataSetChanged();
                   /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            if((!adapterView.getItemAtPosition(i).toString().equals(ERRORELISTA))
                                    &&(!adapterView.getItemAtPosition(i).toString().equals(ERRORELISTA1))){
                                //Log.d("LLL", "L= "+adapterView.getItemAtPosition(i).toString());
                                //Log.d("LLL", "LISTA SCUOLE"+listascuole.get(i).codicescuola);
                                intent.putExtra("codicescuola", listascuole.get(i).codicescuola);
                                startActivity(intent);
                            }
                        }
                    });
                   if (editable.length() == 5) {
                        List.clear();
                        List.add("CIAO");
                        arrayAdapter.notifyDataSetChanged();
                    } else if (editable.length() == 6) {
                        List.clear();
                        List.add("DAVIDE");
                        arrayAdapter.notifyDataSetChanged();
                    } */
                } else {
                    list.clear();
                    list.add(ERRORELISTA);
                    myRecicleAdapter.notifyDataSetChanged();
                }
            }
        });
    }
    public static final int MSG_UPDATE = 1;
    public static Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_UPDATE){
                list.clear();
                myRecicleAdapter.notifyDataSetChanged();
                int i=0;
                if(listascuole.size()!=0){
                while (i<listascuole.size()){
                        list.add(listascuole.get(i).nomescuola+"  ( "+listascuole.get(i).provincia+" )");
                        i++;
                    myRecicleAdapter.notifyDataSetChanged();
                    }
                }else{
                    list.clear();
                    list.add(ERRORELISTA1);
                    myRecicleAdapter.notifyDataSetChanged();
                }
            }
        }
    };
}

