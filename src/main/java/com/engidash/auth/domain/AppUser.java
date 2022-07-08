package com.engidash.auth.domain;

import com.engidash.auth.enums.AppUserRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "\"REGISTRATION\"")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private UUID id;
    @Column(name = "\"FIRST_NAME\"")
    @NotBlank
    private String firstName;

    @Column(name = "\"LAST_NAME\"")
    @NotBlank
    private String lastName;
    @Column(name = "\"USERNAME\"", unique = true, nullable = false)
    @NotBlank
    private String username;
    @Column(name = "\"EMAIL\"", unique = true, nullable = false)
    @NotBlank
    private String email;
    @Column(name = "\"PASSWORD\"")
    @NotBlank
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "\"ROLE\"")
    private AppUserRole appUserRole;
    @Column(name = "\"LOCKED\"")
    private Boolean locked = false;
    @Column(name = "\"ENABLED\"")
    private Boolean enabled = false;

    public AppUser(String firstName,
                   String lastName,
                   String username,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
