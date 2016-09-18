package com.example.yunus.tipterimleri.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.Model.favori;
import com.example.yunus.tipterimleri.R;

import java.util.List;

/**
 * Created by yunus on 6/26/2016.
 */
public class Favori_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<favori> mKisiListesi;

    public Favori_Adapter(Activity activity, List<favori> kisiler) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mKisiListesi = kisiler;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public favori getItem(int position) {
        //şöyle de olabilir: public Object getItem(int position)
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.favori_satir, null);
        TextView textView =
                (TextView) satirView.findViewById(R.id.cevirifavori);
        TextView textView2 =
                (TextView) satirView.findViewById(R.id.kelimefavori);
        final ImageView imageView =
                (ImageView) satirView.findViewById(R.id.favoriButton);
        final favori kisi = mKisiListesi.get(position);

        if (satirView != null){

            if (kisi.getIsFavorite()==0){
            }
            else {

            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (kisi.getIsFavorite()==0){
                kisi.setIsFavorite(1);
                favori person = kisi;
                person.save();
            }
                else {
                kisi.setIsFavorite(0);
                new Delete().from(Kelime.class).where("Kelime_id = ?", kisi.getKelime_id()).execute();

            }
            }
        });

        textView.setText(kisi.getCeviri_adi());
        textView2.setText(kisi.getKelime_adi());

        return satirView;
    }
}
