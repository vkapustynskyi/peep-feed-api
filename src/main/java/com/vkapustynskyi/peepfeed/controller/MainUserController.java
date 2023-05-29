package com.vkapustynskyi.peepfeed.controller;

import com.vkapustynskyi.peepfeed.dto.user.MainUserRequest;
import com.vkapustynskyi.peepfeed.dto.user.UserProfileDto;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MainUserController {

    private final MainUserService userService;

    @PostMapping
    public void createUser(@RequestBody @Valid MainUserRequest request) {
        log.info("Creating user with nickname: {}", request.getNickname());
        userService.create(request);
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public UserProfileDto getCurrentUserProfile() {
        log.info("Getting current user profile");
        return userService.getCurrentProfile();
    }

}
