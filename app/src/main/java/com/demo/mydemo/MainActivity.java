package com.demo.mydemo;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "ShoppingListActivity";
    private Toolbar toolbar;
    private SearchView searchView;
    private MenuItem searchItem;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Shopping-List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSearchable();

    }

    private void setSearchable(){

        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = new SearchView(getSupportActionBar().getThemedContext());
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(false);
        searchView.setIconifiedByDefault(false);
        searchView.setMaxWidth(1000);

        SearchView.SearchAutoComplete searchAutoComplete = searchView
                .findViewById(android.support.v7.appcompat.R.id.search_src_text);


        // Collapse the search menu when the user hits the back key
        searchAutoComplete.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d(TAG, "onFocusChange");
                showSearch(false);
            }
        });

        try {
            // This sets the cursor
            // resource ID to 0 or @null
            // which will make it visible
            // on white background
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");

            mCursorDrawableRes.setAccessible(true);
            //mCursorDrawableRes.set(searchAutoComplete, 0);

        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }


    }

    protected void showSearch(boolean visible) {

        if (visible) {
            Log.d(TAG, "expand");
            searchItem.expandActionView();
        }else {
            Log.d(TAG, "collapse");
            searchItem.collapseActionView();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        this.menu = menu;

        searchItem = menu.add(android.R.string.search_go);
        searchItem.setIcon(R.drawable.ic_search);
        searchItem.setActionView(searchView);
        searchItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

        getMenuInflater().inflate(R.menu.menu, menu);

        /**************************************************/
        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // Get SearchView autocomplete object.
        //final SearchView.SearchAutoComplete searchAutoComplete = findViewById(android.support.v7.appcompat.R.id.search_src_text);
        AutoCompleteTextView searchAutoComplete = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        //CustomAutoCompleteView searchAutoComplete = mSearchView.findViewById(R.id.myautocomplete);
        searchAutoComplete.setThreshold(1); //will start working from first character
        //searchAutoComplete.setDropDownBackgroundResource(android.R.color.holo_blue_light);

        String dataArray[] = {"Apple" , "Amazon"};

        /*ArrayList<SearchArticle> dataArray = new ArrayList<>();
        SearchArticle searchArticle = new SearchArticle();
        searchArticle.setArticleId(2);
        searchArticle.setArticleName("Arno");
        searchArticle.setArticleImagePath("https://www.lineageosroms.org/wp-content/uploads/2017/08/aosp-android-oreo-list.jpg");
        dataArray.add(searchArticle);*/


        /*ArrayAdapter<String> adapter = new ArrayAdapter<String> (this, android.R.layout.simple_dropdown_item_1line) {

            @Override
            public View getView(int position,
                                View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                //text1.setText(dataArray.get(position).getArticleName());
                text1.setText(dataArray[position].toString());

                return view;
            }

        };*/



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, dataArray);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, dataArray);
        //SearchAdapter adapter = new SearchAdapter(this, R.layout.customer_auto, dataArray);

        searchAutoComplete.setAdapter(adapter);


        /**************************************************/

        //MenuItem searchItem = menu.findItem(R.id.articleSearch);
        //SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, "onQueryTextChange");
                return false;
            }
        });

        return true;
    }
}
