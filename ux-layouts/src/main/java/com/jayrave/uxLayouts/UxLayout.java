package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public interface UxLayout<LP extends ViewGroup.LayoutParams & UxLayoutParams> {

    @NonNull
    LP generateDefaultLayoutParams();

    @NonNull
    LP generateLayoutParams(AttributeSet attrs);

    @Nullable
    View getLoadingView();

    @Nullable
    View getContentView();

    @Nullable
    View getEmptyStateView();

    @Nullable
    View getErrorView();

    @Nullable
    View getRetryView();

    void showOnlyLoadingView();
    void showOnlyContentView();
    void showOnlyEmptyStateView();
    void showOnlyErrorView();
    void showOnlyRetryView();
}
