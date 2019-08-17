package com.rejsebuddy.address;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;

import com.rejsebuddy.R;
import com.rejsebuddy.api.tasks.SearchLocationsTask;
import com.rejsebuddy.helpers.DebouncedTextWatcher;

import java.util.List;

public class AddressInputFragment extends Fragment implements AdapterView.OnItemClickListener {

    /**
     * The auto complete text view instance.
     */
    private AutoCompleteTextView input;

    /**
     * The input address change listener.
     */
    private OnAddressChangeListener listener;

    /**
     * Inflates the contacts fragment.
     *
     * @param inflater The layout inflater instance.
     * @param container The group view container.
     * @param state The saved state of the view.
     * @return The inflated fragment view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_address_input, container, false);
    }

    /**
     * Prepares and saves the autocomplete input.
     *
     * @param view The contacts row view.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(View view, Bundle state) {
        // Call super class.
        super.onViewCreated(view, state);

        // Find input and bind on item select.
        this.input = view.findViewById(R.id.input);
        this.input.setOnItemClickListener(this);

        // Add debounced text watcher to search locations.
        this.input.addTextChangedListener(new DebouncedTextWatcher() {

            @Override
            public void textChanged(String str) {
                new SearchLocations(getContext(), AddressInputFragment.this).execute(str);
            }

        });
    }

    /**
     * Binds the on address change listener.
     *
     * @param ctx The application context.
     */
    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);

        try {
            this.listener = (OnAddressChangeListener) ctx;
        } catch (Exception e) {}
    }

    /**
     * Emits the selected address to the listener.
     *
     * @param adapter The data adapter.
     * @param view The current view.
     * @param pos The position of the element.
     * @param id The id of the element.
     */
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
        if (this.listener == null) return;
        this.listener.onAddressChanged((Address) adapter.getItemAtPosition(pos));
    }

    /**
     * Sets the input text property.
     *
     * @param address The address to display.
     */
    public void setAddress(Address address) {
        this.input.setText(address.getAddress());
    }

    /**
     * Interface to listen for address changes.
     */
    public interface OnAddressChangeListener {

        /**
         * Called when the address is changed.
         *
         * @param address The selected address.
         */
        void onAddressChanged(Address address);

    }

    /**
     * Search for locations by autocomplete input text.
     */
    private static class SearchLocations extends SearchLocationsTask<AddressInputFragment> {

        /**
         * Call parent super constructor.
         *
         * @param ctx The application context.
         * @param instance The current class instance.
         */
        SearchLocations(Context ctx, AddressInputFragment instance) {
            super(ctx, instance);
        }

        /**
         * Save async results to new adapter instance.
         *
         * @param results The query list result.
         */
        protected void onPostExecute(List<Address> results) {
            // Create new simple list adapter with results.
            ArrayAdapter adapter = new ArrayAdapter<>(
                this.getContext(),
                android.R.layout.simple_list_item_1,
                results
            );

            // Set adapter on input and notify of change.
            this.getInstance().input.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }

}
