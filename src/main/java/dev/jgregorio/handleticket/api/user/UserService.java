package dev.jgregorio.handleticket.api.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    User save(User user);

    void encryptPass(User user);
}