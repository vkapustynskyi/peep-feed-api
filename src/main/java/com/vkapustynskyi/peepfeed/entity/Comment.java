package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Getter
@Setter
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class Comment extends AuditableEntity {

    private String text;

    @ManyToOne
    private MainUser author;

    @ManyToOne
    private Post post;

}
