/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.idc.web.test.springmvc.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Holds the info for a authenticated user (Principal)
 * @author pascal alma
 */
public class AuthenticatedUser implements UserDetails {

    private final Integer id;
    private final String username;
    private final Integer enterpriseId;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthenticatedUser(Integer id, String username, Integer enterpriseId, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.enterpriseId = enterpriseId;
        this.authorities = authorities;
    }
  
    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{" + "id=" + id + ", username=" + username + ", enterpriseId=" + enterpriseId + ", authorities=" + authorities + '}';
    }

    
}
