package com.vkapustynskyi.peepfeed.mapper;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.entity.Post;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@Mapper
public abstract class PostMapper {

    @Autowired
    private MainUserService userService;

    @Mapping(target = "text", source = "value")
    public abstract Post toEntity(StringValueDto text);

    @Mapping(target = "author", expression = "java(post.getAuthor().getFullNameFirstLetters())")
    @Mapping(target = "authorNickname", expression = "java(post.getAuthor().getNickname())")
    public abstract PostDto toDto(Post post);

    @AfterMapping
    public void afterToDto(@MappingTarget PostDto postDto, Post post) {
        postDto.setIsLiked(post.isLiked(userService.getCurrentUser()));
    }

    public Post fromId(Long id) {
        if (Objects.isNull(id)) {
            return null;

        }
        Post post = new Post();
        post.setId(id);
        return post;
    }
}
