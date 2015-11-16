package com.jayrave.uxLayouts.sample;

import android.support.annotation.NonNull;

import com.jayrave.uxLayouts.UxLayout;

class ShowErrorViewOnNext<T> extends UxLayoutWeakReferenceHolderOnNext<T> {

    public ShowErrorViewOnNext(@NonNull UxLayout uxLayout) {
        super(uxLayout);
    }

    @Override
    public void call(T t) {
        UxLayout uxLayout = getUxLayout();
        if (uxLayout != null) {
            uxLayout.showOnlyErrorView();
        }
    }
}
