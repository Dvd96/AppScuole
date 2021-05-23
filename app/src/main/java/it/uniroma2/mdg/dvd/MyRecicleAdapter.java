package it.uniroma2.mdg.dvd;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecicleAdapter extends RecyclerView.Adapter<MyRecicleAdapter.ViewHolder>{
    private List<String> mData;
    private LayoutInflater mInflater;
    private MyRecyclerViewAdapter.ItemClickListener mClickListener;
    private Context context;
    private int selezione;

    // data is passed into the constructor
    MyRecicleAdapter(Context context, List<String> data,int selezione) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
        this.selezione=selezione;
    }

    // inflates the row layout from xml when needed
    @Override
    public MyRecicleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_elemento_lista, parent, false);
        return new MyRecicleAdapter.ViewHolder(view);
    }



    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final MyRecicleAdapter.ViewHolder holder, final int position) {
        String scuola = mData.get(position);
        holder.myTextView.setText(scuola);
        if(selezione==1) {
            holder.mybutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!(ListaCaratteristiche.list.get(position).equals(ListaCaratteristiche.ERRORELISTA1))) {
                        Intent intent = new Intent(context, Scuola.class);
                        intent.putExtra("codicescuola", ListaCaratteristiche.listascuole.get(position).codicescuola);
                        context.startActivity(intent);
                    }
                }
            });
        }
        if(selezione==2){
            holder.mybutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if ((!(Nome.list.get(position).equals(Nome.ERRORELISTA1)))&&(!(Nome.list.get(position).equals(Nome.ERRORELISTA)))) {
                        Intent intent = new Intent(context, Scuola.class);
                        intent.putExtra("codicescuola", Nome.listascuole.get(position).codicescuola);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        Button mybutton2;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.lista_tw);
            mybutton2 = itemView.findViewById(R.id.lista_bottone);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(MyRecyclerViewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
