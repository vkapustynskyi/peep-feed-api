package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.dto.CommentDto;
import com.vkapustynskyi.peepfeed.dto.CommentRequest;
import com.vkapustynskyi.peepfeed.entity.Comment;
import com.vkapustynskyi.peepfeed.mapper.CommentMapper;
import com.vkapustynskyi.peepfeed.repository.CommentRepository;
import com.vkapustynskyi.peepfeed.service.CommentService;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final CommentMapper mapper;
    private final MainUserService userService;

    @Override
    public void create(CommentRequest request) {
        Comment comment = mapper.toEntity(request);
        comment.setAuthor(userService.getCurrentUser());
        repository.save(comment);
    }

    @Override
    public List<CommentDto> getByPostId(Long postId) {
        return repository.findByPostId(postId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public void delete(Long id) {

    }

}
