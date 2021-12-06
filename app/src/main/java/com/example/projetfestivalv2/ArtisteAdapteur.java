package com.example.projetfestivalv2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.projetfestivalv2.classes.Artiste;

import java.util.ArrayList;

public class ArtisteAdapteur extends ArrayAdapter<Artiste> {
    private Context mContext;
    private int mRessource;

    public ArtisteAdapteur(@NonNull Context context, int resource, @NonNull ArrayList<Artiste> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mRessource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mRessource,parent,false);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView txtName = convertView.findViewById(R.id.txtName);

        Glide.with(convertView).load(getItem(position).getPhoto()).into(imageView);

        txtName.setText(getItem(position).getNom());
        return convertView;
    }
}