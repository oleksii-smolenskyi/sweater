package com.smolenskyi.sweater.domain.util;

import com.smolenskyi.sweater.domain.User;

public abstract class MessageHelper {
    public static String getAuthor(User author) {
        return author != null ? author.getUsername() : "<none>";
    }
}
