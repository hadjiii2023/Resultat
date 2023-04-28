package com.example.resultat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyviewHolder> {

    private Context context ;
    Activity activity;
    private ArrayList moy_id , moy_cin , moy_moy , moy_result ;


    CustomAdapter (Activity activity, Context context, ArrayList moy_id, ArrayList moy_cin, ArrayList moy_moy,
                   ArrayList moy_result) {
        this.activity = activity;
        this.context = context;
        this.moy_id = moy_id;
        this.moy_cin = moy_cin;
        this.moy_moy = moy_moy;
        this.moy_result = moy_result;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyviewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.moy_id.setText(String.valueOf(moy_id.get(position)));
        holder.moy_cin.setText(String.valueOf(moy_cin.get(position)));
        holder.moy_moy.setText(String.valueOf(moy_moy.get(position)));
        holder.moy_result.setText(String.valueOf(moy_result.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(moy_id.get(position)));
                intent.putExtra("cin", String.valueOf(moy_cin.get(position)));
                intent.putExtra("moy", String.valueOf(moy_moy.get(position)));
                intent.putExtra("res", String.valueOf(moy_result.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moy_id.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView moy_id , moy_cin , moy_moy , moy_result ;
        LinearLayout  mainLayout ;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            moy_id = itemView.findViewById(R.id.moy_id_txt);
            moy_cin = itemView.findViewById(R.id.moy_cin_txt);
            moy_moy = itemView.findViewById(R.id.moy_moy_txt);
            moy_result = itemView.findViewById(R.id.moy_res_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout) ;
        }
    }
}
