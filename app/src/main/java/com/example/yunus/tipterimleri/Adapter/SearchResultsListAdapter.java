package com.example.yunus.tipterimleri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.arlib.floatingsearchview.util.Util;
import com.example.yunus.tipterimleri.Model.Ceviri;
import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunus on 6/16/2016.
 */

public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private List<Ceviri> arrayList;
    private Context context;


    public SearchResultsListAdapter(Context context,
                                List<Ceviri>arrayList){
        this.context=context;
        this.arrayList=arrayList;

    }


    @Override
    public int getItemCount(){
        return(null!=arrayList?arrayList.size():0);

    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder,
                                 final int position){
        final SearchViewHolder mainHolder=(SearchViewHolder)holder;
        //Setting text over textview

        mainHolder.Ceviri.setText(arrayList.get(position).getCeviri1());
        mainHolder.Aciklama.setText(arrayList.get(position).getAciklama());

    }

    @Override
    public SearchViewHolder onCreateViewHolder(
            ViewGroup viewGroup,int viewType){
        LayoutInflater mInflater=LayoutInflater.from(viewGroup.getContext());

        ViewGroup mainGroup=(ViewGroup)mInflater.inflate(
                R.layout.search_results_list_item,viewGroup,false);
        SearchViewHolder mainHolder=new SearchViewHolder(mainGroup){
            @Override
            public String toString(){
                return super.toString();
            }
        };


        return mainHolder;

    }
}