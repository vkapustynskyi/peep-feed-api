package com.vkapustynskyi.peepfeed.service;


import com.vkapustynskyi.peepfeed.entity.MainUser;

import java.util.Optional;

public interface MainUserService {

    Optional<MainUser> findByNickname(String username);

}
