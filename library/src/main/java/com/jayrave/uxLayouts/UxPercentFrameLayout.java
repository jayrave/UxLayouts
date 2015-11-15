package com.jayrave.uxLayouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentFrameLayout;
import android.util.AttributeSet;

public class UxPercentFrameLayout extends PercentFrameLayout implements
        IUxLayout<UxPercentFrameLayout.LayoutParams> {

    public UxPercentFrameLayout(Context context) {
        super(context);
    }


    public UxPercentFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public UxPercentFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @NonNull
    @Override
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


    @NonNull
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }


    public static class LayoutParams extends PercentFrameLayout.LayoutParams implements
            UxLayoutHelper.UxLayoutParams {

        @Nullable
        private UxLayoutHelper.UxLayoutInfo mUxLayoutInfo;

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
        public UxLayoutHelper.UxLayoutInfo getUxLayoutInfo() {
            if (mUxLayoutInfo == null) {
                mUxLayoutInfo = new UxLayoutHelper.UxLayoutInfo();
            }

            return mUxLayoutInfo;
        }
    }
}
