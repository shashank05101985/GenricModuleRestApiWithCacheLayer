package com.common.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.common.module.user.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

public class AuthUser implements UserDetails {

    private User user;
    List<String> rolesAndPermissions;

    public AuthUser(User user, List<String> rolesAndPermissions) {
        this.user = user;
        this.rolesAndPermissions = rolesAndPermissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        if(CollectionUtils.isEmpty(rolesAndPermissions)) return authorities;
        for (String rolePermission : rolesAndPermissions) {
            authorities.add(new SimpleGrantedAuthority(rolePermission));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    public int getId()
    {
        return user.getId();
    }

    public String getEmail()
    {
        return user.getEmail();
    }

}
