package com.homestay.user.security.services;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.homestay.user.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
public class UserDetailsImpl implements UserDetails {
    @Getter
    private Long id;

    @Getter
    private String userId;

    private String username;

    @Getter
    private String email;

    @JsonIgnore
    private String password;

    @Getter
    private String isActive;
    @Getter
    private String isDeleted;
    @Getter
    private String uuid;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String userId, String username, String email, String password,String isActive,
                           String isDeleted, String uuid,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userId=userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive=isActive;
        this.isDeleted=isDeleted;
        this.uuid=uuid;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getId(),user.getUserId(),user.getUsername(),user.getEmail(),user.getPassword(),
        user.getIsActive(),user.getIsDeleted(),user.getUuid(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive.equalsIgnoreCase("Y");
    }

    @Override
    public boolean isAccountNonLocked() {
        return isDeleted.equalsIgnoreCase("N");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
