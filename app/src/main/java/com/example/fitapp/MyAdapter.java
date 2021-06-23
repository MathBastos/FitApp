package com.example.fitapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Registros> registros;


    public MyAdapter(Context context,List<Registros> registros){
        this.context = context;
        this.registros = registros;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        holder.textviewData.setText(registros.get(position).getData());
        holder.textviewPeso.setText(registros.get(position).getPeso());
    }


    @Override
    public int getItemCount() {
        return registros.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textviewData;
        TextView textviewPeso;

        public MyViewHolder(final View view) {
            super(view);

            textviewData = itemView.findViewById(R.id.textviewData);
            textviewPeso = itemView.findViewById(R.id.textviewPeso);
        }
    }

}
