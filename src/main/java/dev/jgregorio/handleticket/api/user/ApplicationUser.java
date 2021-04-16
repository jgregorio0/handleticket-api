package dev.jgregorio.handleticket.api.user;

import javax.persistence.*;

@Entity
@Table(name = "app_users")
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_users_gen")
    @SequenceGenerator(name = "app_users_gen", sequenceName = "app_users_seq_id")
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}