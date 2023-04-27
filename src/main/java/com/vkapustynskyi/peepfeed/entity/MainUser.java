package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class MainUser extends AuditableEntity {

    @Column(nullable = false, length = 40, updatable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String name;

    private String description;

    private String iconUrl;

    private String coverUrl;

    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MainUserFollowing> followers;

    @OneToMany(mappedBy = "follower", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<MainUserFollowing> followings;

}
