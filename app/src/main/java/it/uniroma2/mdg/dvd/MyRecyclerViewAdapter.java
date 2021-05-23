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

import com.mapbox.mapboxsdk.log.LoggerDefinition;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerTabStrip;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rigarecycler, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String scuola = mData.get(position);
        holder.myTextView.setText(scuola);


            holder.mybutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("LLL", "position: "+position);
                    Log.d("LLL", "onClick: " + Preferiti.list.size());
                    Toast.makeText(context,"Eliminato " + Preferiti.list.get(position), Toast.LENGTH_SHORT).show();
                    Preferiti.list.remove(position);
                    Preferiti.tinyDB1.putListString("preferiti",Preferiti.list);
                    notifyDataSetChanged();
                    Log.d("LLL", "dopo: " + Preferiti.list.size());
                    if(!(Preferiti.codici.isEmpty())) {
                        Log.d("LLL", "codici :  " + Preferiti.codici.size());
                        Preferiti.codici.remove(position);
                        Preferiti.tinyDB1.putListString("codici",Preferiti.codici);
                        Log.d("LLL", "codici dopo :  " + Preferiti.codici.size());

                    }
                    //notifyItemRangeChanged(position,Preferiti.list.size()-1);
                    //notifyItemRemoved(position);
                }
            });
        holder.mybutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(Preferiti.list.get(position).equals("Non hai aggiunto nessuna Preferenza"))){
                    Intent intent = new Intent(context,Scuola.class);
                    intent.putExtra("codicescuola", Preferiti.codici.get(position));
                    context.startActivity(intent);

                }
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        Button mybutton;
        Button mybutton2;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.lista_tw);
            mybutton = itemView.findViewById(R.id.button2);
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
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
