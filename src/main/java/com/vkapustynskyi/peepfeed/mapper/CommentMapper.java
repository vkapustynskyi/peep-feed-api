package com.vkapustynskyi.peepfeed.mapper;

import com.vkapustynskyi.peepfeed.dto.CommentDto;
import com.vkapustynskyi.peepfeed.dto.CommentRequest;
import com.vkapustynskyi.peepfeed.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {PostMapper.class})
public interface CommentMapper {

    @Mapping(target = "text", source = "comment")
    @Mapping(target = "post", source = "postId")
    Comment toEntity(CommentRequest request);

    @Mapping(target = "comment", source = "text")
    @Mapping(target = "author", expression = "java(comment.getAuthor().getFullNameFirstLetters())")
    @Mapping(target = "authorNickname", expression = "java(comment.getAuthor().getNickname())")
    CommentDto toDto(Comment comment);

}
