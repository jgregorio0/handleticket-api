package dev.jgregorio.handleticket.api.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InitUserAdminEvent {

    private static final Logger logger = LoggerFactory.getLogger(InitUserAdminEvent.class);

    @Autowired
    UserServiceImpl userService;

    @Value("${user.1}")
    String user1;

    @Value("${user.2}")
    String user2;

    @EventListener(ApplicationReadyEvent.class)
    public void initUserAdmin() {
        User user = userService.findByUsername(user1);
        if (user == null) {
            logger.info("USER " + user1 + " NOT FOUND");
            User newUser = new User();
            newUser.setUsername(user1);
            newUser.setPassword(user2);
            userService.encryptPass(newUser);
            newUser.setEnabled(true);
            userService.save(newUser);
            logger.info("USER " + user1 + " CREATED");
        }
    }
}