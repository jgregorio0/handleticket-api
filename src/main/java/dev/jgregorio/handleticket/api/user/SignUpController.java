package dev.jgregorio.handleticket.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class SignUpController {

    @Autowired
    private UserServiceImpl userService;

    //@PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody User user) {
        // save user not enabled
        userService.encryptPass(user);
        user.setEnabled(false);
        user = userService.save(user);
        // return error
        if (user == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // return ok user
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getUsername());
        return ResponseEntity.ok(userDTO);
    }
}