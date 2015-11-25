package com.jayrave.uxLayouts;

import android.view.View;

enum Visibility {

    VISIBLE(View.VISIBLE),
    INVISIBLE(View.INVISIBLE),
    GONE(View.GONE);

    private int mValue;

    Visibility(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }
}
