package com.vkapustynskyi.peepfeed.authentication.service;

import com.vkapustynskyi.peepfeed.authentication.dto.AuthenticationRequest;
import com.vkapustynskyi.peepfeed.dto.StringValueDto;
import com.vkapustynskyi.peepfeed.entity.MainUser;
import com.vkapustynskyi.peepfeed.exception.NotFoundException;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final MainUserService userService;
    private final PasswordEncoder passwordEncoder;

    public StringValueDto authenticate(AuthenticationRequest request) {
        MainUser user = getUserByRequest(request);
        String token = jwtService.generateToken(user);
        return new StringValueDto(token);
    }

    private MainUser getUserByRequest(AuthenticationRequest request) {
        return userService.findByNickname(request.getEmail())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .filter(user -> user.isAccountNonExpired() && user.isCredentialsNonExpired() && user.isEnabled())
                .orElseThrow(() -> new NotFoundException("No user found with current credentials"));
    }
}
