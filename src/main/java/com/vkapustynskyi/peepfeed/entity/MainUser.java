package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MainUser extends AuditableEntity implements UserDetails {

    @Column(nullable = false, length = 40, updatable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private String password;

    private String description;

    private String iconUrl;

    private String coverUrl;

    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MainUserFollowing> followers;

    @OneToMany(mappedBy = "follower", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MainUserFollowing> followings;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MainUserRole role = MainUserRole.USER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nickname;
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

    public String getFullNameFirstLetters() {
        return String.valueOf(getFirstName().charAt(0)) + getLastName().charAt(0);
    }
}
