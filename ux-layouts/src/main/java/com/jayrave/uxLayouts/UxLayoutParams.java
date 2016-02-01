package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;

/**
 * If a layout wants to support better api for proving good UX easily using {@link UxLayout},
 * its {@code LayoutParams} subclass must implement this interface.
 *
 * Your {@code LayoutParams} subclass should contain an instance of {@link UxLayoutInfo}
 * and the implementation of this interface should be a simple accessor.
 */
public interface UxLayoutParams {
    @NonNull
    UxLayoutInfo getUxLayoutInfo();
}
