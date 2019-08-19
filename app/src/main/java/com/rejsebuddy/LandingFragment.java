package com.rejsebuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.rejsebuddy.api.models.Address;
import com.rejsebuddy.helpers.UserLocation;
import com.rejsebuddy.views.address.AddressInputFragment;
import com.rejsebuddy.views.connections.ConnectionsListActivity;

@SuppressWarnings("FieldCanBeLocal")
class LandingFragment extends Fragment implements View.OnClickListener, AddressInputFragment.OnAddressChangeListener {

    /**
     * The selected from address.
     */
    private Address from;

    /**
     * The from address input fragment.
     */
    private AddressInputFragment fromInput;

    /**
     * The selected to address.
     */
    private Address to;

    /**
     * The to address input fragment.
     */
    private AddressInputFragment toInput;

    /**
     * The begin travel button instance.
     */
    private Button travel;

    /**
     * Inflates the contacts fragment.
     *
     * @param inflater The layout inflater instance.
     * @param container The group view container.
     * @param state The saved state of the view.
     * @return The inflated fragment view.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.fragment_landing, container, false);
    }

    /**
     * Binds the view elements to the instance.
     *
     * @param view The current view instance.
     * @param state The previous view state.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle state) {
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
        this.travel.setOnClickListener(this);

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

    /**
     * Parse addresses to connections activity.
     *
     * @param view The current view.
     */
    @Override
    public void onClick(View view) {
        // Get context from view.
        Context ctx = view.getContext();

        // Start activity with to and from address.
        Intent intent = new Intent(ctx, ConnectionsListActivity.class);
        intent.putExtra("TO_ADDRESS", to);
        intent.putExtra("FROM_ADDRESS", from);
        ctx.startActivity(intent);
    }

}
