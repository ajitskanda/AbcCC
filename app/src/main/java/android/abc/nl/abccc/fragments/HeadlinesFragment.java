package android.abc.nl.abccc.fragments;

import android.abc.nl.abccc.R;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Ajit Skanda K on 08-05-2016.
 */

public class HeadlinesFragment extends ListFragment {

    static String[] Headlines = {
            "Article One",
            "Article Two"
    };

    static String[] Articles = {
            "Article One\n\nExcepteur pour-over occaecat squid biodiesel umami gastropub, " +
                    "nulla laborum salvia dreamcatcher fanny pack. Ullamco culpa retro ea, trust " +
                    "fund excepteur eiusmod direct trade banksy nisi lo-fi cray messenger bag. " +
                    "Nesciunt esse carles selvage put a bird on it gluten-free, wes anderson ut" +
                    " trust fund twee occupy viral. Laboris small batch scenester pork belly, " +
                    "leggings ut farm-to-table aliquip yr nostrud iphone viral next level. " +
                    "Craft beer dreamcatcher pinterest truffaut ethnic, authentic brunch. " +
                    "Esse single-origin coffee banksy do next level tempor. Velit synth " +
                    "dreamcatcher, magna shoreditch in american apparel messenger " +
                    "bag narwhal PBR ennui farm-to-table.",
            "Article Two\n\nVinyl williamsburg non velit, master cleanse four loko banh mi. " +
                    "Enim kogi keytar trust fund pop-up portland gentrify. Non ea typewriter " +
                    "dolore deserunt Austin. Ad magna ethical kogi mixtape next level. Aliqua " +
                    "pork belly thundercats, ut pop-up tattooed dreamcatcher kogi accusamus photo " +
                    "booth irony portland. Semiotics brunch ut locavore irure, enim etsy laborum " +
                    "stumptown carles gentrify post-ironic cray. Butcher 3 wolf moon blog synth, " +
                    "vegan carles odd future."
    };

    OnHeadlineSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnHeadlineSelectedListener {
        /** Called by HeadlinesFragment when a list item is selected */
        void onArticleSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Headlines));
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Notify the parent activity of selected item
        mCallback.onArticleSelected(position);

        // Set the item as checked to be highlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }
}
