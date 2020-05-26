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

    // Add a private int variable called mNumberItems
    private int mNumberItems;

    // Create a constructor for GreenAdapter that accepts an int as a parameter for numberOfItems
    // Store the numberOfItems parameter in mNumberItems
    /**
     * Constructor for GreenAdapter that accepts a number of items to display and the specification
     * for the ListItemClickListener.
     *
     * @param numberOfItems Number of items to display in list
     */
    public GreenAdapter(int numberOfItems) {

        mNumberItems = numberOfItems;
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
    class NumberViewHolder extends RecyclerView.ViewHolder {

        //  Within NumberViewHolder, create a TextView variable called listItemNumberView
        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView listItemNumberView;

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
    }


}
