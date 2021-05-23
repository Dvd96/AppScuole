package it.uniroma2.mdg.dvd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCaratteristiche extends AppCompatActivity {

    public static ArrayList<ListaScuola> listascuole;
    public static ArrayList<String> list= new ArrayList<>();
    public static String ERRORELISTA1="LA SCUOLA RICHIESTA NON È STATA TROVATA";
    public static MyRecicleAdapter myRecicleAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_caratteristiche);
        final RecyclerView recyclerView=findViewById(R.id.listacaratteristiche);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecicleAdapter = new MyRecicleAdapter(this,list,1);
        recyclerView.setAdapter(myRecicleAdapter);
        list.clear();
        myRecicleAdapter.notifyDataSetChanged();
        final Intent intent=new Intent(this,Scuola.class);


    }

    public static final int MSG_UPDATE1 = 1;

    public static Handler myHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==MSG_UPDATE1){
                list.clear();
                myRecicleAdapter.notifyDataSetChanged();
                int i=0;
                //Log.d("LLL", ""+listascuole);
                if(listascuole!=null ){
                    if(listascuole.size()!=0){
                        while (i<listascuole.size()) {
                            list.add(listascuole.get(i).nomescuola + "  ( " + listascuole.get(i).provincia + " )");
                            i++;
                            myRecicleAdapter.notifyDataSetChanged();
                        }
                    }else{
                        list.clear();
                        list.add(ERRORELISTA1);
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
