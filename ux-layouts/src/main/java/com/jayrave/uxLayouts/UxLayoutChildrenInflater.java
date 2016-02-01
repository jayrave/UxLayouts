package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayrave.uxLayouts.UxLayoutInfo.Role;

public class UxLayoutChildrenInflater {

    @NonNull private final ViewGroup mViewGroup;
    @NonNull private final LayoutInflater mLayoutInflater;

    private int mLoadingViewLayoutResId = ResourceUtils.INVALID_RESOURCE_ID;
    private int mContentViewLayoutResId = ResourceUtils.INVALID_RESOURCE_ID;
    private int mEmptyStateViewLayoutResId = ResourceUtils.INVALID_RESOURCE_ID;
    private int mErrorViewLayoutResId = ResourceUtils.INVALID_RESOURCE_ID;
    private int mRetryViewLayoutResId = ResourceUtils.INVALID_RESOURCE_ID;

    public UxLayoutChildrenInflater(@NonNull ViewGroup viewGroup) {
        Preconditions.checkNotNull(viewGroup, "Passed in view group shouldn't be null");
        Preconditions.checkTruthiness(
                viewGroup instanceof UxLayout,
                "Passed in view group must be an instance of " + UxLayout.class.getCanonicalName()
        );

        mViewGroup = viewGroup;
        mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
    }

    public void inflateAndAddLoadingViewIfPossible() {
        inflateAndAddViewIfPossible(mLoadingViewLayoutResId, Role.LOADING);
    }

    public void inflateAndAddContentViewIfPossible() {
        inflateAndAddViewIfPossible(mContentViewLayoutResId, Role.CONTENT);
    }

    public void inflateAndAddEmptyStateViewIfPossible() {
        inflateAndAddViewIfPossible(mEmptyStateViewLayoutResId, Role.EMPTY_STATE);
    }

    public void inflateAndAddErrorViewIfPossible() {
        inflateAndAddViewIfPossible(mErrorViewLayoutResId, Role.ERROR);
    }

    public void inflateAndAddRetryViewIfPossible() {
        inflateAndAddViewIfPossible(mRetryViewLayoutResId, Role.RETRY);
    }

    public void inflateAndAddViewIfPossible(int layoutResId, @NonNull Role forRole) {
        if (layoutResId != ResourceUtils.INVALID_RESOURCE_ID) {
            View view = mLayoutInflater.inflate(layoutResId, mViewGroup, false);
            UxLayoutParams uxLayoutParams = (UxLayoutParams) view.getLayoutParams();
            uxLayoutParams.getUxLayoutInfo().setRole(forRole);
            mViewGroup.addView(view);
        }
    }

    public int getLoadingViewLayoutResId() {
        return mLoadingViewLayoutResId;
    }

    public int getContentViewLayoutResId() {
        return mContentViewLayoutResId;
    }

    public int getEmptyStateViewLayoutResId() {
        return mEmptyStateViewLayoutResId;
    }

    public int getErrorViewLayoutResId() {
        return mErrorViewLayoutResId;
    }

    public int getRetryViewLayoutResId() {
        return mRetryViewLayoutResId;
    }

    public void setLoadingViewLayoutResId(int loadingViewLayoutResId) {
        mLoadingViewLayoutResId = loadingViewLayoutResId;
    }

    public void setContentViewLayoutResId(int contentViewLayoutResId) {
        mContentViewLayoutResId = contentViewLayoutResId;
    }

    public void setEmptyStateViewLayoutResId(int emptyStateViewLayoutResId) {
        mEmptyStateViewLayoutResId = emptyStateViewLayoutResId;
    }

    public void setErrorViewLayoutResId(int errorViewLayoutResId) {
        mErrorViewLayoutResId = errorViewLayoutResId;
    }

    public void setRetryViewLayoutResId(int retryViewLayoutResId) {
        mRetryViewLayoutResId = retryViewLayoutResId;
    }
}
