package com.example.greenrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
implements GreenAdapter.ListItemClickListener{

    // Create a private static final int called NUM_LIST_ITEMS and set it equal to 100

    private static final int NUM_LIST_ITEMS = 100;

    // Create a GreenAdapter variable called mAdapter
    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;

    // Create a RecyclerView variable called mNumbersList
    private RecyclerView mNumbersList;

    // Create a Toast variable called mToast to store the current Toast
    /*
     * If we hold a reference to our Toast, we can cancel it (if it's showing)
     * to display a new Toast. If we didn't do this, Toasts would be delayed
     * in showing up if you clicked many list items in quick succession.
     */
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Use findViewById to store a reference to the RecyclerView in mNumbersList
        /*
         * Using findViewById, we get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        // Create a LinearLayoutManager variable called layoutManager
        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // Use setLayoutManager on mNumbersList with the LinearLayoutManager we created above
        mNumbersList.setLayoutManager(layoutManager);

        // COMPLETED (7) Use setHasFixedSize(true) to designate that the contents of the
        //  RecyclerView won't change an item's size
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);


        //  Store a new GreenAdapter in mAdapter and pass it NUM_LIST_ITEMS
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        // Pass in this as the ListItemClickListener to the GreenAdapter constructor
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);

        // Set the GreenAdapter you created on mNumbersList
        mNumbersList.setAdapter(mAdapter);
    }

    // Override onCreateOptionsMenu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        /*
         * When you click the reset menu item, we want to start all over
         * and display the pretty gradient again. There are a few similar
         * ways of doing this, with this one being the simplest of those
         * ways. (in our humble opinion)
         */
        switch (itemId){
            case R.id.action_refresh:
                //  Pass in this as the ListItemClickListener to the GreenAdapter constructor
                mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //  Override ListItemClickListener's onListItemClick method
    /**
     * This is where we receive our callback from
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {
        //  In the beginning of the method, cancel the Toast if it isn't null
        /*
         * Even if a Toast isn't showing, it's okay to cancel it. Doing so
         * ensures that our new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         *
         * Comment out these three lines, run the app, and click on a bunch of
         * different items if you're not sure what I'm talking about.
         */
        if (mToast != null) {
            mToast.cancel();
        }

        // Show a Toast when an item is clicked, displaying that item number that was clicked
        /*
         * Create a Toast and store it in our Toast field.
         * The Toast that shows up will have a message similar to the following:
         *
         *                     Item #42 clicked.
         */
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}
