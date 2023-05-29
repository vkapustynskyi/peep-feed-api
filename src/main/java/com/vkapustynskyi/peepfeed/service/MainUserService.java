package com.vkapustynskyi.peepfeed.service;


import com.vkapustynskyi.peepfeed.dto.user.MainUserRequest;
import com.vkapustynskyi.peepfeed.dto.user.UserProfileDto;
import com.vkapustynskyi.peepfeed.entity.MainUser;

import java.util.Optional;

public interface MainUserService {

    Optional<MainUser> findByNickname(String username);

    void create(MainUserRequest request);

    UserProfileDto getCurrentProfile();

    MainUser getCurrentUser();
}
