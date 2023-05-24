package com.vkapustynskyi.peepfeed.config;

import com.vkapustynskyi.peepfeed.entity.MainUser;
import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @NonNull
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(MainUser.class::isInstance)
                .map(MainUser.class::cast)
                .map(MainUser::getUuid)
                .orElse("system"));
    }
}
