package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.IdHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MainUserFollowing extends IdHolder {

    @ManyToOne
    private MainUser owner;

    @ManyToOne
    private MainUser follower;

}
