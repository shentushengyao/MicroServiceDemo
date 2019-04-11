package com.oauth.sys.security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Configuration
public class AuditorAwareConfig implements AuditorAware<Long> {

    public Optional<Long> getCurrentAuditor() {
//        User user = User.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        Optional<SysUser> byUsername = reposiroty.findFirstByUsername(user.getUsername());
//        return Optional.of(byUsername.get().getId());
        return Optional.of(1L);

    }
}