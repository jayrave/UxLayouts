package com.jayrave.uxLayouts;

import android.support.annotation.NonNull;

/**
 * Container for information relevant to uxLayouts
 */
public class UxLayoutInfo {

    @NonNull
    private Role mRole;

    public UxLayoutInfo() {
        this(Role.UNKNOWN);
    }

    public UxLayoutInfo(@NonNull Role role) {
        Preconditions.checkNotNull(role, "Role shouldn't be null");
        mRole = role;
    }

    @NonNull
    public final Role getRole() {
        return mRole;
    }

    public final void setRole(@NonNull Role role) {
        Preconditions.checkNotNull(role, "Role shouldn't be null");
        mRole = role;
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
