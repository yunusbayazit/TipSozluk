package com.example.yunus.tipterimleri;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import com.example.yunus.tipterimleri.Adapter.Favori_Adapter;
import com.example.yunus.tipterimleri.Adapter.SearchResultsListAdapter;
import com.example.yunus.tipterimleri.Infrastructure.IOgetValue;
import com.example.yunus.tipterimleri.Infrastructure.ws;
import com.example.yunus.tipterimleri.Model.Ceviri;
import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.Model.favori;
import com.example.yunus.tipterimleri.Model.searchFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity{
    List<Kelime> Kelimeler=new ArrayList<Kelime>();
    List<Ceviri> Ceviriler=new ArrayList<Ceviri>();
    List<favori> favoriler=new ArrayList<favori>();

    private final String TAG = "MainActivity";

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private FloatingSearchView mSearchView;

    private RecyclerView mSearchResultsList;
    private SearchResultsListAdapter mSearchResultsAdapter;
    private boolean mIsDarkSearchTheme = false;
    boolean history;
    private ProgressBar progress;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progress=(ProgressBar) findViewById(R.id.progressBar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        for (int i =0;i<10;i++){
        favori kisi=new favori("Kelime adı"+i,"Ceviri"+i,1,1);
        favori person = kisi;
        person.save();}
        final List<favori> allfavorites = new Select().all().from(favori.class).execute();
        favoriler=allfavorites;
        Collections.reverse(favoriler);

        final ListView listemiz = (ListView) findViewById(R.id.left_drawer);
        Favori_Adapter adaptorumuz=new Favori_Adapter(this, favoriler);
        listemiz.setAdapter(adaptorumuz);
        mSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {

                if (item.getItemId() == R.id.action_voice_rec) {


                    new Delete().from(Kelime.class).execute();

                }

            }
        });
        mSearchView.setOnBindSuggestionCallback(new SearchSuggestionsAdapter.OnBindSuggestionCallback() {
            @Override
            public void onBindSuggestion(View suggestionView, ImageView leftIcon,
                                         TextView textView, SearchSuggestion item, int itemPosition) {
                Kelime colorSuggestion = (Kelime) item;

                String textColor = mIsDarkSearchTheme ? "#ffffff" : "#000000";
                String textLight = mIsDarkSearchTheme ? "#bfbfbf" : "#787878";

                if (history) {
                    leftIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                            R.drawable.ic_history_black_24dp, null));

                    Util.setIconColor(leftIcon, Color.parseColor(textColor));
                    leftIcon.setAlpha(.36f);
                } else {
                    leftIcon.setAlpha(0.0f);
                    leftIcon.setImageDrawable(null);
                }

                textView.setTextColor(Color.parseColor(textColor));
                String text = colorSuggestion.getBody()
                        .replaceFirst(mSearchView.getQuery(),
                                "<font color=\"" + textLight + "\">" + mSearchView.getQuery() + "</font>");
                textView.setText(Html.fromHtml(text));
            }

        });
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                mSearchView.showProgress();
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else if(newQuery.length()>1){

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    IOgetValue service= ws.getwsValue();
                    Call<List<Kelime>> control = service.search(new searchFilter(newQuery));
                    control.enqueue(new Callback<List<Kelime>>() {
                        @Override
                        public void onResponse(Response<List<Kelime>> response) {
                            history=false;
                            Kelimeler = response.body();
                            mSearchView.swapSuggestions(Kelimeler);
                            mSearchView.hideProgress();
                        }
                        @Override
                        public void onFailure(Throwable t) {
                            mSearchView.hideProgress();
                        }
                    });
                }
                Log.d(TAG, "onSearchTextChanged()");
            }
        });

        mSearchView.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {
                mSearchResultsList.setTranslationY(newHeight);
            }
        });
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                progress.setVisibility(View.VISIBLE);
                final Kelime colorSuggestion = (Kelime) searchSuggestion;
                IOgetValue service= ws.getwsValue();
                Call<List<Ceviri>> control = service.Ceviri(colorSuggestion.getIdi());
                control.enqueue(new Callback<List<Ceviri>>() {
                    @Override
                    public void onResponse(Response<List<Ceviri>> response) {

                        Ceviriler = response.body();
                        setRecyclerView();
                        progress.setVisibility(View.GONE);
                        mSearchView.hideProgress();

                        List<Kelime> bence =new Select ()
                                .from ( Kelime.class )
                                .where ( "kelime_id = ?", colorSuggestion.getIdi())
                                .execute();
                             if(bence.size()!=0 ){
                            new Delete().from(Kelime.class).where("kelime_id = ?", colorSuggestion.getIdi()).execute();
                            Kelime person = new Kelime(colorSuggestion.getKelime_adi(),colorSuggestion.getIdi());
                            person.save();

                                }
                        else {
                                    Kelime person = colorSuggestion;
                                    person.save();
}

            }

            @Override
            public void onFailure(Throwable t) {
                mSearchView.hideProgress();
            }
        });

    }
    @Override
    public void onSearchAction(String query) {
    }
});

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
@Override
public void onFocus() {
        mSearchView.clearQuery();
    mSearchView.hideProgress();
    history=true;
                //show suggestions when search bar gains focus (typically history suggestions)
    final List<Kelime> allAuthors = new Select().all().from(Kelime.class).execute();
    if ( allAuthors!= null){  Collections.reverse(allAuthors); mSearchView.swapSuggestions(allAuthors);}


    Log.d(TAG, "onFocus()");
            }

            @Override
            public void onFocusCleared() {
                mSearchView.hideProgress();
                Log.d(TAG, "onFocusCleared()");
            }
        });
    }

    private void setupResultsList() {

    }
    @Override
    public void onBackPressed() {
        if (!mSearchView.setSearchFocused(false)) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Hakkında) {
            startActivity(new Intent(getBaseContext(),HakkindaActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    } private void setRecyclerView() {

        mSearchResultsList = (RecyclerView) findViewById(R.id.search_results_list);

        mSearchResultsList.setHasFixedSize(true);
        mSearchResultsList
                .setLayoutManager(new LinearLayoutManager(getBaseContext()));//Linear Items
        SearchResultsListAdapter adapter = new SearchResultsListAdapter(getApplicationContext(), Ceviriler);
        mSearchResultsList.setAdapter(adapter);// set adapter on recyclerview

    }
}
