package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.entity.MainUser;
import com.vkapustynskyi.peepfeed.repository.MainUserRepository;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainUserServiceImpl implements MainUserService {

    private final MainUserRepository repository;

    @Override
    public Optional<MainUser> findByNickname(String username) {
        return repository.findByNickname(username);
    }
}
