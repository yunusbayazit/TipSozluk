package com.example.yunus.tipterimleri.Infrastructure;

import com.example.yunus.tipterimleri.Model.Ceviri;
import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.Model.searchFilter;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by yunus on 6/16/2016.
 */
public interface IOgetValue {
    @POST("Sozluk/Kelime_Listele")
    Call<List<Kelime>> search(@Body searchFilter gonderifilter);

    @GET("Sozluk/Ceviri_Listele/{id}")
    Call<List<Ceviri>> Ceviri(@Path("id") int value);
}
