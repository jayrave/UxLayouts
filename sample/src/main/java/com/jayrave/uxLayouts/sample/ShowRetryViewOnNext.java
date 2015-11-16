package com.jayrave.uxLayouts.sample;

import android.support.annotation.NonNull;

import com.jayrave.uxLayouts.UxLayout;

class ShowRetryViewOnNext<T> extends UxLayoutWeakReferenceHolderOnNext<T> {

    public ShowRetryViewOnNext(@NonNull UxLayout uxLayout) {
        super(uxLayout);
    }

    @Override
    public void call(T t) {
        UxLayout uxLayout = getUxLayout();
        if (uxLayout != null) {
            uxLayout.showOnlyRetryView();
        }
    }
}
