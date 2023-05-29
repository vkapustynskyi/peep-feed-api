package com.vkapustynskyi.peepfeed.mapper;

import com.vkapustynskyi.peepfeed.dto.user.MainUserRequest;
import com.vkapustynskyi.peepfeed.dto.user.UserProfileDto;
import com.vkapustynskyi.peepfeed.dto.user.UserShortDto;
import com.vkapustynskyi.peepfeed.entity.MainUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
public abstract class MainUserMapper {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public abstract MainUser toEntity(MainUserRequest request);

    public abstract UserProfileDto toDto(MainUser user);

    public abstract UserShortDto toShortDto(MainUser user);

    @AfterMapping
    protected void afterToEntity(@MappingTarget MainUser user, MainUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
    }

}
