package com.vkapustynskyi.peepfeed.service;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;

import java.util.List;

public interface PostService {

    void create(StringValueDto text);

    List<PostDto> getMyPosts();

    void delete(Long id);
}
