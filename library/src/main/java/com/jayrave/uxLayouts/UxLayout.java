package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ViewGroup;

public interface UxLayout<LP extends ViewGroup.LayoutParams & UxLayoutHelper.UxLayoutParams> {

    @NonNull
    LP generateDefaultLayoutParams();

    @NonNull
    LP generateLayoutParams(AttributeSet attrs);
}
