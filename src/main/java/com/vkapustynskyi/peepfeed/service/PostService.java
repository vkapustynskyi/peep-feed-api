package com.vkapustynskyi.peepfeed.service;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;

import java.util.List;

public interface PostService {

    void create(StringValueDto text);

    List<PostDto> getMyPosts();

    void delete(Long id);

    List<PostDto> getByAuthorId(Long id);

    List<PostDto> getFeedPosts();

    void approve(Long id);

    List<PostDto> getToModerate();

    void decline(Long id);

    void like(Long id);

    void unlike(Long id);
}
