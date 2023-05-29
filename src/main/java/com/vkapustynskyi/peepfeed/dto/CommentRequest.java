package com.vkapustynskyi.peepfeed.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private String comment;

    private Long postId;

}
