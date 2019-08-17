package com.rejsebuddy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rejsebuddy.address.Address;
import com.rejsebuddy.address.AddressInputFragment;
import com.rejsebuddy.helpers.UserLocation;

class TripsFragment extends Fragment implements AddressInputFragment.OnAddressChangeListener {

    /**
     * TODO
     */
    private Address from;

    /**
     * TODO
     */
    private AddressInputFragment fromInput;

    /**
     * TODO
     */
    private Address to;

    /**
     * TODO
     */
    private AddressInputFragment toInput;

    /**
     * TODO
     */
    private Button travel;

    /**
     * TODO
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trips, container, false);
    }

    /**
     * TODO
     *
     * @param view The contacts row view.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(View view, Bundle state) {
        // Call super class.
        super.onViewCreated(view, state);

        // Bind request user location address button.
        ImageButton request = view.findViewById(R.id.request_location);
        request.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            try {
                // Query user location and set address.
                from = new UserLocation().request(view.getContext(), getActivity());
                fromInput.setAddress(from);

                // Enable travel button if from/to is set.
                if (to != null && from != null) {
                    travel.setEnabled(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }

        });

        // Bind travel button to instance.
        this.travel = view.findViewById(R.id.travel);

        // Get and save address input fragments.
        FragmentManager manager = getChildFragmentManager();

        this.toInput = (AddressInputFragment) manager.findFragmentById(R.id.to);
        this.toInput.setOnAddressChangeListener(this);

        this.fromInput = (AddressInputFragment) manager.findFragmentById(R.id.from);
        this.fromInput.setOnAddressChangeListener(this);
    }

    /**
     * Saves the changed address and switches the travel btn.
     *
     * @param view The activated view.
     * @param address The selected address.
     */
    @Override
    public void onAddressChanged(View view, Address address) {
        // Switch view id to save change.
        switch (view.getId()) {
            case R.id.to:
                this.to = address;
                break;

            case R.id.from:
                this.from = address;
                break;
        }

        // Enable travel button if from/to is set.
        if (this.to != null && this.from != null) {
            this.travel.setEnabled(true);
        }
    }

}
