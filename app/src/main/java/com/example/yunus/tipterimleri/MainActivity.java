package com.example.yunus.tipterimleri;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.SearchSuggestionsAdapter;
import com.example.yunus.tipterimleri.Adapter.SearchResultsListAdapter;
import com.example.yunus.tipterimleri.Infrastructure.IOgetValue;
import com.example.yunus.tipterimleri.Infrastructure.ws;
import com.example.yunus.tipterimleri.Model.Kelime;
import com.example.yunus.tipterimleri.Model.searchFilter;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity{
    List<Kelime> Kelimeler=new ArrayList<Kelime>();

    private final String TAG = "MainActivity";

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;

    private FloatingSearchView mSearchView;

    private RecyclerView mSearchResultsList;
    private SearchResultsListAdapter mSearchResultsAdapter;

    private DrawerLayout mDrawerLayout;

    private boolean mIsDarkSearchTheme = false;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchResultsList = (RecyclerView) findViewById(R.id.search_results_list);
        mSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {

            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
                mSearchView.showProgress();
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    mSearchView.clearSuggestions();
                } else {

                    //this shows the top left circular progress
                    //you can call it where ever you want, but
                    //it makes sense to do it when loading something in
                    //the background.
                    IOgetValue service= ws.getwsValue();
                    Call<List<Kelime>> control = service.search(new searchFilter(newQuery));
                    control.enqueue(new Callback<List<Kelime>>() {
                        @Override
                        public void onResponse(Response<List<Kelime>> response) {

                            Kelimeler = response.body();
                            mSearchView.swapSuggestions(Kelimeler);
                            mSearchView.hideProgress();

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            mSearchView.hideProgress();
                        }
                    });


                    //simulates a query call to a data source
                    //with a new query.




                }

                Log.d(TAG, "onSearchTextChanged()");
            }
        });
    }

    @Override
    public void onBackPressed() {

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
