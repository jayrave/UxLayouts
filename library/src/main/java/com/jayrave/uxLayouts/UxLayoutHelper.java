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


    /**
     * Container for information relevant to uxLayouts
     */
    public static class UxLayoutInfo {

        @NonNull
        private final Role mRole;

        public UxLayoutInfo() {
            this(Role.UNKNOWN);
        }

        public UxLayoutInfo(@NonNull Role role) {
            mRole = role;
        }

        @NonNull
        public Role getRole() {
            return mRole;
        }

        public enum Role {
            UNKNOWN,
            LOADING,
            CONTENT,
            EMPTY_STATE,
            ERROR,
            RETRY
        }
    }


    /**
     * If a layout wants to support better api for proving good UX easily using this helper class,
     * its {@code LayoutParams} subclass must implement this interface.
     *
     * Your {@code LayoutParams} subclass should contain an instance of {@link UxLayoutInfo} and the
     * implementation of this interface should be a simple accessor.
     */
    public interface UxLayoutParams {
        @NonNull
        UxLayoutInfo getUxLayoutInfo();
    }
}
