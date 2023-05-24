package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.service.MainUserDetailsService;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainUserDetailsServiceImpl implements MainUserDetailsService {

    private final MainUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByNickname(username)
                .orElse(null);
    }
}
