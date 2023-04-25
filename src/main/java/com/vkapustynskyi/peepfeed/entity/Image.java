package com.vkapustynskyi.peepfeed.entity;

import com.vkapustynskyi.peepfeed.entity.core.IdHolder;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image extends IdHolder {

    private String path;

}
