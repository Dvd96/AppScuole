package it.uniroma2.mdg.dvd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Preferiti extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
    public static ArrayList<String> list= new ArrayList<>();
    public static ArrayAdapter<String> arrayAdapter;
    public static ArrayList<String> codici=new ArrayList<>();
    public static TinyDB tinyDB1;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Preferiti() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preferiti, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> arrayAdapter;
        RecyclerView recyclerView = getView().findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        MyRecyclerViewAdapter adapter;
        if(!(tinyDB1.getListString("preferiti").isEmpty())){
            list = tinyDB1.getListString("preferiti");
            if(!(tinyDB1.getListString("codici").isEmpty())) {
                codici = tinyDB1.getListString("codici");
            }
        }
        adapter = new MyRecyclerViewAdapter(getContext(),list);
        if(list.isEmpty()){
            list.add("Non hai aggiunto nessuna Preferenza");
            tinyDB1.putListString("preferiti",list);
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(),"hai cliccato elemento " + position,Toast.LENGTH_SHORT).show();


    }
}
