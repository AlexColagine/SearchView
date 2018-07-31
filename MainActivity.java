package com.example.android.popularmovies.UI;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Fragments.Favorite;
import com.example.android.popularmovies.Fragments.PopularRated;
import com.example.android.popularmovies.R;

import static com.example.android.popularmovies.Utils.Utils.QUERY_MOVIE;
import static com.example.android.popularmovies.Utils.Utils.QUERY_POPULAR_PATH;
import static com.example.android.popularmovies.Utils.Utils.QUERY_TOP_RATED_PATH;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public TextView tabOne;
    public TextView tabTwo;
    public TextView tabThree;
    private int[] imageResId = {
            R.drawable.most_popular_image,
            R.drawable.top_rated_image,
            R.drawable.favorite_image,
            R.drawable.most_popular_image_yellow,
            R.drawable.top_rated_image_yellow,
            R.drawable.favorite_image_yellow
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * The {@link android.support.v4.view.PagerAdapter} that will provide
         fragments for each of the sections. We use a
         {@link FragmentPagerAdapter} derivative, which will keep every
         loaded fragment in memory. If this becomes too memory intensive, it
         may be best to switch to a
         {@link android.support.v4.app.FragmentStatePagerAdapter}.
         *  _____________________________________________________________________
         * Create the adapter that will return a fragment for each of the three
         * primary sections of the activity.
         */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        /**
         * The {@link ViewPager} that will host the section contents.
         * ________________________________________________________
         * Set up the ViewPager with the sections adapter.
         */
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

        handleIntent(getIntent());

        QUERY_MOVIE = QUERY_POPULAR_PATH;
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @SuppressLint("NewApi")
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        QUERY_MOVIE = QUERY_POPULAR_PATH;
                        updateUiMostPopular();
                        break;
                    case 1:
                        QUERY_MOVIE = QUERY_TOP_RATED_PATH;
                        updateUiTopRated();
                        break;
                    case 2:
                        updateUiFavorite();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * METHODS :
     * <p>
     * 1 - updateUiMostPopular()
     * 2 - updateUiTopRated()
     * 3 - updateUiFavorite()
     * 4 - setupTabIcons()
     * <p>
     * They are used for the UI of the TabLayout. In particular to set a text of each Fragment with specific icon drawable.
     * They can be useful to change color of a single drawable when ViewPager scroll.
     */

    @SuppressLint("NewApi")
    private void updateUiMostPopular() {
        tabOne.setCompoundDrawablesWithIntrinsicBounds(imageResId[3], 0, 0, 0);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(imageResId[1], 0, 0, 0);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(imageResId[2], 0, 0, 0);

        tabOne.setTextColor(Color.WHITE);
        tabTwo.setTextColor(getColor(R.color.colorToolbar));
        tabThree.setTextColor(getColor(R.color.colorToolbar));
    }

    @SuppressLint("NewApi")
    private void updateUiTopRated() {
        tabOne.setCompoundDrawablesWithIntrinsicBounds(imageResId[0], 0, 0, 0);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(imageResId[4], 0, 0, 0);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(imageResId[2], 0, 0, 0);

        tabOne.setTextColor(getColor(R.color.colorToolbar));
        tabTwo.setTextColor(Color.WHITE);
        tabThree.setTextColor(getColor(R.color.colorToolbar));
    }

    @SuppressLint("NewApi")
    private void updateUiFavorite() {
        tabOne.setCompoundDrawablesWithIntrinsicBounds(imageResId[0], 0, 0, 0);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(imageResId[1], 0, 0, 0);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(imageResId[5], 0, 0, 0);

        tabOne.setTextColor(getColor(R.color.colorToolbar));
        tabTwo.setTextColor(getColor(R.color.colorToolbar));
        tabThree.setTextColor(Color.WHITE);
    }

    @SuppressLint({"InflateParams", "NewApi"})
    private void setupTabIcons() {

        tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.name_fragment, null);
        tabOne.setText(R.string.most_popular);
        tabOne.setTextColor(Color.WHITE);
        tabOne.setCompoundDrawablesWithIntrinsicBounds(imageResId[3], 0, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.name_fragment, null);
        tabTwo.setText(R.string.top_rated);
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(imageResId[1], 0, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.name_fragment, null);
        tabThree.setText(R.string.favorite);
        tabThree.setCompoundDrawablesWithIntrinsicBounds(imageResId[2], 0, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item_share = menu.findItem(R.id.action_share);
        item_share.setVisible(false);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchMenu).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchMenu:
                onSearchRequested();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //qui inserisci il metodo per la ricerca verso il server e passa la query come parametro
            doMySearch(query);
        }
    }

    private void doMySearch(String query) {
        Toast.makeText(this, "Ecco cosa hai ricercato: " + query, Toast.LENGTH_SHORT).show();
    }
}
