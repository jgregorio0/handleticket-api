package dev.jgregorio.handleticket.api.user;

import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    Environment env;

    //@Before
    public void setUp() {
        /*User admin = new User();
        admin.setUsername(env.getProperty());
        admin.setPassword("test");
        admin.setEnabled(true);

        Mockito.when(userRepository.findByUsername(testUsername))
                .thenReturn(admin);*/
    }

    @Test
    public void whenAdmin_thenAdminShouldBeFound() {
        String property = env.getProperty("user.1");
        if (StringUtils.isNotBlank(property)) {
            User testUser = userService.findByUsername(property);
            assertThat(testUser.getUsername(), equalTo(property));
        }
    }
}
