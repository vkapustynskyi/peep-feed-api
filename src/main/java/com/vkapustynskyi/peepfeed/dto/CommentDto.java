package com.vkapustynskyi.peepfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private String text;

    private String author;

    private String authorNickname;

}
