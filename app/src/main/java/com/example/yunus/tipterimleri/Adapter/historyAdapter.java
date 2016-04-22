package com.example.yunus.tipterimleri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.R;

import java.util.List;

/**
 * Created by yunus on 22.04.2016.
 */
public class historyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Kelime> mKisiListesi;

    public historyAdapter(Activity activity, List<Kelime> Kelimeler) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mKisiListesi = Kelimeler;
    }
    @Override
    public int getCount() {
        return mKisiListesi.size();    }

    @Override
    public Object getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.history_list, null);
        TextView textView =
                (TextView) satirView.findViewById(R.id.kelime);
        TextView textView2 =
                (TextView) satirView.findViewById(R.id.ceviri);
        Kelime kisi = mKisiListesi.get(position);

        textView.setText(kisi.getAdı());
        textView2.setText(kisi.getCeviri());
        return satirView;
    }
}
