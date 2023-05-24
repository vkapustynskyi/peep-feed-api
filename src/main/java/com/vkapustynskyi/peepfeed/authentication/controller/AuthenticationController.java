package com.vkapustynskyi.peepfeed.authentication.controller;

import com.vkapustynskyi.peepfeed.authentication.dto.AuthenticationRequest;
import com.vkapustynskyi.peepfeed.authentication.service.AuthenticationService;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public StringValueDto authenticate(@RequestBody @Valid AuthenticationRequest request) {
        log.info("Authentication");
        return authenticationService.authenticate(request);
    }

}
