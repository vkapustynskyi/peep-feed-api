package com.vkapustynskyi.peepfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long id;

    private String text;

    private String status;

    private String author;

    private String authorNickname;

    private Integer likes;

    private Integer shares;

    private Boolean isDeleted = false;

}
