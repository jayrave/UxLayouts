package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        if (!(viewGroup instanceof UxLayout)) {
            throw new RuntimeException(
                    "The passed in view group must be an instance of " + UxLayout.class.getName()
            );
        }

        mViewGroup = viewGroup;
        mLayoutInflater = LayoutInflater.from(viewGroup.getContext());
    }

    public void inflateAndAddLoadingViewIfPossible() {
        inflateAndAddViewIfPossible(
                mLoadingViewLayoutResId, Role.LOADING
        );
    }

    public void inflateAndAddContentViewIfPossible() {
        inflateAndAddViewIfPossible(
                mContentViewLayoutResId, Role.CONTENT
        );
    }

    public void inflateAndAddEmptyStateViewIfPossible() {
        inflateAndAddViewIfPossible(
                mEmptyStateViewLayoutResId, Role.EMPTY_STATE
        );
    }

    public void inflateAndAddErrorViewIfPossible() {
        inflateAndAddViewIfPossible(
                mErrorViewLayoutResId, Role.ERROR
        );
    }

    public void inflateAndAddRetryViewIfPossible() {
        inflateAndAddViewIfPossible(
                mRetryViewLayoutResId, Role.RETRY
        );
    }

    public void inflateAndAddViewIfPossible(int layoutResId, @NonNull Role forRole) {
        if (layoutResId != ResourceUtils.INVALID_RESOURCE_ID) {
            View view = mLayoutInflater.inflate(layoutResId, mViewGroup, false);
            UxLayoutParams uxLayoutParams = (UxLayoutParams) view.getLayoutParams();
            uxLayoutParams.getUxLayoutInfo().setRole(forRole);
            mViewGroup.addView(view);
        }
    }

    @Nullable
    public Integer getLoadingViewLayoutResId() {
        return mLoadingViewLayoutResId;
    }

    @Nullable
    public Integer getContentViewLayoutResId() {
        return mContentViewLayoutResId;
    }

    @Nullable
    public Integer getEmptyStateViewLayoutResId() {
        return mEmptyStateViewLayoutResId;
    }

    @Nullable
    public Integer getErrorViewLayoutResId() {
        return mErrorViewLayoutResId;
    }

    @Nullable
    public Integer getRetryViewLayoutResId() {
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
