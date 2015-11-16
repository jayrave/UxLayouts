package com.jayrave.uxLayouts.sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jayrave.uxLayouts.UxLayout;

import java.lang.ref.WeakReference;

import rx.functions.Action1;

abstract class UxLayoutWeakReferenceHolderOnNext<T> implements Action1<T> {

    @NonNull
    private final WeakReference<UxLayout> mUxLayoutWeakReference;

    public UxLayoutWeakReferenceHolderOnNext(@NonNull UxLayout uxLayout) {
        mUxLayoutWeakReference = new WeakReference<>(uxLayout);
    }

    @Nullable
    public UxLayout getUxLayout() {
        return mUxLayoutWeakReference.get();
    }
}
