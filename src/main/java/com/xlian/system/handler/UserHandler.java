package com.xlian.system.handler;

import com.xlian.system.model.User;

public class UserHandler {

    private UserHandler() {
    }

    private static final ThreadLocal<User> USER_INFO_THREAD_LOCAL = new ThreadLocal<>();

    public static void clear() {
        USER_INFO_THREAD_LOCAL.remove();
    }

    public static void set(User user) {
        USER_INFO_THREAD_LOCAL.set(user);
    }

    public static User getCurrentUser() {
        return USER_INFO_THREAD_LOCAL.get();
    }
}
