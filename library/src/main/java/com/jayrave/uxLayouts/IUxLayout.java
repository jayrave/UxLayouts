package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;

public interface IUxLayout<LP extends ViewGroup.LayoutParams> {

    @NonNull
    LP generateDefaultLayoutParams();

    @NonNull
    LP generateLayoutParams(AttributeSet attrs);
}
