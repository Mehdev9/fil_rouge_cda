package com.insy.fil_rouge_cda.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

    public static String getCurrentUsername(){

    SecurityContext context = SecurityContextHolder.getContext();
        if(context.getAuthentication()==null)
    {
        return null;
    }

        Authentication authentication = context.getAuthentication();
        if (authentication.getPrincipal() == null)
        {
            return null;
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();
        return user.getUsername();
}
         }


