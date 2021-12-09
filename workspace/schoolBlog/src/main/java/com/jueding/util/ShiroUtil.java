package com.jueding.util;

import com.jueding.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author 北落燕门
 */
public class ShiroUtil {

    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
