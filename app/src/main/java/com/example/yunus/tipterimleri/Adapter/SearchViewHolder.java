package com.example.yunus.tipterimleri.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunus.tipterimleri.R;

/**
 * Created by yunus on 6/20/2016.
 */
public class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView Ceviri;
    TextView Aciklama;


    public final Context context;

    public SearchViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        this.Ceviri = (TextView) itemView.findViewById(R.id.ceviri);
        this.Aciklama = (TextView) itemView.findViewById(R.id.aciklama);


    }

}
