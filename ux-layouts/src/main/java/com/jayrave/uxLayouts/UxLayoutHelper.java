package com.jayrave.uxLayouts;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class UxLayoutHelper {

    /**
     * Constructs a {@link UxLayoutChildrenInflater} from attributes associated with a view
     */
    @NonNull
    public static UxLayoutChildrenInflater buildUxLayoutChildrenInflater(
            @NonNull ViewGroup viewGroup, @Nullable AttributeSet attrs) {

        UxLayoutChildrenInflater uxLayoutChildrenInflater = new UxLayoutChildrenInflater(viewGroup);
        if (attrs != null) {
            int defaultResourceId = ResourceUtils.INVALID_RESOURCE_ID;
            TypedArray array = viewGroup.getContext().obtainStyledAttributes(
                    attrs, R.styleable.UxLayout
            );

            uxLayoutChildrenInflater.setLoadingViewLayoutResId(array.getResourceId(
                    R.styleable.UxLayout_ux_loading_layout, defaultResourceId
            ));

            uxLayoutChildrenInflater.setContentViewLayoutResId(array.getResourceId(
                    R.styleable.UxLayout_ux_content_layout, defaultResourceId
            ));

            uxLayoutChildrenInflater.setEmptyStateViewLayoutResId(array.getResourceId(
                    R.styleable.UxLayout_ux_empty_state_layout, defaultResourceId
            ));

            uxLayoutChildrenInflater.setErrorViewLayoutResId(array.getResourceId(
                    R.styleable.UxLayout_ux_error_layout, defaultResourceId
            ));

            uxLayoutChildrenInflater.setRetryViewLayoutResId(array.getResourceId(
                    R.styleable.UxLayout_ux_retry_layout, defaultResourceId
            ));

            array.recycle();
        }

        return uxLayoutChildrenInflater;
    }


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
