package com.vkapustynskyi.peepfeed.controller;

import com.vkapustynskyi.peepfeed.dto.PostDto;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void create(@Valid @RequestBody StringValueDto text) {
        log.info("Creating post");
        postService.create(text);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public List<PostDto> getMyPosts() {
        log.info("Getting my posts");
        return postService.getMyPosts();
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<PostDto> getFeedPosts() {
        log.info("Getting my posts");
        return postService.getFeedPosts();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deletePost(@PathVariable Long id) {
        log.info("Deleting my posts");
        postService.delete(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void approve(@PathVariable Long id) {
        log.info("Approving my posts");
        postService.approve(id);
    }

    @PatchMapping("/{id}/decline")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void decline(@PathVariable Long id) {
        log.info("Declining my posts");
        postService.decline(id);
    }

    @GetMapping("/moderation")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<PostDto> getToModerate() {
        log.info("Getting posts to moderate");
        return postService.getToModerate();
    }

    @PostMapping("/{id}/like")
    @PreAuthorize("isAuthenticated()")
    public void like(@PathVariable Long id) {
        log.info("Like post with id: {}", id);
        postService.like(id);
    }

    @PostMapping("/{id}/unlike")
    @PreAuthorize("isAuthenticated()")
    public void getToModerate(@PathVariable Long id) {
        log.info("Unlike post with id: {}", id);
        postService.unlike(id);
    }


}
