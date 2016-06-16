package com.example.yunus.tipterimleri.Infrastructure;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by yunus on 6/16/2016.
 */
public class ws {
    public static IOgetValue getwsValue(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://tipsozluk.yunusbayazit.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(IOgetValue.class);
    }
}
