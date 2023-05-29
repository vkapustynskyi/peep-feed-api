package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.dto.PostStatus;
import com.vkapustynskyi.peepfeed.entity.core.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.*;

@Getter
@Setter
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Post extends AuditableEntity {

    private String text;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

    @NotNull
    private Integer likes = 0;

    @NotNull
    private Integer shares = 0;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PostStatus status = PostStatus.MODERATION;

    @ManyToOne
    private Post shareOf;

    @ManyToOne
    private Post replyTo;

    @ManyToOne
    private MainUser author;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    @JoinTable(
            name = "post_image",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private List<Image> postImages;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comments;

    @NotAudited
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_like",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<MainUser> postLikes = new HashSet<>();

    public void addLike(MainUser user) {
        if (Objects.isNull(user)) {
            return;
        }
        postLikes.add(user);
    }

    public void removeLike(MainUser user) {
        if (Objects.isNull(user)) {
            return;
        }
        postLikes.remove(user);
    }

    public boolean isLiked(MainUser user) {
        return postLikes.stream()
                .anyMatch(userLike -> Objects.equals(userLike, user));
    }

}
