package com.jayrave.uxLayouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jayrave.percent.PercentFrameLayout;

public class UxPercentFrameLayout extends PercentFrameLayout implements
        UxLayout<UxPercentFrameLayout.LayoutParams> {

    @NonNull private final UxLayoutChildrenManager mChildrenManager;

    public UxPercentFrameLayout(Context context) {
        this(context, null);
    }


    public UxPercentFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public UxPercentFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mChildrenManager = UxLayoutChildrenManager.attachNewManager(this, attrs);
    }


    @Nullable
    @Override
    public View getLoadingView() {
        return mChildrenManager.getLoadingView();
    }


    @Nullable
    @Override
    public View getContentView() {
        return mChildrenManager.getContentView();
    }


    @Nullable
    @Override
    public View getEmptyStateView() {
        return mChildrenManager.getEmptyStateView();
    }


    @Nullable
    @Override
    public View getErrorView() {
        return mChildrenManager.getErrorView();
    }


    @Nullable
    @Override
    public View getRetryView() {
        return mChildrenManager.getRetryView();
    }


    @Override
    public void showOnlyLoadingView() {
        mChildrenManager.showOnlyLoadingView();
    }


    @Override
    public void showOnlyContentView() {
        mChildrenManager.showOnlyContentView();
    }


    @Override
    public void showOnlyEmptyStateView() {
        mChildrenManager.showOnlyEmptyStateView();
    }


    @Override
    public void showOnlyErrorView() {
        mChildrenManager.showOnlyErrorView();
    }


    @Override
    public void showOnlyRetryView() {
        mChildrenManager.showOnlyRetryView();
    }


    @NonNull
    @Override
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


    @NonNull
    @Override
    public LayoutParams generateLayoutParams(@NonNull AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends PercentFrameLayout.LayoutParams
            implements UxLayoutParams {

        @Nullable
        private UxLayoutInfo mUxLayoutInfo;

        public LayoutParams(@NonNull Context c, @NonNull AttributeSet attrs) {
            super(c, attrs);
            mUxLayoutInfo = UxLayoutHelper.buildUxLayoutInfo(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
            mUxLayoutInfo = source.getUxLayoutInfo();
        }

        @NonNull
        @Override
        public UxLayoutInfo getUxLayoutInfo() {
            if (mUxLayoutInfo == null) {
                mUxLayoutInfo = new UxLayoutInfo();
            }

            return mUxLayoutInfo;
        }
    }
}
