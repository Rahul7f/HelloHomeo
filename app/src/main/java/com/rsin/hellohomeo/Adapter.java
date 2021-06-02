package com.rsin.hellohomeo;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.rsin.hellohomeo.Room.Crews;
import com.rsin.hellohomeo.Room.MyDatabase;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Crews> crewModelList;
    Context context;

    public Adapter(List<Crews> crewModelList, Context context) {
        this.crewModelList = crewModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        if (crewModelList.get(position).getStatus().equals("active"))
        {
            holder.status.setText(crewModelList.get(position).getStatus());
        }
        else {
            holder.indicator.setColorFilter(ContextCompat.getColor(context, R.color.offline), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.status.setTextColor(Color.parseColor("#FF5722"));
            holder.status.setText("offline");
        }

        holder.name.setText(crewModelList.get(position).getName());
        holder.agency.setText(crewModelList.get(position).getAgency());

        Glide.with(context).load(crewModelList.get(position).getImage()).into(holder.crew_image);
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase myDatabase = Room.databaseBuilder(context,MyDatabase.class,"CreqwDB")
                        .allowMainThreadQueries()
                        .build();
                myDatabase.dao().crewDelete(crewModelList.get(position));
                crewModelList.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.wiki_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(crewModelList.get(position).getWikipedia()));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return crewModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView crew_image,indicator,delete_btn;
        TextView name,agency,status;
        Button wiki_bnt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            agency = itemView.findViewById(R.id.agency);
            status = itemView.findViewById(R.id.status);
            wiki_bnt = itemView.findViewById(R.id.wiki_bnt);
            crew_image = itemView.findViewById(R.id.memeber_img);
            indicator = itemView.findViewById(R.id.indicator);
            delete_btn = itemView.findViewById(R.id.delete);
        }
    }
}
