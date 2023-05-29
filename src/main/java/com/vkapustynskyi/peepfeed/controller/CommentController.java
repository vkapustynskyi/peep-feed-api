package com.vkapustynskyi.peepfeed.controller;

import com.vkapustynskyi.peepfeed.dto.CommentDto;
import com.vkapustynskyi.peepfeed.dto.CommentRequest;
import com.vkapustynskyi.peepfeed.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public void create(@RequestBody CommentRequest request) {
        log.info("Creating comment for post with id: {}", request.getPostId());
        commentService.create(request);
    }

    @GetMapping
    public List<CommentDto> getComment(@RequestParam Long postId) {
        log.info("Getting comments by post id: {}", postId);
        return commentService.getByPostId(postId);
    }

}
