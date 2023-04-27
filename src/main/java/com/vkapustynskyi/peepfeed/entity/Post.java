package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Post extends AuditableEntity {

    private String text;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isDeleted = false;

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


}
