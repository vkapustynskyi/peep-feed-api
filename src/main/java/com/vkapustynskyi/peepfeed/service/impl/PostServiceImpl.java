package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.PostStatus;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.entity.MainUser;
import com.vkapustynskyi.peepfeed.entity.Post;
import com.vkapustynskyi.peepfeed.exception.NotFoundException;
import com.vkapustynskyi.peepfeed.mapper.PostMapper;
import com.vkapustynskyi.peepfeed.repository.PostRepository;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import com.vkapustynskyi.peepfeed.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<PostDto> getByAuthorId(Long id) {
        MainUser author = userService.getById(id);
        return repository.findByAuthorAndIsDeletedFalse(author)
                .stream()
                .filter(post -> post.getStatus() == PostStatus.APPROVED)
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<PostDto> getFeedPosts() {
        return repository.findByStatusInAndIsDeletedFalse(List.of(PostStatus.APPROVED))
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {
        Post post = getById(id);
        post.setIsDeleted(true);
        repository.save(post);
    }

    @Override
    public void approve(Long id) {
        Post post = getById(id);
        post.setStatus(PostStatus.APPROVED);
        repository.save(post);
    }

    @Override
    public List<PostDto> getToModerate() {
        return repository.findByStatusInAndIsDeletedFalse(List.of(PostStatus.MODERATION))
                .stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void decline(Long id) {
        Post post = getById(id);
        post.setStatus(PostStatus.DECLINED);
        repository.save(post);
    }

    @Override
    public void like(Long id) {
        Post post = getById(id);
        post.addLike(userService.getCurrentUser());
        repository.save(post);
    }

    @Override
    public void unlike(Long id) {
        Post post = getById(id);
        post.removeLike(userService.getCurrentUser());
        repository.save(post);
    }

    private Post getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No post found"));
    }
}
