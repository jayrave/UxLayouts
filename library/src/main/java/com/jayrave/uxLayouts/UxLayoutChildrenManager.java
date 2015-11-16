package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.jayrave.uxLayouts.UxLayoutHelper.UxLayoutInfo;
import com.jayrave.uxLayouts.UxLayoutHelper.UxLayoutParams;

public class UxLayoutChildrenManager implements ViewGroup.OnHierarchyChangeListener {

    @NonNull private ViewGroup mHost;
    @Nullable private View mLoadingView;
    @Nullable private View mContentView;
    @Nullable private View mEmptyStateView;
    @Nullable private View mErrorView;
    @Nullable private View mRetryView;

    @NonNull
    public static UxLayoutChildrenManager attachNewManager(@NonNull ViewGroup host) {
        UxLayoutChildrenManager childrenManager = new UxLayoutChildrenManager(host);
        host.setOnHierarchyChangeListener(childrenManager);
        return childrenManager;
    }


    private UxLayoutChildrenManager(@NonNull ViewGroup host) {
        mHost = host;
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
        return mLoadingView;
    }


    @Nullable
    public View getContentView() {
        return mContentView;
    }


    @Nullable
    public View getEmptyStateView() {
        return mEmptyStateView;
    }


    @Nullable
    public View getErrorView() {
        return mErrorView;
    }


    @Nullable
    public View getRetryView() {
        return mRetryView;
    }


    public void showOnlyLoadingView() {
        setViewsVisibility(
                Visibility.VISIBLE, Visibility.GONE, Visibility.GONE,
                Visibility.GONE, Visibility.GONE
        );
    }


    public void showOnlyContentView() {
        setViewsVisibility(
                Visibility.GONE, Visibility.VISIBLE, Visibility.GONE,
                Visibility.GONE, Visibility.GONE
        );
    }


    public void showOnlyEmptyStateView() {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.VISIBLE,
                Visibility.GONE, Visibility.GONE
        );
    }


    public void showOnlyErrorView() {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.GONE,
                Visibility.VISIBLE, Visibility.GONE
        );
    }


    public void showOnlyRetryView() {
        setViewsVisibility(
                Visibility.GONE, Visibility.GONE, Visibility.GONE,
                Visibility.GONE, Visibility.VISIBLE
        );
    }


    public void setViewsVisibility(
            @NonNull Visibility loadingViewVisibility, @NonNull Visibility contentViewVisibility,
            @NonNull Visibility emptyStateViewVisibility, @NonNull Visibility errorViewVisibility,
            @NonNull Visibility retryViewVisibility) {

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
