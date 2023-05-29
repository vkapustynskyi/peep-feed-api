package com.vkapustynskyi.peepfeed.mapper;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {

    @Mapping(target = "text", source = "value")
    Post toEntity(StringValueDto text);

    @Mapping(target = "author", expression = "java(post.getAuthor().getFullNameFirstLetters())")
    @Mapping(target = "authorNickname", expression = "java(post.getAuthor().getNickname())")
    PostDto toDto(Post post);
}
