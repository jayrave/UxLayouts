package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class UxLayoutChildrenManager implements ViewGroup.OnHierarchyChangeListener {

    @NonNull private ViewGroup mHost;
    @Nullable private View mLoadingView;
    @Nullable private View mContentView;
    @Nullable private View mEmptyStateView;
    @Nullable private View mErrorView;
    @Nullable private View mRetryView;

    @NonNull private final UxLayoutChildrenInflater mUxLayoutChildrenInflater;

    @NonNull
    public static UxLayoutChildrenManager attachNewManager(
            @NonNull ViewGroup host, @Nullable AttributeSet attributeSet) {

        UxLayoutChildrenManager childrenManager = new UxLayoutChildrenManager(host, attributeSet);
        host.setOnHierarchyChangeListener(childrenManager);
        return childrenManager;
    }


    private UxLayoutChildrenManager(@NonNull ViewGroup host, @Nullable AttributeSet attributeSet) {
        if (!(host instanceof UxLayout)) {
            throw new RuntimeException(
                    "The passed in host must be an instance of " + UxLayout.class.getName()
            );
        }

        mHost = host;
        mUxLayoutChildrenInflater = UxLayoutHelper.buildUxLayoutChildrenInflater(
                host, attributeSet
        );

        int childCount = mHost.getChildCount();
        for (int i = 0; i < childCount; i++) {
            onChildViewAdded(mHost, mHost.getChildAt(i));
        }
    }


    @Override
    public void onChildViewAdded(View parent, View child) {
        throwIfNotEqual(mHost, parent);

        // Set appropriate view (only one view can play a role at a time)
        switch (getViewRole(child)) {
            case LOADING:
                removeView(mHost, mLoadingView);
                mLoadingView = child;
                break;

            case CONTENT:
                removeView(mHost, mContentView);
                mContentView = child;
                break;

            case EMPTY_STATE:
                removeView(mHost, mEmptyStateView);
                mEmptyStateView = child;
                break;

            case ERROR:
                removeView(mHost, mErrorView);
                mErrorView = child;
                break;

            case RETRY:
                removeView(mHost, mRetryView);
                mRetryView = child;
                break;

            default:
                removeView(mHost, child);
                break;
        }
    }


    @Override
    public void onChildViewRemoved(View parent, View child) {
        throwIfNotEqual(mHost, parent);

        // Loose appropriate view's reference if required
        switch (getViewRole(child)) {
            case LOADING:
                if (checkEquality(mLoadingView, child)) mLoadingView = null;
                break;

            case CONTENT:
                if (checkEquality(mContentView, child)) mContentView = null;
                break;

            case EMPTY_STATE:
                if (checkEquality(mEmptyStateView, child)) mEmptyStateView = null;
                break;

            case ERROR:
                if (checkEquality(mErrorView, child)) mErrorView = null;
                break;

            case RETRY:
                if (checkEquality(mRetryView, child)) mRetryView = null;
                break;
        }
    }


    @Nullable
    public View getLoadingView() {
        return getLoadingView(true);
    }


    @Nullable
    public View getContentView() {
        return getContentView(true);
    }


    @Nullable
    public View getEmptyStateView() {
        return getEmptyStateView(true);
    }


    @Nullable
    public View getErrorView() {
        return getErrorView(true);
    }


    @Nullable
    public View getRetryView() {
        return getRetryView(true);
    }


    public void showOnlyLoadingView() {
        showOnlyLoadingView(true);
    }


    public void showOnlyContentView() {
        showOnlyContentView(true);
    }


    public void showOnlyEmptyStateView() {
        showOnlyEmptyStateView(true);
    }


    public void showOnlyErrorView() {
        showOnlyErrorView(true);
    }


    public void showOnlyRetryView() {
        showOnlyRetryView(true);
    }


    public void setViewsVisibility(
            @NonNull Visibility loadingViewVisibility, @NonNull Visibility contentViewVisibility,
            @NonNull Visibility emptyStateViewVisibility, @NonNull Visibility errorViewVisibility,
            @NonNull Visibility retryViewVisibility) {

        setViewsVisibility(
                loadingViewVisibility, contentViewVisibility, emptyStateViewVisibility,
                errorViewVisibility, retryViewVisibility, true, true, true, true, true
        );
    }


    @Nullable
    public View getLoadingView(boolean inflateLoadingViewIfRequiredAndPossible) {
        if (inflateLoadingViewIfRequiredAndPossible) {
            inflateLoadingViewIfRequiredAndPossible();
        }

        return mLoadingView;
    }


    @Nullable
    public View getContentView(boolean inflateContentViewIfRequiredAndPossible) {
        if (inflateContentViewIfRequiredAndPossible) {
            inflateContentViewIfRequiredAndPossible();
        }

        return mContentView;
    }


    @Nullable
    public View getEmptyStateView(boolean inflateEmptyStateViewIfRequiredAndPossible) {
        if (inflateEmptyStateViewIfRequiredAndPossible) {
            inflateEmptyStateViewIfRequiredAndPossible();
        }

        return mEmptyStateView;
    }


    @Nullable
    public View getErrorView(boolean inflateErrorViewIfRequiredAndPossible) {
        if (inflateErrorViewIfRequiredAndPossible) {
            inflateErrorViewIfRequiredAndPossible();
        }

        return mErrorView;
    }


    @Nullable
    public View getRetryView(boolean inflateRetryViewIfRequiredAndPossible) {
        if (inflateRetryViewIfRequiredAndPossible) {
            inflateRetryViewIfRequiredAndPossible();
        }

        return mRetryView;
    }


    public void showOnlyLoadingView(boolean inflateLoadingViewIfRequiredAndPossible) {
        setViewsVisibility(
                Visibility.VISIBLE, Visibility.GONE, Visibility.GONE,
                Visibility.GONE, Visibility.GONE, inflateLoadingViewIfRequiredAndPossible,
                false, false, false, false
        );
    }


    public void showOnlyContentView(boolean inflateContentViewIfRequiredAndPossible) {
        setViewsVisibility(
                Visibility.GONE, Visibility.VISIBLE, Visibility.GONE,
                Visibility.GONE, Visibility.GONE, false,
                inflateContentViewIfRequiredAndPossible, false, false, false
        );
    }


    public void showOnlyEmptyStateView(boolean inflateEmptyStateViewIfRequiredAndPossible) {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.VISIBLE,
                Visibility.GONE, Visibility.GONE, false, false,
                inflateEmptyStateViewIfRequiredAndPossible, false, false
        );
    }


    public void showOnlyErrorView(boolean inflateErrorViewIfRequiredAndPossible) {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.GONE,
                Visibility.VISIBLE, Visibility.GONE, false, false,
                false, inflateErrorViewIfRequiredAndPossible, false
        );
    }


    public void showOnlyRetryView(boolean inflateRetryViewIfRequiredAndPossible) {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.GONE,
                Visibility.GONE, Visibility.VISIBLE, false, false,
                false, false, inflateRetryViewIfRequiredAndPossible
        );
    }


    public void setViewsVisibility(
            @NonNull Visibility loadingViewVisibility, @NonNull Visibility contentViewVisibility,
            @NonNull Visibility emptyStateViewVisibility, @NonNull Visibility errorViewVisibility,
            @NonNull Visibility retryViewVisibility,
            boolean inflateLoadingViewIfRequiredAndPossible,
            boolean inflateContentViewIfRequiredAndPossible,
            boolean inflateEmptyStateViewIfRequiredAndPossible,
            boolean inflateErrorViewIfRequiredAndPossible,
            boolean inflateRetryViewIfRequiredAndPossible) {

        if (inflateLoadingViewIfRequiredAndPossible) {
            inflateLoadingViewIfRequiredAndPossible();
        }

        if (inflateContentViewIfRequiredAndPossible) {
            inflateContentViewIfRequiredAndPossible();
        }

        if (inflateEmptyStateViewIfRequiredAndPossible) {
            inflateEmptyStateViewIfRequiredAndPossible();
        }

        if (inflateErrorViewIfRequiredAndPossible) {
            inflateErrorViewIfRequiredAndPossible();
        }

        if (inflateRetryViewIfRequiredAndPossible) {
            inflateRetryViewIfRequiredAndPossible();
        }

        setViewVisibility(mLoadingView, loadingViewVisibility);
        setViewVisibility(mContentView, contentViewVisibility);
        setViewVisibility(mEmptyStateView, emptyStateViewVisibility);
        setViewVisibility(mErrorView, errorViewVisibility);
        setViewVisibility(mRetryView, retryViewVisibility);
    }


    @NonNull
    private UxLayoutInfo.Role getViewRole(@NonNull View view) {
        UxLayoutInfo.Role role = UxLayoutInfo.Role.UNKNOWN;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof UxLayoutParams) {
            UxLayoutInfo uxLayoutInfo = ((UxLayoutParams) layoutParams).getUxLayoutInfo();
            role = uxLayoutInfo.getRole();
        }

        return role;
    }


    private void inflateLoadingViewIfRequiredAndPossible() {
        if (mLoadingView == null) {
            mUxLayoutChildrenInflater.inflateAndAddLoadingViewIfPossible();
        }
    }


    private void inflateContentViewIfRequiredAndPossible() {
        if (mContentView == null) {
            mUxLayoutChildrenInflater.inflateAndAddContentViewIfPossible();
        }
    }


    private void inflateEmptyStateViewIfRequiredAndPossible() {
        if (mEmptyStateView == null) {
            mUxLayoutChildrenInflater.inflateAndAddEmptyStateViewIfPossible();
        }
    }


    private void inflateErrorViewIfRequiredAndPossible() {
        if (mErrorView == null) {
            mUxLayoutChildrenInflater.inflateAndAddErrorViewIfPossible();
        }
    }


    private void inflateRetryViewIfRequiredAndPossible() {
        if (mRetryView == null) {
            mUxLayoutChildrenInflater.inflateAndAddRetryViewIfPossible();
        }
    }


    @SuppressWarnings("ResourceType")
    private static void setViewVisibility(@Nullable View view, @NonNull Visibility visibility) {
        int visibilityInt = visibility.getValue();
        if (view != null && view.getVisibility() != visibilityInt) {
            view.setVisibility(visibilityInt);
        }
    }


    private static boolean checkEquality(@Nullable View view1, @Nullable View view2) {
        return view1 == view2;
    }


    private static void removeView(@NonNull ViewGroup parent, @Nullable View child) {
        if (child != null) {
            parent.removeView(child);
        }
    }


    private static void throwIfNotEqual(@NonNull View expected, @NonNull View actual) {
        if (expected != actual) {
            throw new RuntimeException("Expected: " + expected + "; actual: " + actual);
        }
    }
}
