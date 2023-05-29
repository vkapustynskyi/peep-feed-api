package com.vkapustynskyi.peepfeed.service;

import com.vkapustynskyi.peepfeed.dto.CommentDto;
import com.vkapustynskyi.peepfeed.dto.CommentRequest;

import java.util.List;

public interface CommentService {

    void create(CommentRequest request);

    List<CommentDto> getByPostId(Long postId);

}
