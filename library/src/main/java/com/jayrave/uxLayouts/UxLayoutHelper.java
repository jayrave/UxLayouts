package com.jayrave.uxLayouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

public class UxLayoutHelper {

    /**
     * Constructs a {@link UxLayoutInfo} from attributes associated with a View
     */
    @NonNull
    public static UxLayoutInfo buildUxLayoutInfo(
            @NonNull Context context, @NonNull AttributeSet attrs) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.UxLayout_Layout);
        int value = array.getInt(R.styleable.UxLayout_Layout_ux_layout_role, -1);
        UxLayoutInfo.Role role;
        switch (value) {
            case 0:
                role = UxLayoutInfo.Role.LOADING;
                break;
            case 1:
                role = UxLayoutInfo.Role.CONTENT;
                break;
            case 2:
                role = UxLayoutInfo.Role.EMPTY_STATE;
                break;
            case 3:
                role = UxLayoutInfo.Role.ERROR;
                break;
            case 4:
                role = UxLayoutInfo.Role.RETRY;
                break;
            default:
                role = UxLayoutInfo.Role.UNKNOWN;
                break;
        }

        array.recycle();
        return new UxLayoutInfo(role);
    }
}
