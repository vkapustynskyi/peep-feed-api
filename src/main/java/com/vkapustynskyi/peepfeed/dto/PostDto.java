package com.vkapustynskyi.peepfeed.dto;

import com.vkapustynskyi.peepfeed.dto.user.UserShortDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {

    private Long id;

    private String text;

    private String status;

    private UserShortDto author;

    private Integer likes;

    private Integer shares;

    private Boolean isLiked;

    private Boolean isDeleted = false;

}
