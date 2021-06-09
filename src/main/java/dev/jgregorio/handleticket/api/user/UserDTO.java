package dev.jgregorio.handleticket.api.user;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserDTO {

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pass;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    public UserDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}