package com.vkapustynskyi.peepfeed.authentication.service;

import com.vkapustynskyi.peepfeed.entity.MainUser;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final MainUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByNickname(username)
                .filter(MainUser::isEnabled)
                .orElse(null);
    }
}
