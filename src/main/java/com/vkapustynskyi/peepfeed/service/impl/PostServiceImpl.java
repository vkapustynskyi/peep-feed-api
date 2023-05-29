package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.entity.Post;
import com.vkapustynskyi.peepfeed.exception.NotFoundException;
import com.vkapustynskyi.peepfeed.mapper.PostMapper;
import com.vkapustynskyi.peepfeed.repository.PostRepository;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import com.vkapustynskyi.peepfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final PostMapper mapper;
    private final MainUserService userService;

    @Override
    public void create(StringValueDto text) {
        Post post = mapper.toEntity(text);
        post.setAuthor(userService.getCurrentUser());
        repository.save(post);
    }

    @Override
    public List<PostDto> getMyPosts() {
        return repository.findByAuthorAndIsDeletedFalse(userService.getCurrentUser())
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Post post = getById(id);
        post.setIsDeleted(true);
        repository.save(post);
    }

    private Post getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No post found"));
    }
}
