package com.xlian.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {

    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

    public static String encrypt(String username, String password) {
        return DigestUtils.md5Hex(username + password);
    }


}
