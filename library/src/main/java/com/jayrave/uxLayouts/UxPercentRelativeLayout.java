package com.jayrave.uxLayouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;

public class UxPercentRelativeLayout extends PercentRelativeLayout implements
        IUxLayout<UxPercentRelativeLayout.LayoutParams> {

    public UxPercentRelativeLayout(Context context) {
        super(context);
    }


    public UxPercentRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public UxPercentRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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


    public static class LayoutParams extends PercentRelativeLayout.LayoutParams implements
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
