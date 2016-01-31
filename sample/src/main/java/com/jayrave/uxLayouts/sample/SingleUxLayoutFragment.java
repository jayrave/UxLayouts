package com.jayrave.uxLayouts.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.jayrave.uxLayouts.UxLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SingleUxLayoutFragment extends Fragment {

    private static final int LOADING_DURATION_IN_MS = 2000;
    private static final int SHOW_CONTENT = 0;
    private static final int SHOW_EMPTY_STATE = 1;
    private static final int SHOW_ERROR = 2;
    private static final int SHOW_RETRY = 3;

    @Nullable private Subscription mSubscription;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_single_ux_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Populate spinner
        List<String> modesStrings = new ArrayList<>();
        modesStrings.add(getString(R.string.load_and_show_content));
        modesStrings.add(getString(R.string.load_and_show_empty_state));
        modesStrings.add(getString(R.string.load_and_show_error));
        modesStrings.add(getString(R.string.load_and_show_retry));

        final Spinner modes = (Spinner) view.findViewById(R.id.modes);
        modes.setAdapter(new ArrayAdapter<>(
                getActivity(), android.R.layout.simple_dropdown_item_1line, modesStrings
        ));

        // Show further detail to user on error state
        final UxLayout uxLayout = (UxLayout) view.findViewById(R.id.ux_layout);
        View errorView = uxLayout.getErrorView();
        if (errorView != null) {
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage(getString(R.string.disc_full_error))
                            .create()
                            .show();
                }
            });
        }

        // Show further detail to user on retry state
        View retryView = uxLayout.getRetryView();
        if (retryView != null) {
            retryView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modes.setSelection(SHOW_CONTENT);
                }
            });
        }

        final Observable<Void> delayObservable = Observable.just((Void) null)
                .delay(LOADING_DURATION_IN_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

        // Start loading depending on the selection option
        modes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mSubscription != null) {
                    mSubscription.unsubscribe();
                }

                uxLayout.showOnlyLoadingView();
                mSubscription = delayObservable.subscribe(
                        getAppropriateOnNextAction(position, uxLayout)
                );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                modes.setSelection(SHOW_CONTENT);
            }
        });
    }


    @Override
    public void onStop() {
        super.onStop();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }


    @NonNull
    private Action1<Void> getAppropriateOnNextAction(int position, UxLayout uxLayout) {
        switch (position) {
            case SHOW_CONTENT: return new ShowContentViewOnNext<>(uxLayout);
            case SHOW_EMPTY_STATE: return new ShowEmptyStateViewOnNext<>(uxLayout);
            case SHOW_ERROR: return new ShowErrorViewOnNext<>(uxLayout);
            case SHOW_RETRY: return new ShowRetryViewOnNext<>(uxLayout);
            default: throw new RuntimeException("Out of bounds: " + position);
        }
    }
}
