package dev.jgregorio.handleticket.api.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jgregorio.handleticket.api.user.User;
import dev.jgregorio.handleticket.api.user.UserDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements SecurityConstants {

    private String sk;
    private long expirationTime;
    private String version;
    private String loginPath;
    private AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        this.authenticationManager = authenticationManager;
        this.sk = ctx.getEnvironment().getProperty("security.sk");
        this.expirationTime = ctx.getEnvironment().getProperty("security.expiration.time", Long.class);
        this.version = ctx.getEnvironment().getProperty("security.version");
        this.loginPath = ctx.getEnvironment().getProperty("security.login");
        super.setFilterProcessesUrl("/" + this.version + this.loginPath);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        return super.requiresAuthentication(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            UserDTO loginForm = new ObjectMapper()
                    .readValue(req.getInputStream(), UserDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getEmail(),
                            loginForm.getPass(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String username = ((User) auth.getPrincipal()).getUsername();
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(HMAC512(sk.getBytes()));
        // add token to header
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        // add token and email to response
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(username);
        userDTO.setToken(token);
        String resStr = objectMapper.writeValueAsString(userDTO);
        res.getWriter().write(resStr);
        res.getWriter().flush();
    }
}
