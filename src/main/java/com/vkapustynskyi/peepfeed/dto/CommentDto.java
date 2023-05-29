package com.vkapustynskyi.peepfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String comment;

    private String author;

    private String authorNickname;

}
