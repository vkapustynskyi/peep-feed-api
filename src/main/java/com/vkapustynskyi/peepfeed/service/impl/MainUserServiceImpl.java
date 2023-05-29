package com.vkapustynskyi.peepfeed.service.impl;

import com.vkapustynskyi.peepfeed.dto.user.MainUserRequest;
import com.vkapustynskyi.peepfeed.dto.user.UserProfileDto;
import com.vkapustynskyi.peepfeed.entity.MainUser;
import com.vkapustynskyi.peepfeed.exception.BadRequestException;
import com.vkapustynskyi.peepfeed.exception.NotFoundException;
import com.vkapustynskyi.peepfeed.mapper.MainUserMapper;
import com.vkapustynskyi.peepfeed.repository.MainUserRepository;
import com.vkapustynskyi.peepfeed.service.MainUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainUserServiceImpl implements MainUserService {

    private final MainUserRepository repository;
    private final MainUserMapper mapper;

    @Override
    public Optional<MainUser> findByNickname(String username) {
        return repository.findByNickname(username);
    }

    @Override
    public void create(MainUserRequest request) {
        validateIfAlreadyExists(request);
        MainUser mainUser = mapper.toEntity(request);
        repository.save(mainUser);
    }

    @Override
    public UserProfileDto getCurrentProfile() {
        MainUser currentUser = getCurrentUser();
        return mapper.toDto(currentUser);
    }

    @Override
    public MainUser getCurrentUser() {
        return getAuthentication()
                .orElseThrow(() -> new IllegalStateException("Cannot get user principal"));
    }

    @Override
    public List<UserProfileDto> getUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void toggleUserEnable(Long id) {
        MainUser user = getById(id);
        user.setIsEnabled(!user.getIsEnabled());
        repository.save(user);
    }

    @Override
    public UserProfileDto getUserDtoById(Long id) {
        MainUser user = getById(id);
        return mapper.toDto(user);
    }

    @Override
    public MainUser getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No user found"));
    }

    private static Optional<MainUser> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(MainUser.class::isInstance)
                .map(MainUser.class::cast);
    }

    private void validateIfAlreadyExists(MainUserRequest request) {
        if (findByNickname(request.getNickname()).isPresent()) {
            throw new BadRequestException("Nickname already used, please choose another");
        }
    }
}
