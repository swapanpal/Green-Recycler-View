package com.example.greenrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
// From GreenAdapter, extend RecyclerView.Adapter<NumberViewHolder>
/**
        * We couldn't come up with a good name for this class. Then, we realized
        * that this lesson is about RecyclerView.
        *
        * RecyclerView... Recycling... Saving the planet? Being green? Anyone?
        * #crickets
        *
        * Avoid unnecessary garbage collection by using RecyclerView and ViewHolders.
        *
        * If you don't like our puns, we named this Adapter GreenAdapter because its
        * contents are green.
        */
public class GreenAdapter extends RecyclerView.Adapter<GreenAdapter.NumberViewHolder> {

    private static final String TAG = GreenAdapter.class.getSimpleName();

    /*
     * An on-click handler that we've defined to make it easy for an Activity to interface with
     * our RecyclerView
     */
    final private ListItemClickListener mOnClickListener;

    //  Add a private static int called viewHolderCount that will hold the total number of ViewHolders that are created
    /*
     * The number of ViewHolders that have been created. Typically, you can figure out how many
     * there should be by determining how many list items fit on your screen at once and add 2 to 4
     * to that number. That isn't the exact formula, but will give you an idea of how many
     * ViewHolders have been created to display any given RecyclerView.
     *
     * Here's some ASCII art to hopefully help you understand:
     *
     *    ViewHolders on screen:
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 0 |
     *        *-----------------------------*
     *        |         ViewHolder index: 1 |
     *        *-----------------------------*
     *        |         ViewHolder index: 2 |
     *        *-----------------------------*
     *        |         ViewHolder index: 3 |
     *        *-----------------------------*
     *        |         ViewHolder index: 4 |
     *        *-----------------------------*
     *        |         ViewHolder index: 5 |
     *        *-----------------------------*
     *        |         ViewHolder index: 6 |
     *        *-----------------------------*
     *        |         ViewHolder index: 7 |
     *        *-----------------------------*
     *
     *    Extra ViewHolders (off screen)
     *
     *        *-----------------------------*
     *        |         ViewHolder index: 8 |
     *        *-----------------------------*
     *        |         ViewHolder index: 9 |
     *        *-----------------------------*
     *        |         ViewHolder index: 10|
     *        *-----------------------------*
     *        |         ViewHolder index: 11|
     *        *-----------------------------*
     *
     *    Total number of ViewHolders = 11
     */
    private static int viewHolderCount;


    // Add a private int variable called mNumberItems
    private int mNumberItems;

    // COMPLETED (1) Add an interface called ListItemClickListener
    // COMPLETED (2) Within that interface, define a void method called onListItemClick that takes an int as a parameter
    /**
     * The interface that receives onClick messages.
     */
    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    // Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    // Store the numberOfItems parameter in mNumberItems
    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     * @param numberOfItems Number of items to display in list
     */
    public GreenAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    // Override the onCreateViewHolder method
    // Create and return a new NumberViewHolder within this method
    /**
     *
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (which ours doesn't) you
     *                  can use this viewType integer to provide a different layout. See
     *                  {@link android.support//.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
     *                  for more details.
     * @return A new NumberViewHolder that holds the View for each list item
     */
    @Override
    public NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.number_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        NumberViewHolder viewHolder = new NumberViewHolder(view);

        //  Set the text of viewHolderIndex to "ViewHolder index: " + viewHolderCount
        viewHolder.viewHolderIndex.setText("ViewHolder index: " + viewHolderCount);

        //  Use ColorUtils.getViewHolderBackgroundColorFromInstance and pass in a Context and the viewHolderCount
        int backgroundColorForViewHolder = ColorUtils
                .getViewHolderBackgroundColorFromInstance(context, viewHolderCount);

        //  Set the background color of viewHolder.itemView with the color from above
        viewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);


        // Increment viewHolderCount and log its value
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);

        return viewHolder;
    }

    //  Override onBindViewHolder
    //  Within onBindViewHolder, call holder.bind and pass in the position
    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(NumberViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    // Override getItemCount and return the number of items to display
    /**
     * This method simply returns the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in our forecast
     */
    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    //  Create a class called NumberViewHolder that extends RecyclerView.ViewHolder
    /**
     * Cache of the children views for a list item.
     */
    class NumberViewHolder extends RecyclerView.ViewHolder
    implements View.OnClickListener {

        //  Within NumberViewHolder, create a TextView variable called listItemNumberView
        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;

        //  Add a TextView variable to display the ViewHolder index
        // Will display which ViewHolder is displaying this data
        TextView viewHolderIndex;

        //Create a constructor for NumberViewHolder that accepts a View called itemView as a parameter

        /**
         * Constructor for our ViewHolder. Within this constructor, we get a reference to our
         * TextViews and set an onClickListener to listen for clicks. Those will be handled in the
         * onClick method below.
         * @param itemView The View that you inflated in
         *                 {@link GreenAdapter#onCreateViewHolder(ViewGroup, int)}
         */
        public NumberViewHolder(View itemView) {
            // Within the constructor, call super(itemView) and then find listItemNumberView by ID

            super(itemView);

            listItemNumberView = (TextView) itemView.findViewById(R.id.tv_item_number);

            //  Use itemView.findViewById to get a reference to tv_view_holder_instance
            viewHolderIndex = (TextView) itemView.findViewById(R.id.tv_view_holder_instance);

            //  Call setOnClickListener on the View passed into the constructor
            //  (use 'this' as the OnClickListener)
            itemView.setOnClickListener(this);
        }

        // Within the NumberViewHolder class, create a void method called bind that
        //  accepts an int parameter called listIndex
        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex) {
            // Within bind, set the text of listItemNumberView to the listIndex
            // Be careful to get the String representation of listIndex, as using setText with an int does something different

            listItemNumberView.setText(String.valueOf(listIndex));
        }

        /**
         * Called whenever a user clicks on an item in the list.
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }


}
